package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventCommand object
 */
public class RemoveEventCommandParser implements Parser<RemoveEventCommand> {
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
            List<Prefix> missingPrefix = getMissingPrefixes(argMultimap, PREFIX_EVENTNAME);
            String missingPrefixString = "";
            for (Prefix prefix : missingPrefix) {
                missingPrefixString += prefix + " ";
            }
            throw new ParseException(String.format("Missing prefix(es) for %s!\n"
                + "Message Usage:\n" + RemoveEventCommand.MESSAGE_USAGE, missingPrefixString));
        }


        if (!arePrefixesUnique(argMultimap, PREFIX_EVENTNAME)) {
            List<Prefix> duplicatePrefix = getDuplicatePrefixes(argMultimap, PREFIX_EVENTNAME);
            String duplicatePrefixString = "";
            for (Prefix prefix : duplicatePrefix) {
                duplicatePrefixString += prefix + " ";
            }
            throw new ParseException(String.format("You can only have 1 of each prefix!\n"
                + "Duplicated prefixes are: " + duplicatePrefixString));
        }

        String indexString;
        try {
            indexString = argMultimap.getPreamble().toLowerCase();
            String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get().toLowerCase();
            if (indexString.equals("user")) {
                return new RemoveEventCommand(eventName, null);
            } else if (Integer.parseInt(indexString) > 0) {
                return new RemoveEventCommand(eventName, ParserUtil.parseIndex(indexString));
            } else {
                throw new ParseException(String.format("Invalid index!" + "\n"
                        + "Index can only be 'user' or a positive integer! \n"));
            }
        } catch (Exception pe) {
            throw new ParseException(
                    String.format("Please input an index!\n"
                            + "Message Usage:\n" + RemoveEventCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


    /**
     * Returns true if there are duplicate prefixes
     * @param argumentMultimap
     * @param prefixes
     */
    private static boolean arePrefixesUnique(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() == 1);
    }

    /**
     * Returns the prefixes that is not present in the given {@code ArgumentMultimap}.
     * @throws ParseException if the user input does not conform the expected format
     */
    private static List<Prefix> getMissingPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Returns the prefixes that are not unique in the given {@code ArgumentMultimap}.
     */
    private static List<Prefix> getDuplicatePrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getAllValues(prefix).size() > 1)
            .collect(java.util.stream.Collectors.toList());
    }
}
