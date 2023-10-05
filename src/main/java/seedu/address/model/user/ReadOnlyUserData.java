package seedu.address.model.user;

import seedu.address.model.person.Person;

public interface ReadOnlyUserData {
    
    /**
     * Returns the user.
     * @return
     */
    Person getUser();
}
