package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//guarantee immutability
public class Cca extends TimeBlock {
    private static final String MESSAGE_CONSTRAINTS =
            "Cca name should only contain alphanumeric characters and spaces, and it should not be blank";
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private final String ccaName;

    public Cca(String name, String timeBlockString) {
        super(timeBlockString);

        requireNonNull(name);
        checkArgument(isValidCcaName(name), MESSAGE_CONSTRAINTS);

        this.ccaName = name;
    }

    public static Cca newCca(String unparsedInput) {
        requireNonNull(unparsedInput);

        // Split the unparsed input string based on the last three whitespace occurrences
        String[] parts = unparsedInput.split("\\s+");

        // Check for valid number of parts (at least 4 parts are required)
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid CCA input format. Expected: CCA_NAME DAY HHMM HHMM");
        }

        // Extracting the last three parts for day, startTime, and endTime
        String day = parts[parts.length - 3];
        String startTime = parts[parts.length - 2];
        String endTime = parts[parts.length - 1];

        // Constructing the CCA name by joining the earlier parts
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < parts.length - 3; i++) {
            nameBuilder.append(parts[i]);
            if (i < parts.length - 4) {
                nameBuilder.append(" ");  // Add space between words in the CCA name
            }
        }
        String name = nameBuilder.toString();
        String timeBlockString = day + " " + startTime + " " + endTime;

        // Check for valid CCA name format
        if (!isValidCcaName(name)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        // Further validations can be added (e.g., for valid day, startTime, endTime) if necessary

        return new Cca(name, timeBlockString);
    }

    //@Override
    public boolean isCca() {
        return true;
    }

    public static boolean isValidCcaName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getCcaName() {
        String name = ccaName;
        return name;
    }

    @Override
    public String toString() {
        return ccaName + " " + super.toString();
    }

    public String toJsonString() {
        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("{");
        jsonBuilder.append("\"ccaName\": \"").append(ccaName).append("\",");
        jsonBuilder.append("\"timeBlock\": \"").append(super.toString()).append("\"");
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }

}
