package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Schedule;

/**
 * Represents a command to remove an event from a contact's calendar.
 * The event can be either a dated event or a meetup event.
 * If the index is "user", the event will be removed from the user's calendar.
 * Otherwise, the event will be removed from the specified contact's calendar.
 */
public class RemoveScheduleCommand extends Command {

    public static final String COMMAND_WORD = "rmschedule";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Removes an event from the specified contact's calendar.\n"
        + "Parameters: "
        + "INDEX (must be a positive integer) "
        + "type/EVENT_TYPE (must be either 'cca' or 'module') "
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
     * Constructs a RemoveScheduleCommand object with the specified event name, event type and index.
     * @param eventName The name of the event to be removed.
     * @param eventType The type of the event to be removed.
     * @param index The index of the contact whose calendar the event is to be removed from.
     */
    public RemoveScheduleCommand(String eventName, String eventType, Index index) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.index = index;
    }

    /**
     * Constructs a RemoveScheduleCommand object with the specified event name and event type.
     * @param eventName The name of the event to be removed.
     * @param eventType The type of the event to be removed.
     */
    public RemoveScheduleCommand(String eventName, String eventType) {
        this(eventName, eventType, null);
    }

    /**
     * Executes the RemoveScheduleCommand and removes the specified event from the contact's calendar.
     * @param model The model to execute the command on.
     * @return The CommandResult of the execution.
     * @throws CommandException If an error occurs during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person friend;

        Schedule userSchedule = model.getUser().getSchedule();
        try {
            if (this.index == null) {
                friend = model.getUser();
            } else {
                List<Person> lastShownList = model.getFilteredPersonList();
                if (index.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX + "\n"
                        + "Index can be max " + lastShownList.size() + "!");
                }
                friend = model.getFilteredPersonList().get(index.getZeroBased());
            }
            switch (eventType) {
            // If the event is a cca event, remove it from the specified contact's calendar.
            case "cca":
                if (index == null) {
                    userSchedule.deleteCca(eventName);
                    return new CommandResult("CCA '"
                        + eventName
                        + "'' deleted from your calendar!", false, false, true, false);
                } else {
                    friend = model.getFilteredPersonList().get(index.getZeroBased());
                    friend.getSchedule().deleteCca(eventName);
                    return new CommandResult("Dated Event '"
                        + eventName
                        + "'' deleted from "
                        + friend.getName().toString()
                        + "'s calendar!", false, false, true, false);
                }
            // If the event is a module event, remove it from the specified contact's calendar.
            case "module":
                if (index == null) {
                    userSchedule.deleteModule(eventName);
                    return new CommandResult("Module" + eventName
                        + "'' deleted from your calendar!", false, false, true, false);
                } else {
                    friend = model.getFilteredPersonList().get(index.getZeroBased());
                    friend.getSchedule().deleteModule(eventName);
                    return new CommandResult("Meetup Event '"
                        + eventName
                        + "'' deleted from "
                        + friend.getName().toString()
                        + "'s calendar!", false, false, true, false);
                }
            // If the event type is invalid, throw an exception.
            default:
                throw new CommandException("Invalid event type!\n"
                    + "Event type must either be 'cca' or 'module'!\n");
            }
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Returns a string representation of the specified list of events.
     * @param events The list of events to be represented as a string.
     * @return The string representation of the list of events.
     */
    public String listEvents(List<DatedEvent> events) {
        String result = "";
        for (DatedEvent event : events) {
            result += event.toString() + "\n";
        }
        return result;
    }
}
