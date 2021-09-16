
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Filter {

	public void age(Scanner sc, Connection con) throws Exception {
		System.out.println("Enter age: ");
		int age = sc.nextInt();

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(

				"select count(*) from passengerdetails where age = '" + age + "'");

		while (rs.next()) {
			System.out.println("Count " + rs.getInt(1));

		}
	}

	public void date(Scanner sc, Connection con) throws SQLException {
		
		sc.nextLine();
		System.out.println("Enter date:");
		String date = sc.nextLine();

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(

				"select count(*) from passengerboardingdetails where depdate = '" + date + "'");

		while (rs.next()) {
			System.out.println("Count " + rs.getInt(1));

		}
	}
	
	public void range(Scanner sc, Connection con) throws SQLException
	{
		sc.nextLine();
		System.out.println("Enter Starting range:");
		String r1 = sc.nextLine();
		System.out.println("Enter Ending range:");
		String r2 = sc.nextLine();

		Statement stmt = con.createStatement();

		ResultSet rs1 = stmt.executeQuery("select * from passengerboardingdetails where pid > '" + r1 + "' and pid < '"+r2+"'");
		while (rs1.next()) {

			System.out.println("PID: " + rs1.getString(1) + "\nName: " + rs1.getString(2) + "\nAge: "
					+ rs1.getString(3) + "\nGender: " + rs1.getString(4) + "\nPhone number: "
					+ rs1.getString(5) + "\nStatus: " + rs1.getString(6));

		}
		}
	}


