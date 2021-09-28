
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravelHistory {

	public TravelHistory() throws Exception {

		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();
		
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
				
				if (RegularExpression.alphabet(name)) {
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
				
				if (RegularExpression.phonenum(phonenum)) {
					flagphonenum = 0;
				}

				else {
					System.out.println("Invalid From Input\n");
				}
			}

		}

		int id = SqlQuery.getPassengerID(name, age, gender, phonenum, password, con);
		
		SqlQuery.getInfoWithID(id,con);

		con.commit();


	}
}
