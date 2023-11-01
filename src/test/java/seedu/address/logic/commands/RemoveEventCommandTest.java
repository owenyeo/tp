package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.MeetUpEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.UserBuilder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class RemoveEventCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void execute_validDated_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        model.getUser().getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103 Meeting", "dated", null);
        String expectedMessage = "Dated Event 'CS2103 Meeting' deleted from your calendar!";
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);
        expectedModel.getUser().getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDated_friend_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        Person friend = model.getFilteredPersonList().get(0);
        friend.getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103 Meeting",
                "dated", Index.fromZeroBased(0));
        String expectedMessage = "Dated Event 'CS2103 Meeting' deleted from Alice Pauline's calendar!";
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);
        Person expectedFriend = expectedModel.getFilteredPersonList().get(0);
        expectedFriend.getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMeetUp_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        model.getUser().getSchedule().addMeetUpEvent(MeetUpEvent.newMeetUpEvent("Walk Dog 2023-10-10 1030 1130 n",
                TypicalPersons.BENSON));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("Walk Dog",
                "meetup", null);
        String expectedMessage = "Meetup Event 'Walk Dog' deleted from your calendar!";
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);
        expectedModel.getUser().getSchedule().addMeetUpEvent(MeetUpEvent.newMeetUpEvent("Walk Dog 2023-10-10 1030 1130 n",
                TypicalPersons.BENSON));

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModule_friend_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        Person friend = model.getFilteredPersonList().get(0);
        friend.getSchedule().addMeetUpEvent(MeetUpEvent.newMeetUpEvent("Walk Dog 2023-10-10 1030 1130 n",
                TypicalPersons.BENSON));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("Walk Dog",
                "meetup", Index.fromZeroBased(0));
        String expectedMessage = "Meetup Event 'Walk Dog' deleted from Alice Pauline's calendar!";
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);
        Person expectedFriend = expectedModel.getFilteredPersonList().get(0);
        expectedFriend.getSchedule().addMeetUpEvent(MeetUpEvent.newMeetUpEvent("Walk Dog 2023-10-10 1030 1130 n",
                TypicalPersons.BENSON));

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEventType_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        model.getUser().getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103 Meeting",
                "sleep", null);

        String expectedMessage = "Invalid event type or name!\n"
                + "Event type must either be 'dated' or 'meetup' and event must be in schedule\n";

        assertCommandFailure(removeEventCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidEventName_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        model.getUser().getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2101",
                "dated", null);

        String expectedMessage = "Invalid event type or name!\n"
                + "Event type must either be 'dated' or 'meetup' and event must be in schedule\n";

        assertCommandFailure(removeEventCommand, model, expectedMessage);
    }

    @Test
    public void listEvents_success() {
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103",
                "dated", null);
        ArrayList<DatedEvent> events = new ArrayList<>();
        events.add(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        events.add(DatedEvent.newDatedEvent("Walk Dog 2023-10-10 1030 1130 n"));
        events.add(DatedEvent.newDatedEvent("Competitive sleeping 2023-10-10 1030 1130 y"));
        String expectedMessage = "DatedEvent: [CS2103 Meeting] on 2023-10-10 [Tuesday 1030 1130] Reminder: Yes\n"
                + "DatedEvent: [Walk Dog] on 2023-10-10 [Tuesday 1030 1130] Reminder: No\n"
                + "DatedEvent: [Competitive sleeping] on 2023-10-10 [Tuesday 1030 1130] Reminder: Yes\n";
        assertEquals(expectedMessage, removeEventCommand.listEvents(events));
    }

    @Test
    public void listEvents_empty() {
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103",
                "dated", null);
        ArrayList<DatedEvent> events = new ArrayList<>();
        String expectedMessage = "";
        assertEquals(expectedMessage, removeEventCommand.listEvents(events));
    }

    // same thing as RemoveScheduleCommand

    // removeschedulecommand has 2 constructors but removeeventcommand only has 1

    // duplicate of listEvents unless necessary

}
