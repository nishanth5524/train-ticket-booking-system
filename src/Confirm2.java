
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Confirm2 {

	public Confirm2(Connection con, String tno, String date, String pnr, String berth) throws SQLException {

		int temp = 0;
		int tr_seat = 0;

		try {
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			ResultSet rs4 = stmt.executeQuery(
					"select wseat from boardingdetails where tno='" + tno + "' and depdate ='" + date + "'");

			while (rs4.next()) {
				tr_seat = rs4.getInt(1);
			}

			if (tr_seat >= 5) {
				String sql12 = "update boardingdetails set tseat = tseat + 1 where tno='" + tno + "' and depdate ='"
						+ date + "'";
				stmt.executeUpdate(sql12);
			}

			Statement stmt2 = con.createStatement();
			stmt2.executeUpdate("update passengerboardingdetails set status = 'cancel' where pnr = '" + pnr + "'");
			stmt2.executeUpdate(
					"update passengerboardingdetails set berth = '" + berth + "' where pnr = '" + pnr + "'");
			String pnrresult = null;

			System.out.println("--------------");
			System.out.println(tno);
			System.out.println(date);
			System.out.println("--------------");
			ResultSet rs1 = stmt2
					.executeQuery("select pnr from passengerboardingdetails where status = 'waiting' and pid LIKE '"
							+ tno + "%'  and depdate ='" + date + "' ORDER BY pid LIMIT 1");

			
			while (rs1.next()) {
				pnrresult = rs1.getString(1);
				System.out.println(pnrresult);
				temp++;
			}

			if (temp > 0) {
				System.out.println(pnrresult + " is selected");
				stmt2.executeUpdate(
						"update passengerboardingdetails set status = 'confirm' where pnr ='" + pnrresult + "'");


				
				stmt2.executeUpdate("update boardingdetails set wseat = wseat + 1 where tno='" + tno
						+ "' and depdate ='" + date + "'");
			}
			
			else if(temp == 0)
			{
				if(berth.equals("upper"))
				stmt.executeUpdate("update boardingdetails set upperberth = upperberth + 1 where tno='" + tno
						+ "' and depdate ='" + date + "'");
				else if(berth.equals("lower"))
				{
					stmt.executeUpdate("update boardingdetails set lowerberth = lowerberth + 1 where tno='" + tno
							+ "' and depdate ='" + date + "'");
					
				}
			}
			con.commit();
			System.out.println("ticket cancelled");
			con.setAutoCommit(true);
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
			con.rollback();
			con.setAutoCommit(true);
		}
	}

}
