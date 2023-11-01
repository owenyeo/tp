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
 * The event can be either a dated event or a meetup event.
 * If the index is "user", the event will be removed from the user's calendar.
 * Otherwise, the event will be removed from the specified contact's calendar.
 */
public class RemoveScheduleCommand extends Command {

    public static final String COMMAND_WORD = "rmschedule";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an event from the specified contact's calendar.\n"
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

    public RemoveScheduleCommand(String eventName, String eventType, Index index) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.index = index;
    }

    public RemoveScheduleCommand(String eventName, String eventType) {
        this(eventName, eventType, null);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Schedule userSchedule = model.getUser().getSchedule();
        try {
            switch (eventType) {
                case "cca":
                    if (index == null) {
                        userSchedule.deleteCca(eventName);
                        return new CommandResult("CCA '" + eventName + "'' deleted from your calendar!", false, false, true, false);
                    } else {
                        Person friend = model.getFilteredPersonList().get(index.getZeroBased());
                        friend.getSchedule().deleteCca(eventName);
                        return new CommandResult("Dated Event '" + eventName + "'' deleted from "
                            + friend.getName().toString() + "'s calendar!", false, false, true, false);
                    }
                case "module":
                    if (index == null) {
                        userSchedule.deleteModule(eventName);
                        return new CommandResult("Module" + eventName + "'' deleted from your calendar!", false, false, true, false);
                    } else {
                        Person friend = model.getFilteredPersonList().get(index.getZeroBased());
                        friend.getSchedule().deleteModule(eventName);
                        return new CommandResult("Meetup Event '" + eventName + "'' deleted from "
                            + friend.getName().toString() + "'s calendar!", false, false, true, false);
                    }
                default:
                    throw new CommandException("Invalid event type!\n"
                        + "Event type must either be 'cca' or 'module'!\n");
            }
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    public String listEvents(List<DatedEvent> events) {
        String result = "";
        for (DatedEvent event : events) {
            result += event.toString() + "\n";
        }
        return result;
    }
}
