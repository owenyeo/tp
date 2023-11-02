package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddScheduleCommand;

public class AddScheduleCommandParserTest {
    private AddScheduleCommandParser parser = new AddScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // cca in user
        assertParseSuccess(parser, "user type/cca en/Basketball h/Monday 1030 1130",
                new AddScheduleCommand("Basketball", "cca", "Monday 1030 1130"));

        // cca in friend
        assertParseSuccess(parser, "1 type/cca en/Basketball h/Monday 1030 1130",
                new AddScheduleCommand("Basketball", "cca", Index.fromOneBased(1),
                        "Monday 1030 1130"));

        // module in user
        assertParseSuccess(parser, "user type/module en/CS2103 h/Monday 1030 1130 r/y",
                new AddScheduleCommand("CS2103", "module", "Monday 1030 1130"));

        // module in friend
        assertParseSuccess(parser, "1 type/module en/CS2103 h/Monday 1030 1130 r/y",
                new AddScheduleCommand("CS2103", "module", Index.fromOneBased(1),
                        "Monday 1030 1130"));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user type/cca en/Basketball h/Monday 1030 1130";

        // multiple event type
        assertParseFailure(parser, validCommand + "type/cca",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // multiple event name
        assertParseFailure(parser, validCommand + "en/CS2103",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTNAME));

        // multiple event schedule
        assertParseFailure(parser, validCommand + "h/Monday 1030 1130",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SCHEDULE));

        // invalid value followed by valid value

        // invalid type
        assertParseFailure(parser, "typed/cca" + validCommand,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // invalid schedule
        assertParseFailure(parser, "h/Monday 1030 1145" + validCommand,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SCHEDULE));


        //valid value followed by invalid value

        // invalid type
        assertParseFailure(parser, validCommand + "typed/cca",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENTTYPE));

        // invalid schedule
        assertParseFailure(parser, validCommand + "h/Monday 1030 1145",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SCHEDULE));

    }

    @Test
    public void parse_fieldMissing_failure() {
        String expectedMessage = String.format("Command format is invalid! \n"
                + AddEventCommand.MESSAGE_USAGE);

        String expectedMessage2 = String.format("Invalid index!\n"
                + "Index can only be 'user' or a 'positive integer!' \n");

        // missing schedule name prefix
        assertParseFailure(parser, "user type/cca en/Basketball",
                expectedMessage);

        // missing schedule type prefix
        assertParseFailure(parser, "user en/Basketball h/Monday 1030 1130",
                expectedMessage);

        // missing schedule prefix
        assertParseFailure(parser, "user type/cca en/Basketball",
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "user cca Basketball Monday 1030 1130",
                expectedMessage);

        // wrong index
        // assertParseFailure(parser, "wrong type/dated en/CS2103 Meeting h/2023-10-10 1030 1130 r/y",
        //        expectedMessage2);
    }

    // refer to addeventcommand parser

}
