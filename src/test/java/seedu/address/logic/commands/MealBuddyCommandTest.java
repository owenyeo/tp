package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code MealBuddyCommand}.
 */
public class MealBuddyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void equals() {
        assertEquals(0,0);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        assertEquals(0,0);
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        assertEquals(0,0);
    }

    @Test
    public void toStringMethod() {
        assertEquals(0,0);
    }
}
