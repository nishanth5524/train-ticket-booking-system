
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertTrain {

	public InsertTrain() throws Exception {

		int flagtname = 1;
		String tname = null;
		Scanner sc = new Scanner(System.in);

		while (flagtname == 1) {

			System.out.println("Enter Train Name");

			tname = sc.nextLine();

			if (tname.equals("")) {
				System.out.println("Name Cannot Be Empty\n");
			} else {

				if (RegularExpression.alphawithspace(tname)) {
					flagtname = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		int flagtno = 1;
		String tno = null;

		while (flagtno == 1) {

			System.out.println("Enter Train Number: ");

			tno = sc.nextLine();

			if (tname.equals("")) {
				System.out.println("Number Cannot Be Empty\n");
			} else {

				if (RegularExpression.num(tno)) {
					flagtno = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		int flagfrom = 1;
		String from = null;

		while (flagfrom == 1) {

			System.out.println("Enter FROM: ");

			from = sc.nextLine();

			if (from.equals("")) {
				System.out.println("From Cannot Be Empty\n");
			} else {

				if (RegularExpression.alphabet(from)) {
					flagfrom = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		int flagto = 1;
		String to = null;

		while (flagto == 1) {

			System.out.println("Enter TO: ");

			to = sc.nextLine();

			if (to.equals("")) {
				System.out.println("TO Cannot Be Empty\n");
			} else {

				if (RegularExpression.alphabet(to)) {
					flagto = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();

		SqlQueryAdmin.Inserttrain(tname, tno, from, to, con);
		con.commit();
		
	}

}
