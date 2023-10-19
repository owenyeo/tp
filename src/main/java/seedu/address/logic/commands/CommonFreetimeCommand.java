package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.FreeTime;

/**
 * Represents a command that finds all contacts with the same free time as the User.
 * A CommonFreetimeCommand object can be created with or without a specified contact name.
 * If no name is specified, the command returns the user's common free time with all contacts.
 * If a name is specified, the command returns the user's common free time with the specified contact.
 * Inherits from the Command class and overrides its execute method.
 */
public class CommonFreetimeCommand extends Command {

    public static final String COMMAND_WORD = "cft";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all contacts with the same free time as the User.\n"
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
        ArrayList<Person> overlappingContacts= new ArrayList<>();

        // If user has no free time, return error message
        if (model.getUser().getFreeTimes().isEmpty()) {
            return new CommandResult(MESSAGE_NO_FREE_TIME);
        }
        // If no name is specified, return the user's common free time with all contacts
        if (this.name == null) {
            Set<FreeTime> userFreeTime = model.getUser().getFreeTimes();
            ObservableList<Person> contacts = model.getAddressBook(). getPersonList();
            Set<FreeTime> commonFreeTime = new HashSet<>();
            for (Person contact : contacts) {
                Set<FreeTime> contactFreeTime = contact.getFreeTimes();
                for (FreeTime userTime : userFreeTime) {
                    for (FreeTime contactTime : contactFreeTime) {
                        if (userTime.isOverlap(contactTime)) {
                            commonFreeTime.add(userTime.overlap(contactTime));
                            overlappingContacts.add(contact);
                        }
                    }
                }
            }
            if (commonFreeTime.isEmpty()) {
                return new CommandResult(MESSAGE_NO_CONTACTS);
            } else {
                StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS);
                int i = 0;
                for (FreeTime freeTime : commonFreeTime) {
                    sb.append(overlappingContacts.get(i).getName()).append(" is free at ");
                    sb.append(freeTime.toString()).append("\n");
                    i++;
                }
                return new CommandResult(sb.toString());
            }
        } else {
            try {
                Person friend = model.getPersonWithName(this.name);
                Set<FreeTime> userFreeTime = model.getUser().getFreeTimes();
                Set<FreeTime> friendFreeTime = friend.getFreeTimes();
                Set<FreeTime> commonFreeTime = new HashSet<>();
                for (FreeTime userTime : userFreeTime) {
                    for (FreeTime friendTime : friendFreeTime) {
                        if (userTime.isOverlap(friendTime)) {
                            commonFreeTime.add(userTime.overlap(friendTime));
                        }
                    }
                }
                if (commonFreeTime.isEmpty()) {
                    return new CommandResult("You and " + friend.getName().toString() + " have no common free time!");
                } else {
                    StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS);
                    for (FreeTime freeTime : commonFreeTime) {
                        sb.append(friend.getName().toString()).append(" is free at ")
                                .append(freeTime.toString()).append("\n");
                    }
                    return new CommandResult(sb.toString());
                }
            } catch (Exception e) {
                throw new CommandException(e.getMessage());
            }
        }
    }
}
