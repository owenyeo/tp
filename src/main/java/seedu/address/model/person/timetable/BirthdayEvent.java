package seedu.address.model.person.timetable;

import seedu.address.model.person.Person;

/**
 * Represents a BirthdayEvent in the application.
 * A specialized event to mark the birthday of a specific person.
 */
public class BirthdayEvent extends DatedEvent {
    private Person birthdayGuy;

    /**
     * Initializes a new BirthdayEvent.
     *
     * @param name Name of the event.
     * @param timeBlockString Time block for the event.
     * @param dateString Date of the event.
     * @param friend Person whose birthday is being celebrated.
     */
    public BirthdayEvent(String name, String timeBlockString,
                       String dateString, Person friend) {
        super(name, timeBlockString, dateString, true);
        this.birthdayGuy = friend;
    }

    public Person getBirthdayGuy(){
        return birthdayGuy;
    }

    //TODO: Find someway to make this event repeatable, such that when the current date is over, it calls the next date.
    //I could actually just save it as a LocalDate without a year
}
