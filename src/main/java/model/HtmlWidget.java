package model;

public class HtmlWidget extends Widget{
	private String html;
	
	public HtmlWidget() {
		super();
	}
	
	public HtmlWidget(int id, String name, String html) {
		super(id, name);
		this.html = html;
	}
	
	public HtmlWidget(String name, String text, int order) {
		super(name, text, order);
	}

	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}

}
