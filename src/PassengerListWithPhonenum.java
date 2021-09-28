import java.sql.Connection;
import java.util.Scanner;

public class PassengerListWithPhonenum {

	public PassengerListWithPhonenum() throws Exception {

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();
		Scanner sc = new Scanner(System.in);

		int flagphonenum = 1;
		int flagpassword = 1;
		String phonenum = null;
		String password = null;

		System.out.println("Sign in\n");

		while (flagphonenum == 1) {
			System.out.println("Enter you Phone number");

			phonenum = sc.nextLine();

			if (phonenum.equals("")) {
				System.out.println("Phone num Cannot Be Empty\n");
			} else {

				if (RegularExpression.phonenum(phonenum)) {
					flagphonenum = 0;
				}

				else {
					System.out.println("Invalid from input\n");
				}
			}
		}

		while (flagpassword == 1) {

			System.out.println("Enter your password");

			password = sc.nextLine();

			if (password.equals("")) {
				System.out.println("password Cannot Be Empty\n");
			} else {

				boolean fl = SqlQuery.CheckUser(phonenum, password, con);
				con.commit();

				if (fl == false) {
					System.out.println("Invalid");
					System.exit(0);
				}
				flagpassword = 0;

				SqlQuery.getPassengerListWithPhonenum(phonenum, con);
				con.commit();

			}
		}

	}

}
