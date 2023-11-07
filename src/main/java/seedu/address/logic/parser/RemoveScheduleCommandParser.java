package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventCommand object
 */
public class RemoveScheduleCommandParser implements Parser<RemoveScheduleCommand> {

    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n"
            + RemoveScheduleCommand.MESSAGE_USAGE;

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEventCommand
     * and returns a DeleteEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENTNAME, PREFIX_EVENTTYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE)) {
            throw new ParseException(String.format("Command format is invalid! \n"
                + RemoveScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENTNAME, PREFIX_EVENTTYPE);

        String indexString = argMultimap.getPreamble().toLowerCase();
        String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get().toLowerCase();
        String eventType = argMultimap.getValue(PREFIX_EVENTTYPE).get().toLowerCase();
        if (indexString.equals("user")) {
            return new RemoveScheduleCommand(eventName, eventType, null);
        } else {
            try {
                Integer.parseInt(indexString);
                return new RemoveScheduleCommand(eventName, eventType, ParserUtil.parseIndex(indexString));
            } catch (NumberFormatException e) {
                throw new ParseException(String.format("Invalid index!" + "\n"
                        + "Index can only be 'user' or a positive integer! \n"));
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
