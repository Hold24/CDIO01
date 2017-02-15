package main;
import java.sql.*;

public class main {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  		// Driver name:
	static final String DB_URL = "jdbc:mysql://localhost/";				// Database URL:

	static final String USER = "username";								// Data credentials - user name
	static final String PASS = "password";								// Password

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;

		try{

			Class.forName("com.mysql.jdbc.Driver"); 					// Register JDBC driver

			System.out.println("Connecting to database...");			// Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);		//

			System.out.println("Creating database...");					// Creating the database
			stmt = conn.createStatement();								// And whatever you have set up more

			String sql = "CREATE DATABASE USERS";
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");

		}

		catch(SQLException se){
			se.printStackTrace();										//Handle errors for JDBC
		}

		catch(Exception e){
			e.printStackTrace();										//Handle errors for Class.forName
		}

		finally{
			try{														//finally block used to close resources
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException se2){

			}
			try{														// nothing we can do
				if(conn!=null)
					conn.close();
			}

			catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}//end main
}//end JDBCExample