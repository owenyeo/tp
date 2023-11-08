---
layout: page
title: User Guide
---

## Introduction

Welcome to TimetaBRO, your ultimate companion for managing your university life, social interactions, and schedules. It is dedicated to **NUS students who have many things on their plate, and have trouble arranging meetings with friends and teammates**.

In the past, NUS students had to tediously save their friend’s timetables, and compare them to their own. Most of the time, students do not even have the time to compare timetables, and have to go through the hassle of coordination through messaging apps.

However, with TimetaBRO, you can now **save your friends’ timetables**, and ask it when your friends are free! Make scheduling meetups and meetings slick and easy, and while you’re busy scheduling the best dates, you can also save important details about your friends! No more forgetting birthdays or favourite foods, be the best friend you can be!

## About the user guide
This comprehensive user guide will walk you through all the exciting features TimetaBRO has to offer. New to TimetaBRO? Fret not! This guide will walk you through a quickstart to start using TimetaBRO.

This user guide will also provide information about its amazing functionalities in the features section, optimising your use of TimetaBRO even further. Included is a command summary for your perusal.

Additionally, we included FAQs and a glossary in case you have any additional questions after reading this user guide.

## Quickstart
Before we jump into it, let's make sure that your TimetaBRO is working properly!
1. Ensure you have Java 11 installed on your computer.
    * To check if Java 11 is currently installed, you may follow this short guide.
    * If Java 11 is not installed, you may follow the installation instructions over here.
2. Next, download the latest 'timetabro.jar' from [here](https://github.com/AY2324S1-CS2103T-W12-4/tp).
3. Copy the file to the folder you want to use as a home folder for TimetaBRO.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
    * add n/Owen p/91792309 s/CS2101 Tuesday 1200 1400 b/ 2001-12-26:
    adds a friend named Owen, with phone number 91792309 and birthday on 26 December 2001. He is taking a class on Tuesday 1200-1400 for module CS2101
    * cft Friday 1200 1400:
    Filters your friend list to people who are free on these timings


Nice! Now you know the basic commands and have launched TimetaBRO, lets get into the finer details.


## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

* Clicking on your friend's name will display their timetable on the right hand side.

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`


### Adding a person: `add`

Adds a person to TimetaBRO.

Format: `add n/NAME p/PHONE_NUMBER b/BIRTHDAY a/ADDRESS e/EMAIL [t/TAG]`

<div markdown="span" class="alert alert-primary">Tip:
A person can have any number of tags (including 0)
</div><br />

Successful Command:

- `Added {NAME} to the list.`

Unsuccessful Command:

- `Wrong inputs for prefix: {prefix with error}`

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com t/police b/2001-10-10 a/18 College Ave E, Cinnamon West Learn Lobe, Singapore 138593`
* `add n/Betsy Crowe e/betsycrowe@example.com p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all added friends.

Format: `list`

Successful Command:

Adds a person to your list. `Added {NAME} to the list.`

Unsuccessful Command:

Displays an error message. `To list, please run the command ‘list’`


### Editing a friend's information : `edit`

Edits an existing person in TimetaBRO.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [b/BIRTHDAY] [e/EMAIL] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Successful Command:

Changes the specified parameters of specified friend’s profile

Unsuccessful Command:

Displays an error message `Please specify the details to change! Correct syntax: edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]`


Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower m/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing modules.

### Deleting a person : `delete`

Deletes the specified friend from TimetaBRO.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Successful Command:

Delete person from user’s profile.`(NAME) deleted.`

Unsuccessful Command:

- `Cannot delete (NAME)`
- `Cannot find (INDEX)`

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the list.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Finding common free times with friend(s) : `cft`

Finds friend(s) with the same free times as you.

Format: `cft [INDEX]`

* Finds common free times with friend of the specified `INDEX`.
* Finds common free times with **all friends** in the list if `INDEX` is not included.

Successful Command:\
**Input:** \
`cft`\
**Output:**\
Found common free time with friend.\
`Here are the contacts with the same free time as you:`
- `Andre [Monday 1230 1300]`
- `Owen [Sunday 1200 1400]`

You have no free time: `You have no free time!`

No common free times with friends: `You have no contacts with the same free time as you!`

Unsuccessful Command:

If user inputs a friend that does not exist, the app will display\
`There is no such contact in your contacts!`

Examples:
* `cft` lists all friends .
* `cft n/Betsy` finds people with the name `Betsy` and displays the common free times with this list.

### Add events to you/your friend's schedule : `addevent`
This command adds a dated, non-recurring event to you or your friend's schedule.

Format: `addevent`\
To add event to yourself, use \
`addevent user type/[TYPE] en/[EVENT NAME] h/[DATE TIME] r/[REMINDER]` \
To add event for friend, use \
`addevent INDEX type/[TYPE] en/[EVENT NAME] h/[DATE TIME] r/[REMINDER]`

- Adds an event titled `EVENT_NAME` to either user or
the specified friend and `INDEX`
- Event date and time will be equal to `DATE TIME`
where `DATE TIME` must be entered in the format `YYYY-MM-DD HHMM [start time] HHMM [end time]`
- Users can set whether they want to enable reminders for this event by inputting `y/n` under `[REMINDER]`
- Event names will be changed to all upper case regardless of whether it was keyed it in lower case or upper case

Successful Command:

**Input:** 
````
addevent 1 type/dated en/CS2030 Finals h/2023-10-31 1000 1400 r/y
````
**Output:**
````
New event added:
Dated Event:
CS2030 Finals 2023-10-31 1000 1400 to [Friend Name]
````

**Input:**
````
addevent user type/dated en/CS2101 OP2 h/2023-10-31 1500 1600 r/y
````

**Output:**
````
New event added:
Dated Event:
CS2101 OP2 2023-10-31 1500 1600 to Me
````

Unsuccessful Command:

If a user puts an invalid index,
this error message will be shown

````
Invalid index!
addevent: Adds a non-recurring event to the calendar.
Parameters: INDEX type/EVENT_TYPE en/EVENT_NAME h/[Date [YYYY-MM-DD] StarTime (HHMM) EndTime (HHMM)] r/[REMINDER: y/n]`\
Example: addevent 1 type/dated en/CS2103T Lecture h/2020-03-02 1400 1600 r/y

Note: If you are adding a meetup event, then index refers to the index of the friend you are meeting with.
If you are adding a dated event, then index should be the index of the friend you are adding the dated event to or 'user' if you would like to add the event to yourself
````

 If a user uses the wrong format (i.e missing prefix, wrong event type),
this error message will be shown
````
Input should be in the format 'name YYYY-MM-DD HHMM HHMM yes/no', where:
'name' represents the name and should not contain spaces.
'YYYY-MM-DD' represents a date (e.g., '2023-10-24').
'HHMM' represents a valid 24-hour time format in half-hour blocks (e.g., 0000, 1230, 2300).
The first 'HHMM' represents the starting time (e.g., '0830' for 08:30 AM).
The second 'HHMM' represents the ending time (e.g., '1730' for 05:30 PM).
y/n represents whether you want a reminder for this event.
````


### Removing an event: `rmevent`

Removes the specified event from the specified Person

**Format:**\
To remove an event, you can use the rmevent command with the following parameters:

`rmevent [INDEX] en/[EVENT_NAME]`

INDEX (Index of the friend or 'user')
en/EVENT_NAME (Event name)

Successful commands:

Remove an event from a friend's calendar:\
`rmevent 1 en/CS2103T Lecture`

Remove an event from your own calendar:\
`rmevent user en/CS2103T Lecture`


**Unsuccessful commands:**\
If an invalid index is used:
````
Invalid index!
Parameters: INDEX
en/EVENT_NAME
Example: rmevent 1 en/CS2103T Lecture
NOTE: If you want to remove an event from yourself, use index user
Example: rmevent user en/CS2103T Lecture
````

If wrong command format is used (i.e missing prefixes, wrong event name):
````
Command format is invalid!
Parameters: INDEX
en/EVENT_NAME
Example: rmevent 1 en/CS2103T Lecture
NOTE: If you want to remove an event from yourself, use index user
Example: rmevent user en/CS2103T Lecture
````


### Add recurring events to you/your friend's schedule : `addschedule`
This command adds a recurring event to you or your friend's schedule.

Format: `addschedule`\
To add event to yourself, use \
`addschedule user type/[TYPE] en/[EVENT NAME] h/[DAY TIME]` 

To add event for friend, use \
`addschedule INDEX type/[TYPE] en/[EVENT NAME] h/[DAY TIME]`

- Adds an event titled `EVENT_NAME` to either user or
the specified friend and `INDEX`
- Event date and time will be equal to `DAY TIME`
where `DAY TIME` must be entered in the format `[monday/tuesday/wednesday/thursday/friday/saturday/sunday] HHMM [start time] HHMM [end time]`
- Event names will be changed to all upper case regardless of whether it was keyed it in lower case or upper case

**Successful Command:**\
Input:
````
addschedule 1 type/module en/CS2030 h/Monday 1000 1400
````
Output:
````
New event added:
Module:
CS2030 Monday 1000 1400 to [Friend Name]
````

Input:
````
addschedule user type/CCA en/CS2101 h/Tuesday 1500 1600
````
Output:
````
New event added:
CCA:
CS2101 Tuesday 1500 1600 to Me
````

**Unsuccessful Command:**\
If a user puts an invalid index, this error message will be shown
````
Invalid index!
addevent: Adds a non-recurring event to the calendar.
Parameters: INDEX type/EVENT_TYPE en/EVENT_NAME h/[Day [Day of week] StarTime (HHMM) EndTime (HHMM)]
Example: addschedule 1 type/dated en/CS2103T Lecture h/Wednesday 1400 1600

Note: If you are adding a meetup event, then index refers to the index of the friend you are meeting with.
If you are adding a dated event, then index should be the index of the friend you are adding the dated event to or 'user' if you would like to add the event to yourself
````
 If a user uses the wrong format (i.e missing prefix, wrong event type),
this error message will be shown:
````
Input should be in the format 'name [Day] HHMM HHMM yes/no', where:
'name' represents the name and should not contain spaces.
'Day' represents a day of the week (e.g. Monday).
'HHMM' represents a valid 24-hour time format in half-hour blocks (e.g., 0000, 1230, 2300).
The first 'HHMM' represents the starting time (e.g., '0830' for 08:30 AM).
The second 'HHMM' represents the ending time (e.g., '1730' for 05:30 PM).
y/n represents whether you want a reminder for this event.
````


### Removing a recurring scheduled event: `rmschedule`

Removes the specified recurring item from the specified Person's schedule

**Format:**\
To remove a scheduled event, you can use the rmschedule command with the following parameters:

`rmevent [INDEX] type/[EVENT_TYPE] en/[EVENT_NAME]`

INDEX (Index of the friend or 'user')
type/EVENT_TYPE (Event type, either 'dated' or 'meetup')
en/EVENT_NAME (Event name)

**Successful commands:**

Remove an event from a friend's calendar:\
`rmschedule 1 type/CCA en/CS2103T Lecture`\
Output:\
`CS2103T Lecture has been removed from [Friend]!`

Remove an event from your own calendar:\
`rmschedule user type/module en/CS2103T Lecture`\
Output:\
`CS2103T Lecture has been removed from Me!`

**Unsuccessful commands:**\
If an invalid index is used:
````
Invalid index!
Parameters: INDEX
type/EVENT_TYPE
en/EVENT_NAME
Example: rmschedule 1 type/module en/CS2103T Lecture
NOTE: If you want to remove an event from yourself, use index user
Example: rmschedule user type/module en/CS2103T Lecture
````
If wrong command format is used (i.e missing prefixes, wrong event name):
````
Command format is invalid!
Parameters: INDEX
type/EVENT_TYPE
en/EVENT_NAME
Example: rmschedule 1 type/cca en/CS2103T Lecture
NOTE: If you want to remove an event from yourself, use index user
Example: rmschedule user type/cca en/CS2103T Lecture
````


### Set Reminder for dated events: `setReminder`

Sets a reminder for a dated event from the user's schedule

Format: \
To set a reminder a dated event in your schedule, you can use the setReminder command with the following parameters:

`setReminder [EVENT_NAME]`

**Successful command:** \
Set reminder for an event:
````
setReminder CS2103T Lecture
````
Output:
````
Reminder set for following event:
CS2103T Lecture
````
**Unsuccessful command:**

If an invalid event name is used:
````
setReminder CS1111 Lecture
````
Output:
````
No such event exists!
````

### Set Reminder for dated events: `rmReminder`

Remove a reminder for a dated event from the user's schedule

Format: \
To remove a reminder set for an event in your schedule, you can use the 
rmReminder command with the following parameters:

`rmReminder [EVENT_NAME]`

**Successful command:**

Remove reminder for an event:
````
rmReminder CS2103T Lecture
````
Output: 
````
Reminder removed for following event:
CS2103T Lecture
````

**Unsuccessful command:**\
If an invalid event name is used:
````
rmReminder CS1111 Lecture
No such event exists!
````

### Clearing all entries : `clear`

Clears all entries from TimetaBRO.

Format: `clear`

Successful Command:

`All friends have been deleted`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

**Successful Command:**

The app closes after saving all data.

### Saving the data

TimetaBRO data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TimetaBRO data are saved automatically as a JSON file `[JAR file location]/data/timetabro.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">Caution:
If your changes to the data file makes its format invalid, TimetaBRO will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TimetaBRO home folder.

**Q**: Is TimetaBRO compatible with Mac and Linux operating systems, or is it Windows-specific?
**A**: TimetaBRO is compatible with multiple operating systems, including Windows, Mac, and Linux. It runs on systems that support Java 11, so you can use it on your preferred platform.

**Q:** Can I import my friend's schedule from a different calendar application into TimetaBRO?
**A:** Currently, TimetaBRO does not offer a direct import feature for schedules from other calendar applications. You'll need to manually add your friends' schedules to TimetaBRO using the add command.

**Q:** Is there a way to set recurring events for specific dates or weekdays, like every Tuesday, without manually adding them one by one?
**A:** Yes, you can add recurring events for specific weekdays in TimetaBRO using the addschedule command. This feature allows you to set events for particular days of the week, making it easier to input recurring events.

**Q:** What happens if I accidentally delete a friend or event in TimetaBRO? Is there a way to recover deleted data?
**A:** Unfortunately, TimetaBRO does not have a built-in data recovery feature. When you delete a friend or event, the data is permanently removed from the application. It's essential to double-check your actions to avoid accidental deletions.

**Q:** How can I customize the reminder settings for events added to TimetaBRO?
**A:** You can customize the reminder settings for events when adding them using the addevent command. The r/y or r/n option allows you to enable or disable reminders for specific events.

**Q:** Is there a way to share my TimetaBRO schedule with others or export it to a different format, such as a calendar file?
**A:** TimetaBRO currently does not support sharing schedules with others or exporting them to external formats. It primarily functions as a personal scheduling tool.

**Q:** What sets TimetaBRO apart from other scheduling tools?
**A:** TimetaBRO's unique feature of saving your friends' schedules and finding common free times makes it stand out. It's a powerful tool for enhancing social connections and simplifying event planning, which many users find highly valuable.

**Q:** What are the advantages of saving my friends' schedules in TimetaBRO?
**A:** Saving your friends' schedules in TimetaBRO allows you to easily coordinate meetups and group activities, making it simpler to find a time that works for everyone. It strengthens your social connections and helps you stay connected with friends.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                       | Format and Examples                                                                                                                                                                        |
|------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add a Friend**             | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTHDAY [t/TAG]…​`<br> Example: `add n/John Doe p/98765432 e/johnd@example.com a/1 Hon Sui Sen Dr, Singapore 117588 b/2023-11-05 t/police` |
| **Add an Event**             | `addevent [INDEX] type/EVENT_TYPE en/EVENT NAME h/DATE TIME r/REMINDER`<br> Example: `addevent 1 type/dated en/CS2030 Finals h/2023-10-31 1000 1400 r/y`                                   |
| **Remove an Event**          | `rmevent [INDEX] type/EVENT_TYPE en/EVENT NAME`<br> Example: `rmevent 1 type/dated en/CS2103T Lecture`                                                                                     |
| **Add a Recurring Event**    | `addschedule [INDEX] type/EVENT_TYPE en/EVENT_NAME h/DAY TIME`<br> Example: `addschedule 1 type/module en/CS2030 h/Monday 1000 1400`                                                       |
| **Remove a Recurring Event** | `rmschedule [INDEX] type/EVENT_TYPE en/EVENT_NAME`<br> Example: `rmschedule 1 type/CCA en/CS2103T Lecture`                                                                                 |
| **Remove a Reminder**        | `rmReminder EVENT_NAME`<br> Example: `rmReminder CS2103T Lecture`                                                                                                                          |
| **Set a Reminder**           | `setReminder EVENT_NAME`<br> Example: `setReminder CS2103T Lecture`                                                                                                                        |
| **Clear All Entries**        | `clear`                                                                                                                                                                                    |
| **Delete a Friend**          | `delete INDEX`<br> Example: `delete 3`                                                                                                                                                     |
| **Edit Friend Info**         | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`<br> Example: `edit 2 n/James Lee e/jameslee@example.com`                                               |
| **Edit User Info**           | `user [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`<br> Example: `user n/James Lee e/jameslee@example.com`                                                       |
| **List All Friends**         | `list`                                                                                                                                                                                     |
| **View Help**                | `help`                                                                                                                                                                                     |
| **Find Common Free Times**   | `cft [INDEX]`<br>NOTE: To check common free times with entire friends list, use `cft` without any index.<br>Example: `cft`                                                                 |                                                                       

