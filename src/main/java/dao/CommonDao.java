package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CommonDao {
	final String DB_URL = "jdbc:mysql://cs5200-summer2018-he.c3prattaw347.us-east-2.rds.amazonaws.com/hw3_he_dennis_summer_2018";
	final String USERNAME = "He";
	final String PASSWORD = "Iloveyou1230!";
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	Connection connection = null;
    PreparedStatement prepStatement = null;
}
