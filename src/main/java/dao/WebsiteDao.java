package dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import model.Role;
import model.Website;

public class WebsiteDao extends CommonDao{
	public static WebsiteDao instance = null;
	public static WebsiteDao getInstance() {
		if (instance == null) {
			instance = new WebsiteDao();
		}
		return instance;
	}
	private WebsiteDao() {};
	
	/**
	 * Insert a new website 
	 * @param developerId the owner of the website
	 * @param website the webiste to insert
	 * @return 1 if inserted, 0 if not
	 */
	public int createWebsiteForDeveloper(int developerId, Website website) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String CREATE_WEBSITE = "INSERT INTO website (name, description, created, updated, visits) VALUES(?, ?, ?, ?, ?)";
			prepStatement = connection.prepareStatement(CREATE_WEBSITE);
			
			prepStatement.setString(1, website.getName());
			prepStatement.setString(2, website.getDescription());
			prepStatement.setDate(3, website.getCreated());
			prepStatement.setDate(4, website.getUpdated());
			prepStatement.setInt(5, website.getVisits());
			
			result = prepStatement.executeUpdate();
			
			//Find websiteId for new website
			String FIND_WEBSITE_BY_NAME = "SELECT * FROM website WHERE name=?";
			prepStatement = connection.prepareStatement(FIND_WEBSITE_BY_NAME);
			prepStatement.setString(1, website.getName());
			ResultSet resultset = prepStatement.executeQuery();
			
			int websiteId = 0;
			while(resultset.next()) {
				websiteId = resultset.getInt("id");
			}
			
			RoleDao.getInstance().assignWebsiteRole(developerId, websiteId, Role.RoleType.OWNER);	
			
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
	 * Find all websites
	 * @return all websites
	 */
	public Collection<Website> findAllWebsites() {
		
		Collection<Website> websites = new ArrayList<Website>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_ALL_WEBSITES = "SELECT * FROM website";
			prepStatement = connection.prepareStatement(FIND_ALL_WEBSITES);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				
				Website website = new Website(id, name, description, created, updated, visits);
				
				website.setPages(PageDao.getInstance().findPagesForWebsite(id));
                
                websites.add(website);
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
		return websites;
	}
	
	
	/**
	 * Find all websites belong to a developer
	 * @param developerId
	 * @return a collection of websites
	 */
	public Collection<Website> findWebsitesForDeveloper(int developerId) {
		Collection<Website> websites = new ArrayList<Website>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_ALL_WEBSITES = "SELECT * FROM website where id in (select websiteId from websiterole where developerId = ? and roletype = 'owner')";
			
			prepStatement = connection.prepareStatement(FIND_ALL_WEBSITES);
			
			prepStatement.setInt(1,developerId);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				
				Website website = new Website(id, name, description, created, updated, visits);
				
				website.setPages(PageDao.getInstance().findPagesForWebsite(id));
                
                websites.add(website);
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
		return websites;
	}
	
	/**
	 * Find a website with certain id
	 * @param websiteId the id of the website
	 * @return the website
	 */
	public Website findWebsiteById(int websiteId) {
		Website website = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_WEBSITE_BY_ID = "SELECT * FROM website where id = ?";
			
			prepStatement = connection.prepareStatement(FIND_WEBSITE_BY_ID);
			
			prepStatement.setInt(1,websiteId);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				
				website = new Website(id, name, description, created, updated, visits);
				
				website.setPages(PageDao.getInstance().findPagesForWebsite(id));
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
		return website;
	}
	
	
	/**
	 * Update a website 
	 * @param websiteId
	 * @param website
	 * @return
	 */
	public int updateWebsite(int websiteId, Website website) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String  UPDATE_WEBSITE = "UPDATE website SET name = ?, description = ?, created = ?, updated = ?, visits = ? where id = ? ";
			prepStatement = connection.prepareStatement(UPDATE_WEBSITE);
			prepStatement.setString(1, website.getName());
			prepStatement.setString(2, website.getDescription());
			prepStatement.setDate(1, website.getCreated());
			prepStatement.setDate(1, website.getUpdated());
			prepStatement.setInt(1, website.getVisits());
			prepStatement.setInt(1, websiteId);
			
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
	 * Delete a website with certain id
	 * @param websiteId the id of the website to delete
	 * @return 1 when deleted, 0 if not
	 */
	public int deleteWebsite(int websiteId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String  DELETE_WEBSITE = "DELETE FROM website where id = ?";
			prepStatement = connection.prepareStatement(DELETE_WEBSITE);
			prepStatement.setInt(1, websiteId);
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
	 * Find a website with certain name
	 * @param websiteName the name of the website
	 * @return the website
	 */
	public Website findWebsiteByName (String websiteName) {
		Website website = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_WEBSITE_BY_NAME = "SELECT * FROM website where name = ?";
			
			prepStatement = connection.prepareStatement(FIND_WEBSITE_BY_NAME);
			
			prepStatement.setString(1,websiteName);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				
				website = new Website(id, name, description, created, updated, visits);
				
				website.setPages(PageDao.getInstance().findPagesForWebsite(id));
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
		return website;
	}
	
}
