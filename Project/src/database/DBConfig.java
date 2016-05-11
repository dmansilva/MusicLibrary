package database;

/**
 * A convenience class to store information from the database configuration file.
 * @author srollins
 *
 */
public class DBConfig {

	private String username;
	private String password;
	private String db;
	private String host;
	private String port;
	
	// my database is user22
	
	/**
	 * Constructor.
	 * @param username
	 * @param password
	 * @param db
	 * @param host
	 * @param port
	 */
	public DBConfig(String username, String password, String db, String host,
			String port) {
		super();
		this.username = username;
		this.password = password;
		this.db = db;
		this.host = host;
		this.port = port;
	}
	
	public DBConfig(){
		
		super();
		this.username = "user22";
		this.password = "user22";
		this.db = "user22";
		this.host = "sql.cs.usfca.edu";
		this.port = "3306";
		
	}

	/**
	 * Return username
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Return password
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Return database.
	 * @return
	 */
	public String getDb() {
		return db;
	}

	/**
	 * Return host.
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Return port.
	 * @return
	 */
	public String getPort() {
		return port;
	}	
}