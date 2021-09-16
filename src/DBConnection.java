
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public Connection DB() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/train";
		String username = "nishanth";
		String password = "Nishanth@5524";

		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
}
