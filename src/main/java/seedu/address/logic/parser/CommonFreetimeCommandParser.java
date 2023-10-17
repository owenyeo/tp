package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import seedu.address.logic.commands.CommonFreetimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class CommonFreetimeCommandParser implements Parser<CommonFreetimeCommand> {

    @Override
    public CommonFreetimeCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        Name name;
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (Exception e) {
            throw new ParseException(String.format("Wrong command format!", CommonFreetimeCommand.MESSAGE_USAGE));
        }

        return new CommonFreetimeCommand(name);
    }
}