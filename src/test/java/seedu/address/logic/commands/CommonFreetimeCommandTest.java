package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalUsers.JAMES;
import static seedu.address.testutil.TypicalUsers.JANE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code CommonFreeTimeCommand}.
 */

public class CommonFreetimeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @BeforeEach
    public void setUp() {
        model.setUser(JANE);
        expectedModel.setUser(JANE);
    }

    @Test
    public void execute_userNoFreetime_failure() {
        model.setUser(JAMES);
        expectedModel.setUser(JAMES);
        CommonFreetimeCommand commonFreetimeCommand = new CommonFreetimeCommand();
        assertCommandFailure(commonFreetimeCommand, model, CommonFreetimeCommand.MESSAGE_NO_CONTACTS);
    }

    @Test
    public void execute_nameFriend_success() {
        CommonFreetimeCommand commonFreetimeCommand = new CommonFreetimeCommand(Index.fromOneBased(2));
        String expectedMessage = "You have common free times with Benson Meier at:" + "\n"
                + "[Monday 0000 1730]" + "\n" + "[Monday 2000 2330]" + "\n"
                + "[Tuesday 0000 2330]" + "\n" + "[Wednesday 0000 1130]" + "\n" + "[Wednesday 1300 2330]" + "\n"
                + "[Thursday 0000 2330]" + "\n" + "[Friday 0000 2330]" + "\n" + "[Saturday 0000 2330]" + "\n"
                + "[Sunday 0000 2330]" + "\n";
        assertCommandSuccess(commonFreetimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final CommonFreetimeCommand standardCommand = new CommonFreetimeCommand(Index.fromOneBased(1));

        final CommonFreetimeCommand standardAllCommand = new CommonFreetimeCommand();
        // same values -> returns true
        CommonFreetimeCommand commandWithSameValues = new CommonFreetimeCommand(Index.fromOneBased(1));
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        assertEquals(new CommonFreetimeCommand(), standardAllCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new CommonFreetimeCommand(Index.fromOneBased(2)));
    }

    @Test
    public void toStringMethod() {
        CommonFreetimeCommand commonFreetimeCommandAlice = new CommonFreetimeCommand(Index.fromOneBased(1));
        String expected = CommonFreetimeCommand.class.getCanonicalName()
                + "{index=" + Index.class.getCanonicalName() + "{zeroBasedIndex=" + 0 + "}}";
        assertEquals(expected, commonFreetimeCommandAlice.toString());
    }
}
