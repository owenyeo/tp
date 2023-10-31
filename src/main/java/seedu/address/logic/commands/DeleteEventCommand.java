package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "rmevent";
    public static final String MESSAGE_USAGE = "rmevent: Removes an event from the calendar.\n"
            + "Parameters: en/EVENT_NAME\n"
            + "Example: " + COMMAND_WORD + " en/CS2103T Lecture";

    private String eventName;

    public DeleteEventCommand(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
