package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
    
    /**
     * Converts this Jackson-friendly adapted Module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        if (!Module.isValidModuleName(name)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }

        if(!TimeBlock.isValidTimeBlock(timeBlockString)) {
            throw new IllegalValueException(TimeBlock.MESSAGE_CONSTRAINTS);
        }

        return new Module(name, timeBlockString);
    }
}
