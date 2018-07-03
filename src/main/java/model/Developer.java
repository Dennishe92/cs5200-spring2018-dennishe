package model;

import java.util.Collection;

public class Developer extends Person{
	private String developerKey;
	private Collection<Website> websites;
	
    public Developer(int id, String username, String password, String firstName, String lastName, String email) {
        super(id, username, password, firstName, lastName, email);
    }
    
    public Developer(int id, String username, String password, String firstName, String lastName, String email, String developerKey) {
        super(id, username, password, firstName, lastName, email);
        this.developerKey = developerKey;
    }
    
    public Developer (String username, String password, String firstName, String lastName, String email, String developerKey) {
        super (username, password, firstName, lastName, email);
        this.developerKey = developerKey;
    }

	public String getDeveloperKey() {
		return developerKey;
	}
	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}

	public Collection<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(Collection<Website> websites) {
		this.websites = websites;
	}
	
}
