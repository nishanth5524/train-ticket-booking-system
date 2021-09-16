
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BookTicket {

	public BookTicket() throws Exception {

		String from = null;
		String to = null;
		String date = null;
		String trainname = null;
		String tno = null;

		int flagfrom = 1;
		int flagto = 1;
		int flagdate = 1;

		Scanner sc = new Scanner(System.in);

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();

		while (flagfrom == 1) {

			System.out.println("From: ");

			from = sc.nextLine();

			if (from.equals("")) {
				System.out.println("From Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[a-zA-Z]*$");
				Matcher m = p.matcher(from);

				if (m.find()) {
					flagfrom = 0;
				}

				else {
					System.out.println("Invalid From Input\n");
				}
			}

		}

		while (flagto == 1) {
			System.out.println("\nTo: ");

			to = sc.nextLine();

			if (to.equals("")) {
				System.out.println("To Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[a-zA-Z]*$");
				Matcher m = p.matcher(to);

				if (m.find()) {
					flagto = 0;
				}

				else {
					System.out.println("Invalid To Input\n");
				}

			}
		}

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select tno, tname from traindetails where froml = '" + from + "' and tol ='" + to + "'");
			while (rs.next()) {
				tno = rs.getString(1);
				trainname = rs.getString(2);
			}
		} catch (Exception ex) {

			System.out.println(ex);
		}

		while (flagdate == 1) {

			System.out.println("\nEnter date (yyyy-mm-dd) ");

			date = sc.nextLine();

			if (date.equals("")) {
				System.out.println("Date Cannot Be Empty\n");
			} else {
				String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

				Pattern p1 = Pattern.compile(regex);

				Matcher m = p1.matcher(date);

				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				Date date2 = new Date();

				if (m.find()) {

					if (date1.after(date2) == true)
						flagdate = 0;

					else
						System.out.println("Invalid Date Input\n");

				}

				else {
					System.out.println("Invalid Date Format\n");
				}
			}
		}

		try {
			int tempcount = 0;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select * from boardingdetails where tno = '" + tno + "' and depdate = '" + date + "'");

			if (rs.next() == false) {
				System.out.println("Train is not available");
			}

			else {

				do {

					if (tempcount == 0) {
						System.out.println("Train availability list\n");
					}

					tempcount++;
					System.out.println("Train name: " + trainname);

				} while (rs.next());
				 PassengersCount pcobj = new PassengersCount(from, to, date, trainname, tno, con);
			}
		} catch (Exception ex) {
			System.out.println("Train is not available" + ex);
		}

	}

}
