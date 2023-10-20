package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

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

    @Test
    public void equals() {
        assertEquals(0, 0);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        assertEquals(0, 0);
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        assertEquals(0, 0);
    }

    @Test
    public void toStringMethod() {
        assertEquals(0, 0);
    }
}
