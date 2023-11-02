package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddEventCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;
import seedu.address.testutil.UserBuilder;

public class AddEventCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void execute_validDated_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        AddEventCommand addEventCommand = new AddEventCommand("CS2103 Meeting",
                "2023-10-10 1030 1130", "y", "dated");
        String expectedMessage = MESSAGE_SUCCESS + ("\nDated Event:\n" + "CS2103 Meeting"
                + " " + "2023-10-10 1030 1130" + " to " + newUser.getName());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(addEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDated_friendSuccess() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        Person friend = model.getFilteredPersonList().get(0);
        AddEventCommand addEventCommand = new AddEventCommand("CS2103 Meeting",
                Index.fromZeroBased(0), "2023-10-10 1030 1130", "y", "dated");
        String expectedMessage = MESSAGE_SUCCESS + ("\nDated Event:\n" + "CS2103 Meeting"
                + " " + "2023-10-10 1030 1130" + " to " + friend.getName());
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(addEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMeetUpUser_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        assertThrows(CommandException.class, () -> new AddEventCommand("CS2103 Meeting",
                "2023-10-10 1030 1130", "y", "meetup"));
    }

    @Test
    public void execute_validMeetUpFriend_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        Person friend = model.getFilteredPersonList().get(0);
        AddEventCommand addEventCommand = new AddEventCommand("Walk Dog",
                Index.fromZeroBased(0), "2023-10-10 1030 1130", "n", "meetup");
        String expectedMessage = MESSAGE_SUCCESS + "\nMeet up event\n" + "Walk Dog"
                + " " + "2023-10-10 1030 1130" + " with " + friend.getName();

        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(addEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEventType_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        AddEventCommand addEventCommand = new AddEventCommand("Walk Dog",
                Index.fromZeroBased(0), "2023-10-10 1030 1130", "n", "sleep");

        String expectedMessage = "Invalid event type!" + "\n Event type can only be 'Dated' or 'Meetup'";

        assertCommandFailure(addEventCommand, model, expectedMessage);
    }

    //invalid dated event with yourself bug in addeventcommand
    // ((friend.getName().toString().toLowerCase().equals("user")))
}
