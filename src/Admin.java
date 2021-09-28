
import java.sql.Connection;
import java.util.Scanner;

public class Admin {

	Admin() throws Exception {

		Scanner sc = new Scanner(System.in);

		int flagusername = 1;
		String username = null;

		while (flagusername == 1) {

			System.out.println("Enter User Name");

			username = sc.nextLine();

			if (username.equals("")) {
				System.out.println("User Name Cannot Be Empty\n");
			} else {

				if (RegularExpression.alphabet(username)) {
					flagusername = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		int flagpassword = 1;
		String password = null;

		while (flagpassword == 1) {

			System.out.println("Enter password: ");

			password = sc.nextLine();

			if (password.equals("")) {
				System.out.println("Password Cannot Be Empty\n");
			} else {

				if (RegularExpression.num(password)) {
					flagpassword = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}
		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();
		String userdb = SqlQueryAdmin.getusername(con);
		String passdb = SqlQueryAdmin.getpassword(con);
		
		if(!(userdb.equals(username) && passdb.equals(password)))
		{
			System.out.println("Invalid user");
			System.exit(0);
		}
		
		System.out.println("-------------------------------");
		System.out.println("- Train ticket booking system -");
		System.out.println("-------------------------------\n");

		while (true) {

			System.out.println("\n[1] Insert Train");
			System.out.println("[2] Scheduling train ");
			System.out.println("[3] Cancel train");
			System.out.println("[4] Filter");
			System.out.println("[5] Travel History");
			System.out.println("[6] Exit\n");

			
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
				TravelHistory obj = new TravelHistory();
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
