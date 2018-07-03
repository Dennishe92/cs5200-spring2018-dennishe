package model;

import java.sql.Date;

public class User extends Person{

	private int userAgreement;
	private String userKey;

	public User(int id, String username, String password, String firstName, String lastName, String email, Date dob, int userAgreement, String userKey) {
		super(id, username, password, firstName, lastName, email, dob);
		this.userAgreement = userAgreement;
		this.userKey = userKey;
	}
	
    public User (String username, String password, String firstName, String lastName, String email, String userKey) {
        super (username, password, firstName, lastName, email);
        this.userKey = userKey;
    }

	
    public User() {
        super();
    }
	
	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public int isUserAgreement() {
		return userAgreement;
	}
	public void setUserAgreement(int userAgreement) {
		this.userAgreement = userAgreement;
	}
	
}
