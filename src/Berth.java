
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Berth {

	public String berth(int age, String gender, String tno, String date, Connection con) throws SQLException {

		int ucount = 0;
		int lcount = 0;
		
		ucount = SqlQuery.getUpperBerth(tno, date, con);
		lcount = SqlQuery.getLowerBerth(tno, date, con);

		if (age >= 60 && lcount > 0) {
			return "lower";
		}

		else if (age >= 45 && gender == "female" && lcount > 0) {
			return "lower";
		}

		else if(ucount > 0) {
		return "upper";
		}
		
		else if(ucount == 0 && lcount > 0) {
			return "lower";
		}
		
		else if (age >= 60 && lcount == 0 && ucount >0) {
			return "upper";
		}

		else if (age >= 45 && gender == "female" && lcount == 0 && ucount >0) {
			return "upper";
		}
			
		return null;
	}

}
