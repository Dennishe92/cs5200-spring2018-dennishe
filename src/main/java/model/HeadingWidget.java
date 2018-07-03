package model;

public class HeadingWidget extends Widget {
	private int size;

	
	public HeadingWidget() {
		super();
	}
	
	public HeadingWidget(int id, String name, int size) {
		super(id, name);
		this.size = size;
	}
	
	public HeadingWidget(String name, String text, int order) {
		super(name, text, order);
	}
	
	public HeadingWidget(String name, String text, int order, int size) {
		super(name, text, order);
		this.size = size;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

}
