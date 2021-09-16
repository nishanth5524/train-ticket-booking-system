
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scheduling {

	public Scheduling() throws Exception {

		String tnum = null;
		
		String dated = null;
		String datea = null;
		String deptime = null;
		String arrtime = null;
		
		int tseat = 0;
		int wseat = 0;

		int flagtnum = 1;
		int flagdate = 1;
		int flagtseat = 1;
		int flagwseat = 1;
		int flagdeptime = 1;
		int flagarrtime = 1;

		Scanner sc = new Scanner(System.in);

		while (flagtnum == 1) {

			System.out.println("train number: ");

			tnum = sc.nextLine();

			if (tnum.equals("")) {
				System.out.println("Train num Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("\\d");
				Matcher m = p.matcher(tnum);

				if (m.find()) {
					flagtnum = 0;
				}

				else {
					System.out.println("Invalid Train Number Input\n");
				}
			}

		}

		while (flagtseat == 1) {
			System.out.println("\nTotal Seat: ");

			try {
				tseat = sc.nextInt();

				if (tseat == 0) {
					System.out.println("Total Seat Cannot Be Empty\n");
				}

				else {
					flagtseat = 0;

				}

			} catch (Exception e) {
				System.out.println("\nInvalid count");
				System.exit(0);
			}
		}

		while (flagwseat == 1) {
			System.out.println("\nWaiting list Seat: ");

			try {
				wseat = sc.nextInt();

				if (wseat == 0) {
					System.out.println("Waiting list Seat Cannot Be Empty\n");
				}

				else {
					flagwseat = 0;

				}

			} catch (Exception e) {
				System.out.println("\nInvalid count");
				System.exit(0);
			}
		}

		sc.nextLine();
				
		while (flagdate == 1) {

			System.out.println("Dep Date: ");

			dated = sc.nextLine();

			if (dated.equals("")) {
				System.out.println("date Cannot Be Empty\n");
			} else {

				String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(dated);

				if (m.find()) {
					flagdate = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		int flagdatea = 1;
		while (flagdatea == 1) {

			System.out.println("Arr Date: ");

			datea = sc.nextLine();

			if (datea.equals("")) {
				System.out.println("date Cannot Be Empty\n");
			} else {

				String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(datea);

				if (m.find()) {
					flagdatea = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		while (flagdeptime == 1) {

			System.out.println("Dep time: ");

			deptime = sc.nextLine();

			if (deptime.equals("")) {
				System.out.println("Dep time Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");
				Matcher m = p.matcher(deptime);

				if (m.find()) {
					flagdeptime = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		while (flagarrtime == 1) {

			System.out.println("Arr time: ");

			arrtime = sc.nextLine();

			if (arrtime.equals("")) {
				System.out.println("Arr time Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");
				Matcher m = p.matcher(arrtime);

				if (m.find()) {
					flagarrtime = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		int flagupperberth = 1;
		String upperberth = null;
		
		while (flagupperberth == 1) {

			System.out.println("Upper berth: ");

			upperberth = sc.nextLine();

			if (upperberth.equals("")) {
				System.out.println("Upper berth Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[0-9]*$");
				Matcher m = p.matcher(upperberth);

				if (m.find()) {
					flagupperberth = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}
		
		int flaglowerberth = 1;
		String lowerberth = null;
		
		while (flaglowerberth == 1) {

			System.out.println("Lower berth: ");

			lowerberth = sc.nextLine();

			if (lowerberth.equals("")) {
				System.out.println("lower berth Cannot Be Empty\n");
			} else {
				Pattern p = Pattern.compile("^[0-9]*$");
				Matcher m = p.matcher(lowerberth);

				if (m.find()) {
					flaglowerberth = 0;
				}

				else {
					System.out.println("Invalid Input\n");
				}
			}

		}

		
		DBConnection cobj = new DBConnection();
		Connection con = cobj.DB();

		Statement stmt = con.createStatement();

		String sql1 = "insert into boardingdetails values('" + tnum + "','" + tseat + "','" + wseat + "','" + dated
				+ "','" + datea + "','" + deptime + "','" + arrtime + "','"+lowerberth+"','"+upperberth+"');";

		stmt.executeUpdate(sql1);

	}

}
