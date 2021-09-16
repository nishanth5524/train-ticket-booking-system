
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Berth {

	public String berth(int age, String gender, String tno, String date, Connection con) throws SQLException {

		int ucount = 0;
		int lcount = 0;
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select lowerberth from boardingdetails where tno = '" + tno + "' and depdate = '"+date+"'");

		while (rs.next()) {
			String result = rs.getString(1);
			ucount = Integer.parseInt(result);
		}

		Statement stmt1 = con.createStatement();
		ResultSet rs1 = stmt.executeQuery("select upperberth from boardingdetails where tno = '" + tno + "' and depdate = '"+date+"'");

		while (rs1.next()) {
			String result = rs1.getString(1);
			lcount = Integer.parseInt(result);
		}

		
		if (age >= 60 && lcount > 0) {
			return "lower";
		}

		else if (age >= 45 && gender == "female" && lcount > 0) {
			return "lower";
		}

		else if(ucount > 0)
		return "upper";

		return null;
	}

}
