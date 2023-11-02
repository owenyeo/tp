package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Schedule;


/**
 * Represents a command to remove an event from a contact's calendar.
 * The event can either be a dated event or a meetup event.
 * If the index is "user", the event will be removed from the user's calendar.
 * Otherwise, the event will be removed from the specified contact's calendar.
 */
public class RemoveEventCommand extends Command {

    public static final String COMMAND_WORD = "rmevent";
    public static final String MESSAGE_USAGE =
        "rmevent: Removes an event from the specified contact's calendar.\n"
            + "Parameters: "
            + "INDEX\n"
            + "type/EVENT_TYPE\n"
            + "en/EVENT_NAME\n"
            + "Example: " + COMMAND_WORD
            + " 1"
            + " type/dated"
            + " en/CS2103T Lecture\n"
            + "NOTE: If you want to remove an event from yourself, use index user\n"
            + "Example: " + COMMAND_WORD
            + " user"
            + " type/dated"
            + " en/CS2103T Lecture";

    private final String eventName;
    private final String eventType;
    private final Index index;

    /**
     * Represents a command that removes an event from the address book.
     * The event to be removed is specified by its name, type and index in the list.
     */
    public RemoveEventCommand(String eventName, String eventType, Index index) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.index = index;
    }

    /**
     * Removes an event from the user's schedule or a friend's schedule.
     * The type of event to be removed can either be a dated event or a meetup event.
     * If the index is not null, the event will be removed from the friend's schedule.
     * Otherwise, the event will be removed from the user's schedule.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result, along with other information.
     * @throws CommandException If the event to be removed does not exist or the event type is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Schedule userSchedule = model.getUser().getSchedule();
        try {
            switch (eventType) {
            case "dated":
                if (index == null) {
                    userSchedule.deleteDatedEvent(eventName);
                    return new CommandResult("Dated Event '"
                        + eventName
                        + "' deleted from your calendar!", false, false, true, false);
                } else {
                    Person friend = model.getFilteredPersonList().get(index.getZeroBased());
                    friend.getSchedule().deleteDatedEvent(eventName);
                    return new CommandResult("Dated Event '"
                        + eventName
                        + "' deleted from "
                        + friend.getName().toString()
                        + "'s calendar!", false, false, true, false);
                }
            case "meetup":
                if (index == null) {
                    userSchedule.deleteMeetUpEvent(eventName);
                    return new CommandResult("Meetup Event '"
                        + eventName
                        + "' deleted from your calendar!", false, false, true, false);
                } else {
                    Person friend = model.getFilteredPersonList().get(index.getZeroBased());
                    friend.getSchedule().deleteMeetUpEvent(eventName);
                    return new CommandResult("Meetup Event '"
                        + eventName
                        + "' deleted from "
                        + friend.getName().toString()
                        + "'s calendar!", false, false, true, false);
                }
            default:
                throw new CommandException("Invalid event type!\n"
                    + "Event type must either be 'dated' or 'meetup'!\n");
            }
        } catch (Exception e) {
            throw new CommandException("Event " + eventName + " does not exist!\n"
                + "Please check that you have entered the correct event name!\n");
        }
    }

    /**
     * Returns a string representation of the given list of DatedEvents.
     * Each event's string representation is separated by a newline character.
     *
     * @param events The list of DatedEvents to be converted to a string.
     * @return A string representation of the given list of DatedEvents.
     */
    public String listEvents(List<DatedEvent> events) {
        String result = "";
        for (DatedEvent event : events) {
            result += event.toString() + "\n";
        }
        return result;
    }
}
