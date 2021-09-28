
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Confirm2 {

	public Confirm2(Connection con, String tno, String date, String pnr, String berth) throws SQLException {

		int temp = 0;
		int totalwaitingseat = 0;

		String depdate = PassengerDetails.q.peek().getDepdate();
		String pid = PassengerDetails.q.peek().getPid();
		int id = PassengerDetails.q.peek().getId();

		try {

			String pnrresult = null;

			SqlQuery.UpdateStatusCancel(pnr, con);
			// pnrresult = SqlQuery.SelectWaitingPassenger(tno, date, con);

			pnrresult = PassengerDetails.q.peek().getPnr();

			if (pnrresult != null && !pnrresult.isEmpty()) {
				temp++;
			}

			if (temp > 0) {
				System.out.println(pnrresult + " is selected");
				SqlQuery.UpdateWaitingSeatplus(tno, date, con);
				SqlQuery.Insertpassengerboardingdetails(pnrresult, "confirm", depdate, "WL", pid, id, con);
				// SqlQuery.UpdateStatusConfirm(pnrresult, con);
				SqlQuery.SetBerth(berth, pnrresult, con);
				PassengerDetails.q.remove();
			}

			else if (temp == 0) {
				if (berth.equals("upper"))
					SqlQuery.UpdateUpperBerthplus(tno, date, con);
				else if (berth.equals("lower")) {
					SqlQuery.UpdateLowerBerthplus(tno, date, con);
				}
				SqlQuery.UpdateTotalSeatplus(tno, date, con);
			}
			con.commit();
			System.out.println("ticket cancelled");

		} catch (Exception ex) {
			System.out.println(ex);
			con.rollback();

		}
	}

}
