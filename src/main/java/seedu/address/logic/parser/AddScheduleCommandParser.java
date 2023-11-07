package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddScheduleCommand object.
 */
public class AddScheduleCommandParser implements Parser<AddScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddScheduleCommand
     * and returns an AddScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENTNAME, PREFIX_EVENTTYPE, PREFIX_SCHEDULE);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE, PREFIX_SCHEDULE)) {
            throw new ParseException(String.format("Command format is invalid! \n"
                + AddScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENTNAME, PREFIX_SCHEDULE,
                PREFIX_REMINDER);

        String indexString;

        String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get().toLowerCase();
        String eventType = argMultimap.getValue(PREFIX_EVENTTYPE).get().toLowerCase();
        String schedule = argMultimap.getValue(PREFIX_SCHEDULE).get();

        try {
            indexString = argMultimap.getPreamble().toLowerCase();
        } catch (Exception pe) {
            throw new ParseException(String.format("Please input an index!" + "\n"
                + "Message Usage:\n" + AddScheduleCommand.MESSAGE_USAGE));
        }

        if (indexString.equals("user")) {
            return new AddScheduleCommand(eventName, eventType, schedule);
        } else if (Integer.parseInt(indexString) > 0) {
            return new AddScheduleCommand(eventName, eventType,
                ParserUtil.parseIndex(indexString), schedule);
        } else {
            throw new ParseException("Invalid index!\n"
                + "Index can only be 'user' or a 'positive integer!' \n");
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
