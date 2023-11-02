/**package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.RemoveScheduleCommand;

public class RemoveScheduleCommandParserTest {
    private RemoveScheduleCommandParser parser = new RemoveScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // cca in user
        assertParseSuccess(parser, "user type/cca en/Basketball",
                new RemoveScheduleCommand("Basketball", "cca", null));

        // cca in friend
        assertParseSuccess(parser, "1 type/cca en/Basketball",
                new RemoveScheduleCommand("Basketball", "cca", Index.fromOneBased(1)));

        // module in user
        assertParseSuccess(parser, "user type/module en/CS2103",
                new RemoveScheduleCommand("CS2103", "module", Index.fromOneBased(1)));

        // module in friend
        assertParseSuccess(parser, "1 type/module en/CS2103",
                new RemoveScheduleCommand("CS2103", "module", Index.fromOneBased(1)));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user type/cca en/Basketabll";

        // multiple event type
        assertParseFailure(parser, validCommand + "type/cca",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // multiple event name
        assertParseFailure(parser, validCommand + "en/Basketball",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));

        // multiple fields repeated
        assertParseFailure(parser, validCommand + "typed/cca" + "en/Basketball",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE, PREFIX_EVENTNAME));

        // invalid value followed by valid value

        // invalid type
        assertParseFailure(parser, "typed/cca" + validCommand,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // invalid name
        assertParseFailure(parser, "en/Basketball" + validCommand,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));


        //valid value followed by invalid value

        // invalid type
        assertParseFailure(parser, validCommand + "typed/cca",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // invalid name
        assertParseFailure(parser, validCommand + "en/Basketball",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));
    }

    @Test
    public void parse_fieldMissing_failure() {
        String expectedMessage = String.format("Command format is invalid! \n"
                + AddEventCommand.MESSAGE_USAGE);

        String expectedMessage2 = String.format("Invalid index!\n"
                + "Index can only be 'user' or a 'positive integer!' \n");

        // missing event name prefix
        assertParseFailure(parser, "user type/cca",
                expectedMessage);

        // missing event type prefix
        assertParseFailure(parser, "user en/Basketball",
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "user cca Basketball",
                expectedMessage);

        // wrong index
        // assertParseFailure(parser, "wrong type/cca en/Basketball",
        //        expectedMessage2);
    }

    // same as addEventCommandParser
}
*/