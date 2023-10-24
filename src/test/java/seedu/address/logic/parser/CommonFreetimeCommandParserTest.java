package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommonFreetimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CommonFreetimeCommandParserTest {
    private CommonFreetimeCommandParser parser = new CommonFreetimeCommandParser();

    @Test
    public void parse_emptyArg_returnsCommonFreetimeCommand() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommonFreetimeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCommonFreetimeCommand() throws ParseException {
        assertParseSuccess(parser, " n/Alice Pauline",
                new CommonFreetimeCommand(ParserUtil.parseName("Alice Pauline")));
    }

}
