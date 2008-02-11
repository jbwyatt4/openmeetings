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

