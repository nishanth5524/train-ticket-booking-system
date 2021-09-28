
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

				if (RegularExpression.num(tnum)) {
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

				if (RegularExpression.date(dated)) {
					flagdate = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();

		try {

			Statement stmt2 = con.createStatement();
			SqlQueryAdmin.updatepassengerboardingdetails(tnum, dated, con);
			SqlQueryAdmin.deleteboardingdetails(tnum, dated, con);
			con.commit();
			System.out.println("Done!");
		} catch (Exception ex) {
			System.out.println(ex);
			con.rollback();

		}
	}

}
