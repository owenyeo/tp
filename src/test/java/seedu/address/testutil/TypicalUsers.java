package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.user.User;

/**
 * A utility class containing a list of {@code User} objects to be used in tests.
 */
public class TypicalUsers {

    public static final User ALICE = new UserBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withBirthday("1998-01-01").withSchedule()
            .withTags("friends").build();
    public static final User BENSON = new UserBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withBirthday("1998-01-01")
            .withSchedule()
            .withTags("owesMoney", "friends").build();
    public static final User CARL = new UserBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("wall street").build();
    public static final User DANIEL = new UserBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("10th street")
            .withTags("friends").build();
    public static final User ELLE = new UserBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("michegan ave").build();
    public static final User FIONA = new UserBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("little tokyo").build();
    public static final User GEORGE = new UserBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("4th street").build();

    // Manually added
    public static final User HOON = new UserBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("little india").build();
    public static final User IDA = new UserBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("chicago ave").build();

    public static final User JAMES = new UserBuilder().withName("James Tan").withPhone("99999999")
            .withEmail("james@example.com").withBirthday("1998-01-01")
            .withSchedule().withAddress("utown").build();

    // Manually added - User's details found in {@code CommandTestUtil}
    public static final User AMY = new UserBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withBirthday(VALID_BIRTHDAY_AMY)
            .withSchedule().withTags(VALID_TAG_FRIEND).build();
    public static final User BOB = new UserBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthday(VALID_BIRTHDAY_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalUsers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (User person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<User> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

