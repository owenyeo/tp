package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.user.ReadOnlyUserData;
import seedu.address.model.user.UserData;
import seedu.address.testutil.PersonBuilder;

public class JsonSerializableUserDataTest {
    @Test
    public void constructor_validJsonAdaptedPerson_success() throws IllegalValueException {
        JsonAdaptedPerson jsonAdaptedPerson = new JsonAdaptedPerson(new PersonBuilder().build());
        JsonSerializableUserData userData = new JsonSerializableUserData(jsonAdaptedPerson);
        assertEquals(new PersonBuilder().build(), userData.toModelType().getUser());
    }

    @Test
    public void constructor_validReadOnlyUserData_success() throws IllegalValueException {
        ReadOnlyUserData readOnlyUserData = new UserData(new PersonBuilder().build());
        JsonSerializableUserData userData = new JsonSerializableUserData(readOnlyUserData);
        assertEquals(new PersonBuilder().build(), userData.toModelType().getUser());
    }

    @Test
    public void toModelType_validJsonSerializableUserData_success() throws IllegalValueException {
        JsonAdaptedPerson jsonAdaptedPerson = new JsonAdaptedPerson(new PersonBuilder().build());
        JsonSerializableUserData userData = new JsonSerializableUserData(jsonAdaptedPerson);
        UserData modelUserData = userData.toModelType();
        assertEquals(new PersonBuilder().build(), modelUserData.getUser());
    }

}
