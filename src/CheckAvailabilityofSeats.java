
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class CheckAvailabilityofSeats {

	public CheckAvailabilityofSeats(String from, String to, String date, Connection con, int passengerscount,
			String trainname,String tno) throws SQLException {

		String status = null;
		int seatcount = 0;
		int wseatcount = 0;
		int flagseatcount = 1;
		int flagwaitingcount = 1;
		int flaginorup = 1;

		Scanner sc = new Scanner(System.in);
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(
				"select tseat from boardingdetails where tno = '" + tno + "' and depdate ='" + date + "'");

		while (flagseatcount == 1) {

			while (rs.next()) {
				//System.out.println("Total seats available: " + rs.getInt(1));
				seatcount = rs.getInt(1);
			}

			if (seatcount >= passengerscount) {
				status = "confirm";
				flagseatcount = 0;
			}

			else{

				try {
					while (flagwaitingcount == 1) {
						
						Statement stmt1 = con.createStatement(); 
						
						System.out.println("\nEnter [1] to view availability of Waiting list seat count");

						int choice = sc.nextInt();

						if (choice == 1) {
							
						   flagwaitingcount = 0;
							ResultSet rs1 = stmt1.executeQuery("select wseat from boardingdetails where tno = '" + tno + "' and depdate ='" + date + "'");

							while (rs1.next()) {
								System.out.println("Total Waiting list seats available: " + rs1.getInt(1));
								wseatcount = rs1.getInt(1);
							}
							
							System.out.println("Enter Total number of Waiting list seat count");
							int passengerwseatcount = sc.nextInt();
							
							if (wseatcount >= passengerwseatcount) {
								status = "waiting";
								flagseatcount = 0;
								passengerscount = passengerwseatcount;
								
							}

							else {
								System.out.println("\nInavailability of seats");
							}
						}
						
						else {
							System.out.println("Invalid Input:");
						}
					}

				} catch (Exception e) {
					System.out.println(e);
				}

			}
		}
		
		int tbill = 500 * passengerscount;
		System.out.println("Train Name: " + trainname);
		System.out.println("Total Amount: " + tbill + "₹");
		
		while(flaginorup == 1)
		{
			System.out.println("Signin or Signup to book your ticket\n\nEnter your choice\n[1] Sign_up\n[2] Sign_in");

			int schoice = sc.nextInt();

			if (schoice == 1) {
				Signup obj = new Signup(con,date,status,trainname,passengerscount);
				flaginorup = 0;
			}

			else if (schoice == 2) {
				Signin obj = new Signin(con,date,status,trainname,passengerscount);
				flaginorup = 0;
			}

			else {

				System.out.println("Invalid Selection");

			}
		}
	}

}
