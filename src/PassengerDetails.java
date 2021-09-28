import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerDetails {

	static Queue<WaitingListQueue> q = new LinkedList<>();

	PassengerDetails(int passengerscount, String phonenum, String trainname, String depdate, String status,
			String email, int tbill, Connection con) throws SQLException {

		int flagname = 1;
		int flagage = 1;
		int flaggender = 1;
		int count = 1;
		String name = null;
		int age = 0;
		String gender = null;
		String tno = null;

		Scanner sc = new Scanner(System.in);

		tno = SqlQuery.getTrainNumberWithName(trainname, con);
		con.commit();

		for (int i = 1; i <= passengerscount; i++) {
			flagname = 1;
			flagage = 1;
			flaggender = 1;

			SqlQuery.Updatecount(tno, con);
			con.commit();

			while (flagname == 1) {

				System.out.println("Enter passenger " + i + " Name");

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
			String berth1 = obj1.berth(age, gender, tno, depdate, con);

			int tempcount = SqlQuery.getCount(tno, con);
			con.commit();
			String pid = tno;

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

			int result = 0;

			int amount = tbill / passengerscount;

			try {

				Credits ob1 = new Credits(amount, email, phonenum, con);
				duppassengerdetails ob = new duppassengerdetails();
				int temp = ob.check(name, age, gender, phonenum, email, con);

				if (temp == 0) {
					SqlQuery.InsertPassengerDetails(name, age, gender, phonenum, email, con);
				}

				int id = SqlQuery.getPassengerID(name, age, gender, phonenum, email, con);

				PNR obj = new PNR();
				String pnr = obj.generateRandomPNR(con);
				System.out.println("PNR: " + pnr);

				if (status.equals("confirm")) {
					result = SqlQuery.Insertpassengerboardingdetails(pnr, status, depdate, berth1, pid, id, con);
				} else if (status.equals("waiting")) {

					WaitingListQueue qobj = new WaitingListQueue(pnr, status, depdate, berth1, pid, id);
					q.add(qobj);
					SqlQuery.UpdateWaitingSeatminus(tno, depdate, con);
					// result = SqlQuery.Insertpassengerboardingdetails(pnr, status, depdate, "WL",
					// pid, id, con);

				}
				if (result == 1) {
					if (status.equals("confirm")) {
						// SqlQuery.UpdateTotalSeatminus(tno, depdate, con);

						if (berth1.equals("upper")) {

							SqlQuery.UpdateUpperBerthminus(tno, depdate, con);

						} else if (berth1.equals("lower")) {

							SqlQuery.UpdateLowerBerthminus(tno, depdate, con);
						}
					}
				}

//					else {
//						  SqlQuery.UpdateWaitingSeatminus(tno, depdate, con);
//					}

				SqlQuery.Updatecount(tno, con);

				con.commit();

				sc.nextLine();

			} catch (Exception ex) {
				for (int j = 1; j <= passengerscount; j++) {
					SqlQuery.UpdateTotalSeatplus(tno, depdate, con);
				}
				System.out.println(ex);
				con.rollback();

			}
		}

	}

}
