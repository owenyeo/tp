package seedu.address.testutil;

import seedu.address.model.person.timetable.DatedEvent;

import java.util.ArrayList;

public class TypicalDatedEvents {

    public static final ArrayList<DatedEvent> NORMAL_DATEDEVENTS = new DatedEventsBuilder()
            .withDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"))
            .withDatedEvent(DatedEvent.newDatedEvent("Walk Dog 2023-10-10 1030 1130 n"))
            .withDatedEvent(DatedEvent.newDatedEvent("Competitive sleeping 2023-10-10 1030 1130 y"))
            .build();

}
