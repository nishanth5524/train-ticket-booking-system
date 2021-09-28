
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengersCount {

	public PassengersCount(String from, String to, String date, String trainname, String tno, Connection con)
			throws SQLException {

		Scanner sc = new Scanner(System.in);

		int passengerscount = 0;

		int flagpassengerscount = 1;

		System.out.println("Total Seats: " + SqlQuery.getTotalSeat(tno, date, con));
		con.commit();
		while (flagpassengerscount == 1) {
			System.out.println("\nEnter Passengers Count: ");

			try {
				passengerscount = sc.nextInt();

				if (passengerscount == 0) {
					System.out.println("Passengers Count Cannot Be Empty\n");
				}

				else {
					System.out.println("Passengers Count: " + passengerscount);
					flagpassengerscount = 0;

					CheckAvailabilityofSeats casobj = new CheckAvailabilityofSeats(from, to, date, con, passengerscount,
							trainname, tno);

				}

			} catch (Exception e) {

				System.out.println("\nInvalid Passenger count");
				System.out.println(e);
				// System.exit(0);
			}
		}

	}
}
