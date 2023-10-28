package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.model.person.timetable.TimeBlock;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Jackson-friendly version of {@link TimeBlock}.
 */
public class JsonAdaptedTimeBlock {

    private final String timeBlockString;

    /**
     * Constructs a {@code JsonAdaptedTimeBlock} with the given {@code timeBlockString}.
     */
    @JsonCreator
    public JsonAdaptedTimeBlock(String timeBlockString) {
        this.timeBlockString = timeBlockString;
    }

    /**
     * Converts a given {@code TimeBlock} into this class for Jackson use.
     */
    public JsonAdaptedTimeBlock(TimeBlock source) {
        timeBlockString = source.getTimeBlockString();
    }

    @JsonValue
    public String getTimeBlockString() {
        return timeBlockString;
    }

    public TimeBlock toModelType() {
        if (!TimeBlock.isValidTimeBlock(timeBlockString)) {
            throw new IllegalArgumentException(TimeBlock.MESSAGE_CONSTRAINTS);
        }
        return new TimeBlock(timeBlockString);
    }
    
}
