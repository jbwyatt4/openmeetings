#/opt/local/bin/ruby

#
# class_name_convert_tool
#
# togawamanabu <togawamanabu@gmail.com>
#

require "rexml/document"
require "rexml/parsers/pullparser"
require "fileutils"
require 'erb'

require 'pp'

include REXML

class LzxFile
  attr_reader :class_list
  @locale

  def initialize(path, locale)
    @class_list = Array.new
    @locale = locale

    createlist(path)
  end

  def createlist(path)
    source = File.read path
    parser = Parsers::PullParser.new(source)

    docsource_path = "#{File.dirname(File.expand_path(path))}/doc/lzx/#{@locale}/#{File.basename(path, ".*")}_doc.xml"
    if File.exist?(docsource_path)
      docxml = Document.new(File.new(docsource_path))
    else
      docxml = nil
    end

    lzxclass = nil
    pre_comment = ""

    while parser.has_next?
      res = parser.pull

      case res.event_type
        when :comment
          pre_comment = res[0]
          pre_comment = pre_comment[1..-1] if pre_comment[0, 1] == "-"
        when :start_element
          tag = res[0]
          name = res[1]["name"]

          case tag
            when "class"
              @class_list << lzxclass = LzxClass.new(name, pre_comment, res[1]["extends"])
              lzxclass.doc = XPath.first(docxml, "/rlzxdoc/class[@name='#{lzxclass.classname}']/doc") unless docxml.nil?
            when "method"
              lzxclass.add_method(name, res[1]["args"], pre_comment) unless lzxclass.nil?
              #p lzxclass
            when "attribute"
              lzxclass.add_attribute(name, res[1]["type"], res[1]["value"], pre_comment) unless lzxclass.nil?
            when "view"
              lzxclass.add_view(name, pre_comment) unless lzxclass.nil?
            when "event"
              lzxclass.add_event(name, pre_comment) unless lzxclass.nil?
          end
        pre_comment = ""
      end
    end
  end
end

class LzxClass
  attr_reader :classname, :attribute_list, :method_list, :event_list, :view_list, :classcomment, :extends, :category, :private
  attr_accessor :doc, :hierarchy_depths

  def initialize(classname, comment, extends)
    @attribute_list = Array.new
    @method_list = Array.new
    @event_list = Array.new
    @view_list = Array.new
    @private = false
    @private = comment.include?(@@private_keyword) unless comment.nil?

    @classname = classname
    @classcomment = comment
    @extends = extends
  end

  def private?
    @private
  end

  def add_method(name, args, comment)
    private = false
    private = comment.include?(@@private_keyword) unless comment.nil?

    if args.nil?
      args = { }
    else
      args = args.split(",")
    end
    @method_list << {:name => name, :args => args, :comment => comment, :private => private}
  end

  def add_attribute(name, type, value, comment)
    private = false
    private = comment.include?(@@private_keyword) unless comment.nil?
    @attribute_list << {:name => name, :type => type, :value => value, :comment => comment, :private => private}
  end

  def add_event(name, comment)
    private = false
    private = comment.include?(@@private_keyword) unless comment.nil?
    @event_list << {:name => name, :comment => comment, :private => private}
  end

  def add_view(name, comment)
    private = false
    private = comment.include?(@@private_keyword) unless comment.nil?
    @view_list << {:name => name, :comment => comment, :private => private}
  end

  def get_depths_string
    rtn = ""
    (hierarchy_depths - 1).times{rtn += "../"}
    return rtn
  end
end

def add_class_from_lzx(lzxfile_path)
  p = LzxFile.new(lzxfile_path, @locale)
  p.class_list.each{ |lzxclass|
    lzxclass.hierarchy_depths = lzxfile_path.split("/").length
    @lzx_classes << { :path => lzxfile_path, :class => lzxclass}
  }
end

# main
@@private_keyword = "@keywords private"

#basedir to create lzxdoc
@basedir = ARGV[0] || "."

#outputdir for lzxdoc
@outputdir = ARGV[1] || "doc"

#exceptdirs list from lzxdoc
exceptdirs_input = ARGV[2] || "doc"
@exceptdirs = exceptdirs_input.split(",")

#locale (ex. ja)
@locale = ARGV[3] || "ja"

#output keyword comments?
@output_private_keywords = false

@rlzxdoc_root = File.dirname(File.expand_path(__FILE__))

@lzx_classes = Array.new

expand_outputdir = File.expand_path(@outputdir)

#cleanup
FileUtils.rm_r(expand_outputdir) if File.exist?(expand_outputdir)
Dir.mkdir(expand_outputdir)

FileUtils.cp("#{@rlzxdoc_root}/styles.css", expand_outputdir)
FileUtils.cp("#{@rlzxdoc_root}/index.html", expand_outputdir)

Dir::chdir(@basedir)

Dir::glob("*.lzx").each{ |lzxfile| add_class_from_lzx(lzxfile) }

Dir::glob("**/").each { |dir|
  unless @exceptdirs.include?(File.dirname(dir).split("/").last)
    Dir::glob("#{dir}*.lzx").each {|lzxfile|
      add_class_from_lzx(lzxfile)
    }
  end
}

@nav = Hash.new

class_erb = File.open(@rlzxdoc_root + '/class.rhtml') {|f| ERB.new(f.read)}

@lzx_classes.each{ |lzxclass|
  html_output_dir = "#{expand_outputdir}/#{File.dirname(lzxclass[:path])}"
  FileUtils.mkdir_p(html_output_dir) unless File.exist?(html_output_dir)

  @lzxclass = lzxclass[:class]
  unless @lzxclass.nil?
    unless @lzxclass.private?
      File.open("#{html_output_dir}/#{@lzxclass.classname}.html", "w"){ |file| file.puts class_erb.result(binding) }

      url = "#{File.dirname(lzxclass[:path])}/#{@lzxclass.classname}.html"
      category = File.dirname(lzxclass[:path])
      @nav.store(category, Array.new) unless @nav.key?(category)
      @nav[category] << {:title => @lzxclass.classname, :url => url}
      puts "create: #{lzxclass[:path]}"
    end
  end
}

#create nav html
nav_erb = File.open(@rlzxdoc_root + '/nav.rhtml') {|f| ERB.new(f.read)}
File.open("#{expand_outputdir}/nav.html", "w"){ |file| file.puts nav_erb.result(binding)}

#clean up empty dirs
#Dir::glob("**/").each { |dir| Dir.rmdir(dir) if Dir.entries(dir).join == "..."}
