package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

/**
 * Represents a Module in the application.
 * Contains information about the module name and its timings.
 */
public class Module extends WeeklyEvent {
    private static final String MESSAGE_CONSTRAINTS = "Module Name should be in the format 'AANNNN', \n"
            + "where 'AA' are any two alphabetic characters (e.g., CS, cS, Cs), \n"
            + "and 'NNNN' represents a four-digit number (e.g., 2100, 1001, 0001). \n"
            + "The alphabetic characters are case-insensitive.";
    private static final String VALIDATION_REGEX = "^[a-zA-Z]{2}\\d{4}$";
    private ArrayList<TimeBlock> moduleTimings;
    private String moduleName;
    /**
     * Initializes a new Module with the provided name and timings.
     *
     * @param name The name of the module.
     * @param timeBlocks A list of time blocks representing the module timings.
     */
    public Module(String name, ArrayList<String> timeBlocks) {
        requireNonNull(name);
        requireNonNull(timeBlocks);
        checkArgument(isValidModuleName(name), MESSAGE_CONSTRAINTS);

        this.moduleName = name;

        // Initializing the moduleTimings list
        this.moduleTimings = new ArrayList<>();

        // Converting each string in timeBlocks to an instance of TimeBlock
        // and adding it to moduleTimings
        for (String timeBlockStr : timeBlocks) {
            TimeBlock timeBlock = new TimeBlock(timeBlockStr);
            this.moduleTimings.add(timeBlock);
        }
    }

    /**
     * Adds a new timing to the module's schedule.
     *
     * @param timeBlockString The time block to be added.
     */
    public void addTiming(String timeBlockString) {
        requireNonNull(timeBlockString);
        this.moduleTimings.add(new TimeBlock(timeBlockString));
    }

    /**
     * Removes a timing from the module's schedule.
     *
     * @param timeBlockString The time block to be removed.
     */
    public void removeTiming(String timeBlockString) {
        requireNonNull(timeBlockString);
        for (TimeBlock timeBlock: moduleTimings) {
            if (timeBlock.toString().equals(timeBlockString)) {
                this.moduleTimings.remove(timeBlock);
            }
        }
    }

    /**
     * Edits the name of the module.
     *
     * @param newName The new name for the module.
     */
    public void editName(String newName) {
        requireNonNull(newName);
        checkArgument(isValidModuleName(newName), MESSAGE_CONSTRAINTS);
        this.moduleName = newName;
    }

    /**
     * Checks if the given module name is valid.
     *
     * @param test The module name to check.
     * @return true if the module name is valid, false otherwise.
     */
    private static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the current event is a module.
     *
     * @return True since it is a module.
     */
    @Override
    public boolean isModule() {
        return true;
    }

    /**
     * Checks if the current event is a CCA (Co-curricular activity).
     *
     * @return False since it is not a CCA.
     */
    @Override
    public boolean isCca() {
        return false;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // Append the module name
        builder.append(moduleName);
        builder.append(": "); // separator between module name and timings

        // Append each TimeBlock's toString value
        for (TimeBlock timeBlock : moduleTimings) {
            builder.append(timeBlock.toString());
            builder.append(", "); // separator between timings
        }

        // Remove the last comma and space if there are any TimeBlocks
        if (!moduleTimings.isEmpty()) {
            builder.setLength(builder.length() - 2);
        }

        return builder.toString();
    }

    /**
     * Retrieves the timings for the module on a specific day of the week.
     *
     * @param day The day of the week (e.g., 1 for Monday, 2 for Tuesday, etc.)
     * @return A list of time blocks when the module takes place on the specified day.
     */
    @Override
    public ArrayList<TimeBlock> getTimingsForDayOfWeek(int day) { //monday is 1, tues is 2, etc.
        ArrayList<TimeBlock> dayTimings = new ArrayList<>();
        for (TimeBlock timeBlock : moduleTimings) {
            if (timeBlock.isOnDay(day)) {
                dayTimings.add(timeBlock);
            }
        }
        return dayTimings;
    }
}
