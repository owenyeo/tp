package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in the application.
 * Contains information about the module name and its timings.
 */
public class Module extends TimeBlock {
    public static final String MESSAGE_CONSTRAINTS = "Module Name should be in the format 'AANNNN', \n"
            + "where 'AA' are any two alphabetic characters (e.g., CS, cS, Cs), \n"
            + "and 'NNNN' represents a four-digit number (e.g., 2100, 1001, 0001). \n"
            + "The alphabetic characters are case-insensitive.";
    private static final String VALIDATION_REGEX = "^[a-zA-Z]{2}\\d{4}$";
    private final String moduleName;

    /**
     * Constructs a {@code Module} with the specified name and time block.
     *
     * @param name The name of the module. Should follow the format described in MESSAGE_CONSTRAINTS.
     * @param timeBlockString The time block for the module. Expected format: DAY HHMM HHMM.
     */
    public Module(String name, String timeBlockString) { //name = name, timeBlockString = DAY HHMM HHMM
        super(timeBlockString);
        requireNonNull(name);
        checkArgument(isValidModuleName(name), MESSAGE_CONSTRAINTS);

        this.moduleName = name;
    }

    /**
     * Factory method to create a new Module object from a given unparsed input string.
     * Expected format for the input string: NAME DAY HHMM HHMM.
     *
     * @param unparsedInput The input string containing module details.
     * @return A new Module object.
     * @throws IllegalArgumentException If the given input does not adhere to the expected format.
     */
    public static Module newModule(String unparsedInput) {
        requireNonNull(unparsedInput);

        // Split the unparsed input string by whitespace
        String[] parts = unparsedInput.split("\\s+");

        // Check for valid number of parts
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid module input format. Expected: NAME DAY HHMM HHMM");
        }

        String name = parts[0];
        String day = parts[1];
        String startTime = parts[2];
        String endTime = parts[3];
        String timeBlockString = day + " " + startTime + " " + endTime;

        // Check for valid module name format
        if (!isValidModuleName(name)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        return new Module(name, timeBlockString);
    }

    /**
     * Creates a new Module object with the given name while retaining the timings from the original module.
     *
     * @param newName The new name for the module.
     * @return A new Module object with the updated name.
     * @throws IllegalArgumentException If the given new name does not adhere to the module naming constraints.
     */
    public Module editName(String newName) {
        requireNonNull(newName);
        checkArgument(isValidModuleName(newName), MESSAGE_CONSTRAINTS);
        return new Module(newName, super.getTimeBlockString());
    }

    /**
     * Returns the name of the module.
     *
     * @return The name of the module.
     */
    public String getName() {
        return moduleName;
    }

    /**
     * Checks if the given module name is valid.
     *
     * @param test The module name to check.
     * @return true if the module name is valid, false otherwise.
     */
    public static boolean isValidModuleName(String test) {
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
     * Returns a Json readable string representation of the instance of the module.
     *
     * @return String that is readable by JsonAdaptableModule.
     */
    public String toJsonString() {
        return "{"
                + "\"name\": \"" + moduleName + "\","
                + "\"timeBlock\": \"" + super.toString() + "\""
                + "}";
    }

    @Override
    public String toString() {
        return "Module: [" + moduleName + "] " + super.toString();
    }
}
