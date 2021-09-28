
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class duppassengerdetails {

	public int check(String name, int age, String gender, String phonenum, String email, Connection con)
			throws SQLException {

		int count = SqlQuery.CheckDupPassengerDetails(name, age, gender, phonenum, email, con);

		if (count > 0) {
			return 1;
		}
		return 0;

	}

}
