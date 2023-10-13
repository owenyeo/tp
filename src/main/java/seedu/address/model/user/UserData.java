package seedu.address.model.user;

import seedu.address.model.person.Person;

/**
 * Represents a User in the address book.
 */
public class UserData implements ReadOnlyUserData {

    private Person user;

    public UserData() {};

    public UserData(Person user) {
        this.user = user;
    }

    public UserData(ReadOnlyUserData userData) {
        this(userData.getUser());
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void resetData(ReadOnlyUserData newData) {
        this.setUser(newData.getUser());
    }

    @Override
    public Person getUser() {
        return user;
    }
    
}
