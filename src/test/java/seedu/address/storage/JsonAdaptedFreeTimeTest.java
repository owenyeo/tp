package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedFreeTimeTest {
    @Test
    public void toModelType_invalidFreeTimes_throwsIllegalValueException() {
        JsonAdaptedFreeTime freeTime = new JsonAdaptedFreeTime("monday");
        assertThrows(IllegalValueException.class, freeTime::toModelType);
    }
}
