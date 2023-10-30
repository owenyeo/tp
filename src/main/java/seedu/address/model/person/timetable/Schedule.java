package seedu.address.model.person.timetable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Represents a schedule consisting of modules, CCAs (Co-Curricular Activities), and dated events.
 */
public class Schedule {
    private final List<Module> modulesList = new ArrayList<>();
    private final List<Cca> ccasList = new ArrayList<>();
    private final List<DatedEvent> datedEventsList = new ArrayList<>();
    private final List<MeetUpEvent> meetUpEventsList = new ArrayList<>();

    public Schedule() {}
    
    /**
     * Creates a new Schedule object.
     */
    public Schedule(List<Module> modulesList, List<Cca> ccasList, List<DatedEvent> datedEventsList, List<MeetUpEvent> meetUpEventsList) {
        this.modulesList.addAll(modulesList);
        this.ccasList.addAll(ccasList);
        this.datedEventsList.addAll(datedEventsList);
        this.meetUpEventsList.addAll(meetUpEventsList);
    }
    /**
     * Retrieves the list of time blocks scheduled for the current week.
     *
     * @return A list of time blocks for the current week.
     */
    public List<TimeBlock> getThisWeeksSchedule() {
        LocalDate today = LocalDate.now();
        LocalDate startOfThisWeek = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        LocalDate endOfThisWeek = startOfThisWeek.plusDays(6);

        List<TimeBlock> thisWeeksSchedule = new ArrayList<>();
        thisWeeksSchedule.addAll(modulesList);
        thisWeeksSchedule.addAll(ccasList);
        for (DatedEvent event : datedEventsList) {
            if (event.getDate().isAfter(startOfThisWeek.minusDays(1))
                    && event.getDate().isBefore(endOfThisWeek.plusDays(1))) {
                thisWeeksSchedule.add(event);
            }
        }
        for (MeetUpEvent event : meetUpEventsList) {
            if (event.getDate().isAfter(startOfThisWeek.minusDays(1))
                    && event.getDate().isBefore(endOfThisWeek.plusDays(1))) {
                thisWeeksSchedule.add(event);
            }
        }
        return thisWeeksSchedule;
    }

    /**
     * Retrieves the list of dated events scheduled for the current week that have reminders.
     *
     * @return A list of dated events with reminders for the current week.
     */
    public List<DatedEvent> getThisWeeksDatedEventsWithReminder() {
        LocalDate today = LocalDate.now();
        LocalDate startOfThisWeek = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        LocalDate endOfThisWeek = startOfThisWeek.plusDays(6);

        List<DatedEvent> thisWeeksDatedEventsWithReminder = new ArrayList<>();
        for (DatedEvent event : datedEventsList) {
            if (event.getDate().isAfter(startOfThisWeek.minusDays(1))
                    && event.getDate().isBefore(endOfThisWeek.plusDays(1))
                    && event.hasReminder()) { // This checks if the event has a reminder set.
                thisWeeksDatedEventsWithReminder.add(event);
            }
        }
        return thisWeeksDatedEventsWithReminder;
    }

    /**
     * Retrieves the list of meet up events scheduled for the current week that have reminders.
     *
     * @return A list of meet up events with reminders for the current week.
     */
    public List<MeetUpEvent> getThisWeeksMeetUpEventsWithReminder() {
        LocalDate today = LocalDate.now();
        LocalDate startOfThisWeek = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        LocalDate endOfThisWeek = startOfThisWeek.plusDays(6);

        List<MeetUpEvent> thisWeeksMeetUpEventsWithReminder = new ArrayList<>();
        for (MeetUpEvent event : meetUpEventsList) {
            if (event.getDate().isAfter(startOfThisWeek.minusDays(1))
                    && event.getDate().isBefore(endOfThisWeek.plusDays(1))
                    && event.hasReminder()) { // This checks if the event has a reminder set.
                thisWeeksMeetUpEventsWithReminder.add(event);
            }
        }
        return thisWeeksMeetUpEventsWithReminder;
    }

    /**
     * Computes the free time slots for the current week.
     *
     * @return A list of free time slots for the current week.
     */
    public List<FreeTime> getThisWeeksFreeTime() {
        boolean[][] timeSlots = new boolean[7][48];
        List<FreeTime> freeTimes = new ArrayList<>();

        List<TimeBlock> thisWeeksScheduleList = getThisWeeksSchedule();
        // Step 2: Mark occupied slots
        for (TimeBlock tb : thisWeeksScheduleList) {
            markSlots(timeSlots, tb);
        }

        // Step 3: Identify free time slots
        for (int day = 0; day < 7; day++) {
            int startSlot = -1;
            for (int slot = 0; slot < 48; slot++) {
                if (!timeSlots[day][slot] && startSlot == -1) {
                    // Start of a free time slot
                    startSlot = slot;
                } else if (timeSlots[day][slot] && startSlot != -1) {
                    // End of a free time slot
                    freeTimes.add(createFreeTime(day, startSlot, slot - 1));
                    startSlot = -1;
                }
            }
            if (startSlot != -1) {
                // Free time slot goes until the end of the day
                freeTimes.add(createFreeTime(day, startSlot, 47));
            }
        }

        return freeTimes;
    }
    

    /**
     * Returns true if there is free time in the schedule for the current week.
     * A schedule has free time if there are any time slots that are not occupied by any events.
     *
     * @return True if there is free time in the schedule for the current week, false otherwise.
     */
    public boolean hasFreeTime() {
        return !getThisWeeksFreeTime().isEmpty();
    }

    /**
     * Marks the time slots corresponding to a given time block as occupied.
     *
     * @param timeSlots 2D array representing the occupied time slots.
     * @param tb The time block to be marked.
     */
    private void markSlots(boolean[][] timeSlots, TimeBlock tb) {
        int dayIndex = tb.getDay().getValue() - 1;

        // Convert start and end times to slot indices
        int startSlot = convertTimeToSlot(tb.getStartTime());
        int endSlot = convertTimeToSlot(tb.getEndTime());

        // Mark all slots between start and end as occupied
        for (int slot = startSlot; slot < endSlot; slot++) {
            timeSlots[dayIndex][slot] = true;
        }
    }

    /**
     * Converts a given time in HHMM format to its corresponding slot index.
     *
     * @param time Time in HHMM format.
     * @return Slot index corresponding to the time.
     */
    private int convertTimeToSlot(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        return hour * 2 + minute / 30;
    }

    /**
     * Converts a given slot index to its corresponding time in HHMM format.
     *
     * @param slot Slot index.
     * @return Time in HHMM format corresponding to the slot.
     */
    private String convertSlotToTime(int slot) {
        int hour = slot / 2; // each slot represents 30 minutes, so 2 slots represent 1 hour
        int minute = (slot % 2) * 30; // remainder will be either 0 or 1, representing either 0 or 30 minutes

        // Convert to HHMM format
        String hourString = (hour < 10) ? "0" + hour : String.valueOf(hour);
        String minuteString = (minute == 0) ? "00" : "30";

        return hourString + minuteString;
    }

    /**
     * Creates a FreeTime object representing a free time slot.
     *
     * @param day Day index (0-6).
     * @param startSlot Start slot index of the free time.
     * @param endSlot End slot index of the free time.
     * @return A FreeTime object.
     */
    private FreeTime createFreeTime(int day, int startSlot, int endSlot) {
        // Convert day and slot indices back to a string representation
        DayOfWeek dayOfWeek = DayOfWeek.of(day + 1);
        String startTime = convertSlotToTime(startSlot);
        String endTime = convertSlotToTime(endSlot);
        return new FreeTime(dayOfWeek.name() + " " + startTime + " " + endTime);
    }

    /**
     * Returns an unmodifiable list of dated events in the schedule.
     *
     * @return List of dated events.
     */
    public List<DatedEvent> getDatedEventsList() {
        return Collections.unmodifiableList(datedEventsList);
    }

    /**
     * Returns an unmodifiable list of meet up events in the schedule.
     *
     * @return List of meet up events.
     */
    public List<MeetUpEvent> getMeetUpEventsList() {
        return Collections.unmodifiableList(meetUpEventsList);
    }

    /** 
     * Returns an unmodifiable list of modules in the schedule.
     *
     * @return List of modules.
     */
    public List<Module> getModulesList() {
        return Collections.unmodifiableList(modulesList);
    }

    /**
     * Returns an unmodifiable list of CCAs in the schedule.
     *
     * @return List of CCAs.
     */
    public List<Cca> getCcasList() {
        return Collections.unmodifiableList(ccasList);
    }

    /**
     * Computes overlapping free time slots with another schedule.
     *
     * @param otherSchedule The other schedule to compare with.
     * @return A list of overlapping free time slots.
     */
    public List<FreeTime> getThisWeeksFreeTimesWith(Schedule otherSchedule) {
        List<FreeTime> myFreeTimes = getThisWeeksFreeTime();
        List<FreeTime> otherFreeTimes = otherSchedule.getThisWeeksFreeTime();

        List<FreeTime> overlappingFreeTimes = new ArrayList<>();

        for (FreeTime myFreeTime : myFreeTimes) {
            for (FreeTime otherFreeTime : otherFreeTimes) {
                if (myFreeTime.isOverlap(otherFreeTime)) {
                    overlappingFreeTimes.add(myFreeTime.overlap(otherFreeTime));
                }
            }
        }

        return overlappingFreeTimes;
    }

    /**
     * Adds a module to the schedule.
     *
     * @param moduleString String representation of the module.
     */
    public void addModule(String moduleString) {
        modulesList.add(Module.newModule(moduleString));
    }

    /**
     * Edits a module in the schedule.
     *
     * @param moduleName Name of the module to be edited.
     * @param timeBlockString String representation of the new module time block.
     */
    public void editModule(String moduleName, String timeBlockString) {
        // Remove all instances of the module with the given name
        modulesList.removeIf(module -> module.getName().equals(moduleName));

        // Add the new module
        modulesList.add(Module.newModule(timeBlockString));
    }

    /**
     * Adds a CCA to the schedule.
     *
     * @param ccaString String representation of the CCA.
     */
    public void addCca(String ccaString) {
        ccasList.add(Cca.newCca(ccaString));
    }

    /**
     * Edits a CCA in the schedule.
     *
     * @param ccaName Name of the CCA to be edited.
     * @param unparsedInput String representation of the new CCA.
     */
    public void editCca(String ccaName, String unparsedInput) {
        // Remove all instances of the CCA with the given name
        ccasList.removeIf(cca -> cca.getCcaName().equals(ccaName));

        // Add the new CCA
        ccasList.add(Cca.newCca(unparsedInput));
    }

    /**
     * Removes a CCA from the schedule.
     *
     * @param ccaName Name of the CCA to be removed.
     */
    public void deleteCca(String ccaName) {
        ccasList.removeIf(cca -> cca.getCcaName().equals(ccaName));
    }

    /**
     * Removes a module from the schedule.
     *
     * @param moduleName Name of the module to be removed.
     */
    public void deleteModule(String moduleName) {
        modulesList.removeIf(module -> module.getName().equals(moduleName));
    }

    /**
     * Adds a dated event to the schedule.
     *
     * @param eventString String representation of the dated event.
     */
    public void addDatedEvent(String eventString) {
        datedEventsList.add(DatedEvent.newDatedEvent(eventString));
    }

    /**
     * Edits a dated event in the schedule.
     *
     * @param eventName Name of the dated event to be edited.
     * @param unparsedInput String representation of the new dated event.
     */
    public void editDatedEvent(String eventName, String unparsedInput) {
        // Remove all instances of the dated event with the given name
        datedEventsList.removeIf(event -> event.getName().equals(eventName));

        // Add the new dated event
        datedEventsList.add(DatedEvent.newDatedEvent(unparsedInput));
    }

    /**
     * Removes a dated event from the schedule.
     *
     * @param eventName Name of the dated event to be removed.
     */
    public void deleteDatedEvent(String eventName) {
        datedEventsList.removeIf(event -> event.getName().equals(eventName));
    }

    /**
     * Adds a meet-up event to the schedule.
     *
     * @param meetUpEventString String representation of the meet-up event.
     */
    public void addMeetUpEvent(String meetUpEventString, Person friend) {
        meetUpEventsList.add(MeetUpEvent.newMeetUpEvent(meetUpEventString, friend));
    }

    /**
     * Removes a meet-up event from the schedule.
     *
     * @param meetUpEventName Name of the meet-up event to be removed.
     */
    public void deleteMeetUpEvent(String meetUpEventName) {
        meetUpEventsList.removeIf(event -> event.getName().equals(meetUpEventName));
    }
}
