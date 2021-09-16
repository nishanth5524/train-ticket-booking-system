
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeBerthcout {

	public ChangeBerthcout(String tno,String date,String pnr,String berth,Connection con) throws SQLException {
		
		Statement stmt = con.createStatement();
		
		if(berth.equals("upper"))
		stmt.executeUpdate("update boardingdetails set upperberth = upperberth + 1 where tno='" + tno
				+ "' and depdate ='" + date + "'");
		else if(berth.equals("lower"))
		{
			stmt.executeUpdate("update boardingdetails set lowerberth = lowerberth + 1 where tno='" + tno
					+ "' and depdate ='" + date + "'");
			
		}
	}
	
}
