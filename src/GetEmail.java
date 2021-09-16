
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetEmail {

	public String getemail(String phonenum,String password,Connection con) throws SQLException
	{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select email from signup where phonenum = '" + phonenum + "' and userpassword = '" + password + "'");
		String email = null;
		
		while(rs.next())
		{
			email = rs.getString(1);
		}
		return email;
	}
	
}
