
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("-------------------------------");
		System.out.println("- Train ticket booking system -");
		System.out.println("-------------------------------\n");

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n[1] Book Ticket");
			System.out.println("[2] Ticket Status");
			System.out.println("[3] Cancel Ticket");
			System.out.println("[4] Rebook Ticket");
			System.out.println("[5] Exit\n");

			int n = sc.nextInt();

			if (n == 1) {
				BookTicket btobj = new BookTicket();

			}

			else if (n == 2) {
				TicketStatus tsobj = new TicketStatus();
			}

			else if (n == 3) {
				CancelTicket ctobj = new CancelTicket();
			} 
			else if(n == 4)
			{
				Rebooking rbobj = new Rebooking();
			}
			
			else if (n == 5) {
				System.out.println("Thank you :-)");
				System.exit(0);

			}

			else {
				System.out.println("Invalid Number");
			}

		}

	}

}
