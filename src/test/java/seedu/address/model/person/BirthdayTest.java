package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BirthdayTest {
    @Test
    public void equals() {
        Birthday birthday = new Birthday("2001-04-19");
        assertFalse(birthday.equals(null));
    }

    @Test
    public void getDate() {
        Birthday birthday = new Birthday("2001-04-19");
        assertTrue(birthday.getDate().equals(LocalDate.parse("2001-04-19")));
    }
}
