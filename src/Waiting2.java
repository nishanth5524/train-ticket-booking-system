
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Waiting2 {

	public Waiting2(Connection con, String tno, String date, String pnr, String berth) throws SQLException {

		try {

			Statement stmt1 = con.createStatement();

			SqlQuery.UpdateStatusCancel(pnr, con);

			SqlQuery.UpdateWaitingSeatplus(tno, date, con);

			con.commit();
			System.out.println(pnr + " ticket cancelled");

		} catch (Exception ex) {
			System.out.println(ex);
			con.rollback();

		}
	}

}
