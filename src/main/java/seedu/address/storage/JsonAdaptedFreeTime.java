package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.FreeTime.FreeTime;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
public class JsonAdaptedFreeTime {

    private final String freeTime;

    /**
     * Constructs a {@code JsonAdaptedFreeTime} with the given {@code freeTimeName}.
     */
    @JsonCreator
    public JsonAdaptedFreeTime(String freeTimeName) {
        this.freeTime = freeTimeName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedFreeTime(FreeTime source) {
        freeTime = source.freeTime;
    }

    @JsonValue
    public String getFreeTime() {
        return freeTime;
    }

    /**
     * Converts this Jackson-friendly adapted FreeTime object into the model's {@code FreeTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted freeTime.
     */
    public FreeTime toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(freeTime)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new FreeTime(freeTime);
    }

}