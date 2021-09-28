
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;

public class PNR {

	public String generateRandomPNR(Connection con) throws SQLException {

		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		int flag = 1;
		String pnr = null;

		while (flag == 1) {
			for (int i = 0; i < 6; i++) {
				int randomIndex = random.nextInt(chars.length());
				sb.append(chars.charAt(randomIndex));
			}

			pnr = sb.toString();

			boolean count = SqlQuery.checkpnrDuplicate(pnr, con);

			if (count == true) {
				flag = 1;
			}

			else {
				flag = 0;

			}
		}
		return pnr;
	}

}
