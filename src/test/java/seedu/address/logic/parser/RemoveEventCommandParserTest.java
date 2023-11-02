package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.RemoveEventCommand;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class RemoveEventCommandParserTest {
    private RemoveEventCommandParser parser = new RemoveEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // dated event in user
        assertParseSuccess(parser, "user type/dated en/CS2103 Meeting",
                new RemoveEventCommand("CS2103 Meeting", "dated", null));

        // dated event in friend
        assertParseSuccess(parser, "1 type/dated en/CS2103",
                new RemoveEventCommand("CS2103 Meeting", "dated", Index.fromOneBased(1)));

        // meetup event in user
        assertParseSuccess(parser, "1 type/meetup en/CS2103 Meeting",
                new RemoveEventCommand("CS2103 Meeting","meetup", Index.fromOneBased(1)));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user type/dated en/CS2103 Meeting";

        // multiple event type
        assertParseFailure(parser, validCommand + "type/dated",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // multiple event name
        assertParseFailure(parser, validCommand + "en/CS2103 Meeting",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));

        // multiple fields repeated
        assertParseFailure(parser, validCommand + "typed/dated" + "en/CS2103 Meeting",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE, PREFIX_EVENTNAME));

        // invalid value followed by valid value

        // invalid type
        assertParseFailure(parser, "typed/dated" + validCommand,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // invalid name
        assertParseFailure(parser, "en/CS2103 Meeting" + validCommand,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));


        //valid value followed by invalid value

        // invalid type
        assertParseFailure(parser, validCommand + "typed/dated",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // invalid name
        assertParseFailure(parser, validCommand + "en/CS2103 Meeting",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));
    }

    @Test
    public void parse_fieldMissing_failure() {
        String expectedMessage = String.format("Command format is invalid! \n" +
                AddEventCommand.MESSAGE_USAGE);

        String expectedMessage2 = String.format("Invalid index!\n"
                + "Index can only be 'user' or a 'positive integer!' \n");

        // missing event name prefix
        assertParseFailure(parser, "user type/dated h/2023-10-10 1030 1130 r/y",
                expectedMessage);

        // missing event type prefix
        assertParseFailure(parser, "user en/CS2103 Meeting h/2023-10-10 1030 1130 r/y",
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "user dated CS2103 Meeting",
                expectedMessage);

        // wrong index
        // assertParseFailure(parser, "wrong type/dated en/CS2103 Meeting",
        //        expectedMessage2);
    }

    // same as addEventCommandParser
}
