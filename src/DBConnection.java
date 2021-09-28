
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class DBConnection {

	public Connection DB() throws Exception {

		Connection con = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/train";
		String username = "nishanth";
		String password = "Nishanth@5524";

		con = DriverManager.getConnection(url, username, password);
		con.setAutoCommit(false);

		con.setTransactionIsolation(con.TRANSACTION_READ_COMMITTED);

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				System.out.println("Time Limit exceded 10 min");
				System.exit(0);
			}

		};
		timer.schedule(task, 600000);

		return con;
	}
}
