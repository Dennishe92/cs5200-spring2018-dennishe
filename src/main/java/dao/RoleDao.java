package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Role;

public class RoleDao extends CommonDao{
	public static RoleDao instance = null;
	public static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}
	private RoleDao() {};
	
	/**
	 * Assign a user with the role in a certain website
	 * @param userId the developer's id
	 * @param websiteId the website's id
	 * @param roleId the id of the role
	 * @return 1 if assigned, 0 if not;
	 */
	public int assignWebsiteRole(int developerId, int websiteId, Role.RoleType role) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String ASSIGN_WEBSITE_ROLE = "INSERT INTO websiterole (developerId, websiteId, roletype) VALUES(?,?,?)";
			prepStatement = connection.prepareStatement(ASSIGN_WEBSITE_ROLE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, websiteId);
			if (role == Role.RoleType.ADMIN) {
				prepStatement.setString(3, "admin");
			} else if (role == Role.RoleType.EDITOR) {
				prepStatement.setString(3, "editor");
			} else if (role == Role.RoleType.OWNER) {
				prepStatement.setString(3, "owner");
			} else if (role == Role.RoleType.REVIEWER) {
				prepStatement.setString(3, "reviewer");
			} else if (role == Role.RoleType.WRITER) {
				prepStatement.setString(3, "writer");
			}
			
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
	 * Assign a role to a devloper on a page
	 * @param developerId the id of the developer
	 * @param pageId the id of the page
	 * @param roleId the id of the role
	 * @return 1 if assigned, 0 if not
	 */
	public int assignPageRole(int developerId, int pageId, Role.RoleType role) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String ASSIGN_PAGE_ROLE = "INSERT INTO pagerole (developerId, pageId, roletype) VALUES(?,?,?)";
			prepStatement = connection.prepareStatement(ASSIGN_PAGE_ROLE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, pageId);
			if (role == Role.RoleType.ADMIN) {
				prepStatement.setString(3, "admin");
			} else if (role == Role.RoleType.EDITOR) {
				prepStatement.setString(3, "editor");
			} else if (role == Role.RoleType.OWNER) {
				prepStatement.setString(3, "owner");
			} else if (role == Role.RoleType.REVIEWER) {
				prepStatement.setString(3, "reviewer");
			} else if (role == Role.RoleType.WRITER) {
				prepStatement.setString(3, "writer");
			}
			
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
	 * Update a role to a devloper on a page
	 * @param developerId the id of the developer
	 * @param pageId the id of the page
	 * @param roleId the id of the role
	 * @return 1 if assigned, 0 if not
	 */
	public int updatePageRole(int developerId, int pageId, Role.RoleType role) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String UPDATE_PAGE_ROLE = "UPDATE pagerole SET roletype = ? where developerId = ? and pageId = ?";
			prepStatement = connection.prepareStatement(UPDATE_PAGE_ROLE);
			if (role == Role.RoleType.ADMIN) {
				prepStatement.setString(1, "admin");
			} else if (role == Role.RoleType.EDITOR) {
				prepStatement.setString(1, "editor");
			} else if (role == Role.RoleType.OWNER) {
				prepStatement.setString(1, "owner");
			} else if (role == Role.RoleType.REVIEWER) {
				prepStatement.setString(1, "reviewer");
			} else if (role == Role.RoleType.WRITER) {
				prepStatement.setString(1, "writer");
			}
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, pageId);
			
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
	 * Delete a role of a developer in a website
	 * @param developerId the developer's id
	 * @param websiteId the website's id
	 * @param roleId the id of the role
	 * @return 1 if deleted, 0 if not
	 */
	public int deleteWebsiteRole(int developerId, int websiteId, Role.RoleType role) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String DELETE_WEBSITE_ROLE = "DELETE * FROM websiterole where developerId = ? AND websiteId = ? AND roletype = ?";
			prepStatement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, websiteId);
			
			if (role == Role.RoleType.ADMIN) {
				prepStatement.setString(3, "admin");
			} else if (role == Role.RoleType.EDITOR) {
				prepStatement.setString(3, "editor");
			} else if (role == Role.RoleType.OWNER) {
				prepStatement.setString(3, "owner");
			} else if (role == Role.RoleType.REVIEWER) {
				prepStatement.setString(3, "reviewer");
			} else if (role == Role.RoleType.WRITER) {
				prepStatement.setString(3, "writer");
			}
			
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
	 * Delete a role to a devloper on a page
	 * @param developerId the id of the developer
	 * @param pageId the id of the page
	 * @param roleId the id of the role
	 * @return 1 if deleted, 0 if not
	 */
	public int deletePageRole(int developerId, int pageId, Role.RoleType role) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String DELETE_PAGE_ROLE = "DELETE FROM pagerole where developerId = ? AND pageId = ? AND roletype = ?";
			prepStatement = connection.prepareStatement(DELETE_PAGE_ROLE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, pageId);
			
			if (role == Role.RoleType.ADMIN) {
				prepStatement.setString(3, "admin");
			} else if (role == Role.RoleType.EDITOR) {
				prepStatement.setString(3, "editor");
			} else if (role == Role.RoleType.OWNER) {
				prepStatement.setString(3, "owner");
			} else if (role == Role.RoleType.REVIEWER) {
				prepStatement.setString(3, "reviewer");
			} else if (role == Role.RoleType.WRITER) {
				prepStatement.setString(3, "writer");
			}
			
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
	 * Find role for a developer in a page
	 * @param developerId id of developer
	 * @param pageId id of page
	 * @return the role
	 */
	public Role findPageRoleForDeveloper(int developerId, int pageId){
		Role result = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_PAGE_ROLE = "SELECT * FROM pagerole where developerId = ? AND pageId = ?";
			prepStatement = connection.prepareStatement(FIND_PAGE_ROLE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, pageId);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
				Role.RoleType role = null;
				if (results.getString("roletype").equals("admin")) {
					role = Role.RoleType.ADMIN;
				} else if (results.getString("roletype").equals("editor")) {
					role = Role.RoleType.EDITOR;
				} else if (results.getString("roletype").equals("owner")) {
					role = Role.RoleType.OWNER;
				} else if (results.getString("roletype").equals("reviewer")) {
					role = Role.RoleType.REVIEWER;
				} else if (results.getString("roletype").equals("writer")) {
					role = Role.RoleType.WRITER;
				} 
				result = new Role(developerId, pageId, role);
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
		return result;
	}
	
}
