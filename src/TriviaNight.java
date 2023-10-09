import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * This class represents a Trivia Night event.
 */
public class TriviaNight {
	private String eventName;
	private Date eventDate;
	private int numberOfParticipants;
	private String mainTheme;
	private String registerUrl;
	private String chatUrl;
	private String eventLocation;
	private String eventDescription;

	public TriviaNight(
			String eventName, Date eventDate, int numberOfParticipants,
			String mainTheme, String registerUrl, String chatUrl,
			String eventLocation, String eventDescription) {
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.numberOfParticipants = numberOfParticipants;
		this.mainTheme = mainTheme;
		this.registerUrl = registerUrl;
		this.chatUrl = chatUrl;
		this.eventLocation = eventLocation;
		this.eventDescription = eventDescription;
	}

	// Getters and Setters
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) throws ParseException {
		this.eventDate = new SimpleDateFormat(
				"dd-MM-yyyy").parse(eventDate);
	}

	public int getNumberOfParticipants() {
		return numberOfParticipants;
	}

	public void setNumberOfParticipants(int numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}

	public String getMainTheme() {
		return mainTheme;
	}

	public void setMainTheme(String mainTheme) {
		this.mainTheme = mainTheme;
	}

	public String getRegisterUrl() {
		return registerUrl;
	}

	public void setRegisterUrl(String registerUrl) {
		this.registerUrl = registerUrl;
	}

	public String getChatUrl() {
		return chatUrl;
	}

	public void setChatUrl(String chatUrl) {
		this.chatUrl = chatUrl;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	// Additional methods
	public String getDescription() {
		return "Event Name: " + eventName + "\n" +
				"Event Date: " + eventDate + "\n" +
				"Number of Participants: " + numberOfParticipants + "\n" +
				"Main Theme: " + mainTheme + "\n" +
				"Register URL: " + registerUrl + "\n" +
				"Chat URL: " + chatUrl + "\n" +
				"Event Location: " + eventLocation + "\n" +
				"Event Description: " + eventDescription + "\n";
	}

	public String getTitle() {
		return "Event Name: " + eventName;
	}
}


/**
 * This class represents a collection of Trivia Night events.
 */
class TriviaNightCollection {
	private TriviaNight[] events;
	private int count;

	public TriviaNightCollection() {
		events = new TriviaNight[10]; // Initial capacity of 10
		count = 0;
	}

	public void add(TriviaNight newEvent) {
		if (count == events.length) {
			// Expand the array if needed
			events = Arrays.copyOf(events, count * 2);
		}
		events[count++] = newEvent;
	}

	public void remove(int index) {
		if (index >= 0 && index < count) {
			System.arraycopy(
					events, index + 1, events, index,
					count - index - 1);
			events[count - 1] = null; // Clear the last element
			count--;
		}
	}

	public void printOne(int index) {
		if (index >= 0 && index < count) {
			System.out.println(events[index].getDescription());
		} else {
			System.out.println("Invalid index.");
		}
	}

	public void printAll() {
		for (int i = 0; i < count; i++) {
			System.out.println("Event #" + (i + 1));
			System.out.println(events[i].getDescription());
		}
	}

	public void sortByNumberOfParticipants() {
		Arrays.sort(
				events, 0, count,
				Comparator.comparingInt(TriviaNight::getNumberOfParticipants));
	}

	public void sortByDate() {
		Arrays.sort(
				events, 0, count,
				Comparator.comparing(TriviaNight::getEventDate));
	}

	public void searchByDescription(String phrase) {
		for (int i = 0; i < count; i++) {
			if (events[i].getDescription().contains(phrase)) {
				System.out.println("Event #" + (i + 1));
				System.out.println(events[i].getDescription());
			}
		}
	}

	public void searchByDateYear(int searchYear) {
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < count; i++) {
			calendar.setTime(events[i].getEventDate());
			int eventYear = calendar.get(Calendar.YEAR);
			if (eventYear == searchYear) {
				System.out.println("Event #" + (i + 1));
				System.out.println(events[i].getDescription());
			}
		}
	}

	private String getAll() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < count; i++) {
			result.append("Event #").append(i + 1).append("\n");
			result.append("Event Name: ");
			result.append(events[i].getEventName()).append("\n");
			result.append("Event Date: ");
			result.append(events[i].getEventDate()).append("\n");
			result.append("Number of participants: ");
			result.append(events[i].getNumberOfParticipants()).append("\n");
			result.append("Main Theme: ");
			result.append(events[i].getMainTheme()).append("\n");
			result.append("Register URL: ");
			result.append(events[i].getRegisterUrl()).append("\n");
			result.append("Chat URL: ");
			result.append(events[i].getChatUrl()).append("\n");
			result.append("Event Location: ");
			result.append(events[i].getEventLocation()).append("\n");
			result.append("Event Description: ");
			result.append(events[i].getEventDescription()).append("\n");
			result.append("\n");
		}
		return result.toString();
	}

	public void saveToFile(String path) {
		try {
			FileWriter fileWriter = new FileWriter(path);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(getAll());

			bufferedWriter.close();
			System.out.println(
					"Content has been written to the file successfully.");
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}

	public void readFromFile(String path) {
		try (FileReader fileReader = new FileReader(path);
		     BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			String eventName = "";
			Date eventDate = null;
			int numberOfParticipants = 0;
			String mainTheme = "";
			String registerUrl = "";
			String chatUrl = "";
			String eventLocation = "";
			String eventDescription = "";

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String value = "";
				if (line.contains(":") && line.indexOf(":") + 2 < line.length()) {
					value = line.substring(line.indexOf(":") + 2);
				}
				if (line.contains("Event Name:")) {
					eventName = value;
				}
				if (line.contains("Event Date:")) {
					try {
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"E MMM dd HH:mm:ss z yyyy", Locale.US);
						eventDate = dateFormat.parse(value);
					} catch (ParseException e) {
						// Handle date parsing error gracefully, log the error, and continue
						System.err.println("Error parsing date: " + value);
					}
				}
				if (line.contains("Number of participants")) {
					try {
						numberOfParticipants = Integer.parseInt(value);
					} catch (NumberFormatException e) {
						// Handle number parsing error gracefully, log the error, and continue
						System.err.println(
								"Error parsing number of participants: " + value
						);
					}
				}
				if (line.contains("Main Theme")) {
					mainTheme = value;
				}
				if (line.contains("Register URL")) {
					registerUrl = value;
				}
				if (line.contains("Chat URL")) {
					chatUrl = value;
				}
				if (line.contains("Event Location")) {
					eventLocation = value;
				}
				if (line.contains("Event Description")) {
					eventDescription = value;
				}
				if (line.trim().isEmpty()) {
					add(new TriviaNight(eventName, eventDate,
							numberOfParticipants, mainTheme, registerUrl,
							chatUrl, eventLocation, eventDescription));
					// Reset fields for the next event
					eventName = "";
					eventDate = null;
					numberOfParticipants = 0;
					mainTheme = "";
					registerUrl = "";
					chatUrl = "";
					eventLocation = "";
					eventDescription = "";
				}
			}
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}
}