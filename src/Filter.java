
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Filter {

	public void age(Scanner sc, Connection con) throws Exception {

		int flagage = 1;
		int age = 0;

		while (flagage == 1) {
			System.out.println("Enter Age");

			try {
				age = sc.nextInt();

				if (age == 0) {
					System.out.println("Age Cannot Be Empty\n");
				}

				else {
					flagage = 0;

				}

			} catch (Exception e) {
				System.out.println("\nInvalid age");
				System.exit(0);
			}
		}

		SqlQueryAdmin.getAge(age, con);
		con.commit();
	}

	public void date(Scanner sc, Connection con) throws SQLException {

		sc.nextLine();

		int flagdate = 1;
		String date = null;
		while (flagdate == 1) {

			System.out.println("Dep Date: ");

			date = sc.nextLine();

			if (date.equals("")) {
				System.out.println("date Cannot Be Empty\n");
			} else {

				if (RegularExpression.date(date)) {
					flagdate = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}
		
		SqlQueryAdmin.getDate(date, con);
		con.commit();

	}

	public void range(Scanner sc, Connection con) throws SQLException {
		sc.nextLine();

		String r1 = null;
		int flagr1 = 1;

		while (flagr1 == 1) {

			System.out.println("r1: ");

			r1 = sc.nextLine();

			if (r1.equals("")) {
				System.out.println("r1 Cannot Be Empty\n");
			} else {

				if (RegularExpression.num(r1)) {
					flagr1 = 0;
				}

				else {
					System.out.println("Invalid r1 Input\n");
				}
			}

		}

		String r2 = null;
		int flagr2 = 1;

		while (flagr2 == 1) {

			System.out.println("r2: ");

			r2 = sc.nextLine();

			if (r2.equals("")) {
				System.out.println("r2 Cannot Be Empty\n");
			} else {

				if (RegularExpression.num(r2)) {
					flagr2 = 0;
				}

				else {
					System.out.println("Invalid r2 Input\n");
				}
			}

		}

		SqlQueryAdmin.geRange(r1, r2, con);
		con.commit();
	}
}
