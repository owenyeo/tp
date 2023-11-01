package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
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
        name = source.getName();
        timeBlockString = source.getTimeBlockString();
    }
    
    public Cca toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException("CCA name should not be null");
        }
        if (timeBlockString == null) {
            throw new IllegalValueException("Time block should not be null");
        }

        return new Cca(name, timeBlockString);
    }
}
