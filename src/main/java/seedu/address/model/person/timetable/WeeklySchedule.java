package seedu.address.model.person.timetable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a WeeklySchedule in the application.
 * Contains a list of weekly events for a person.
 */
public class WeeklySchedule {
    //at its very base, this is a class that contains all the modules for a person.
    //it needs to do a few things: be compared with other weekly schedules to find common freetimes
    //needs to be able to do that for specific days
    private ArrayList<WeeklyEvent> schedule = new ArrayList<>();

    /**
     * Constructs an empty WeeklySchedule object.
     */
    public WeeklySchedule() {
    }

    /**
     * Retrieves the timings for all events on a specific day of the week.
     *
     * @param day The day of the week (e.g., 1 for Monday, 2 for Tuesday, etc.)
     * @return A list of time blocks when events take place on the specified day.
     */
    public List<TimeBlock> getTimingsForDayOfWeek(int day) {
        List<TimeBlock> timeBlocks = new ArrayList<>();
        for (WeeklyEvent event: schedule) {
            timeBlocks.addAll(event.getTimingsForDayOfWeek(day));
        }
        return timeBlocks;
    }
}
