package database;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;



public class DBHelper {
	
	
	private static DBConfig dbconfig = new DBConfig();
	private static String USER = "user";
	private static String FAVORITE = "favs";
	
	
	
	/* 
	 * The addUser() method takes in the full name, username, 
	 * and password and constructs an appropriate INSERT statement, and uses a 
	 * PreparedStatement object to insert the information into the database.
	*/
	
	public static void addUser(String fullname, String username, String password) {
		
		
		try {
			
			Connection con = getConnection(dbconfig);
			if (!tableExists(con, USER)) {
				createUserTable(dbconfig);
			}
				
				PreparedStatement userTable = con.prepareStatement("INSERT INTO user (fullname, username, password) VALUES (?, ?, ?)");
				
				userTable.setString(1, fullname);
				userTable.setString(2, username);
				userTable.setString(3, password);

				userTable.execute();
				con.close();
				
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * addFavorite method adds the new favorite song, storing the trackId for that song and username
	 */
	
	public static void addFavorite(String currentUserName, String trackId) {
		
		try {
			
			Connection con = getConnection(dbconfig);
			if (!tableExists(con, FAVORITE)) {
				
				createFavoritesTable(dbconfig);
			}
				
				PreparedStatement favTable = con.prepareStatement("INSERT INTO favs (username, trackid) VALUES (?, ?)");
				
				favTable.setString(1, currentUserName);
				favTable.setString(2, trackId);
				
				favTable.execute();
				con.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * verifyUserName method takes in the username that is trying to be added and sees if
	 * that username is already in the database.
	 */
	
	public static boolean verifyUserName(String username) {
		
		
		Connection con = null;
		ResultSet set = null;
		
		try {
			
			con = getConnection(dbconfig);
			PreparedStatement userTable = con.prepareStatement("SELECT username FROM user WHERE username = ?");
			userTable.setString(1, username);
			set = userTable.executeQuery();
			boolean result = set.absolute(1);
			return result;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		finally {
			if (con != (null)) {
				try {
					con.close();
					set.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return false;
	}
	
	/*
	 * The verifyUser()  might take a username and password and return true if 
	 * the username and password match a row in the database, and false if not.
	 */
	
	public static boolean verifyUser(String username, String password)  {
		
		
		String loginInfo = "SELECT username, password FROM user";
		Connection con = null;
		PreparedStatement userTable;
		try {
			con = getConnection(dbconfig);
			userTable = con.prepareStatement(loginInfo);
			ResultSet result = userTable.executeQuery();
			
			while (result.next()) {
				
				String userName = result.getString("username");
				String passWord = result.getString("password");
				
				if (username.equals(userName) && password.equals(passWord)) {
					return true;
				}
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			if (con != (null)) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
		
	}
	
	
	
	/*
	 * getFavList method gets all the trackid that associate with the current user, then we 
	 * use the trackId to extract artist and title to display in a table
	 */
	
	public static HashMap<String, String> getFavList(String username) {
		
		
		HashMap<String, String> favMap = new HashMap<String, String>();
		Connection con = null;
		ResultSet set = null;
		try {
			con = getConnection(dbconfig);
			PreparedStatement favLists = con.prepareStatement("SELECT trackid FROM favs WHERE username = ?");
			favLists.setString(1, username);
			set = favLists.executeQuery();
			while (set.next()) {
				
				String trackId = set.getString("trackid");
				favMap.put(trackId, username);
			}
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			if (con != (null)) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return favMap;
	}
	
	
	
	// create a table called users that has full name, username, and password
	public static void createUserTable(DBConfig dbconfig) throws SQLException {
		
		 
		Connection conn = getConnection(dbconfig);
		
		String sql = "CREATE TABLE user " +
                "(fullname VARCHAR(100) NOT NULL, " + 
                " username VARCHAR(100), " + 
                " password VARCHAR(100), " +
                " PRIMARY KEY ( username ))"; 
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.execute();
		
		conn.close();
		
		
	}
	
	public static void createFavoritesTable(DBConfig dbconfig) throws SQLException {
		
		Connection conn = getConnection(dbconfig);
		
		String sql = "CREATE TABLE favs " +
                "(username VARCHAR(100) NOT NULL, " + 
                " trackid VARCHAR(100))";
                
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.execute();
		
		conn.close();
		
		
	}
	
	/**
	 * A helper method that returns a database connection.
	 * A calling method is responsible for closing the connection when finished.
	 * @param dbconfig
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(DBConfig dbconfig) throws SQLException {

		String username  = dbconfig.getUsername();
		String password  = dbconfig.getPassword();
		String db  = dbconfig.getDb();

		try {
			// load driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (Exception e) {
			System.err.println("Can't find driver");
			System.exit(1);
		}

		// format "jdbc:mysql://[hostname][:port]/[dbname]"
		//note: if connecting through an ssh tunnel make sure to use 127.0.0.1 and
		//also to that the ports are set up correctly
		String host = dbconfig.getHost();
		String port = dbconfig.getPort();
		String urlString = "jdbc:mysql://" + host + ":" + port + "/"+db;
		Connection con = DriverManager.getConnection(urlString,
				username,
				password);

		return con;
	}

	/**
	 * If the artist table exists in the database, removes that table.
	 * 
	 * @param dbconfig
	 * @param tables
	 * @throws SQLException
	 */
	public static void clearTables(DBConfig dbconfig, ArrayList<String> tables) throws SQLException {

		Connection con = getConnection(dbconfig);

		for(String table: tables) {
			//create a statement object
			Statement stmt = con.createStatement();
			if(tableExists(con, table)) {
				String dropStmt = "DROP TABLE " + table;
				stmt.executeUpdate(dropStmt);
			}

		}
		con.close();
	}

	/**
	 * Helper method that determines whether a table exists in the database.
	 * @param con
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	private static boolean tableExists(Connection con, String table) throws SQLException {

		DatabaseMetaData metadata = con.getMetaData();
		ResultSet resultSet;
		resultSet = metadata.getTables(null, null, table, null);

		if(resultSet.next()) {
			// Table exists
			return true;
		}		
		return false;
	}


}
