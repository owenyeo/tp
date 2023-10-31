package seedu.address.storage.timetable;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.model.person.timetable.MeetUpEvent;


public class JsonAdaptedScheduleTest {  
    public static final String VALID_MODULE_NAME = "CS2100";
    public static final String VALID_TIMEBLOCK = "Monday 1200 1300";
    public static final String VALID_DATE = "2001-12-26 1200 1400";

    public static final String INVALID_MODULE_NAME = "okok";
    public static final String INVALID_TIMEBLOCK = "monday";
    public static final String INVALID_DATE = "26-12-2001";

    @Test
    public void toModelType_invalidCca_throwsIllegalValueException() {
        Schedule schedule = new Schedule();
        Cca newCca = new Cca(VALID_MODULE_NAME, INVALID_TIMEBLOCK);
        schedule.addCca(newCca);

        JsonAdaptedSchedule jsonSchedule = new JsonAdaptedSchedule(schedule);
        assertThrows(IllegalArgumentException.class, jsonSchedule::toModelType);
    }

    @Test
    public void toModelType_invalidDatedEvent_throwsIllegalValueException() {
        Schedule schedule = new Schedule();
        DatedEvent testDatedEvent = new DatedEvent(VALID_MODULE_NAME, VALID_TIMEBLOCK, INVALID_DATE, false);
        schedule.addDatedEvent(testDatedEvent);

        JsonAdaptedSchedule jsonSchedule = new JsonAdaptedSchedule(schedule);
        assertThrows(IllegalArgumentException.class, jsonSchedule::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        Schedule schedule = new Schedule();
        Module module = new Module(INVALID_MODULE_NAME, VALID_TIMEBLOCK);
        schedule.addModule(module);

        JsonAdaptedSchedule jsonSchedule = new JsonAdaptedSchedule(schedule);
        assertThrows(IllegalArgumentException.class, jsonSchedule::toModelType);
    }

    @Test
    public void toModelType_invalidMeetUpEvent_throwsIllegalValueException() {
        Schedule schedule = new Schedule();
        MeetUpEvent meetUpEvent = new MeetUpEvent(VALID_MODULE_NAME, VALID_TIMEBLOCK, INVALID_DATE, false, ALICE);

        schedule.addMeetUpEvent(meetUpEvent);

        JsonAdaptedSchedule jsonSchedule = new JsonAdaptedSchedule(schedule);
        assertThrows(IllegalArgumentException.class, jsonSchedule::toModelType);
    }
}
