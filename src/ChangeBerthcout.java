
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeBerthcout {

	public ChangeBerthcout(String tno, String date, String pnr, String berth, Connection con) throws SQLException {

		Statement stmt = con.createStatement();

		if (berth.equals("upper"))
			SqlQuery.UpdateUpperBerthplus(tno, date, con);
		else if (berth.equals("lower")) {
			SqlQuery.UpdateLowerBerthplus(tno, date, con);

		}
	}

}
