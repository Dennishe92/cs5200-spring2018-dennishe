package model;

import model.Role.DomainType;

public class Priviledge {
	public enum PriviledgeType {CREATE, READ, UPDATE, DELETE}
	
    private int developerId;
    private int websiteId;
    private int pageId;
    private Role.DomainType domainType;
    private PriviledgeType priviledge;
    

    
	public Priviledge(int developerId, int websiteId, int pageId, DomainType domainType, PriviledgeType priviledge) {
		super();
		this.developerId = developerId;
		this.websiteId = websiteId;
		this.pageId = pageId;
		this.domainType = domainType;
		this.priviledge = priviledge;
	}


	public Priviledge() {
		super();
	}


	public int getDeveloperId() {
		return developerId;
	}


	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}


	public int getWebsiteId() {
		return websiteId;
	}


	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}


	public int getPageId() {
		return pageId;
	}


	public void setPageId(int pageId) {
		this.pageId = pageId;
	}


	public Role.DomainType getDomainType() {
		return domainType;
	}


	public void setDomainType(Role.DomainType domainType) {
		this.domainType = domainType;
	}


	public PriviledgeType getPriviledge() {
		return priviledge;
	}


	public void setPriviledge(PriviledgeType priviledge) {
		this.priviledge = priviledge;
	}


}
