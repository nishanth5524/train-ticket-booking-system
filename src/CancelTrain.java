
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CancelTrain {

	public CancelTrain() throws Exception {

		int flagtnum = 1;
		String tnum = null;
		int flagdate = 1;
		String dated = null;

		Scanner sc = new Scanner(System.in);

		while (flagtnum == 1) {

			System.out.println("train number: ");

			tnum = sc.nextLine();

			if (tnum.equals("")) {
				System.out.println("Train num Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("\\d");
				Matcher m = p.matcher(tnum);

				if (m.find()) {
					flagtnum = 0;
				}

				else {
					System.out.println("Invalid Train Number Input\n");
				}
			}

		}

		while (flagdate == 1) {

			System.out.println("Dep Date: ");

			dated = sc.nextLine();

			if (dated.equals("")) {
				System.out.println("date Cannot Be Empty\n");
			} else {

				String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(dated);

				if (m.find()) {
					flagdate = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}
		
		
		
		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();
		
		try
		{
		con.setAutoCommit(false);	
		
		Statement stmt2 = con.createStatement();
		stmt2.executeUpdate("update passengerboardingdetails set status = 'cancel' where pid LIKE '"+tnum+"%'  and depdate ='" + dated + "'");
		stmt2.executeUpdate("delete from boardingdetails where tno = '"+tnum+"' and depdate = '" + dated + "'"); 
		con.commit();
		System.out.println("Done!");
		con.setAutoCommit(true);
		con.close();
		}catch(Exception ex)
		{
			System.out.println(ex);
			con.rollback();
			con.setAutoCommit(true);
			con.close();
			
		}
	}

}
