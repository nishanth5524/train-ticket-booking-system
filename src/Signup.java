
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup {

	public Signup(Connection con, String date, String status, String trainname, int passengerscount,int tbill)
			throws SQLException {

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

		boolean count = SqlQuery.checkSignupDuplicate(phonenum, con);
		con.commit();

		if (count == true) {
			System.out.println("user already exists");
			System.out.println("Sign in");
			Signin obj = new Signin(con, date, status, trainname, passengerscount,tbill);
			System.exit(0);

		}

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
				System.out.println("\nInvalid age");
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

		int flagemail = 1;
		String email = null;

		while (flagemail == 1) {

			System.out.println("Email: ");

			email = sc.nextLine();

			if (email.equals("")) {
				System.out.println("Email Cannot Be Empty\n");
			} else {

				if (RegularExpression.email(email)) {
					flagemail = 0;
				}

				else {
					System.out.println("Invalid From Input\n");
				}
			}

		}

		while (flagpassword == 1) {

			System.out.println("Enter your password");

			password = sc.nextLine();

			if (password.equals("")) {
				System.out.println("password Cannot Be Empty\n");
			} else {
				System.out.println("Re-Enter your password");

				String repassword = sc.nextLine();

				if (password.equals(repassword)) {

					SqlQuery.InsertSignup(name, phonenum, age, gender, repassword, email, con);
					con.commit();
					PassengerDetails obj = new PassengerDetails(passengerscount, phonenum, trainname, date, status,
							email,tbill, con);
				//	Credits ob = new Credits(tbill, email, phonenum, con);
					flagpassword = 0;
					System.out.println("Your account is created successfully!");

				} else {
					System.out.println("Invalid");
				}
			}
		}

	}

}
