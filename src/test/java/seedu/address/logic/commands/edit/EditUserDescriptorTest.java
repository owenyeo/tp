package seedu.address.logic.commands.edit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EditUserDescriptorTest {

    @Test
    public void equals() {
        EditUserDescriptor editUserDescriptor = new EditUserDescriptor();
        assertEquals(editUserDescriptor, editUserDescriptor);

        assertNotEquals(editUserDescriptor, null);

    }
}
