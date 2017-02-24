package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	// Database URL
	static final String DB_path = "jdbc:mysql://localhost/cdio_1?";
	// Database credentials
	static final String user = "EinarLogi";
	static final String pw = "qwe123";
	
	
	public void readDB() throws Exception {
		try {
			// This will load the MySQL driver
			//			Class.forName("com.mysql.jdbc.Driver");
			// Set up the connection with DB
			connect = DriverManager.getConnection(DB_path, user, pw);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from cdio_1.users");
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
				System.out.println("UserID: " + userID + " | Name: " + name + " | Initials: " + ini + " | Password: " + password + " | CPR: " + cpr + " | Roles: " + roles);


			}

		}
		catch (Exception e) {
			throw e;
		}
		finally {
			close();
		}
	}



	public boolean checkForUser(int id) throws SQLException {
		connect = DriverManager.getConnection(DB_path, user, pw);
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from cdio_1.users where userID = " + id);
		boolean userExists = resultSet.next();
		close();
		return userExists;
	}

	public void writeUser(String[] A, int x) throws SQLException {
		connect = DriverManager.getConnection("jdbc:mysql://localhost/cdio_1?"
				+ "user=EinarLogi&password=qwe123");
		preparedStatement = connect.prepareStatement("insert into cdio_1.users values (?,?,?,?,?,?)");
		preparedStatement.setInt(1, x);
		preparedStatement.setString(2, A[0]);
		preparedStatement.setString(3, A[1]);
		preparedStatement.setString(4, A[2]);
		preparedStatement.setString(5, A[3]);
		preparedStatement.setString(6, A[4]);
		preparedStatement.executeUpdate();
		close();

	}

	public void deleteUser(int id) throws SQLException {
		connect = DriverManager.getConnection(DB_path, user, pw);
		preparedStatement = connect.prepareStatement("delete from cdio_1.users where userID = " + id);
		preparedStatement.executeUpdate();
		close();
	}

	public void updateUser(String newValue, String column, int id) throws SQLException {
		connect = DriverManager.getConnection(DB_path, user, pw);
		preparedStatement = connect.prepareStatement("UPDATE users SET " + column + " = " +  "\"" + newValue + "\""  + " where userID = " + id);
		preparedStatement.executeUpdate();
		close();
	}

	public String[] loadUser(int id) throws SQLException {
		connect = DriverManager.getConnection(DB_path, user, pw);

		statement = connect.createStatement();
		resultSet = statement
				.executeQuery("select * from cdio_1.users where userID = " + id);

		String[] userValues = new String[5];
		while(resultSet.next()){
			userValues[0] = resultSet.getString("name");
			userValues[1] = resultSet.getString("initials");
			userValues[2] = resultSet.getString("password");
			userValues[3] = resultSet.getString("cpr");
			userValues[4] = resultSet.getString("roles");
		}

		return userValues;

	}

	// Used to close the ResultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
