package model;

public class Phone {
	private String phone;
	private int primary;
	
	public Phone(String phone, int primary) {
		super();
		this.phone = phone;
		this.primary = primary;
	}
	
	public Phone() {
		super();
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int isPrimary() {
		return primary;
	}
	public void setPrimary(int primary) {
		this.primary = primary;
	}
	
}
