---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of TimetaBRO.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of TimetaBRO in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

**Description:**

The `UI` component manages the user interface of TimetaBRO so it responds to any command to user inputs or action accordingly.
It uses the JavaFx Ui framework.
The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

**Functionality:**

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` objects residing in the `Model`.

**Component Structure:**

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

Depending on the state of the application, certain parts of the UI are shown or hidden in `MainWindow`. eg. `HelpWindow` and `SelectedFriendCard`.

Upon TimetaBRO being launched, the `Reminder` window will be shown on the bottom right hand corner of the desktop's screen.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("addschedule user type/cca en/table tennis h/monday 1400 1600")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/AddScheduleSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddScheduleCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `AddScheduleCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddScheduleCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

**Description:**

The `Model` component stores and manages data. It accomplishes this by creating and maintaining a runtime representation of the data utilising Java's Object Oriented Programming abilites. These objects are abstract representations of their real world counterparts and their relationships with each other, simulating their relationships.

**Functionality:**

* `Model` is not dependent on the other packages. 
* `Ui` references the `Model` to retrieve relevant information about the `User` and the friends to be displayed on the `MainWindow`. 
* `Logic` component communicates with the `Model` to make modifications based on the commands inputted. 
* `Storage` component refers to the `Model` to store the data on the computer's local memory. 

**Component structure:**

<img src="images/ModelClassDiagram.png" width="600" />

The `Model` can be broken down into its subpackages:

* `Person` subpackage:
  * Represents a `Person` in the addressbook and their attributes that the application manages, namely their `timetable`, `Phone` number, `Address`, `Birthday`, `Email`, and `Name`.
  * `UniquePersonList` ensures that the list of persons does not contain duplicate phone numbers or emails, and supports basic list operations.
    <img src="images/TimetableClassDiagram.png" width="350" />
  * `timetable` subpackage encapsulates a person's schedule that includes a list of module timings (`Module`),
co-curricular activities timings (`Cca`), and dated events (`DatedEvent`). The `Schedule` class provides functionality
to retrieve the schedule for the current week, for a specific day, and to manage free time within the schedule. It also
supports operations to add, edit, and delete various time blocks like modules and CCAs, ensuring that there are no
overlapping events.
* `user` subpackage:
  * The `User` class extends the `Person` class and includes additional management for dated events specific to the user. It allows for setting and removing reminders for events, retrieving events with reminders for the current day, and managing the user's dated events.
* `util` subpackage:
  * The `SampleUtilData` utility class populates the `AddressBook` with sample data, providing first-time users a perspective of the application in use.

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save address book data, user preference, and user data in JSON format, and read them back into corresponding objects.
* inherits from `AddressBookStorage`, `UserPrefStorage`, and `UserDataStorage` which means it can be treated as any one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

<img src = "images/TimetableStorageClassDiagram.png" width="550" />

The `Timetable` Classes
* Allows users to save their friends' and their own timetables in JSON format, and read them back into corresponding objects
* Depends on the related files in `Model`.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Reminder feature

### Add friend's schedule feature

### Edit user details feature

### Model design considerations for schedule

### Click to View Friend Timetable Feature

#### Description

Whichever list cell of the friend list is clicked on, 
it becomes selected, 
and is displayed on the bottom half of the right hand side of the app.

#### Implementation
* The user clicks on the cell within the `ListView` of the friend list.
* The `onMouseClicked` event is triggered upon the user's click.
* `PersonListPanel.PersonListViewCell#updateItem()` handles this `MouseEvent` object:
  * It checks if the event is a single click. 
  * If so, it notes the Person object in the selected list cell and fires a new event `ListCellSelectedEvent` with the selected person.
* The `ListCellSelectedEvent` extends `Event` saves the selected person object.
* The event filter in `MainWindow#fillInnerParts()` handles the `ListCellSelectedEvent` 
and retrieves the selected person from it using `ListCellSelectedEvent#getSelectedPerson()`.
* The selected person is used to create a new `SelectedFriendCard`, which is stored under `friendProfile`.
* The contents of the `SelectedFriendPlaceHolder` is replaced with the `friendProfile`.
* The position of the selected friend in the friend list is saved in `selectedFriendPos` for refreshing the display with any changes.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage university life, social interactions, and schedule
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts & schedule faster than a typical mouse/GUI-driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​      | I want to …​                                                                       | So that I can…​                                                            |
|----------|--------------|------------------------------------------------------------------------------------|----------------------------------------------------------------------------|
| `* * *`  | new user     | see a welcoming popup when i first open the app                                    | navigate the app easily and fill up my own details                         |
| `* * *`  | user         | add contacts to my list of friends using information like name and contact details | identify them more easily                                                  |
| `* * *`  | user         | view my list of friends                                                            | see all my friends in a glance                                             |
| `* * *`  | user         | edit details of my friends                                                         | keep their information up to date or change any wrongly filled information |
| `* * *`  | user         | search for specific friend                                                         | find their information more easily                                         |
| `* * *`  | user         | delete friends from my list of friends                                             | remove people who are no longer my friends and not needed in the list      |
| `* * *`  | student      | add my timetable to the app                                                        | easily access and keep track of my own timetable                           |
| `* * *`  | student      | add my friend's timetable to the app                                               | keep track of my friends                                                   |
| `* * *`  | student      | identify common free time slots with friends                                       | organise meals or other social activities with them                        |
| `* * *`  | student      | set reminders about events                                                         | be well-prepared and organised for all my commitments                      |
| `* * *`  | student      | create events                                                                      | keep track of important commitments and activities                         |
| `* * *`  | busy student | receive reminders about events                                                     | remember any upcoming events                                               |
| `* * *`  | busy student | receive reminders about my friends' birthdays                                      | plan something for their birthday                                          |
| `* *`    | student      | edit my timetable                                                                  | update changes in my timetable                                             |
| `* *`    | student      | view my own timetable                                                              | plan my day and easily view my commitments                                 |
| `* *`    | student      | view my friends' timetables                                                        | know more about their day                                                  |
| `* *`    | student      | visually compare my timetable with that of my friends                              | quickly identify overlaps or free times                                    |
| `* *`    | student      | identify common modules with my friends                                            | attend classes with them                                                   |
| `* *`    | student      | edit events                                                                        | update any change in event details                                         |
| `*`      | student      | give my friends nicknames and set their profile pictures in my list                | personalise and easily identify them                                       |
| `*`      | student      | set my own profile picture                                                         | personalise my own profile                                                 |
| `*`      | student      | add friends to events                                                              | remember who is attending events                                           |
| `*`      | student      | create friend groups                                                               | easily manage and view schedules for specific circles of friends           |
| `*`      | student      | see combined timetables of multiple friends                                        | find common free time visually                                             |
| `*`      | student      | add notes to events                                                                | remember additional information about the event                            |

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to delete a specific person in the list
4.  AddressBook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.


**Use case: Edit a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to edit a specific person in the list
4.  AddressBook edits the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

**Use case: Add a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to add a new person to the list
4.  AddressBook adds the new person

    Use case ends.

**Extensions**

* 3a. Not all the required fields of the person are provided.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

**Use case: Find a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to find names containing an inputted keyword
4.  AddressBook shows a list of persons whose names contain the keyword

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.


**Use case: Check for common free times with friends**

**MSS**

1. User requests to list persons
2. AddressBook shows a list of persons
3. User requests for common free times either with a specific friend or entire address book
4. If user requests for common free times with a specific friend, AddressBook shows the friend's free times.
5. If user requests for common free times with entire address book, AddressBook shows list of friends with common free times, and their associated common free times

    Use case ends.

**Extensions**

* 2a. The list is empty
* 4a. Friend has no common free time with User
* 4b. Friend name specified by user does not exist
* 5a. No contacts in User's address book has common free times with user


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should not have a latency of more than 2 seconds to ensure optimal user experience
5.  Should be able to hold up to 10 modules per person without noticeable detriments to the performance of the app
6.  Should ensure the integrity of user data, preventing any data corruption or loss during normal usage.
7.  Should implement appropriate security measures to protect user data from unauthorized access or tampering.
8.  Should be designed with accessibility in mind, ensuring that it is usable by individuals with disabilities, including those who rely on screen readers or keyboard navigation.
9.  Should be able to handle a growing number of contacts without a significant decrease in performance.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Performance**: Speed at which the app completes queries.
* **Tampering**: Modifying data without permission from the owner of said data.
* **normal usage**: Day-to-day usage of the app without any errors occurring.
* **Optimal user experience**: User can utilise all functionality without bugs and lag.
* **Event**:
* **Non-recurring event**:
* **Recurring event**:
* **Timetable**:
* **Reminder**:

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Add friend

### Add schedule

### Add event

### Edit user details

### Delete schedule

### Delete event

### Edit friend's details

### Toggle reminder for events

### Help command

### Clear command

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
