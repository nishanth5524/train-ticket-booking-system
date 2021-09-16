
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerDetails {

	PassengerDetails(int passengerscount, String phonenum, String trainname, String depdate, String status,String email, Connection con)
			throws SQLException {

		int flagname = 1;
		int flagage = 1;
		int flaggender = 1;
		int count = 1;
		String name = null;
		int age = 0;
		String gender = null;
		String tid = null;
		
		Scanner sc = new Scanner(System.in);

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("select tno from traindetails where tname = '" + trainname + "'");

		while (rs.next()) {
			tid = rs.getString(1);

		}

			
		
		for (int i = 1; i <= passengerscount; i++) {
			flagname = 1;
			flagage = 1;
			flaggender = 1;

			while (flagname == 1) {

				System.out.println("Enter passenger " + i + " Name");

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
				System.out.println("Enter passenger " + i + " Age");

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

			Berth obj1 = new Berth();
			String berth1 = obj1.berth(age, gender,tid, depdate, con);
			
			int tempcount = 0;
			String pid = tid;

			Statement stmt2 = con.createStatement();
			ResultSet rs1 = stmt2.executeQuery("select count from tidcount where tno = '" + tid + "'");

			while (rs1.next()) {
				tempcount = rs1.getInt(1);
			}

			if (tempcount > 0 && tempcount < 10) {
				pid = pid + 0;
				pid = pid + 0;
				pid = pid + 0;
				pid = pid + 0;
				pid = pid + tempcount;
			}

			else if (tempcount > 9 && tempcount < 100) {
				pid = pid + 0;
				pid = pid + 0;
				pid = pid + 0;
				pid = pid + tempcount;

			}

			else if (tempcount > 99 && tempcount < 1000) {
				pid = pid + 0;
				pid = pid + 0;
				pid = pid + tempcount;
			}

			else if (tempcount > 999 && tempcount < 10000) {
				pid = pid + 0;
				pid = pid + tempcount;
			}

			else if (tempcount > 9999 && tempcount < 100000) {
				pid = pid + tempcount;
			}

			

			int rstt = 0;
			
			try {
				
				con.setAutoCommit(false);
				
				duppassengerdetails ob = new duppassengerdetails();
				int temp = ob.check(name, age, gender, phonenum, email, con);
				 
				if(temp ==0)
				{
				String sql = "insert into passengerdetails (name, age, gender, phonenum, email) values('" + name + "','" + age + "','" + gender + "','" + phonenum + "','" + email + "');";
				stmt.executeUpdate(sql);
				}
				
				ResultSet result = stmt.executeQuery("select id from passengerdetails where name = '" + name + "'and age = '" + age + "'and gender = '" + gender + "'and phonenum = '" + phonenum + "'");
				
				int id = 0;
				while(result.next())
				{
					id = result.getInt(1);
				}
				
				PNR obj = new PNR();
				String pnr = obj.generateRandomPNR();
				System.out.println("PNR: " + pnr);
				String sq = "insert into passengerboardingdetails values('" + pnr + "','" + status + "','" + depdate + "','" + berth1
					+ "','" + pid + "','" + id + "');";

				rstt = stmt.executeUpdate(sq);
				con.commit();
				con.setAutoCommit(true);

			} catch (Exception ex) {
				System.out.println(ex);
				con.rollback();
				con.setAutoCommit(true);
			}

			try{

				con.setAutoCommit(false);
				
				if (rstt == 1) {
				String sql5 = "update tidcount set count = count + 1 where tno = '" + tid + "'";

				if (status == "confirm") {
					String sql1 = "update boardingdetails set tseat = tseat - 1 where tno = '" + tid + "' and depdate = '"+depdate+"'";
					stmt.executeUpdate(sql1);
					
					if(berth1.equals("upper"))
					{
						stmt.executeUpdate("update boardingdetails set upperberth = upperberth - 1 where tno = '" + tid + "' and depdate = '"+depdate+"'");
					}
					else if(berth1.equals("lower"))
					{
						stmt.executeUpdate("update boardingdetails set lowerberth = lowerberth - 1 where tno = '" + tid + "' and depdate = '"+depdate+"'");
					}
				}

				else {
					String sql2 = "update boardingdetails set wseat = wseat - 1 where tno = '" + tid + "' and depdate = '"+depdate+"'";
					stmt.executeUpdate(sql2);
				}

				stmt.executeUpdate(sql5);
				
				

				con.commit();
				con.setAutoCommit(true);
				sc.nextLine();

			}
			
		}catch(Exception ex)
		{
			System.out.println(ex);
			con.rollback();
			con.setAutoCommit(true);
		}
	}
		con.close();
	}

}
