package dao;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.Developer;
import model.Phone;

public class DeveloperDao extends CommonDao{
	
	public static DeveloperDao instance = null;
	public static DeveloperDao getInstance() {
		if (instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}
	private DeveloperDao() {};
	
	
	/**
	 * create a new developer
	 * @param developer a developer
	 * @return 1 when inserted, 0 if nothing inserted
	 */
	public int createDeveloper(Developer developer) {
		
		int resultPerson = 0;
		int resultDeveloper = 0;

		try {
			Class.forName(JDBC_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			// create person
			String CREATE_NEW_PERSON = "INSERT INTO person(username, password, firstName, lastName, email, dob) VALUES(?,?,?,?,?,?)";
			prepStatement = connection.prepareStatement(CREATE_NEW_PERSON);
			prepStatement.setString(1, developer.getUsername());
			prepStatement.setString(2, developer.getPassword());
			prepStatement.setString(3, developer.getFirstName());
			prepStatement.setString(4, developer.getLastName());
			prepStatement.setString(5, developer.getEmail());
			prepStatement.setDate(6, developer.getDob());
			
			resultPerson = prepStatement.executeUpdate();
			
			//get new person's id
			String FIND_PERSON_BY_USERNAME = "SELECT * FROM person WHERE username=?";
			prepStatement = connection.prepareStatement(FIND_PERSON_BY_USERNAME);
			prepStatement.setString(1, developer.getUsername());
			ResultSet resultset = prepStatement.executeQuery();
			int id = 0;
			while(resultset.next()) {
				id = resultset.getInt("id");
			}
			
			// create new developer
			String CREATE_NEW_DEVELOPER = "INSERT INTO developer(id, developerKey) VALUES(?,?)";
			prepStatement = connection.prepareStatement(CREATE_NEW_DEVELOPER);
			prepStatement.setInt(1, id);
			prepStatement.setString(2, developer.getDeveloperKey());
			
			resultDeveloper = prepStatement.executeUpdate();
			
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
		return Math.min(resultPerson, resultDeveloper);
	}

	/**
	 * find all developers
	 * @return a collection of all developers
	 */
	public Collection<Developer> findAllDevelopers() {
		Collection<Developer> developers = new ArrayList<Developer>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_ALL_DEVELOPERS = "SELECT p.*, d.developerKey FROM developer d INNER JOIN person p on p.id = d.personId";
			prepStatement = connection.prepareStatement(FIND_ALL_DEVELOPERS);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
                Developer developer = new Developer(results.getInt("id"), results.getString("username"), 
                		results.getString("password"),results.getString("firstName"), results.getString("lastName"), 
                		results.getString("email"));
                
                developer.setDeveloperKey(results.getString("developerKey"));
                developer.setDob(results.getDate("dob"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                
                developers.add(developer);
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
		return developers;
	}
	
	/**
	 * return a developer with certain id
	 * @param developerId the id of the developer
	 * @return the developer with the id or null
	 */
	public Developer findDeveloperById(int developerId) {
		Developer result = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_DEVELOPER_BY_ID = "select p.*, d.* from person p inner join developer d on p.id = d.personId where p.id = ?";
			prepStatement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
			prepStatement.setInt(1, developerId);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
                Developer developer = new Developer(results.getInt("id"), results.getString("username"), results.getString("password"),
                		results.getString("firstName"), results.getString("lastName"), results.getString("email"));
                
                developer.setDeveloperKey(results.getString("developerKey"));
                developer.setDob(results.getDate("dob"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                
                result = developer;
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
	
	
	/**
	 * find the developer by a given user name
	 * @param username the user name
	 * @return the developer or null
	 */
	public Developer findDeveloperByUsername(String username) {
		Developer result = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_DEVELOPER_BY_ID = "select p.*, d.* from person p inner join developer d on p.id = d.id where p.username = ?";
			prepStatement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
			prepStatement.setString(1, username);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
                Developer developer = new Developer(results.getInt("id"), results.getString("username"), results.getString("password"),
                		results.getString("firstName"), results.getString("lastName"), results.getString("email"));
                
                developer.setDeveloperKey(results.getString("developerKey"));
                developer.setDob(results.getDate("dob"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                
                result = developer;
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
	
	/**
	 * Find a developer by credentials
	 * @param username the username
	 * @param password the password
	 * @return a developer when it matches, else null
	 */
	public Developer findDeveloperByCredentials(String username, String password) {
		
		Developer result = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String FIND_DEVELOPER_BY_ID = "select p.*, d.* from person p inner join developer d on p.id = d.personId where p.username = ? and p.password = ?";
			prepStatement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
			prepStatement.setString(1, username);
			prepStatement.setString(2, password);
			
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
                Developer developer = new Developer(results.getInt("id"), results.getString("username"), results.getString("password"),
                		results.getString("firstName"), results.getString("lastName"), results.getString("email"));
                
                developer.setDeveloperKey(results.getString("developerKey"));
                developer.setDob(results.getDate("dob"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                
                result = developer;
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
	
	
	/**
	 * update a developer
	 * @param developerId the developer to be updated
	 * @param developer the new developer 
	 * @return 1 when updated, 0 when no row is touched
	 */
	public int updateDeveloper(int developerId, Developer developer) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String UPDATE_DEVELOPER = "UPDATE person p, developer d SET p.username=?, p.password=?, p.firstName=?, p.lastName=?, p.email=?, p.dob=?, d.developerKey=? WHERE p.id=? AND p.id=d.personId";
			prepStatement = connection.prepareStatement(UPDATE_DEVELOPER);
			
			prepStatement.setString(1, developer.getUsername());
			prepStatement.setString(2, developer.getPassword());
			prepStatement.setString(3, developer.getFirstName());
			prepStatement.setString(4, developer.getLastName());
			prepStatement.setString(5, developer.getEmail());
			prepStatement.setDate(6, developer.getDob());
			prepStatement.setString(7, developer.getDeveloperKey());
			prepStatement.setInt(8, developerId);
			
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
	 * delete a developer
	 * @param developerId the id of the developer to delete
	 * @return 1 if deleted, 0 when not
	 */
	public int deleteDeveloper(int developerId) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			String DELETE_DEVELOPER = "DELETE p.*, d.* from person p, developer d where p.id = d.personId and p.id = ?";
			prepStatement = connection.prepareStatement(DELETE_DEVELOPER);
			prepStatement.setInt(1, developerId);
			
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
	 * create the phone for developer
	 * @param developerId the if of developer
	 * @param phone the phone
	 * @return 1 if updated, 0 if not
	 */
    public int createPhoneForDeveloper(int developerId, Phone phone) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
            String CREATE_PHONE_FOR_DEVELOPER = "INSERT INTO phone(phone, phone.primary, personId) VALUES(?,?,?) ";
            prepStatement = connection.prepareStatement(CREATE_PHONE_FOR_DEVELOPER);
            prepStatement.setString(1, phone.getPhone());
            prepStatement.setInt(2, phone.isPrimary());
            prepStatement.setInt(3, developerId);
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
	 * Find the phone for a developer
	 * @param developerId the id of the developer
	 * @return the phone
	 */
	
    public Phone findPhoneForDeveloper(int developerId, int primary) {
    		Phone phone = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
            String FIND_PHONE_FOR_DEVELOPER = "SELECT * FROM phone WHERE personId = ? and phone.primary = ?";
			prepStatement = connection.prepareStatement(FIND_PHONE_FOR_DEVELOPER);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, primary);

			ResultSet results = prepStatement.executeQuery();
            
            while (results.next()) {
            		phone = new Phone (results.getString("phone"), results.getInt("primary"));
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
		
		return phone;
    }
    

	/**
	 * Update the phone for developer
	 * @param developerId the id of the developer
	 * @param phone the phone
	 * @return 1 if updated, 0 if not
	 */
    public int updatePhoneForDeveloper(int developerId, Phone phone) {
		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
            String UPDATE_PHONE_FOR_DEVELOPER = "UPDATE phone SET phone = ?, phone.primary = ?, phone.personId = ? where phone.primary = ? and personId = ?";
            prepStatement = connection.prepareStatement(UPDATE_PHONE_FOR_DEVELOPER);
            prepStatement.setString(1, phone.getPhone());
            prepStatement.setInt(2, phone.isPrimary());
            prepStatement.setInt(3, developerId);
            prepStatement.setInt(4, phone.isPrimary());
            prepStatement.setInt(5, developerId);
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
     * Delete a address for a developer
     * @param developerId id of developer
     * @param address address to delete
     * @return 1 if deleted, 0 if not
     */
    public int deleteAddressForDeveloper (int developerId, int primary) {
    		int result = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
            String DELETE_ADDRESS_FOR_DEVELOPER = "DELETE FROM address WHERE personId = ? AND address.primary = ?";
            prepStatement = connection.prepareStatement(DELETE_ADDRESS_FOR_DEVELOPER);
            prepStatement.setInt(1,developerId);
            prepStatement.setInt(2, primary);
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
