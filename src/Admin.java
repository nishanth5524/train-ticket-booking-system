
import java.sql.Connection;
import java.util.Scanner;

public class Admin {

	public static void main(String[] args) throws Exception {

		System.out.println("-------------------------------");
		System.out.println("- Train ticket booking system -");
		System.out.println("-------------------------------\n");

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n[1] Insert Train");
			System.out.println("[2] Scheduling train ");
			System.out.println("[3] Cancel train");
			System.out.println("[4] Filter");
			System.out.println("[5] Travel Hi1story");
			System.out.println("[6] Exit\n");

			DBConnection cobj = new DBConnection();
			Connection con = cobj.DB();
			int n = sc.nextInt();

			if (n == 1) {
				InsertTrain obj = new InsertTrain();
			} else if (n == 2) {

				Scheduling obj = new Scheduling();

			} else if (n == 3) {

				CancelTrain obj = new CancelTrain();
				
			} else if (n == 4) {
				Filter obj = new Filter();

				System.out.println("\n[1] age\n[2] date\n[3]range");
				int m = sc.nextInt();

				if (m == 1)
					obj.age(sc, con);
				else if (m == 2)
					obj.date(sc, con);
				else if (m == 3)
					obj.range(sc, con);

			}

			else if (n == 5) {
				TravelHistory obj = new TravelHistory(con);
			}

			else if (n == 6) {
				System.out.println("Thank you :-)");
				System.exit(0);
			} else {
				System.out.println("Invalid input");
			}

		}

	}

}
