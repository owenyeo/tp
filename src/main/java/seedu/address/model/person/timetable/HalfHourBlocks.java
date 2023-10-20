package seedu.address.model.person.timetable;

/**
 * Represents the half-hour blocks within a day.
 * Each block is represented as a boolean value indicating if that half-hour block is free.
 */
public class HalfHourBlocks {
    private static final int BLOCKS_IN_DAY = 48;
    private final boolean[] blocks = new boolean[BLOCKS_IN_DAY];

    /**
     * Constructs a new HalfHourBlocks with specified start and end half-hour blocks.
     *
     * @param startHalfHour The starting half-hour block (inclusive).
     * @param endHalfHour The ending half-hour block (exclusive).
     */
    public HalfHourBlocks(int startHalfHour, int endHalfHour) {
        for (int i = startHalfHour; i < endHalfHour; i++) {
            blocks[i] = true;
        }
    }

    /**
     * Checks if the current HalfHourBlocks overlaps with another HalfHourBlocks.
     *
     * @param other The other HalfHourBlocks to check against.
     * @return true if there's an overlap, false otherwise.
     */
    public boolean overlaps(HalfHourBlocks other) {
        for (int i = 0; i < BLOCKS_IN_DAY; i++) {
            if (this.blocks[i] && other.blocks[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a new HalfHourBlocks representing the overlap between the current and another HalfHourBlocks.
     *
     * @param other The other HalfHourBlocks to check against.
     * @return A new HalfHourBlocks representing the overlap.
     */
    public HalfHourBlocks getOverlap(HalfHourBlocks other) {
        HalfHourBlocks overlap = new HalfHourBlocks(0, 0);
        for (int i = 0; i < BLOCKS_IN_DAY; i++) {
            overlap.blocks[i] = this.blocks[i] && other.blocks[i];
        }
        return overlap;
    }


    /**
     * Returns a string representation of the HalfHourBlocks object.
     * The string contains the indices of the half-hour blocks that are marked as free time, separated by a hyphen.
     * For example, if blocks 0, 2, and 4 are marked as free time, the string returned will be "0-2-4-".
     *
     * @return A string representation of the HalfHourBlocks object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < BLOCKS_IN_DAY; i++) {
            if ((blocks[i] && blocks[i - 1] == false) || blocks[i] && i == 0) {
                startIndex = i;
            }
            if ((blocks[i] && blocks[i + 1] == false) || blocks[i] && i == BLOCKS_IN_DAY - 1) {
                endIndex = i + 1;
            }
        }
        
        int startTimeInt = startIndex / 2 * 100 + (startIndex % 2) * 30;
        int endTimeInt = endIndex / 2 * 100 + (endIndex % 2) * 30;
        sb.append(startTimeInt).append(" ").append(endTimeInt);
        return sb.toString();
    }
}
