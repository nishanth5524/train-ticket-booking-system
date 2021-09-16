
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

		while (flagpnr == 1) {

			System.out.println("PNR: ");

			pnr = sc.nextLine();

			if (pnr.equals("")) {
				System.out.println("PNR Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
				Matcher m = p.matcher(pnr);

				if (m.find()) {

					Statement stmt1 = con.createStatement();
					ResultSet rs1 = stmt1.executeQuery(
							"select status, berth, depdate from passengerboardingdetails where pnr = '" + pnr + "'");
					while (rs1.next()) {
						status = rs1.getString(1);
						System.out.println("Status: " + rs1.getString(1) + "\nberth: " + rs1.getString(2)
								+ "\ndepdate: " + rs1.getString(3));
						berth = rs1.getString(2);
						date = rs1.getString(3);
					}

					flagpnr = 0;
				}

				else {
					System.out.println("Invalid PNR Input\n");
				}
			}

		}

		if (status.equals("cancel")) {
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("select pid from passengerboardingdetails where pnr = '" + pnr + "'");
			while (rs1.next()) {
				pid = rs1.getString(1);

			}

			String tno = pid.substring(0, 5);

			int tseat = 0;
			int wseat = 0;
			
			Statement stmt = con.createStatement();
			ResultSet re = stmt.executeQuery(
					"select tseat, wseat from boardingdetails where tno='" + tno + "' and depdate ='" + date + "'");

			while (re.next()) {
				tseat = re.getInt(1);
				wseat = re.getInt(2);
			}
			
			if(tseat == 0 && wseat == 0)
			{
				System.out.println("Seats are full");
				System.exit(0);
			}

			if (berth.equals("upper"))
				stmt1.executeUpdate("update boardingdetails set upperberth = upperberth - 1 where tno='" + tno
						+ "' and depdate ='" + date + "'");
			else if (berth.equals("lower")) {
				stmt1.executeUpdate("update boardingdetails set lowerberth = lowerberth - 1 where tno='" + tno
						+ "' and depdate ='" + date + "'");

			}

			Statement stmt11 = con.createStatement();
			ResultSet rs11 = stmt11.executeQuery("select tseat from boardingdetails where tno = '" + tno + "'");
			int count = 0;
			while (rs11.next()) {
				count = rs11.getInt(1);

			}

			if (count > 0) {
				Statement stmt111 = con.createStatement();
				String sql1 = "update passengerboardingdetails set status = 'confirm' where pnr = '" + pnr + "'";
				stmt111.executeUpdate(sql1);
				String sql2 = "update boardingdetails set tseat = tseat - 1 where tno = '" + tno + "' and depdate = '"
						+ date + "'";
				stmt111.executeUpdate(sql2);
				System.out.println("Ticket Rebooked status : confirm");
			}

			else {
				Statement stmt111 = con.createStatement();
				ResultSet rs111 = stmt111.executeQuery("select wseat from boardingdetails where tno = '" + tno + "'");
				int count1 = 0;
				while (rs111.next()) {
					count1 = rs111.getInt(1);

				}

				if (count1 > 0) {
					Statement stmt1111 = con.createStatement();
					String sql1 = "update passengerboardingdetails set status = 'waiting' where pnr = '" + pnr + "'";
					stmt1111.executeUpdate(sql1);
					String sql2 = "update boardingdetails set tseat = tseat - 1 where tno = '" + tno
							+ "' and depdate = '" + date + "'";
					stmt111.executeUpdate(sql2);

					System.out.println("Ticket Rebooked status : waiting");
				}

				else {
					System.out.println("Inavailability of seats");
					System.exit(0);
				}
			}
		}

	}

}
