package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Schedule;

public class DeleteEventCommand extends Command {

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
            + " en/CS2103T Lecture"
            + "NOTE: If you want to remove an event from yourself, use index user\n"
            + "Example: " + COMMAND_WORD
            + " user"
            + " type/dated"
            + " en/CS2103T Lecture";

    private final String eventName;
    private final String eventType;
    private final Index index;

    public DeleteEventCommand(String eventName, String eventType, Index index) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Schedule userSchedule = model.getUser().getSchedule();
        try {
            switch (eventType) {
                case "dated":
                    if (index == null) {
                        userSchedule.deleteDatedEvent(eventName);
                    } else {
                        Person friend = model.getFilteredPersonList().get(index.getZeroBased());
                        friend.getSchedule().deleteDatedEvent(eventName);
                    }
                case "meetup":
                    if (index == null) {
                        userSchedule.deleteMeetUpEvent(eventName);
                    } else {
                        Person friend = model.getFilteredPersonList().get(index.getZeroBased());
                        friend.getSchedule().deleteMeetUpEvent(eventName);
                    }
                default:
                    throw new CommandException("Invalid event type!\n"
                        + "Event type must either be 'dated' or 'meetup'!\n");
            }
        } catch (Exception e) {
            throw new CommandException("Event does not exist!\n"
                + "Please check that you have entered the correct event name!\n");
        }
    }
}
