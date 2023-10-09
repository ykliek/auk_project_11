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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This represents main login of the program.
 */
public class Main {

	private static final Scanner scanner = new Scanner(System.in);;
	/**
	 * Main method of the program.
	 * @param args command line arguments
	 * @throws ParseException if date is not in the correct format
	 */
	public static void main(String[] args) throws ParseException {
		TriviaNightCollection triviaNightCollection = new TriviaNightCollection();

		System.out.println("Welcome to the Trivia Night Database!");
		int choice;
		String pathToFile;

		do {
			System.out.println("Choose an option to proceed:");
			System.out.println("1 - Add a Trivia Night event");
			System.out.println("2 - Remove a Trivia Night event");
			System.out.println("3 - Print all Trivia Night events");
			System.out.println("4 - Sort Trivia Night events by number of participants");
			System.out.println("5 - Sort Trivia Night events by date");
			System.out.println("6 - Search Trivia Night events by keyword");
			System.out.println("7 - Search Trivia Night events by year");
			System.out.println("8 - Save events to file");
			System.out.println("9 - Load events from file");
			System.out.println("0 - Exit the program");
			System.out.print("Enter your choice: ");

			choice = getUserInt();
			scanner.nextLine(); // Consume the newline character

			switch (choice) {
				case 1:
					TriviaNight newEvent = createTriviaNightEvent();
					triviaNightCollection.add(newEvent);
					System.out.println(
							"Trivia Night event added to the collection.");
					break;
				case 2:
					System.out.print(
							"Enter the index of the event to remove: ");
					int indexToRemove = getUserInt();
					triviaNightCollection.remove(indexToRemove - 1);
					break;
				case 3:
					triviaNightCollection.printAll();
					break;
				case 4:
					triviaNightCollection.sortByNumberOfParticipants();
					System.out.println(
							"Trivia Night events sorted by number of " +
									"participants.");
					break;
				case 5:
					triviaNightCollection.sortByDate();
					System.out.println("Trivia Night events sorted by date.");
					break;
				case 6:
					System.out.print("Enter a keyword to search for events: ");
					String keyword = scanner.nextLine();
					triviaNightCollection.searchByDescription(keyword);
					break;
				case 7:
					System.out.print("Enter a year to search for events: ");
					int year = getUserInt();
					triviaNightCollection.searchByDateYear(year);
					break;
				case 8:
					System.out.print("Enter path to file: ");
					pathToFile = scanner.nextLine();
					triviaNightCollection.saveToFile(pathToFile);
					break;
				case 9:
					System.out.print("Enter path to file: ");
					pathToFile = scanner.nextLine();
					triviaNightCollection.readFromFile(pathToFile);
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

	/**
	 * Creates a new Trivia Night event with user input.
	 * @return TriviaNight object
	 * @throws ParseException if date is not in the correct format
	 */
	private static TriviaNight createTriviaNightEvent() throws ParseException {
		System.out.println("Adding a new Trivia Night event to the collection:");
		System.out.print("Event Name: ");
		String eventName = scanner.nextLine();
		System.out.print("Event Date (dd-MM-yyyy): ");
		Date eventDate = getUserDate();
		System.out.print("Number of Participants: ");
		int numberOfParticipants = getUserInt();
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

	private static Date getUserDate() {
		while(true) {
			try {
				return new SimpleDateFormat(
						"dd-MM-yyyy").parse(scanner.nextLine());
			} catch (ParseException e) {
				System.out.println(
						"\u001B[31mIt is not a date. Try again: \u001B[0m");
			}
		}
	}

	private static int getUserInt() {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println(
						"\u001B[31mIt is not a number. Try again: \u001B[0m");
			}
		}
	}
}