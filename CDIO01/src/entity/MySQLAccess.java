package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void readDB() throws Exception {
		try {
			// This will load the MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			// Set up the connection with DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/cdio_1?"
					+ "user=EinarLogi&password=qwe123");
			
			// Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                            .executeQuery("select * from cdio_1.users where userID = 11");
            writeResultSet(resultSet);
			
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			//close();
		}
	}
	
	private void writeResultSet(ResultSet resultSet) throws Exception {
		//ResultSet is initially before the first data set
		while(resultSet.next()){
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
			
			int userID = resultSet.getInt("userID");
			String name = resultSet.getString("name");
			String ini = resultSet.getString("initials");
			String password = resultSet.getString("password");
			String cpr = resultSet.getString("cpr");
			String roles = resultSet.getString("roles");
			System.out.println();
			System.out.println("UserID: " + userID);
            System.out.println("Name: " + name);
            System.out.println("Initials: " + ini);
            System.out.println("Password: " + password);
            System.out.println("CPR: " + cpr);
            System.out.println("Roles: " + roles);
            System.out.println();
			
		}
	}

}
