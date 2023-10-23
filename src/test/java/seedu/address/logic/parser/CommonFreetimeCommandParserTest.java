package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommonFreetimeCommand;

public class CommonFreetimeCommandParserTest {
    private CommonFreetimeCommandParser parser = new CommonFreetimeCommandParser();

    @Test
    public void parse_emptyArg_returnsCommonFreetimeCommand() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommonFreetimeCommand.MESSAGE_USAGE));
    }

}
