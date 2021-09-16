
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravelHistory {

	public TravelHistory(Connection con) throws SQLException {

		String name = null;
		String phonenum = null;
		int age = 0;
		String gender = null;
		String password = null;

		int flagname = 1;
		int flagphonenum = 1;
		int flagage = 1;
		int flaggender = 1;
		int flagpassword = 1;

		Scanner sc = new Scanner(System.in);

		while (flagname == 1) {

			System.out.println("Name: ");

			name = sc.nextLine();

			if (name.equals("")) {
				System.out.println("Name Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[a-zA-Z]*$");
				Matcher m = p.matcher(name);

				if (m.find()) {
					flagname = 0;
				}

				else {
					System.out.println("Invalid From Input\n");
				}
			}

		}

		while (flagage == 1) {
			System.out.println("\nEnter Age: ");

			try {
				age = sc.nextInt();

				if (age == 0) {
					System.out.println("Age Cannot Be Empty\n");
				}

				else {
					flagage = 0;

				}

			} catch (Exception e) {
				System.out.println("\nInvalid count");
				System.exit(0);
			}
		}

		while (flaggender == 1) {

			System.out.println("Choose your gender\n\n[1] Male\n[2] Female\n[3] Prefer not to say");

			try {

				int cgender = sc.nextInt();

				if (cgender == 0) {
					System.out.println("Gender field Cannot Be Empty\n");
				}

				else {
					flagage = 0;

					if (cgender == 1) {
						gender = "male";
						flaggender = 0;
						break;
					}

					else if (cgender == 2) {
						gender = "female";
						flaggender = 0;
					}

					else if (cgender == 3) {
						gender = "Prefer not to say";
						flaggender = 0;
					} else {
						System.out.println("Invaled Selection");
					}
				}
			} catch (Exception e) {
				System.out.println("\nInvalid input");
				System.exit(0);
			}
		}

		sc.nextLine();
		
		while (flagphonenum == 1) {

			System.out.println("Phone number: ");

			phonenum = sc.nextLine();

			if (phonenum.equals("")) {
				System.out.println("Phone num Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("(0|91)?[6-9][0-9]{9}");
				Matcher m = p.matcher(phonenum);

				if (m.find()) {
					flagphonenum = 0;
				}

				else {
					System.out.println("Invalid From Input\n");
				}
			}

		}

		int id = 0;
		Statement stmt1 = con.createStatement();
		ResultSet rs1 = stmt1.executeQuery("select id from passengerdetails where name = '" + name + "' and age ='"
				+ age + "' and gender ='" + gender + "' and phonenum ='" + phonenum + "'");

		while (rs1.next()) {
			id = rs1.getInt(1);
			System.out.println(id);
		}

		
		Statement stmt11 = con.createStatement();
		ResultSet rs11 = stmt11.executeQuery("select pnr, status, depdate,berth, pid from passengerboardingdetails where id = '" + id + "'");

		while (rs11.next()) {
			System.out.println("pnr: " + rs11.getString(1) + "\nstatus: " + rs11.getString(2) + "\ndepdate: "
					+ rs11.getString(3) + "\nberth: " + rs11.getString(4) + "\npid: " + rs11.getString(5));

			System.out.println("\n");
		}
		
		con.close();

	}
}
