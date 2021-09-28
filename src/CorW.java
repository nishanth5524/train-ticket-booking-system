
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CorW {

	public CorW(Connection con, String pnr) throws SQLException {

		String status = SqlQuery.getStatusPassenger(pnr, con);
		String date = SqlQuery.getdepdatePassenger(pnr, con);
		String pid = SqlQuery.getPIDPassenger(pnr, con);
		String berth = SqlQuery.getBerthPassenger(pnr, con);
		con.commit();

		if (status.equals("waiting")) {

			String tno = pid.substring(0, 5);
			Waiting2 obj = new Waiting2(con, tno, date, pnr, berth);
		}

		else if (status.equals("confirm")) {

			String tno = pid.substring(0, 5);
			Confirm2 obj = new Confirm2(con, tno, date, pnr, berth);

		}
	}

}
