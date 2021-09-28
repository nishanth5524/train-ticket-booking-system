import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQueryAdmin {

	public static void Inserttrain(String tname, String tno, String from, String to, Connection con)
			throws SQLException {
		PreparedStatement stmt = con.prepareStatement("insert into traindetails values(?,?,?,?,?)");

		stmt.setString(1, tname);
		stmt.setString(2, tno);
		stmt.setString(3, from);
		stmt.setString(4, to);
		stmt.setInt(5, 1);

		stmt.setQueryTimeout(10);
		
		stmt.executeUpdate();

	}

	public static void InsertBoardingdetails(String tnum, int tseat, int wseat, String dated, String datea,
			String deptime, String arrtime, String lowerberth, String upperberth, Connection con) throws SQLException {

		PreparedStatement stmt = con.prepareStatement("insert into boardingdetails values(?,?,?,?,?,?,?,?,?)");

		stmt.setString(1, tnum);
		stmt.setInt(2, tseat);
		stmt.setInt(3, wseat);
		stmt.setString(4, dated);
		stmt.setString(5, datea);
		stmt.setString(6, deptime);
		stmt.setString(7, arrtime);
		stmt.setString(8, lowerberth);
		stmt.setString(9, upperberth);

		stmt.executeUpdate();

	}

	public static void updatepassengerboardingdetails(String tnum, String dated, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"update passengerboardingdetails set status = 'cancel' where pid LIKE ? and depdate = ?");

		stmt.setString(1, tnum + "%");
		stmt.setString(2, dated);

		stmt.executeUpdate();
	}

	public static void deleteboardingdetails(String tnum, String dated, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("delete from boardingdetails where tno = ? and depdate = ?");
		stmt.setString(1, tnum);
		stmt.setString(2, dated);

		stmt.executeUpdate();
	}

	public static void getAge(int age, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select count(*) from passengerdetails where age = ?");

		stmt.setInt(1, age);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			System.out.println("Count " + rs.getInt(1));

		}
	}

	public static void getDate(String date, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("select count(*) from passengerboardingdetails where depdate = ?");

		stmt.setString(1, date);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			System.out.println("Count " + rs.getInt(1));

		}
	}

	public static void geRange(String r1, String r2, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("select * from passengerboardingdetails where pid > ? and pid < ?");

		stmt.setString(1, r1);
		stmt.setString(2, r2);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			System.out.println("PID: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\nAge: " + rs.getString(3)
					+ "\nGender: " + rs.getString(4) + "\nPhone number: " + rs.getString(5) + "\nStatus: "
					+ rs.getString(6));

			System.out.println();
		}
	}

	public static String getusername(Connection con) throws SQLException {

		String username = null;

		PreparedStatement stmt = con.prepareStatement("select username from admin");

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			username = rs.getString(1);
		}
		return username;

	}

	public static String getpassword(Connection con) throws SQLException {
		
		String password = null;

		PreparedStatement stmt = con.prepareStatement("select password from admin");

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			password = rs.getString(1);
		}
		return password;

	}
}
