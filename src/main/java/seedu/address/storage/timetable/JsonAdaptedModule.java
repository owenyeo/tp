package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.TimeBlock;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {

    private final String name;
    private final String timeBlockString;

    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name,
        @JsonProperty("timeblock") String timeBlockString) {
        this.name = name;
        this.timeBlockString = timeBlockString;
    }

    public JsonAdaptedModule(Module source) {
        name = source.getName();
        timeBlockString = source.getTimeBlockString();
    }
    
    public Module toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException("Module name should not be null");
        }
        if (timeBlockString == null) {
            throw new IllegalValueException("Time block should not be null");
        }

        return new Module(name, timeBlockString);
    }
}
