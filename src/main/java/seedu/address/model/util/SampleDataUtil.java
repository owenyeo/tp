package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.person.timetable.MeetUpEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.ReadOnlyUserData;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Birthday("2023-10-30"),
                new Schedule(),
                 getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Birthday("2023-10-30"),
                new Schedule(),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Birthday("2000-01-01"),
                new Schedule(),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Birthday("2000-01-01"),
                new Schedule(),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Birthday("2000-01-01"),
                new Schedule(),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Birthday("2000-01-01"),
                new Schedule(),
                getTagSet("colleagues"))
        };
    }

    public static List<Module> getSampleModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(new Module("CS2103", "Wednesday 1200 1300"));
        modules.add(new Module("CS2101", "Tuesday 1200 1400"));
        modules.add(new Module("CS2100", "Monday 1400 1500"));
        return modules;
    }

    public static List<Cca> getSampleCcas() {
        List<Cca> ccas = new ArrayList<>();
        ccas.add(new Cca("Basketball", "Monday 1800 2000"));
        ccas.add(new Cca("Soccer", "Tuesday 1800 2000"));
        ccas.add(new Cca("Tennis", "Wednesday 1800 2000"));
        return ccas;
    }

    public static List<DatedEvent> getSampleDatedEvents() {
        List<DatedEvent> datedEvents = new ArrayList<>();
        datedEvents.add(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        datedEvents.add(DatedEvent.newDatedEvent("Walk Dog 2023-10-10 1030 1130 n"));
        datedEvents.add(DatedEvent.newDatedEvent("Competitive sleeping 2023-10-10 1030 1130 y"));
        return datedEvents;
    }

    public static List<MeetUpEvent> getSampleMeetUpEvents() {
        List<MeetUpEvent> meetUpEvents = new ArrayList<>();
        meetUpEvents.add(MeetUpEvent.newMeetUpEvent("CS2103 Meeting 2023-10-10 1030 1130 y", getSamplePersons()[0]));
        meetUpEvents.add(MeetUpEvent.newMeetUpEvent("Walk Dog 2023-10-10 1030 1130 n", getSamplePersons()[1]));
        meetUpEvents.add(MeetUpEvent.newMeetUpEvent("Competitive sleeping 2023-10-10 1030 1130 y",
                getSamplePersons()[2]));
        return meetUpEvents;
    }

    public static Schedule getSampleSchedule() {
        return new Schedule(getSampleModules(), getSampleCcas(), getSampleDatedEvents(), getSampleMeetUpEvents());
    }

    public static User getSampleUser() {
        return new User(new Name("Me"), new Phone("00000000"), new Email("me@example.com"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Birthday("2000-01-01"),
                new Schedule(),
                 getTagSet("me"), new ArrayList<DatedEvent>());
    }

    public static ReadOnlyUserData getSampleUserData() {
        return new UserData(getSampleUser());
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a set of free times containing the list of strings given.
     */
    public static Set<FreeTime> getFreeTimeSet(String... strings) {
        return Arrays.stream(strings)
                .map(FreeTime::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
