package seedu.address.model.user;

import seedu.address.model.person.Person;

/**
 * Unmodifiable view of user data.
 */
public interface ReadOnlyUserData {

    /**
     * Returns the user.
     * @return
     */
    Person getUser();
}
