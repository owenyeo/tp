package seedu.address.ui;

import javafx.event.Event;
import javafx.event.EventType;
import seedu.address.model.person.Person;

public class ListCellSelectedEvent extends Event {
    public static final EventType<ListCellSelectedEvent> LIST_CELL_SELECTED =
            new EventType<>(Event.ANY, "LIST_CELL_SELECTED");

    private final Person selectedPerson;

    public ListCellSelectedEvent(Person selectedPerson) {
        super(LIST_CELL_SELECTED);
        this.selectedPerson = selectedPerson;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }
}
