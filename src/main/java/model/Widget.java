package model;

public class Widget {
	private int id;
	private String name;
	private String text;
	private int order;
	private int width;
	private int height;
	private String cssClass;
	private String cssStyle;
	
	public Widget() {
		super();
	}
	
	public Widget(int id, String name, String text, int order, int width, int height, String cssClass,
			String cssStyle) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
		this.order = order;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
	}
	
    public Widget(int id, String name) {
        this.id = id;
        this.name = name;
    }
	
    public Widget(String name, String text, int order) {
		this.name = name;
        this.text = text;
        this.order = order;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getCssStyle() {
		return cssStyle;
	}
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	
}
