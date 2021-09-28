
import java.util.Scanner;

public class Main {

	Main() throws Exception {

		System.out.println("-------------------------------");
		System.out.println("- Train ticket booking system -");
		System.out.println("-------------------------------\n");

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n[1] Book Ticket");
			System.out.println("[2] Ticket Status");
			System.out.println("[3] Cancel Ticket");
			System.out.println("[4] Rebook Ticket");
			System.out.println("[5] Travel Hi1story");
			System.out.println("[6] Passenger List registered with phonenum");
			System.out.println("[7] Add Credits");
			System.out.println("[8] Exit\n");

			int n = sc.nextInt();

			if (n == 1) {
				BookTicket btobj = new BookTicket();

			}

			else if (n == 2) {
				TicketStatus tsobj = new TicketStatus();
			}

			else if (n == 3) {
				CancelTicket ctobj = new CancelTicket();
			} else if (n == 4) {
				Rebooking rbobj = new Rebooking();
			} else if (n == 5) {
				TravelHistoryPassenger obj = new TravelHistoryPassenger();
			} else if (n == 6) {
				PassengerListWithPhonenum obj = new PassengerListWithPhonenum();
			}
			else if (n == 7) {
				AddCredits obj = new AddCredits();
			}
			else if (n == 8) {
				System.out.println("Thank you :-)");
				System.exit(0);

			}

			else {
				System.out.println("Invalid Number");
			}

		}

	}

}
