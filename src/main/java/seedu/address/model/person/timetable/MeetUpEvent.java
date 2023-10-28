package seedu.address.model.person.timetable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateTimeFormatterPattern));
        String dayOfWeek = date.getDayOfWeek().name();

        // Create the time block
        String timeBlockString = dayOfWeek + " " + startTime + " " + endTime;

        return new MeetUpEvent(nameString, timeBlockString, dateString, reminder, friend);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString()).append("\n");
        str.append("Meeting: ").append(friend.getName());

        return str.toString();
    }
}
