
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CorW {

	public CorW(Connection con, String pnr) throws SQLException {

		String result = null;
		String date = null;
		String pid = null;
		String berth = null;

		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt
				.executeQuery("select status from passengerboardingdetails where pnr = '" + pnr + "'");

		while (resultSet.next()) {
			result = resultSet.getString(1);
			System.out.println(result);

		}
		
		Statement stmt1 = con.createStatement();
		ResultSet resultSet1 = stmt1
				.executeQuery("select berth from passengerboardingdetails where pnr = '" + pnr + "'");

		while (resultSet1.next()) {
			berth = resultSet1.getString(1);
			System.out.println(berth);

		}

		ResultSet resultSet3 = stmt1.executeQuery("select pid from passengerboardingdetails where pnr = '" + pnr + "'");

		while (resultSet3.next()) {
			pid = resultSet3.getString(1);

		}

		if (result.equals("waiting")) {

			String tno = pid.substring(0, 5);

			Statement stmt11 = con.createStatement();
			ResultSet resultSet11 = stmt11.executeQuery("select depdate from passengerboardingdetails where pnr = '" + pnr + "'");

			while (resultSet11.next()) {
				date = resultSet11.getString(1);
			}

			Waiting2 obj = new Waiting2(con, tno, date, pnr,berth);
		}

		else if (result.equals("confirm")) {

			String tno = pid.substring(0, 5);

			Statement stmt11 = con.createStatement();
			ResultSet resultSet11 = stmt11.executeQuery("select depdate from passengerboardingdetails where pnr = '" + pnr + "'");

			while (resultSet11.next()) {
				date = resultSet11.getString(1);
			}

			Confirm2 obj = new Confirm2(con, tno, date, pnr,berth);

		}
	}

}
