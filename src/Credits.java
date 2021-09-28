import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Credits {

	public Credits(int tbill, String email, String phonenum, Connection con) throws SQLException {

		System.out.println("Checking Credits.........");
		System.out.println("Checking may delay if this credits being accessed by another connection!!!");

		Scanner sc = new Scanner(System.in);
		try {

			int credits = SqlQuery.getCredits(phonenum, con);

			if (tbill <= credits) {
				System.out.println("Credits :" + credits);
				System.out.println("Bill: " + tbill);
				System.out.println("Credits Available");
				int flag = 1;

				while (flag == 1) {
					System.out.println("Proceed with credits to pay: y(or)n");

					String choice = sc.nextLine();
					if (choice.equals("y") || choice.equals("Y")) {

						SqlQuery.updateCreditsminus(email, phonenum, tbill, con);
						System.out.println("Payment Done!");
						flag = 0;
					}

					else if (choice.equals("n") || choice.equals("N")) {
						System.out.println("proceeding to payment gate");
						System.out.println("Done!");
						flag = 0;
					} else {
						System.out.println("Invalid option");
					}
				}

			}

			else {
				System.out.println("Credits Not Available proceeding to payment gate");
			}

			con.commit();
		} catch (Exception ex) {

			con.rollback();
			System.out.println(ex);
		}

	}

}
