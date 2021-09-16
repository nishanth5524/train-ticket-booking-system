
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

		while (flagpnr == 1) {

			System.out.println("pnr: ");

			pnr = sc.nextLine();

			if (pnr.equals("")) {
				System.out.println("pnr Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[A-Za-z0-9]*$");
				Matcher m = p.matcher(pnr);

				if (m.find()) {
					
					Statement stmt = con.createStatement();
					ResultSet resultSet = stmt.executeQuery("select status from passengerboardingdetails where pnr = '" + pnr + "'");

					if (!resultSet.next()) {
						System.out.println("Ticket Not Found");
						System.exit(0);
					}
					else
					{
						CorW obj = new CorW(con,pnr);
						flagpnr = 0;
					}
					
					
				} else {
					System.out.println("Invalid PNR Input\n");
				}

			}

		}
	}
}
