package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditUserCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void execute_allFieldsSpecified_success() {
        Person newUser = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(newUser).build();
        EditUserCommand editUserCommand = new EditUserCommand(descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_USER_SUCCESS, Messages.format(newUser));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Person newUser = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(newUser).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditUserCommand editUserCommand = new EditUserCommand(descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_USER_SUCCESS, Messages.format(newUser));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateUser_failure() {
        Person user = model.getUser();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(user).build();
        EditUserCommand editUserCommand = new EditUserCommand(descriptor);

        assertCommandFailure(editUserCommand, model, EditUserCommand.MESSAGE_DUPLICATE_USER);
    }

    @Test
    public void equals() {
        final EditUserCommand standardCommand = new EditUserCommand(DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditUserCommand commandWithSameValues = new EditUserCommand(copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditUserCommand(DESC_BOB));
    }

    @Test
    public void toStringMethod_nullEditUserDescriptor() {
        EditPersonDescriptor editUserDescriptor = new EditPersonDescriptor();
        EditUserCommand editUserCommand = new EditUserCommand(editUserDescriptor);
        String expected = EditUserCommand.class.getCanonicalName() + "{" + "editUserDescriptor="
                + editUserDescriptor + "}";
        assertEquals(expected, editUserCommand.toString());
    }

    @Test
    public void toStringMethod_filledEditPersonDescriptor() {
        EditPersonDescriptor editUserDescriptor = DESC_BOB;
        EditUserCommand editUserCommand = new EditUserCommand(editUserDescriptor);
        String expected = EditUserCommand.class.getCanonicalName() + "{" + "editUserDescriptor="
                + editUserDescriptor + "}";
        assertEquals(expected, editUserCommand.toString());
    }

}
