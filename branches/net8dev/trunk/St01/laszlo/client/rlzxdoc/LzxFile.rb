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
