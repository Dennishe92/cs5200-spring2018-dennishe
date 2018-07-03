package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import model.Priviledge;

public class PriviledgeDao extends CommonDao {
	public static PriviledgeDao instance = null;
	public static PriviledgeDao getInstance() {
		if (instance == null) {
			instance = new PriviledgeDao();
		}
		return instance;
	}
	private PriviledgeDao() {};
	
	/**
	 * Insert a priviledge of developer in a website
	 * @param developerId the id of the developer
	 * @param websiteId the id of the website
	 * @param priviledgeId the id of the priviledge
	 * @return 1 if assigned, 0 if not
	 */
	public int assignWebsitePriviledge(int developerId, int websiteId, Priviledge.PriviledgeType priviledge) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String ASSIGN_WEBSITE_PRIVILEDGE = "INSERT INTO websitepriviledge (developerId, websiteId, priviledge), VALUES(?,?,?)";
			prepStatement = connection.prepareStatement(ASSIGN_WEBSITE_PRIVILEDGE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, websiteId);
			
			if(priviledge == Priviledge.PriviledgeType.CREATE) {
				prepStatement.setString(3, "create");
			} else if (priviledge == Priviledge.PriviledgeType.READ) {
				prepStatement.setString(3, "read");
			} else if (priviledge == Priviledge.PriviledgeType.UPDATE) {
				prepStatement.setString(3, "update");
			} else if (priviledge == Priviledge.PriviledgeType.DELETE) {
				prepStatement.setString(3, "delete");
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
	 * Insert a priviledge of developer in a page
	 * @param developerId the id of the developer
	 * @param pageId the id of the page
	 * @param priviledgeId the id of the priviledge
	 * @return 1 if assigned, 0 if not
	 */
	public int assignPagePriviledge(int developerId, int pageId, Priviledge.PriviledgeType priviledge) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String ASSIGN_PAGE_PRIVILEDGE = "INSERT INTO pagepriviledge (developerId, pageId, priviledge), VALUES(?,?,?)";
			prepStatement = connection.prepareStatement(ASSIGN_PAGE_PRIVILEDGE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, pageId);
			
			if(priviledge == Priviledge.PriviledgeType.CREATE) {
				prepStatement.setString(3, "create");
			} else if (priviledge == Priviledge.PriviledgeType.READ) {
				prepStatement.setString(3, "read");
			} else if (priviledge == Priviledge.PriviledgeType.UPDATE) {
				prepStatement.setString(3, "update");
			} else if (priviledge == Priviledge.PriviledgeType.DELETE) {
				prepStatement.setString(3, "delete");
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
	 * Delete a priviledge of developer in a website
	 * @param developerId the id of the developer
	 * @param websiteId the id of the website
	 * @param priviledgeId the id of the priviledge
	 * @return 1 if deleted, 0 if not
	 */
	public int deleteWebsitePriviledge(int developerId, int websiteId, Priviledge.PriviledgeType priviledge) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String DELETE_WEBSITE_PRIVILEDGE = "DELETE * FROM websitepriviledge where developerId = ? AND websiteId = ? AND priviledge = ?";
			prepStatement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, websiteId);
			
			if(priviledge == Priviledge.PriviledgeType.CREATE) {
				prepStatement.setString(3, "create");
			} else if (priviledge == Priviledge.PriviledgeType.READ) {
				prepStatement.setString(3, "read");
			} else if (priviledge == Priviledge.PriviledgeType.UPDATE) {
				prepStatement.setString(3, "update");
			} else if (priviledge == Priviledge.PriviledgeType.DELETE) {
				prepStatement.setString(3, "delete");
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
	 * Delete a priviledge of developer in a page
	 * @param developerId the id of the developer
	 * @param pageId the id of the page
	 * @param priviledgeId the id of the priviledge
	 * @return 1 if deleted, 0 if not
	 */
	public int deletePagePriviledge(int developerId, int pageId, Priviledge.PriviledgeType priviledge) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String DELETE_PAGE_PRIVILEDGE = "DELETE * FROM websitepriviledge where developerId = ? AND pageId = ? AND priviledge = ?";
			prepStatement = connection.prepareStatement(DELETE_PAGE_PRIVILEDGE);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, pageId);
			
			if(priviledge == Priviledge.PriviledgeType.CREATE) {
				prepStatement.setString(3, "create");
			} else if (priviledge == Priviledge.PriviledgeType.READ) {
				prepStatement.setString(3, "read");
			} else if (priviledge == Priviledge.PriviledgeType.UPDATE) {
				prepStatement.setString(3, "update");
			} else if (priviledge == Priviledge.PriviledgeType.DELETE) {
				prepStatement.setString(3, "delete");
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
}
