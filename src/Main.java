/* ************************************************************************** */
/*                                                                            */
/*                                                 :       ::    :: ::   ::   */
/*   Main.java                                    :+:      :+    :+ :+  :+    */
/*                                               +:+:+     :+    :+ :+  :+    */
/*   By: ykliek <yurii.kliek@auk.edu.ua>        +#   :+    +#    :+ +#:+      */
/*                                             +#+#+#+#+   +#    +# +# +#     */
/*   Created: 2023/10/03 23:38:30 by ykliek   #+       #+  #+    #+ #+  #+    */
/*   Updated: 2023/10/03 23:38:31 by ykliek  ##         ##  ######  ##   ##   */
/*                                                                            */
/* ************************************************************************** */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		TriviaNightCollection triviaNightCollection = new TriviaNightCollection();

		System.out.println("Welcome to the Trivia Night Database!");
		int choice;

		do {
			System.out.println("Choose an option to proceed:");
			System.out.println("1 - Add a Trivia Night event");
			System.out.println("2 - Remove a Trivia Night event");
			System.out.println("3 - Print all Trivia Night events");
			System.out.println("4 - Sort Trivia Night events by number of participants");
			System.out.println("5 - Search Trivia Night events by keyword");
			System.out.println("6 - Save events to file");
			System.out.println("7 - Load events from file");
			System.out.println("0 - Exit the program");
			System.out.print("Enter your choice: ");

			choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (choice) {
				case 1:
					TriviaNight newEvent = createTriviaNightEvent(scanner);
					triviaNightCollection.add(newEvent);
					System.out.println("Trivia Night event added to the collection.");
					break;
				case 2:
					System.out.print("Enter the index of the event to remove: ");
					int indexToRemove = scanner.nextInt();
					triviaNightCollection.remove(indexToRemove);
					break;
				case 3:
					triviaNightCollection.printAll();
					break;
				case 4:
					triviaNightCollection.sort();
					System.out.println("Trivia Night events sorted by number of participants.");
					break;
				case 5:
					System.out.print("Enter a keyword to search for events: ");
					String keyword = scanner.nextLine();
					triviaNightCollection.search(keyword);
					break;
				case 6:
					triviaNightCollection.saveToFile();
					break;
				case 7:
					triviaNightCollection.readFromFile();
					break;
				case 0:
					System.out.println("Exiting the program. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 0);

		scanner.close();
	}

	private static TriviaNight createTriviaNightEvent(Scanner scanner) throws ParseException {
		System.out.println("Adding a new Trivia Night event to the collection:");
		System.out.print("Event Name: ");
		String eventName = scanner.nextLine();
		System.out.print("Event Date (dd-MM-yyyy): ");
		Date eventDate = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());
		System.out.print("Number of Participants: ");
		int numberOfParticipants = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character
		System.out.print("Main Theme: ");
		String mainTheme = scanner.nextLine();
		System.out.print("Register URL: ");
		String registerUrl = scanner.nextLine();
		System.out.print("Chat URL: ");
		String chatUrl = scanner.nextLine();
		System.out.print("Event Location: ");
		String eventLocation = scanner.nextLine();
		System.out.print("Event Description: ");
		String descriptionFilePath = scanner.nextLine();

		return new TriviaNight(eventName, eventDate, numberOfParticipants, mainTheme,
				registerUrl, chatUrl, eventLocation, descriptionFilePath);
	}
}