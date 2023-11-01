package seedu.address.testutil;

import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.MeetUpEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;

import java.util.ArrayList;

public class TypicalSchedule {

    public static final Schedule NORMAL_SCHEDULE = new ScheduleBuilder()
            .withModule(new Module("CS2103", "Wednesday 1200 1300"))
            .withModule(new Module("CS2101", "Tuesday 1200 1400"))
            .withModule(new Module("CS2100", "Monday 1400 1500"))
            .withCca(new Cca("Basketball", "Monday 1800 2000"))
            .withCca(new Cca("Soccer", "Tuesday 1800 2000"))
            .withCca(new Cca("Tennis", "Wednesday 1800 2000"))
            .withDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"))
            .withDatedEvent(DatedEvent.newDatedEvent("Walk Dog 2023-10-10 1030 1130 n"))
            .withDatedEvent(DatedEvent.newDatedEvent("Competitive sleeping 2023-10-10 1030 1130 y"))
            .withMeetUpEvent(MeetUpEvent.newMeetUpEvent("CS2103 Meeting 2023-10-10 1030 1130 y",
                    TypicalPersons.ALICE))
            .withMeetUpEvent(MeetUpEvent.newMeetUpEvent("Walk Dog 2023-10-10 1030 1130 n",
                    TypicalPersons.BENSON))
            .withMeetUpEvent(MeetUpEvent.newMeetUpEvent("Competitive sleeping 2023-10-10 1030 1130 y",
                    TypicalPersons.CARL))
            .build();

    public static final Schedule FULL_SCHEDULE = new ScheduleBuilder()
            .withModule(new Module("CS2103", "Monday 0000 2330"))
            .withModule(new Module("CS2101", "Tuesday 0000 2330"))
            .withModule(new Module("CS2100", "Wednesday 0000 2330"))
            .withModule(new Module("CS2103", "Thursday 0000 2330"))
            .withModule(new Module("CS2101", "Friday 0000 2330"))
            .withModule(new Module("CS2100", "Saturday 0000 2330"))
            .withModule(new Module("CS2103", "Sunday 0000 2330"))
            .build();

    public static final Cca NORMAL_CCA = new Cca("Basketball", "Monday 1800 2000");
    public static final Module NORMAL_MODULE = new Module("CS2103", "Wednesday 1200 1300");
    public static final DatedEvent NORMAL_DATED_EVENT = DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y");
    public static final MeetUpEvent NORMAL_MEETUP_EVENT = MeetUpEvent.newMeetUpEvent("CS2103 Meeting 2023-10-10 1030 1130 y",
            TypicalPersons.ALICE);
}
