package seedu.address.model.person.timetable;

import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents a MeetUpEvent in the application.
 * A specialized event that includes a list of friends (attendees) for the meetup.
 */
public class MeetUpEvent extends DatedEvent {

    private ArrayList<Person> friends;

    /**
     * Initializes a new MeetUpEvent with the provided details and list of friends attending the meetup.
     *
     * @param name The name of the meetup event.
     * @param timeBlockString The time block for the event.
     * @param dateString The date of the event in the format "YYYY-MM-DD".
     * @param reminder A flag indicating if a reminder is set for this event.
     * @param friends A list of friends attending the meetup.
     */
    public MeetUpEvent(String name, String timeBlockString,
                       String dateString, boolean reminder, ArrayList<Person> friends) {
        super(name, timeBlockString, dateString, reminder);
        this.friends = friends;
    }
    //TODO: Add a verifier for if the friend actually exists
    //TODO: Add a constructor that takes in an unparsedString from the CLI straight and convert it

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString()).append("\n");
        str.append("Meetup Attendees:\n");

        for (Person friend : friends) {
            str.append(friend.getName()).append("\n");
        }

        return str.toString();
    }
}
