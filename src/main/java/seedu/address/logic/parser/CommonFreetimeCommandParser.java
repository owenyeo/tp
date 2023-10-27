package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.CommonFreetimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new CommonFreetimeCommand object.
 */
public class CommonFreetimeCommandParser implements Parser<CommonFreetimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CommonFreetimeCommand
     * and returns a CommonFreetimeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CommonFreetimeCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        Name name;
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CommonFreetimeCommand.MESSAGE_USAGE));
        }

        return new CommonFreetimeCommand(name);
    }
}
