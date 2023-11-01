package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.TimeBlock;

public class JsonAdaptedCca {

    private final String name;
    private final String timeBlockString;

    @JsonCreator
    public JsonAdaptedCca(@JsonProperty("name") String name,
        @JsonProperty("timeblock") String timeBlockString) {
        this.name = name;
        this.timeBlockString = timeBlockString;
    }

    public JsonAdaptedCca(Cca source) {
        name = source.getCcaName();
        timeBlockString = source.getTimeBlockString();
    }
    
    public Cca toModelType() throws IllegalValueException {
        if (!Cca.isValidCcaName(name)) {
            throw new IllegalValueException(Cca.MESSAGE_CONSTRAINTS);
        }

        if (!TimeBlock.isValidTimeBlock(timeBlockString)) {
            throw new IllegalValueException(TimeBlock.MESSAGE_CONSTRAINTS);
        }

        return new Cca(name, timeBlockString);
    }
}
