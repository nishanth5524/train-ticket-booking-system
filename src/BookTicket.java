
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

				if (RegularExpression.alphabet(from)) {
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

				if (RegularExpression.alphabet(to)) {
					flagto = 0;
				}

				else {
					System.out.println("Invalid To Input\n");
				}

			}
		}

		String tno = SqlQuery.getTrainNumber(from, to, con);
		String trainname = SqlQuery.getTrainName(from, to, con);
		con.commit();

		while (flagdate == 1) {

			System.out.println("\nEnter date (yyyy-mm-dd) ");

			date = sc.nextLine();

			if (date.equals("")) {
				System.out.println("Date Cannot Be Empty\n");
			} else {

				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				Date date2 = new Date();

				if (RegularExpression.date(date)) {

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

			if (SqlQuery.checkAvailabilityofTrain(tno, date, con) == false) {
				con.commit();
				System.out.println("Train is not available");
			}

			else {

				if (tempcount == 0) {
					System.out.println("Train availability list\n");
				}

				tempcount++;
				System.out.println("Train name: " + trainname);

				PassengersCount pcobj = new PassengersCount(from, to, date, trainname, tno, con);
			}
		} catch (Exception ex) {
			con.commit();
			System.out.println("Train is not available" + ex);
		}

	}

}
