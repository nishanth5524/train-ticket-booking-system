import java.util.Scanner;

public class HomePage {

	public static void main(String[] args) {

		System.out.println("-------------------------------");
		System.out.println("- Train ticket booking system -");
		System.out.println("-------------------------------\n");

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n[1] Admin");
			System.out.println("[2] User");
			System.out.println("[3] Exit\n");

			int n = sc.nextInt();

			if (n == 1) {
				try {
					Admin aobj = new Admin();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			else if (n == 2) {
				try {
					Main uobj = new Main();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			else if (n == 3) {
				System.out.println("Thank you :-)");
				System.exit(0);

			}

			else {
				System.out.println("Invalid Number");

			}
		}
	}
}
