#/opt/local/bin/ruby

#
# lzxdoc build tool
#
# togawamanabu <togawamanabu@gmail.com>
#

require "rexml/document"
require "rexml/parsers/pullparser"
require "fileutils"
require 'erb'
require File.dirname(__FILE__) + '/LzxFile'
require File.dirname(__FILE__) + '/LzxClass'

require 'pp'

include REXML

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

#create class list html
class_list_erb = File.open(@rlzxdoc_root + '/class_list.rhtml') { |f| ERB.new(f.read)}
File.open("#{expand_outputdir}/classlist.html", "w"){ |file| file.puts class_list_erb.result(binding)}

#clean up empty dirs
#Dir::glob("**/").each { |dir| Dir.rmdir(dir) if Dir.entries(dir).join == "..."}
