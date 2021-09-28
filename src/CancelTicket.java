
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CancelTicket {

	public CancelTicket() throws Exception {

		Scanner sc = new Scanner(System.in);

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();
		int flagpnr = 1;
		String pnr = null;

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

				boolean fl = SqlQuery.CheckUser(phonenum, password, con);
				con.commit();

				if (fl == false) {
					System.out.println("Invalid");
					System.exit(0);
				}

				flagpassword = 0;
				while (flagpnr == 1) {

					System.out.println("pnr: ");

					pnr = sc.nextLine();

					if (pnr.equals("")) {
						System.out.println("pnr Cannot Be Empty\n");
					} else {

						if (RegularExpression.alphanum(pnr)) {

							String temp = null;
							temp = SqlQuery.getStatusPassenger(pnr, con);
							con.commit();

							if (temp == null) {
								System.out.println("Ticket Not Found");
								System.exit(0);
							} else {
								boolean result = CheckPNRwithPhonenum.CheckPNR(pnr, phonenum, con);

								if (result == false) {
									System.out.println("Invalid");
								} else {
									CorW obj = new CorW(con, pnr);
									flagpnr = 0;
								}
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
