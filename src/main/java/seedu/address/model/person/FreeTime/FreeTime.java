package seedu.address.model.person.FreeTime;

import java.time.DayOfWeek;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's free time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFreeTime(String)}
 */
public class FreeTime implements Comparable<FreeTime> {

    public static final String MESSAGE_CONSTRAINTS = "Input should be in the format 'DAY HHMM HHMM', \n" +
            "where 'DAY' is a valid day of the week (e.g., Monday, tuesday, WEDNESDAY), \n" +
            "and 'HHMM' represents a valid 24-hour time format in half-hour blocks (e.g., 0000, 1230, 2300). \n" +
            "Day is case-insensitive.";

    public static final String VALIDATION_REGEX = "^(?i)(monday|tuesday|wednesday|thursday|friday|saturday|sunday) " +
            "([01]?[0-9]|2[0-3])(00|30) ([01]?[0-9]|2[0-3])(00|30)$"; //format: (case-insensitive) day 2359 2359

    public final String freeTime;
    private final DayOfWeek day;
    private final HalfHourBlocks timeBlocks;

    /**
     * Constructs a {@code FreeTime}.
     *
     * @param freeTime A valid time.
     */
    public FreeTime(String freeTime) {
        requireNonNull(freeTime);
        checkArgument(isValidFreeTime(freeTime), MESSAGE_CONSTRAINTS);

        String[] parts = freeTime.split(" ");
        this.day = DayOfWeek.valueOf(parts[0].toUpperCase());
        int startBlock = Integer.parseInt(parts[1]) / 100 * 2 + (Integer.parseInt(parts[1]) % 100) / 30;
        int endBlock = Integer.parseInt(parts[2]) / 100 * 2 + (Integer.parseInt(parts[2]) % 100) / 30;
        this.timeBlocks = new HalfHourBlocks(startBlock, endBlock);

        //Capitalize the first letter of the day
        String formattedDay = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
        this.freeTime = formattedDay + " " + parts[1] + " " + parts[2];
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
     * Compares this FreeTime instance with another instance.
     * The comparison is primarily based on the day of the week, followed by the start time, and then the end time.
     *
     * @param other The other FreeTime instance to compare against.
     * @return A negative integer, zero, or a positive integer as this FreeTime is less than, equal to, or greater than the specified FreeTime.
     */
    @Override
    public int compareTo(FreeTime other) {
        if (this.day.compareTo(other.day) != 0) {
            return this.day.compareTo(other.day);
        }
        // You can further refine this if needed
        return this.timeBlocks.toString().compareTo(other.timeBlocks.toString());
    }

    /**
     * Checks if the current FreeTime overlaps with another FreeTime.
     * If there's an overlap, returns a new FreeTime representing the overlapping period.
     *
     * @param other The other FreeTime to check against.
     * @return An Optional containing the overlapping FreeTime if it exists, or an empty Optional otherwise.
     */
    public FreeTime overlap(FreeTime other) {
        if (this.day != other.day) {
            return null;
        }

        if (this.timeBlocks.overlaps(other.timeBlocks)) {
            HalfHourBlocks overlapBlocks = this.timeBlocks.getOverlap(other.timeBlocks);
            // You can convert overlapBlocks back to a string representation if needed
            return new FreeTime(day + " " + overlapBlocks.toString());
        }

        return null;
    }


    /*
     * Checks if the current FreeTime overlaps with another FreeTime.
     */
    public boolean isOverlap(FreeTime other) {
        if (this.day != other.day) {
            return false;
        }

        if (this.timeBlocks.overlaps(other.timeBlocks)) {
            return true;
        }

        return false;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + freeTime + ']';
    }

}