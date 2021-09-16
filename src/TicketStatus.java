
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

		int flagpnr = 1;
		String pnr = null;

		while (flagpnr == 1) {

			System.out.println("PNR: ");

			pnr = sc.nextLine();

			if (pnr.equals("")) {
				System.out.println("PNR Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
				Matcher m = p.matcher(pnr);

				if (m.find()) {

//					String pnrtrainname = pnr.substring(0,5);
//
//					Statement stmt = con.createStatement();
//					ResultSet rs = stmt.executeQuery("select tname from traindetails where tno = '" + pnrtrainname + "'");
//					while (rs.next()) {
//
//						System.out.println("Train name: " + rs.getString(1));
//
//					}

					Statement stmt1 = con.createStatement();
					ResultSet rs1 = stmt1.executeQuery("select status, berth, depdate from passengerboardingdetails where pnr = '" + pnr + "'");
					while (rs1.next()) {

						System.out.println("Status: " + rs1.getString(1) + "\nberth: " + rs1.getString(2) + "\ndepdate: "
								+ rs1.getString(3));

					}

					flagpnr = 0;
				}

				else {
					System.out.println("Invalid PNR Input\n");
				}
			}

		}

	}
}
