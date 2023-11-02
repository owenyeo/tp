package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.FreeTime;

public class JsonAdaptedFreeTimeTest {

    @Test
    public void toModelType_validFreeTime_returnsFreeTime() throws Exception {
        FreeTime expectedFreeTime = new FreeTime("monday 1000 1030");
        JsonAdaptedFreeTime freeTime = new JsonAdaptedFreeTime("monday 1000 1030");
        assertEquals(expectedFreeTime, freeTime.toModelType());
    }

    @Test
    public void toModelType_validFreeTime_returnsFreeTimeAltConstructor() throws Exception {
        FreeTime expectedFreeTime = new FreeTime("monday 1000 1030");
        JsonAdaptedFreeTime freeTime = new JsonAdaptedFreeTime(new FreeTime("monday 1000 1030"));
        assertEquals(expectedFreeTime, freeTime.toModelType());
    }

    @Test
    public void getFreeTime_validFreeTime_returnsFreeTime() {
        JsonAdaptedFreeTime freeTime = new JsonAdaptedFreeTime("monday 1000 1030");
        assertEquals("monday 1000 1030", freeTime.getFreeTime());
    }

    @Test
    public void toModelType_invalidFreeTimes_throwsIllegalValueException() {
        JsonAdaptedFreeTime freeTime = new JsonAdaptedFreeTime("monday");
        assertThrows(IllegalValueException.class, freeTime::toModelType);
    }
}
