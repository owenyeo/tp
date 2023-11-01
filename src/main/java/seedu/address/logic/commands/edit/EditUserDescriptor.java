package seedu.address.logic.commands.edit;

import java.util.ArrayList;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.timetable.DatedEvent;

/**
 * Stores the details to edit the user with. Each non-empty field value will replace the
 * corresponding field value of the user.
 */
public class EditUserDescriptor extends EditPersonDescriptor {
    private ArrayList<DatedEvent> datedEvents;

    public EditUserDescriptor() {
        super();
        datedEvents = new ArrayList<>();
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditUserDescriptor(EditUserDescriptor toCopy) {
        super(toCopy);
        setDatedEvents(toCopy.datedEvents);
    }

    public Optional<ArrayList<DatedEvent>> getDatedEvents() {
        return Optional.ofNullable(datedEvents);
    }

    public void setDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserDescriptor)) {
            return false;
        }

        // state check
        EditUserDescriptor e = (EditUserDescriptor) other;

        return super.equals(e)
                && datedEvents.equals(e.datedEvents);
    }

    public String toString() {
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("address", super.getAddress())
                .add("birthday", super.getBirthday())
                .add("free times", super.getSchedule())
                .add("tags", super.getTags())
                .add("dated events", datedEvents)
                .toString();
    }
}
