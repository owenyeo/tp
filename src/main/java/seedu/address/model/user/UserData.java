package seedu.address.model.user;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

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

    /**
     * Replaces the contents of the user with {@code neData}.
     * {@code newData} must not be null.
     */
    public void resetData(ReadOnlyUserData newData) {
        requireNonNull(newData);
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserData)) {
            return false;
        }

        UserData otherUserData = (UserData) other;
        return user.equals(otherUserData.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User : " + user.toString());
        return sb.toString();
    }

}
