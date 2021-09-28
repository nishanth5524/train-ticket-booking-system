import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

	public static boolean alphabet(String value) {

		Pattern p = Pattern.compile("^[A-Za-z]*$");
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;

	}

	public static boolean alphanum(String value) {

		Pattern p = Pattern.compile("^[A-Za-z0-9]*$");
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;

	}

	public static boolean alphawithspace(String value) {

		Pattern p = Pattern.compile("^[a-zA-Z\\s]*$");
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;

	}

	public static boolean num(String value) {

		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;

	}

	public static boolean date(String value) {
		String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;

	}

	public static boolean phonenum(String value) {
		Pattern p = Pattern.compile("(0|91)?[6-9][0-9]{9}");
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;
	}

	public static boolean email(String value) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;
	}

	public static boolean time(String value) {
		Pattern p = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");
		Matcher m = p.matcher(value);

		if (m.find())
			return true;

		return false;
	}

}
