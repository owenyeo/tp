package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.TimeBlock;

public class JsonAdaptedCca {

    private final String name;
    private final String timeBlockString;

    @JsonCreator
    public JsonAdaptedCca(String name, String timeBlockString) {
        this.name = name;
        this.timeBlockString = timeBlockString;
    }

    public JsonAdaptedCca(Cca source) {
        name = source.getCcaName();
        timeBlockString = source.getTimeBlockString();
    }
    
    public Cca toModelType() {
        if (!Cca.isValidCcaName(name) || !TimeBlock.isValidTimeBlock(timeBlockString)) {
            throw new IllegalArgumentException(Cca.MESSAGE_CONSTRAINTS);
        }

        return new Cca(name, timeBlockString);
    }
}
