package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's free time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFreeTime(String)}
 */
public class FreeTime {

    public static final String MESSAGE_CONSTRAINTS = "Input should be in the format 'DAY HHMM HHMM', \n" +
            "where 'DAY' is a valid day of the week (e.g., Monday, tuesday, WEDNESDAY), \n" +
            "and 'HHMM' represents a valid 24-hour time format (e.g., 0000, 1234, 2359). \n" +
            "Day is case-insensitive.";

    public static final String VALIDATION_REGEX = "^(?i)(monday|tuesday|wednesday|thursday|friday|saturday|sunday) " +
            "([01]?[0-9]|2[0-3])[0-5][0-9] ([01]?[0-9]|2[0-3])[0-5][0-9]$"; //format: (case-insensitive) day 2359 2359

    public final String freeTime;

    /**
     * Constructs a {@code FreeTime}.
     *
     * @param freeTime A valid time.
     */
    public FreeTime(String freeTime) {
        requireNonNull(freeTime);
        checkArgument(isValidFreeTime(freeTime), MESSAGE_CONSTRAINTS);
        this.freeTime = freeTime;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidFreeTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FreeTime)) {
            return false;
        }

        FreeTime otherFreeTime = (FreeTime) other;
        return freeTime.equals(otherFreeTime.freeTime);
    }

    @Override
    public int hashCode() {
        return freeTime.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + freeTime + ']';
    }

}