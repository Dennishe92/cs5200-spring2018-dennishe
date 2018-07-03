package dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.Page;

public class PageDao extends CommonDao{
	public static PageDao instance = null;
	public static PageDao getInstance() {
		if (instance == null) {
			instance = new PageDao();
		}
		return instance;
	}
	private PageDao() {};
	
	/**
	 * Insert a page into with a certain websiteId
	 * @param websiteId the id of the website
	 * @param page the page to insert
	 * @return 1 if inserted, 0 if not
	 */
	public int createPageForWebsite(int websiteId, Page page) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String CREATE_PAGE = "INSERT INTO page (name, title, description, views, created, updated, websiteId) VALUES(?, ?, ?, ?, ?, ?, ?)";
			prepStatement = connection.prepareStatement(CREATE_PAGE);
			
			prepStatement.setString(1, page.getName());
			prepStatement.setString(2, page.getTitle());
			prepStatement.setString(3, page.getDescription());
			prepStatement.setInt(4, page.getViews());
			prepStatement.setDate(5, page.getCreated());
			prepStatement.setDate(6, page.getUpdated());
			prepStatement.setInt(7, websiteId);
			
			result = prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Find all pages
	 * @return a collection of pages
	 */
	public Collection<Page> findAllPages() {
		Collection<Page> pages = new ArrayList<Page>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_ALL_PAGES = "SELECT * FROM page";
			prepStatement = connection.prepareStatement(FIND_ALL_PAGES);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String title = results.getString("title");
				String description = results.getString("description");
				int views = results.getInt("views");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				
				Page page = new Page(id, name, title, description,views,created, updated);
				
				page.setWidgets(WidgetDao.getInstance().findWidgetsForPage(id)); 
                
                pages.add(page);
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pages;
	}
	
	/**
	 * Find the page of certain id
	 * @param pageId the id of the page
	 * @return the page
	 */
	public Page findPageById(int pageId) {
		Page page = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String FIND_PAGE_BY_ID = "SELECT * FROM page where id = ?";
			prepStatement = connection.prepareStatement(FIND_PAGE_BY_ID);
			prepStatement.setInt(1, pageId);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String title = results.getString("title");
				String description = results.getString("description");
				int views = results.getInt("views");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				
				page = new Page(id, name, title, description, views, created, updated);
				page.setWidgets(WidgetDao.getInstance().findWidgetsForPage(id));
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return page;
	}
	
	/**
	 * Find all pages for a website
	 * @param websiteId the id of the website
	 * @return all pages in the website
	 */
	public Collection<Page> findPagesForWebsite(int websiteId) {
		Collection<Page> pages = new ArrayList<Page>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_ALL_PAGES = "SELECT * FROM page where websiteId = ?";
			prepStatement = connection.prepareStatement(FIND_ALL_PAGES);
			prepStatement.setInt(1, websiteId);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String title = results.getString("title");
				String description = results.getString("description");
				int views = results.getInt("views");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				
				Page page = new Page(id, name, title, description,views,created, updated);
				
				page.setWidgets(WidgetDao.getInstance().findWidgetsForPage(id)); 
                
                pages.add(page);
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pages;
	}
	
	/**
	 * update a page
	 * @param pageId the page to update
	 * @param page the new page to cover the old one
	 * @return 1 if updated, 0 if not
	 */
	public int updatePage(int pageId, Page page) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String UPDATE_PAGE = "UPDATE page SET name = ?, title = ?, description = ?, views = ?, created = ?, updated = ? WHERE id = ? ";
			
			prepStatement = connection.prepareStatement(UPDATE_PAGE);
			prepStatement.setString(1, page.getName());
			prepStatement.setString(2, page.getTitle());
			prepStatement.setString(3, page.getDescription());
			prepStatement.setInt(4, page.getViews());
			prepStatement.setDate(5, page.getCreated());
			prepStatement.setDate(6, page.getUpdated());
			prepStatement.setInt(7,pageId);
			
			result = prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	/**
	 * Delete the page with certain id
	 * @param pageId the id of the page
	 * @return 1 if deleted, 0 if not
	 */
	public int deletePage(int pageId) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String DELETE_PAGE = "DELETE FROM page WHERE id = ?";
			
			prepStatement = connection.prepareStatement(DELETE_PAGE);
			prepStatement.setInt(1, pageId);
			result = prepStatement.executeUpdate();	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
