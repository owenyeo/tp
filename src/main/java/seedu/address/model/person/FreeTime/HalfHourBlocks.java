package seedu.address.model.person.FreeTime;

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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BLOCKS_IN_DAY; i++) {
            if (blocks[i]) {
                sb.append(i).append("-");
            }
        }
        return sb.toString();
    }
}