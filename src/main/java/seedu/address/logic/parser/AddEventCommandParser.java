package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.stream.Stream;

import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddEventCommandParser implements Parser<AddEventCommand> {

    @Override
    public AddEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENTNAME, PREFIX_EVENTTYPE, PREFIX_SCHEDULE, PREFIX_REMINDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE, PREFIX_SCHEDULE, PREFIX_REMINDER)) {
            throw new ParseException(String.format("Command format is invalid! \n" + AddEventCommand.MESSAGE_USAGE));
        }
        String indexString;

            String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get();
            String eventType = argMultimap.getValue(PREFIX_EVENTTYPE).get();
            String schedule = argMultimap.getValue(PREFIX_SCHEDULE).get();
            String reminder = argMultimap.getValue(PREFIX_REMINDER).get();

            indexString = argMultimap.getPreamble().toLowerCase();
            if (indexString.equals("user")) {
                return new AddEventCommand(eventName, schedule, reminder, eventType);
            } else if (Integer.parseInt(indexString) > 0) {
                return new AddEventCommand(eventName, ParserUtil.parseIndex(indexString), schedule, reminder, eventType);
            } else {
                throw new ParseException("Invalid index!\n" 
                    + AddEventCommand.MESSAGE_USAGE);
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
