package model;

public class ImageWidget extends Widget{
	private String src;
	
	
	public ImageWidget() {
		super();
	}
	
	public ImageWidget(int id, String name, String src) {
		super(id, name);
		this.src = src;
	}
	
	public ImageWidget(String name, String text, int order, String src) {
		super(name, text, order);
		this.src = src;
	}
	

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
}
