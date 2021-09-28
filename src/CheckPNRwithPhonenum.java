import java.sql.Connection;
import java.sql.SQLException;

public class CheckPNRwithPhonenum {

	public static boolean CheckPNR(String pnr,String phonenum,Connection con) throws SQLException {
		
		int id = SqlQuery.getidwithpnr(pnr, con);
		
		boolean result = SqlQuery.checkidwithphonenum(phonenum, id, con);
		return result;
		
	}

}
