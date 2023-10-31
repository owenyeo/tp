package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.Schedule;

/**
 * Represents a command to add a schedule to a specified contact.
 * Inherits from the Command class.
 */
public class AddScheduleCommand extends Command {

    public static final String COMMAND_WORD = "addschedule";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a schedule to the specified contact.\n"
            + "Parameters: "
            + "INDEX\n"
            + "type/EVENT_TYPE\n"
            + "en/EVENT_NAME\n"
            + "h/[DAY OF WEEK START_TIME [HHMM] END_TIME [HHMM]]\n"
            + "Example: " + COMMAND_WORD
            + " 1"
            + " type/cca"
            + " en/CS2103T Lecture\n"
            + " h/Monday 1400 1600\n"
            + "NOTE: If you want to add an event to yourself, use index user\n"
            + "Example: " + COMMAND_WORD
            + " user"
            + " type/cca"
            + " en/CS2103T Lecture"
            + " h/Monday 1400 1600";
            
    public static final String MESSAGE_SUCCESS = "New event added: ";

    private final String eventName;
    private final String eventType;
    private final Index index;
    private final String schedule;

    public AddScheduleCommand(String eventName, String eventType, Index index, String schedule) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.index = index;
        this.schedule = schedule;
    }

    public AddScheduleCommand(String eventName, String eventType, String schedule) {
        this(eventName, eventType, null, schedule);
    }
    
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person friend;

        try {
            if (this.index == null) {
                friend = model.getUser();
            } else {
                friend = model.getFilteredPersonList().get(index.getZeroBased());
            }

            Schedule friendSchedule = friend.getSchedule();
            
            switch (eventType) {
                case "cca":
                    friendSchedule.addCca(eventName + " " + schedule);
                    return new CommandResult(MESSAGE_SUCCESS + "\nCCA:\n" + eventName + " " + schedule + " to " + friend.getName());
                case "module":
                    Schedule friendScedule = friend.getSchedule();
                    friendScedule.addModule(eventName + " " + schedule);
                    return new CommandResult(MESSAGE_SUCCESS + "\nModule:\n" + eventName + " " + schedule + " to " + friend.getName());
                default:
                    throw new CommandException("Invalid event type!"
                        + "\n Event type can only be 'Module' or 'CCA'" );
            }
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
