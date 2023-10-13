package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.MealBuddyCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class MealBuddyCommandParserTest {

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertEquals(0,0);
    }

    @Test
    public void parse_validArgs_returnsMealBuddyCommand() {
        assertEquals(0,0);
    }
}
