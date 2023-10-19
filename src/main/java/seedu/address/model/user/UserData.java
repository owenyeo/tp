package seedu.address.model.user;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a User in the address book.
 */
public class UserData implements ReadOnlyUserData {

    private Person user = new Person();

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

    public ObservableList<Person> getUserView() {
        UniquePersonList userView = new UniquePersonList();
        userView.add(user);
        return userView.asUnmodifiableObservableList();
    }

    @Override
    public Person getUser() {
        return user;
    }
    
}
