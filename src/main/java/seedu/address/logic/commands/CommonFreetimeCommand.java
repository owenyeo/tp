package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.person.timetable.Schedule;

/**
 * Represents a command that finds all contacts with the same free time as the User.
 * A CommonFreetimeCommand object can be created with or without a specified contact name.
 * If no name is specified, the command returns the user's common free time with all contacts.
 * If a name is specified, the command returns the user's common free time with the specified contact.
 * Inherits from the Command class and overrides its execute method.
 */
/**
 * Represents a command to find all contacts with the same free time as the User.
 * If no name is specified, the command returns the user's common free time with all contacts.
 * If a name is specified, the command returns the user's common free time with the specified contact.
 */
public class CommonFreetimeCommand extends Command {

    public static final String COMMAND_WORD = "cft";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all contacts with the same free time as the User.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_COMMON_FREETIME_SUCCESS =
            "Here are the contacts with the same free time as you: \n";
    public static final String MESSAGE_NO_FREE_TIME = "You have no free time!";
    public static final String MESSAGE_NO_CONTACTS = "You have no contacts with the same free time as you!";

    private Name name = null; // name of person (user) to find common free time with

    public CommonFreetimeCommand() {
    }

    public CommonFreetimeCommand(Name name) {
        this.name = name;
    }

    /**
     * Executes the CommonFreetimeCommand to find common free time between user and person.
     * If no name is specified, the command returns the user's common free time with all contacts.
     * If a name is specified, the command returns the user's common free time with the specified contact.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult object that contains the result of executing the command.
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        ArrayList<Person> overlappingContacts = new ArrayList<>();
        Schedule userSchedule = model.getUser().getSchedule();

        // If user has no free time, return error message
        if (!model.getUser().getSchedule().hasFreeTime()) {
            throw new CommandException(MESSAGE_NO_FREE_TIME);
        }
        // If no name is specified, return the user's common free time with all contacts
        if (this.name == null) {
            ObservableList<Person> contacts = model.getAddressBook().getPersonList();
            ArrayList<FreeTime> commonFreeTime = new ArrayList<>();

            getContactsWithFreeTime(contacts, commonFreeTime, overlappingContacts, userSchedule);

            if (commonFreeTime.isEmpty()) {
                throw new CommandException(MESSAGE_NO_CONTACTS);
            } else {
                return new CommandResult(createCommonFreeTimeMessage(commonFreeTime, overlappingContacts).toString());
            }
        } else {   
            try {         
                Person friend = model.getPersonWithName(this.name);
                return new CommandResult(createCommonFreeTimeMessage(model.getUser(), friend).toString());
            } catch (NullPointerException e) {
                 throw new CommandException("There is no such contact in your contacts!");
            } 
        }
    }

    /**
     * Returns a message indicating that the user and the given friend have no common free time.
     *
     * @param friend the friend to check for common free time
     * @return a message indicating that the user and the given friend have no common free time
     */
    public static String createNoOverlapFriendMessage(Person friend) {
        return "You and " + friend.getName().toString() + " have no common free time!";
    }

    /**
     * Retrieves the contacts that have free time in common with the user's schedule, and adds their overlapping free times
     * to the commonFreeTime list.
     * @param contacts The list of contacts to check for overlapping free times.
     * @param commonFreeTime The list to add the overlapping free times to.
     * @param overlappingContacts The list to add the contacts with overlapping free times to.
     * @param userSchedule The user's schedule to compare with the contacts' schedules.
     */
    public void getContactsWithFreeTime(ObservableList<Person> contacts, ArrayList<FreeTime> commonFreeTime, ArrayList<Person> overlappingContacts, Schedule userSchedule) {
        for (Person contact : contacts) {
            Schedule contactSchedule = contact.getSchedule();
            if (userSchedule.getThisWeeksFreeTimesWith(contactSchedule).equals(null)) {
                continue;
            } else {
                overlappingContacts.add(contact);
                commonFreeTime.addAll(userSchedule.getThisWeeksFreeTimesWith(contactSchedule));
            }
        }
    }

    /**
     * Creates a message containing the common free times between two persons.
     *
     * @param user the first person
     * @param friend the second person
     * @return a StringBuilder containing the common free times between the two persons
     * @throws CommandException if there are no common free times between the two persons
     */
    public StringBuilder createCommonFreeTimeMessage(Person user, Person friend) throws CommandException {
        Schedule userSchedule = user.getSchedule();
        Schedule friendSchedule = friend.getSchedule();
        List<FreeTime> commonFreeTimeWithFriend = userSchedule.getThisWeeksFreeTimesWith(friendSchedule);
        if (commonFreeTimeWithFriend.isEmpty()) {
            throw new CommandException(createNoOverlapFriendMessage(friend));
        } else {
            StringBuilder sb = new StringBuilder("You have common free times with "
                + friend.getName().toString()
                + " at:\n");
            for (FreeTime cft : commonFreeTimeWithFriend) {
                sb.append(cft.toString()).append("\n");
            }
            return sb;
        }
    }

    /**
     * Creates a message containing the common free time slots for a list of overlapping contacts.
     * @param commonFreeTime An ArrayList of FreeTime objects representing the common free time slots.
     * @param overlappingContacts An ArrayList of Person objects representing the overlapping contacts.
     * @return A StringBuilder object containing the message with the common free time slots for the overlapping contacts.
     */
    public StringBuilder createCommonFreeTimeMessage(ArrayList<FreeTime> commonFreeTime, ArrayList<Person> overlappingContacts) {
        StringBuilder sb = new StringBuilder(MESSAGE_COMMON_FREETIME_SUCCESS);
        for (Person contactName : overlappingContacts) {
            Name nameOfContact = contactName.getName();
            sb.append(nameOfContact).append(" is free at ");
            for (FreeTime freeTime : commonFreeTime) {
                sb.append(freeTime.toString()).append("\n");
            }
        }
        return sb;
    }

    /**
     * Returns a List<FreeTime> of common free times with specified contact
     * @param model {@code Model} which the command should operate on.
     * @param contactName Name of contact to find common free time with
     * @return List<FreeTime> of common free times with specified contact
     * @throws CommandException if an error occurs during command execution.
     */
    public List<FreeTime> getCommonFreeTimeWith(Model model, Name contactName) throws CommandException {
        Person friend = model.getPersonWithName(contactName);
        Schedule friendSchedule = friend.getSchedule();
        Schedule userSchedule = model.getUser().getSchedule();
        List<FreeTime> commonFreeTime = userSchedule.getThisWeeksFreeTimesWith(friendSchedule);
        if (commonFreeTime.isEmpty()) {
            throw new CommandException(createNoOverlapFriendMessage(friend));
        } else {
            return commonFreeTime;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommonFreetimeCommand)) {
            return false;
        }

        CommonFreetimeCommand otherCommonFreetimeCommand = (CommonFreetimeCommand) other;
        return (name == null && otherCommonFreetimeCommand.name == null)
                || name.equals(otherCommonFreetimeCommand.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .toString();
    }
}
