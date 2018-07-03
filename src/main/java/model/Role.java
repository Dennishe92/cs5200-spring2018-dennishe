package model;

public class Role {
    public enum RoleType {ADMIN, EDITOR, OWNER, REVIEWER, WRITER}
    public enum DomainType {WEBSITE, PAGE}

    private int developerId;
    private int websiteId;
    private int pageId;
    private DomainType domainType;
    private RoleType role;

    public Role() {
        super();
    }
    
    public Role(int developerId, int websiteId, int pageId, DomainType type, RoleType role) {
        this.developerId = developerId;
        this.websiteId = websiteId;
        this.pageId = pageId;
        this.domainType = type;
        this.role = role;
    }
    
    public Role(int developerId, int pageId, RoleType role) {
        this.developerId = developerId;
        this.pageId = pageId;
        this.role = role;
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

	public DomainType getDomainType() {
		return domainType;
	}

	public void setDomainType(DomainType domainType) {
		this.domainType = domainType;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

}
