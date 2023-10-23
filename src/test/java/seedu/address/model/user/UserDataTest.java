package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.PersonBuilder;

public class UserDataTest {
    @Test
    public void setUser_newUser() {
        UserData userData = new UserData(new PersonBuilder().build());
        assertEquals(userData.getUser(), new PersonBuilder().build());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        UserData userData = new UserData();
        assertThrows(NullPointerException.class, () -> userData.resetData(null));
    }

    @Test
    public void resetData_filled() {
        UserData userData = new UserData(new PersonBuilder().build());
        UserData newUserData = new UserData();
        userData.resetData(newUserData);
        assertEquals(userData, new UserData());
    }

    @Test
    public void getUserView() {
        UserData userData = new UserData();
        UniquePersonList userView = new UniquePersonList();
        userView.add(userData.getUser());
        assertEquals(userData.getUserView(), userView.asUnmodifiableObservableList());
    }

    @Test
    public void equals() {
        UserData userData = new UserData(new PersonBuilder().build());

        // same values -> returns true
        assertTrue(userData.equals(new UserData(new PersonBuilder().build())));

        // same object -> returns true
        assertTrue(userData.equals(userData));

        // null -> returns false
        assertFalse(userData.equals(null));

        // different types -> returns false
        assertFalse(userData.equals(5.0f));

        // different values -> returns false
        assertFalse(userData.equals(new UserData()));
    }

    @Test
    public void testHashCode() {
        UserData userData = new UserData(new PersonBuilder().build());
        assertEquals(userData.hashCode(), new UserData(new PersonBuilder().build()).hashCode());
    }

    @Test
    public void testToString() {
        UserData userData = new UserData(new PersonBuilder().build());
        String expected = "User : " + userData.getUser().toString();
        assertEquals(expected, userData.toString());
    }

}
