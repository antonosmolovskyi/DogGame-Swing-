package thesis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionMySQL {

	public static final String SELECT_RANK = "SELECT * FROM ranks";
	public static final String INSERT_RANK = "INSERT INTO ranks (name, steps) VALUES ('%s', %d)";
	
	public Connection getMySqlConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/thesis?" +  "user=root&password=");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public void addPlayer(String name, int steps) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn = getMySqlConnection();
		Statement st = conn.createStatement();
		st.execute(String.format(INSERT_RANK, name, steps));
		if(st != null) {
			st.close();
		}
		conn.close();
	}

}
