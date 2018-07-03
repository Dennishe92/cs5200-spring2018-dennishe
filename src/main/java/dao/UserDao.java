package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDao extends CommonDao{
	
	public static UserDao instance = null;
	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	private UserDao() {};
	
	public int createUser(User user) {
		
		int resultPerson = 0;
		int resultUser = 0;

		try {
			Class.forName(JDBC_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			// create person
			String CREATE_NEW_PERSON = "INSERT INTO person(username, password, firstName, lastName, email, dob) VALUES(?,?,?,?,?,?)";
			prepStatement = connection.prepareStatement(CREATE_NEW_PERSON);
			prepStatement.setString(1, user.getUsername());
			prepStatement.setString(2, user.getPassword());
			prepStatement.setString(3, user.getFirstName());
			prepStatement.setString(4, user.getLastName());
			prepStatement.setString(5, user.getEmail());
			prepStatement.setDate(6, user.getDob());
			
			resultPerson = prepStatement.executeUpdate();
			
			//get new person's id
			String FIND_PERSON_BY_USERNAME = "SELECT * FROM person WHERE username=?";
			prepStatement = connection.prepareStatement(FIND_PERSON_BY_USERNAME);
			prepStatement.setString(1, user.getUsername());
			ResultSet resultset = prepStatement.executeQuery();
			int id = 0;
			while(resultset.next()) {
				id = resultset.getInt("id");
			}
			
			// create new developer
			String CREATE_NEW_USER = "INSERT INTO user(id, userAgreement, userKey) VALUES(?,?,?)";
			prepStatement = connection.prepareStatement(CREATE_NEW_USER);
			prepStatement.setInt(1, id);
			prepStatement.setInt(2, user.isUserAgreement());
			prepStatement.setString(3, user.getUserKey());
			
			resultUser = prepStatement.executeUpdate();
			
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
		return Math.min(resultPerson, resultUser);
	}
	

}
