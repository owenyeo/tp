package seedu.address.testutil;

import seedu.address.model.person.timetable.DatedEvent;

import java.util.ArrayList;

public class DatedEventsBuilder {
    private ArrayList<DatedEvent> datedEvents;

    public DatedEventsBuilder(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
    }

    public DatedEventsBuilder() {
        datedEvents = new ArrayList<>();
    }

    public DatedEventsBuilder withDatedEvent(DatedEvent datedEvent) {
        datedEvents.add(datedEvent);
        return this;
    }

    public ArrayList<DatedEvent> build() {
        return datedEvents;
    }

}
