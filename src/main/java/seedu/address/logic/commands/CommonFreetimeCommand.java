package seedu.address.logic.commands;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.FreeTime.FreeTime;
import seedu.address.model.user.UserData;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Checks for a contact with the same free time as the User 
 * and returns a list of contacts with the same free time.
 */
public class CommonFreetimeCommand extends Command {

    public static final String COMMAND_WORD = "cft";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts with the same free time as the User.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Here are the contacts with the same free time as you: \n";
    public static final String MESSAGE_NO_FREE_TIME = "You have no free time!";
    public static final String MESSAGE_NO_CONTACTS = "You have no contacts with the same free time as you!";

    public Name name = null;

    public CommonFreetimeCommand() {
    }

    public CommonFreetimeCommand(Name name) {
        this.name = name;
    }


    /**
     * Finds common free time between user and person
     * 
     * @param model {@code Model} which the command should operate on.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (this.name == null) {
            return new CommandResult(MESSAGE_NO_FREE_TIME);
        } else {
            try {
                Person friend = model.getPersonWithName(this.name);
                Set<FreeTime> userFreeTime = model.getUser().getFreeTimes();
                Set<FreeTime> friendFreeTime = friend.getFreeTimes();
                Set<FreeTime> commonFreeTime = new HashSet<>();
                for (FreeTime user : userFreeTime) {
                    for (FreeTime friendTime : friendFreeTime) {
                        if (user.hasOverlap(friendTime)) {
                            commonFreeTime.add(user);
                        }
                    }
                }
                if (commonFreeTime.isEmpty()) {
                    return new CommandResult("You and " + friend.getName().toString() + " have no common free time!");
                } else {
                    StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS);
                    for (FreeTime freeTime : commonFreeTime) {
                        sb.append(friend.getName().toString()).append(" is free at ").append(freeTime.toString()).append("\n");
                    }
                    return new CommandResult(sb.toString());
                }
            } catch (Exception e) {
                throw new CommandException(e.getMessage());
            }
        }
    }
    
}
