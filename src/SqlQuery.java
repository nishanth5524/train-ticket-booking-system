import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQuery {

	public static String getTrainName(String from, String to, Connection con) throws SQLException {

		String trainname = null;
		PreparedStatement stmt = con.prepareStatement("select tname from traindetails where froml = ? and tol = ?");

		stmt.setString(1, from);
		stmt.setString(2, to);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			trainname = rs.getString(1);
		}

		return trainname;

	}

	public static String getTrainNumber(String from, String to, Connection con) throws SQLException {

		String trainnum = null;
		PreparedStatement stmt = con.prepareStatement("select tno from traindetails where froml = ? and tol = ?");

		stmt.setString(1, from);
		stmt.setString(2, to);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			trainnum = rs.getString(1);
		}

		rs.close();
		return trainnum;

	}

	public static boolean checkAvailabilityofTrain(String tno, String date, Connection con) throws SQLException {

		PreparedStatement stmt = con.prepareStatement("select * from boardingdetails where tno = ? and depdate = ? ");

		stmt.setString(1, tno);
		stmt.setString(2, date);

		ResultSet rs = stmt.executeQuery();

		if (rs.next() == false)
			return false;

		rs.close();
		return true;

	}

	public static int getTotalSeat(String tno, String date, Connection con) throws SQLException {

		int totalseat = 0;

		PreparedStatement stmt = con
				.prepareStatement("select tseat from boardingdetails where tno = ? and depdate = ? ");

		stmt.setString(1, tno);
		stmt.setString(2, date);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			totalseat = rs.getInt(1);
		}

		return totalseat;

	}

	public static int getWaitingSeat(String tno, String date, Connection con) throws SQLException {

		int waitingseat = 0;
		PreparedStatement stmt = con
				.prepareStatement("select wseat from boardingdetails where tno = ? and depdate = ?");

		stmt.setString(1, tno);
		stmt.setString(2, date);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			waitingseat = rs.getInt(1);
		}

		return waitingseat;

	}

	public static boolean checkSignupDuplicate(String phonenum, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select * from signup where phonenum = ?");

		stmt.setString(1, phonenum);

		ResultSet rs = stmt.executeQuery();

		int count = 0;

		while (rs.next()) {
			count = count + 1;
		}

		if (count == 0)
			return false;

		return true;
	}

	public static boolean checkpnrDuplicate(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select * from passengerboardingdetails where pnr = ?");

		stmt.setString(1, pnr);

		ResultSet rs = stmt.executeQuery();

		int count = 0;

		while (rs.next()) {
			count = count + 1;
		}

		if (count == 0)
			return false;

		return true;
	}

	public static int CheckSignin(String phonenum, String password, Connection con) throws SQLException {

		PreparedStatement stmt = con.prepareStatement("select * from signup where phonenum = ? and userpassword = ?");

		stmt.setString(1, phonenum);
		stmt.setString(2, password);

		ResultSet rs = stmt.executeQuery();

		int count = 0;
		while (rs.next()) {
			count = count + 1;
		}

		return count;

	}

	public static String getTrainNumberWithName(String trainname, Connection con) throws SQLException {
		String tno = null;

		PreparedStatement stmt = con.prepareStatement("select tno from traindetails where tname = ?");

		stmt.setString(1, trainname);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			tno = rs.getString(1);

		}

		return tno;

	}

	public static int getCount(String tno, Connection con) throws SQLException {

		int tempcount = 0;

		PreparedStatement stmt = con.prepareStatement("select count from traindetails where tno = ?");

		stmt.setString(1, tno);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			tempcount = rs.getInt(1);
		}

		return tempcount;

	}

	public static void InsertSignup(String name, String phonenum, int age, String gender, String password, String email,
			Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("insert into signup values(?,?,?,?,?,?)");

		stmt.setString(1, name);
		stmt.setString(2, phonenum);
		stmt.setInt(3, age);
		stmt.setString(4, gender);
		stmt.setString(5, password);
		stmt.setString(6, email);

		stmt.executeUpdate();

	}

	public static void InsertPassengerDetails(String name, int age, String gender, String phonenum, String email,
			Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"insert into passengerdetails (name, age, gender, phonenum, email) values(?,?,?,?,?)");

		stmt.setString(1, name);
		stmt.setInt(2, age);
		stmt.setString(3, gender);
		stmt.setString(4, phonenum);
		stmt.setString(5, email);

		stmt.executeUpdate();
	}

	public static int Insertpassengerboardingdetails(String pnr, String status, String depdate, String berth1,
			String pid, int id, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("insert into passengerboardingdetails values(?,?,?,?,?,?)");

		stmt.setString(1, pnr);
		stmt.setString(2, status);
		stmt.setString(3, depdate);
		stmt.setString(4, berth1);
		stmt.setString(5, pid);
		stmt.setInt(6, id);

		int rst = stmt.executeUpdate();
		return rst;
	}

	public static int CheckDupPassengerDetails(String name, int age, String gender, String phonenum, String email,
			Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"select * from passengerdetails where name = ? and age = ? and gender = ? and phonenum = ? and email = ?");

		stmt.setString(1, name);
		stmt.setInt(2, age);
		stmt.setString(3, gender);
		stmt.setString(4, phonenum);
		stmt.setString(5, email);

		int count = 0;

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			count = count + 1;
		}

		return count;
	}

	public static int getPassengerID(String name, int age, String gender, String phonenum, String email, Connection con)
			throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"select id from passengerdetails where name = ? and age = ? and gender = ? and phonenum = ? ");

		stmt.setString(1, name);
		stmt.setInt(2, age);
		stmt.setString(3, gender);
		stmt.setString(4, phonenum);

		ResultSet rs = stmt.executeQuery();

		int id = 0;
		while (rs.next()) {
			id = rs.getInt(1);
		}

		return id;

	}

	public static void Updatecount(String tno, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("update traindetails set count = count + 1 where tno = ?");

		stmt.setString(1, tno);
		stmt.executeUpdate();
	}

	public static void UpdateTotalSeatminus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update boardingdetails set tseat = tseat - 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();

	}

	public static void UpdateTotalSeatplus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update boardingdetails set tseat = tseat + 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();

	}

	public static void UpdateWaitingSeatminus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update boardingdetails set wseat = wseat - 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();

	}

	public static void UpdateWaitingSeatplus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update boardingdetails set wseat = wseat + 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();

	}

	public static void UpdateUpperBerthminus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"update boardingdetails set upperberth = upperberth - 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();

	}

	public static void UpdateUpperBerthplus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"update boardingdetails set upperberth = upperberth + 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();

	}

	public static void UpdateLowerBerthminus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"update boardingdetails set lowerberth = lowerberth - 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();
	}

	public static void UpdateLowerBerthplus(String tno, String depdate, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"update boardingdetails set lowerberth = lowerberth + 1 where tno = ? and depdate = ?");
		stmt.setString(1, tno);
		stmt.setString(2, depdate);
		stmt.executeUpdate();
	}

	public static String getStatusPassenger(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select status from passengerboardingdetails where pnr = ?");

		stmt.setString(1, pnr);

		ResultSet rs = stmt.executeQuery();

		String status = null;

		while (rs.next()) {

			status = rs.getString(1);
		}
		return status;
	}

	public static String getBerthPassenger(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select berth from passengerboardingdetails where pnr = ?");

		stmt.setString(1, pnr);

		ResultSet rs = stmt.executeQuery();

		String berth = null;

		while (rs.next()) {

			berth = rs.getString(1);
		}
		return berth;
	}

	public static String getdepdatePassenger(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select depdate from passengerboardingdetails where pnr = ?");

		stmt.setString(1, pnr);

		ResultSet rs = stmt.executeQuery();

		String depdate = null;

		while (rs.next()) {

			depdate = rs.getString(1);
		}
		return depdate;
	}

	public static String getPIDPassenger(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select pid from passengerboardingdetails where pnr = ?");

		stmt.setString(1, pnr);

		ResultSet rs = stmt.executeQuery();

		String pid = null;

		while (rs.next()) {

			pid = rs.getString(1);
		}
		return pid;
	}

	public static void UpdateStatusCancel(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update passengerboardingdetails set status = 'cancel' where pnr = ?");
		stmt.setString(1, pnr);
		stmt.executeUpdate();

	}

	public static void UpdateStatusConfirm(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update passengerboardingdetails set status = 'confirm' where pnr = ?");
		stmt.setString(1, pnr);
		stmt.executeUpdate();

	}

	public static void UpdateStatusWaiting(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update passengerboardingdetails set status = 'waiting' where pnr = ?");
		stmt.setString(1, pnr);
		stmt.executeUpdate();

	}

	public static String SelectWaitingPassenger(String tno, String date, Connection con) throws SQLException {
		String pnr = null;
		PreparedStatement stmt = con.prepareStatement(
				"select pnr from passengerboardingdetails where status = 'waiting' and pid LIKE ?  and depdate = ? ORDER BY pid LIMIT 1");

		stmt.setString(1, tno + "%");
		stmt.setString(2, date);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			pnr = rs.getString(1);
		}
		return pnr;

	}

	public static void SetBerth(String berth, String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("update passengerboardingdetails set berth = ? where pnr = ?");
		stmt.setString(1, berth);
		stmt.setString(2, pnr);
		stmt.executeUpdate();

	}

	public static int getLowerBerth(String tno, String date, Connection con) throws SQLException {
		int value = 0;
		PreparedStatement stmt = con
				.prepareStatement("select lowerberth from boardingdetails where tno = ? and depdate = ?");

		stmt.setString(1, tno);
		stmt.setString(2, date);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String result = rs.getString(1);
			value = Integer.parseInt(result);
		}

		return value;
	}

	public static int getUpperBerth(String tno, String date, Connection con) throws SQLException {
		int value = 0;
		PreparedStatement stmt = con
				.prepareStatement("select upperberth from boardingdetails where tno = ? and depdate = ?");

		stmt.setString(1, tno);
		stmt.setString(2, date);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String result = rs.getString(1);
			value = Integer.parseInt(result);
		}

		return value;
	}

	public static String getEmail(String phonenum, String password, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("select email from signup where phonenum = ? and userpassword = ?");

		stmt.setString(1, phonenum);
		stmt.setString(2, password);

		ResultSet rs = stmt.executeQuery();

		String email = null;

		while (rs.next()) {
			email = rs.getString(1);
		}
		return email;
	}

	public static int getCredits(String phonenum, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select credits from signup where phonenum = ? FOR UPDATE");

		stmt.setString(1, phonenum);
      
		ResultSet rs = stmt.executeQuery();

		int credits = 0;

		while (rs.next()) {
			credits = rs.getInt(1);
		}
		return credits;

	}

	public static void updateCreditsplus(String email, String phonenum, int value, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update signup set credits = credits + ? where email = ? and phonenum = ?");

		stmt.setInt(1, value);
		stmt.setString(2, email);
		stmt.setString(3, phonenum);

		stmt.executeUpdate();
	}

	public static void updateCreditsminus(String email, String phonenum, int value, Connection con)
			throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("update signup set credits = credits - ? where email = ? and phonenum = ?");

		stmt.setInt(1, value);
		stmt.setString(2, email);
		stmt.setString(3, phonenum);

		stmt.executeUpdate();
	}

	public static void getPassengerListWithPhonenum(String phonenum, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(
				"select passengerdetails.name, passengerdetails.age, passengerdetails.gender, passengerboardingdetails.pnr from passengerdetails INNER JOIN passengerboardingdetails on passengerdetails.id = passengerboardingdetails.id where passengerdetails.phonenum = ?");

		stmt.setString(1, phonenum);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			System.out.println("Name: " + rs.getString(1));
			System.out.println("Age: " + rs.getString(2));
			System.out.println("gender: " + rs.getString(3));
			System.out.println("pnr: " + rs.getString(4));

			System.out.println();

		}
	}

	public static void getInfoWithID(int id, Connection con) throws SQLException {
		PreparedStatement stmt = con
				.prepareStatement("select pnr, status, depdate,berth, pid from passengerboardingdetails where id = ?");

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			System.out.println("pnr: " + rs.getString(1) + "\nstatus: " + rs.getString(2) + "\ndepdate: "
					+ rs.getString(3) + "\nberth: " + rs.getString(4) + "\npid: " + rs.getString(5));

			System.out.println("\n");
		}
	}

	public static void updatecredit(int credit, String phonenum, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("update signup set credits = credits + ? where phonenum = ?");
		stmt.setInt(1, credit);
		stmt.setString(2, phonenum);

		stmt.executeUpdate();

	}

	public static boolean checkidwithphonenum(String phonenum, int id, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select id from passengerdetails where phonenum = ?");
		stmt.setString(1, phonenum);

		ResultSet rs = stmt.executeQuery();
		boolean flag = false;

		while (rs.next()) {

			int id_db = rs.getInt(1);

			if (id_db == id)
				flag = true;

		}

		return flag;

	}

	public static int getidwithpnr(String pnr, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select id from passengerboardingdetails where pnr = ?");
		stmt.setString(1, pnr);

		ResultSet rs = stmt.executeQuery();

		int id = 0;

		while (rs.next()) {

			id = rs.getInt(1);

		}

		return id;
	}

	public static boolean CheckUser(String phonenum, String password, Connection con) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select * from signup where phonenum = ? and userpassword = ?");

		stmt.setString(1, phonenum);
		stmt.setString(2, password);

		ResultSet rs = stmt.executeQuery();

		boolean flag = false;
		int count = 0;

		while (rs.next()) {

			count++;

		}

		if (count > 0)
			flag = true;

		return flag;

	}

}
