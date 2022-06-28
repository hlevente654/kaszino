package alapPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class javaSqlConnection {
	
	private static final String USERNAME = "javasqlusername";
	private static final String PASSWORD = "javasqlpassword";
	private static final String CONN = "jdbc:mysql://localhost/kaszino";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONN,USERNAME, PASSWORD );
	}
}
