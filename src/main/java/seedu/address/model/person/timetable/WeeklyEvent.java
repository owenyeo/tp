package seedu.address.model.person.timetable;

import java.util.ArrayList;

/**
 * Represents a WeeklyEvent in the application.
 * Acts as a base class for different types of weekly events.
 */
public abstract class WeeklyEvent {
    /**
     * Checks if the current event is a module.
     *
     * @return False by default; overridden in subclasses as needed.
     */
    public boolean isModule() {
        return false;
    }

    /**
     * Checks if the current event is a CCA (Co-curricular activity).
     *
     * @return False by default; overridden in subclasses as needed.
     */
    public boolean isCca() {
        return false;
    }

    /**
     * Adds a new timing to the event timings.
     * This is an abstract method and should be implemented by subclasses.
     *
     * @param timeBlockString The string representation of the time block to add.
     */
    public abstract void addTiming(String timeBlockString);

    /**
     * Removes a specified timing from the event timings.
     * This is an abstract method and should be implemented by subclasses.
     *
     * @param timeBlockString The string representation of the time block to remove.
     */
    public abstract void removeTiming(String timeBlockString);

    /**
     * Edits the name of the event.
     * This is an abstract method and should be implemented by subclasses.
     *
     * @param name The new name for the event.
     */
    public abstract void editName(String name);

    /**
     * Retrieves the timings for the event on a specific day of the week.
     * This is an abstract method and should be implemented by subclasses.
     *
     * @param day The day of the week (e.g., 1 for Monday, 2 for Tuesday, etc.)
     * @return A list of time blocks when the event takes place on the specified day.
     */
    public abstract ArrayList<TimeBlock> getTimingsForDayOfWeek(int day);
}
