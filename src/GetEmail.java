
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetEmail {

	public String getemail(String phonenum,String password,Connection con) throws SQLException
	{
		String email = SqlQuery.getEmail(phonenum, password, con);
		con.commit();
		return email;
	}
	
}
