
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketStatus {
	public TicketStatus() throws Exception {

		Scanner sc = new Scanner(System.in);

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();

		int flagphonenum = 1;
		int flagpassword = 1;
		String phonenum = null;
		String password = null;

		System.out.println("Sign in\n");

		while (flagphonenum == 1) {
			System.out.println("Enter you Phone number");

			phonenum = sc.nextLine();

			if (phonenum.equals("")) {
				System.out.println("Phone num Cannot Be Empty\n");
			} else {

				if (RegularExpression.phonenum(phonenum)) {
					flagphonenum = 0;
				}

				else {
					System.out.println("Invalid from input\n");
				}
			}
		}

		while (flagpassword == 1) {

			System.out.println("Enter your password");

			password = sc.nextLine();

			if (password.equals("")) {
				System.out.println("password Cannot Be Empty\n");
			} else {
				flagpassword = 0;
				int flagpnr = 1;
				String pnr = null;

				boolean fl = SqlQuery.CheckUser(phonenum, password, con);
				con.commit();

				if (fl == false) {
					System.out.println("Invalid");
					System.exit(0);
				}

				while (flagpnr == 1) {

					System.out.println("PNR: ");

					pnr = sc.nextLine();

					if (pnr.equals("")) {
						System.out.println("PNR Cannot Be Empty\n");
					} else {

						if (RegularExpression.alphanum(pnr)) {

							boolean result = CheckPNRwithPhonenum.CheckPNR(pnr, phonenum, con);

							if (result == false) {
								System.out.println("Invalid");
							} else {

								String status = SqlQuery.getStatusPassenger(pnr, con);
								String berth = SqlQuery.getBerthPassenger(pnr, con);
								String depdate = SqlQuery.getdepdatePassenger(pnr, con);
								con.commit();
								System.out.println("Status: " + status + "\nberth: " + berth + "\ndepdate: " + depdate);
								flagpnr = 0;
							}
						} else {
							System.out.println("Invalid PNR Input\n");
						}
					}

				}

			}

		}
	}
}
