#/opt/local/bin/ruby

#
# lzx class name creater
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

#basedir to create class list
@basedir = ARGV[0] || "."

#outputdir for lzxdoc
@outputdir = ARGV[1] || "class_names"

#exceptdirs list from lzxdoc
exceptdirs_input = ARGV[2] || "doc"
@exceptdirs = exceptdirs_input.split(",")

@rlzxdoc_root = File.dirname(File.expand_path(__FILE__))

@lzx_classes = Array.new

expand_outputdir = File.expand_path(@outputdir)

#cleanup
FileUtils.rm_r(expand_outputdir) if File.exist?(expand_outputdir)
Dir.mkdir(expand_outputdir)

Dir::chdir(@basedir)

Dir::glob("*.lzx").each{ |lzxfile| add_class_from_lzx(lzxfile) }

Dir::glob("**/").each { |dir|
  unless @exceptdirs.include?(File.dirname(dir).split("/").last)
    Dir::glob("#{dir}*.lzx").each {|lzxfile|
      add_class_from_lzx(lzxfile)
    }
  end
}

@lzx_classes.sort!{ |a,b|
  a[:class].classname <=> b[:class].classname}

nav_erb = File.open(@rlzxdoc_root + '/class_name_csv.erb') {|f| ERB.new(f.read, nil, '%')}
File.open("#{expand_outputdir}/class_name.csv", "w"){ |file| file.puts nav_erb.result(binding)}
