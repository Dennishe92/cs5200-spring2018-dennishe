package model;

public class YouTubeWidget extends Widget{
	private String url;
	private int shareable;
	private int expandable;
	
	public YouTubeWidget() {
		super();
	}
	
	public YouTubeWidget(int id, String name, String url, int shareable, int expandable, int widgetId) {
		super(id, name);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}
	
	public YouTubeWidget (String name, String text, int order, String url) {
		super(name, text, order);
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int isShareable() {
		return shareable;
	}
	public void setShareable(int shareable) {
		this.shareable = shareable;
	}
	public int isExpandable() {
		return expandable;
	}
	public void setExpandable(int expandable) {
		this.expandable = expandable;
	}

}
	
	

