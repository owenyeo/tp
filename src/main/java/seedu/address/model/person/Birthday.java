package seedu.address.model.person;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be in the format of YYYY-MM-DD OR YYYY-M-D and should be a valid date.";
    public static final String VALIDATION_REGEX =
            "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public final LocalDate date;

    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(birthday);
    }

    public static Boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return date.equals(otherBirthday.date);

    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
