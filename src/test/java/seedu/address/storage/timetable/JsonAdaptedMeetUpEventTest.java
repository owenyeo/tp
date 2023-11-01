package seedu.address.storage.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalSchedule.NORMAL_MEETUP_EVENT;


import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.MeetUpEvent;
import seedu.address.model.person.timetable.TimeBlock;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonAdaptedPerson;

public class JsonAdaptedMeetUpEventTest {
    private static final String INVALID_TIMEBLOCK = "monday";
    private static final String INVALID_DATE = "26-12-2001";
    
    private static final String VALID_NAME = "CS2100";
    private static final String VALID_TIMEBLOCK = "Monday 1200 1300";
    private static final String VALID_DATE = "2001-12-26 1200 1400";

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMeetUpEvent datedEvent = new JsonAdaptedMeetUpEvent(null, VALID_TIMEBLOCK, VALID_DATE, false, new JsonAdaptedPerson(IDA));
        assertThrows(IllegalValueException.class, datedEvent::toModelType);
    }

    @Test
    public void toModelType_invalidTimeBlock_throwsIllegalValueException() {
        JsonAdaptedMeetUpEvent datedEvent = new JsonAdaptedMeetUpEvent(VALID_NAME, INVALID_TIMEBLOCK, VALID_DATE, false, new JsonAdaptedPerson(ALICE));
        String expectedMessage = MeetUpEvent.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, datedEvent::toModelType);
    }

    @Test
    public void toModelType_nullTimeBlock_throwsIllegalValueException() {
        JsonAdaptedMeetUpEvent datedEvent = new JsonAdaptedMeetUpEvent(VALID_NAME, null, VALID_DATE, false, new JsonAdaptedPerson(HOON));
        String expectedMessage = MeetUpEvent.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, datedEvent::toModelType);
    }

    @Test
    public void toModelType_invalidDateString_throwsIllegalValueException() {
        JsonAdaptedMeetUpEvent datedEvent = new JsonAdaptedMeetUpEvent(VALID_NAME, VALID_TIMEBLOCK, INVALID_DATE, false, new JsonAdaptedPerson(HOON));
        String expectedMessage = DatedEvent.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, datedEvent::toModelType);
    }

    @Test
    public void toModelType_nullPerson_throwsIllegalValueException() {
        JsonAdaptedMeetUpEvent datedEvent = new JsonAdaptedMeetUpEvent(VALID_NAME, VALID_TIMEBLOCK, INVALID_DATE, false, null);
        assertThrows(IllegalValueException.class, datedEvent::toModelType);
    }

    @Test
    public void toModelType_validDatedEvent_success() throws IllegalValueException {
        JsonAdaptedMeetUpEvent datedEvent = new JsonAdaptedMeetUpEvent(NORMAL_MEETUP_EVENT);
        assertEquals(NORMAL_MEETUP_EVENT, datedEvent.toModelType());
    }


}

