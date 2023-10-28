package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.person.Person;

/**
 * Represents a MeetUpEvent in the application.
 * A specialized event that includes a list of friends (attendees) for the meetup.
 */
public class MeetUpEvent extends DatedEvent {

    private final Person friend;

    /**
     * Initializes a new MeetUpEvent with the provided details and list of friends attending the meetup.
     *
     * @param name The name of the meetup event.
     * @param timeBlockString The time block for the event.
     * @param dateString The date of the event in the format "YYYY-MM-DD".
     * @param friend The friend attending the meetup.
     */
    public MeetUpEvent(String name, String timeBlockString,
                       String dateString, boolean reminder, Person friend) {
        super(name, timeBlockString, dateString, reminder);
        this.friend = friend;
    }

    /**
     * Factory method to create a new MeetUpEvent object from a given unparsed input string and a Person object
     * representing a friend.
     *
     * The expected format for the input string is: "EVENT_NAME DATE YYYY-MM-DD START_TIME END_TIME REMINDER_STATUS"
     * Where:
     * <ul>
     *   <li>EVENT_NAME is the name of the meetup event and can contain spaces.</li>
     *   <li>DATE in 'YYYY-MM-DD' format represents the date of the meetup event.</li>
     *   <li>START_TIME and END_TIME in 'HHMM' format represent the start and
     *   end times of the meetup event respectively.</li>
     *   <li>REMINDER_STATUS is 'y' if a reminder is set for the meetup event, 'n' otherwise.</li>
     * </ul>
     *
     * Example input: "lunch with Alice 2023-11-10 1200 1330 y"
     *
     * @param unparsedInput The input string containing MeetUpEvent details.
     * @param friend The Person object representing a friend for the meetup.
     * @return A new MeetUpEvent object.
     * @throws IllegalArgumentException If the given input does not adhere to the expected format.
     */
    public static MeetUpEvent newMeetUpEvent(String unparsedInput, Person friend) {
        requireNonNull(unparsedInput);

        // Regex to capture the name, date, start time, and end time
        String regex = "^(.+) (\\d{4}-\\d{2}-\\d{2}) (\\d{4}) (\\d{4}) (y|n)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(unparsedInput);

        checkArgument(matcher.matches(), MESSAGE_CONSTRAINTS);

        // Extracting components using the matcher
        String nameString = matcher.group(1); // event name
        String dateString = matcher.group(2); // date
        String startTime = matcher.group(3); // start time
        String endTime = matcher.group(4); // end time
        boolean reminder = matcher.group(5).equals("y"); // reminder status

        checkArgument(isValidDateTimeString(dateString), MESSAGE_CONSTRAINTS);

        // Parse the date
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN));
        String dayOfWeek = date.getDayOfWeek().name();

        // Create the time block
        String timeBlockString = dayOfWeek + " " + startTime + " " + endTime;

        return new MeetUpEvent(nameString, timeBlockString, dateString, reminder, friend);
    }

    @Override
    public String toString() {
        String str = super.toString() + "\n" +
                "Meeting: " + friend.getName();

        return str;
    }
}
