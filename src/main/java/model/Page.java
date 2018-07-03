package model;

import java.util.Collection;
import java.sql.Date;

public class Page {
	private int id;
	private String name;
	private String title;
	private String description;
	private int views;
	private Date created;
	private Date updated;
	private Collection<Widget> widgets;
	
	public Page() {
		super();
	}
	
	public Page(int id, String name, String title, String description, int views, Date created, Date updated) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.views = views;
		this.created = created;
		this.updated = updated;
	}
	
    public Page(String name, String description, Date created, Date updated, int views) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.views = views;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Collection<Widget> getWidgets() {
		return widgets;
	}

	public void setWidgets(Collection<Widget> widgets) {
		this.widgets = widgets;
	}

	
	
}
