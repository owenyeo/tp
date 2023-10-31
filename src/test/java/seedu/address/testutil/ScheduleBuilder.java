package seedu.address.testutil;

import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.MeetUpEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;

public class ScheduleBuilder {

    private Schedule schedule;

    public ScheduleBuilder() {
        schedule = new Schedule();
    }

    public ScheduleBuilder(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Adds a new {@code Module} to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withModule(Module module) {
        schedule.addModule(module);
        return this;
    }

    /**
     * Adds a new {@code Cca} to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withCca(Cca cca) {
        schedule.addCca(cca);
        return this;
    }

    /**
     * Adds a new {@code DatedEvent} to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withDatedEvent(DatedEvent datedEvent) {
        schedule.addDatedEvent(datedEvent);
        return this;
    }

    /**
     * Adds a new {@code MeetUpEvent} to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withMeetUpEvent(MeetUpEvent meetUpEvent) {
        schedule.addMeetUpEvent(meetUpEvent);
        return this;
    }

    public Schedule build() {
        return schedule;
    }
    
}
