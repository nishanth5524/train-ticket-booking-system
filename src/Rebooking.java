
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rebooking {

	Rebooking() throws Exception {
		Scanner sc = new Scanner(System.in);

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();

		int flagpnr = 1;
		String pnr = null;
		String status = null;
		String pid = null;
		String date = null;
		String berth = null;

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

				try {
				
				boolean fl = SqlQuery.CheckUser(phonenum, password, con);
				
				if (fl == false) {
					System.out.println("Invalid");
					System.exit(0);
				}

				flagpassword = 0;

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
								status = SqlQuery.getStatusPassenger(pnr, con);
								berth = SqlQuery.getBerthPassenger(pnr, con);
								date = SqlQuery.getdepdatePassenger(pnr, con);
								
								flagpnr = 0;
							}
						}

						else {
							System.out.println("Invalid PNR Input\n");
						}
					}

				}

				if (status.equals("cancel") && berth.equals("WL")) {
					System.out.println("Rebooking not allowed");
					System.exit(0);
				}

				if (status.equals("cancel")) {

					pid = SqlQuery.getPIDPassenger(pnr, con);
					

					String tno = pid.substring(0, 5);

					int tseat = 0;
					int wseat = 0;

					tseat = SqlQuery.getTotalSeat(tno, date, con);
					wseat = SqlQuery.getWaitingSeat(tno, date, con);
					

					if (tseat == 0 && wseat == 0) {
						System.out.println("Seats are full");
						System.exit(0);
					}

					if (berth.equals("upper"))
						SqlQuery.UpdateUpperBerthminus(tno, date, con);
					else if (berth.equals("lower")) {

						SqlQuery.UpdateLowerBerthminus(tno, date, con);
					}

					int count = SqlQuery.getTotalSeat(tno, date, con);

					if (count > 0) {
						Statement stmt111 = con.createStatement();

						SqlQuery.UpdateStatusConfirm(pnr, con);

						SqlQuery.UpdateTotalSeatminus(tno, date, con);
						System.out.println("Ticket Rebooked status : confirm");
					}

					else {
						Statement stmt111 = con.createStatement();

						int countwaiting = SqlQuery.getWaitingSeat(tno, date, con);

						if (countwaiting > 0) {

							SqlQuery.UpdateStatusWaiting(pnr, con);
							SqlQuery.UpdateTotalSeatminus(tno, date, con);
							System.out.println("Ticket Rebooked status : waiting");
						}

						else {
							System.out.println("Inavailability of seats");
							System.exit(0);
						}
					}
				}
		
				con.commit();
				
				}catch(Exception ex)
				{
				System.out.println(ex);
				con.rollback();
				
				}
		}
		}

	}

}
