package seedu.address.storage.timetable;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.MeetUpEvent;
import seedu.address.model.person.timetable.TimeBlock;
import seedu.address.storage.JsonAdaptedPerson;

/**
 * Jackson-friendly version of {@link MeetUpEvent}.
 */
public class JsonAdaptedMeetUpEvent {

    private final String name;
    private final String timeBlockString;
    private final String localDateString;
    private final boolean reminder;
    private final JsonAdaptedPerson friend;

    /**
     * Constructs a {@code JsonAdaptedMeetUpEvent} with the given {@code MeetUpEventName}.
     */
    @JsonCreator
    public JsonAdaptedMeetUpEvent(@JsonProperty("name") String name,
            @JsonProperty("timeblock") String timeBlockString,
            @JsonProperty("date") String localDateString,
            @JsonProperty("reminder") boolean reminder,
            @JsonProperty("friend") JsonAdaptedPerson friend) {
        this.name = name;
        this.timeBlockString = timeBlockString;
        this.localDateString = localDateString;
        this.reminder = reminder;
        this.friend = friend;
    }

    /**
     * Converts a given {@code MeetUpEvent} into this class for Jackson use.
     */
    public JsonAdaptedMeetUpEvent(MeetUpEvent source) {
        name = source.getName();
        timeBlockString = source.getTimeBlockString();
        LocalDate date = source.getDate();
        localDateString = date.toString();
        reminder = source.hasReminder();
        friend = new JsonAdaptedPerson(source.getFriend());
    }


    /**
     * Converts this Jackson-friendly adapted MeetUpEvent object into the model's {@code MeetUpEvent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted MeetUpEvent.
     */
    public MeetUpEvent toModelType() throws IllegalValueException {
        if (!MeetUpEvent.isValidDateTimeString(localDateString)) {
            throw new IllegalValueException(MeetUpEvent.MESSAGE_CONSTRAINTS);
        }

        if (!TimeBlock.isValidTimeBlock(timeBlockString)) {
            throw new IllegalValueException(TimeBlock.MESSAGE_CONSTRAINTS);
        }
        
        LocalDate date = LocalDate.parse(localDateString);
        return new MeetUpEvent(name, timeBlockString, date.toString(), reminder, friend.toModelType());
    }

}

