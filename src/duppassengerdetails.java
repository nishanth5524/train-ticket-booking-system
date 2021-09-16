
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class duppassengerdetails {

	public int check(String name, int age, String gender, String phonenum,String email,Connection con) throws SQLException
	{
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select * from passengerdetails where name = '" + name + "' and age = '" + age + "' and gender = '" + gender + "' and phonenum = '" + phonenum + "' and email = '" + email + "'");
		int count = 0;
		
		while (rs.next()) {
			count = count + 1;
		}
		
		if(count>0)
		{
			return 1;
		}
		return 0;
		
	}
	
}
