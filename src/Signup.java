
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Signup {

	public Signup(Connection con,String date,String status,String trainname,int passengerscount) throws SQLException {

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
				Pattern p = Pattern.compile("(0|91)?[6-9][0-9]{9}");
				Matcher m = p.matcher(phonenum);

				if (m.find()) {
					flagphonenum = 0;
				}

				else {
					System.out.println("Invalid From Input\n");
				}
			}

		}
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select * from signup where phonenum = '" + phonenum + "'");
		int count = 0;
		
		while (rs.next()) {
			count = count + 1;
		}
		
		if(count>0)
		{
			System.out.println("user already exists");
			System.out.println("Sign in");
			Signin obj = new Signin(con,date,status,trainname,passengerscount);
			System.exit(0);
			
		}

		
		while (flagname == 1) {

			System.out.println("Name: ");

			name = sc.nextLine();

			if (name.equals("")) {
				System.out.println("Name Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[a-zA-Z]*$");
				Matcher m = p.matcher(name);

				if (m.find()) {
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
		
		int flagemail = 1;
		String email = null;
		
		while (flagemail == 1) {

			System.out.println("Email: ");

			email = sc.nextLine();

			if (email.equals("")) {
				System.out.println("Email Cannot Be Empty\n");
			} else {
				String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(email);

				if (m.find()) {
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
					System.out.println("Your account is created successfully!");
					
					Statement stmt1 = con.createStatement();
					stmt1.executeUpdate("insert into signup values('" + name + "','" + phonenum + "','" + age + "','" + gender
							+ "','" + password + "','"+email+"')");
					
					PassengerDetails obj = new PassengerDetails(passengerscount, phonenum, trainname, date, status,email, con);
					flagpassword = 0;
					

				} else {
					System.out.println("Invalid");
				}
			}
		}
		
	

	}

}
