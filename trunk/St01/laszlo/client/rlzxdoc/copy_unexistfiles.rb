#/opt/local/bin/ruby

#
# file copy unexit files from a to b
#
# togawamanabu <togawamanabu@gmail.com>
#

require "fileutils"

# main

@from = ARGV[0] || "."
@to = ARGV[1] || "converted_lzx"

@expand_todir = File.expand_path(@to)

puts @expand_todir

Dir::chdir(@from)
Dir::glob("**/").each { |dir|
  #puts dir
  to_dir = @expand_todir + "/" + @from + "/" + dir
  FileUtils.mkdir_p(to_dir) unless File.exist?(to_dir)
  Dir::glob("#{dir}*.*").each {|file|
    checkfile =  @expand_todir + "/" + @from + "/" + file
    unless File.extname(file) == ".lzx"
      unless File.exist?(checkfile)
        FileUtils.cp(File.expand_path(file), to_dir)
        puts checkfile
      end
    end
  }
}
