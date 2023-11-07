package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventCommand object
 */
public class RemoveEventCommandParser implements Parser<RemoveEventCommand> {

    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n"
            + RemoveEventCommand.MESSAGE_USAGE;

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEventCommand
     * and returns a DeleteEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENTNAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENTNAME)) {
            throw new ParseException(String.format("Command format is invalid! \n"
                + RemoveEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENTNAME);

        String indexString = argMultimap.getPreamble().toLowerCase();
        String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get().toLowerCase();
        if (indexString.equals("user")) {
            return new RemoveEventCommand(eventName, null);
        } else {
            try {
                Integer.parseInt(indexString);
                return new RemoveEventCommand(eventName, ParserUtil.parseIndex(indexString));
            } catch (NumberFormatException e) {
                throw new ParseException(String.format("Invalid index!" + "\n" +
                        "Index can only be 'user' or a positive integer! \n"));
            }
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
