package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.timetable.DatedEvent;

/**
 * Set reminders of events in user's timetable that will be sent to user.
 * Keyword matching is case insensitive.
 */
public class SetReminderCommand extends Command {

    public static final String COMMAND_WORD = "setReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Set reminders of events in user's timetable"
            + " that will be sent to user.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103t lesson";

    public static final String MESSAGE_NO_EVENT = "No such event exists!";

    public static final String MESSAGE_SET_REMINDER_SUCCESS = "Reminder set for following event: \n";

    private final String eventName;

    public SetReminderCommand(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<DatedEvent> event = model.getUser().getDatedEvent(eventName);
        if (event.isEmpty()) {
            throw new CommandException(MESSAGE_NO_EVENT);
        } else {
            event.get().setReminder();
            StringBuilder sb = new StringBuilder(MESSAGE_SET_REMINDER_SUCCESS);
            sb.append(event.get().getName());
            return new CommandResult(sb.toString());
        }
    }
}
