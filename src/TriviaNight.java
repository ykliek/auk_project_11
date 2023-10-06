import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TriviaNight {
	private String eventName;
	private Date eventDate;
	private int numberOfParticipants;
	private String mainTheme;
	private String registerUrl;
	private String chatUrl;
	private String eventLocation;
	private String eventDescription;

	public TriviaNight(String eventName, Date eventDate, int numberOfParticipants, String mainTheme,
	                   String registerUrl, String chatUrl, String eventLocation, String eventDescription) {
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
		this.eventDate = new SimpleDateFormat("dd-MM-yyyy").parse(eventDate);
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
		StringBuilder descriptionBuilder = new StringBuilder();
		descriptionBuilder.append("Event Name: ").append(eventName).append("\n");
		descriptionBuilder.append("Event Date: ").append(eventDate).append("\n");
		descriptionBuilder.append("Number of Participants: ").append(numberOfParticipants).append("\n");
		descriptionBuilder.append("Main Theme: ").append(mainTheme).append("\n");
		descriptionBuilder.append("Register URL: ").append(registerUrl).append("\n");
		descriptionBuilder.append("Chat URL: ").append(chatUrl).append("\n");
		descriptionBuilder.append("Event Location: ").append(eventLocation).append("\n");
		descriptionBuilder.append("Event Description: ").append(eventDescription).append("\n");
		return descriptionBuilder.toString();
	}

	public String getTitle() {
		return "Event Name: " + eventName;
	}
}

class TriviaNightCollection {
	private List<TriviaNight> events = new ArrayList<>();

	public void add(TriviaNight newEvent) {
		events.add(newEvent);
	}

	public void remove(int i) {
		if (i >= 0 && i < events.size()) {
			events.remove(i);
		}
	}

	public void printOne(int i) {
		if (i >= 0 && i < events.size()) {
			System.out.println(events.get(i).getDescription());
		} else {
			System.out.println("Invalid index.");
		}
	}

	public void printAll() {
		for (int i = 0; i < events.size(); i++) {
			System.out.println("Event #" + (i + 1));
			System.out.println(events.get(i).getDescription());
		}
	}

	public void printList() {
		for (int i = 0; i < events.size(); i++) {
			System.out.println((i + 1) + ". " + events.get(i).getTitle());
		}
	}

	public void sort() {
		Collections.sort(events, (event1, event2) ->
				Integer.compare(event1.getNumberOfParticipants(), event2.getNumberOfParticipants()));
	}

	public void search(String phrase) {
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getDescription().contains(phrase)) {
				System.out.println("Event #" + (i + 1));
				System.out.println(events.get(i).getDescription());
			}
		}
	}

	// Save all events to a specific file (e.g., "events.txt")
	public void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("events.txt"))) {
			oos.writeObject(events);
			System.out.println("Events saved to file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read all events from the specific file (e.g., "events.txt")
	public void readFromFile() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("events.txt"))) {
			events = (List<TriviaNight>) ois.readObject();
			System.out.println("Events loaded from file.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
