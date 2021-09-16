
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Waiting2 {

	public Waiting2(Connection con, String tno, String date, String pnr,String berth) throws SQLException {

		try {
			con.setAutoCommit(false);
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("update passengerboardingdetails set status = 'cancel' where pnr = '" + pnr + "'");
			stmt1.executeUpdate("update passengerboardingdetails set berth = '"+berth+"' where pnr = '" + pnr + "'");
//			if(berth.equals("upper"))
//				stmt1.executeUpdate("update boardingdetails set upperberth = upperberth + 1 where tno='" + tno
//						+ "' and depdate ='" + date + "'");
//				else if(berth.equals("lower"))
//				{
//					stmt1.executeUpdate("update boardingdetails set lowerberth = lowerberth + 1 where tno='" + tno
//							+ "' and depdate ='" + date + "'");
//					
//				}
			String sql1 = "update boardingdetails set wseat = wseat + 1 where tno='" + tno + "' and depdate ='" + date
					+ "'";
			stmt1.executeUpdate(sql1);
			
			
			con.commit();
			System.out.println(pnr + " ticket cancelled");
			con.setAutoCommit(true);
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
			con.rollback();
			con.setAutoCommit(true);
		}
	}

}
