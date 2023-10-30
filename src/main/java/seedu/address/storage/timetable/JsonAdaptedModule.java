package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.TimeBlock;

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
    
    public Module toModelType() {
        if (!Module.isValidModuleName(name) || !TimeBlock.isValidTimeBlock(timeBlockString)) {
            throw new IllegalArgumentException(Module.MESSAGE_CONSTRAINTS);
        }

        return new Module(name, timeBlockString);
    }
}
