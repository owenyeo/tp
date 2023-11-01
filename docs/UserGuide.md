# TimetaBRO User Guide

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

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`


### Adding a person: `add`

Adds a person to TimetaBRO.

Format: `add n/NAME [u/NICKNAME] p/PHONE_NUMBER t/TELEGRAM_HANDLE e/EMAIL [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]`

<div markdown="span" class="alert alert-primary">Tip:
A person can have any number of tags (including 0)
</div><br />

Successful Command:

- `Added {NAME} to the list.`

Unsuccessful Command:

- `Wrong inputs for prefix: {prefix with error}`

Examples:
* `add n/John Doe p/98765432 t/johndoe e/johnd@example.com m/CS2103T /from 1200 /to 1300 /day Wednesday t/police`
* `add n/Betsy Crowe t/betsycrowe e/betsycrowe@example.com p/1234567 m/CS2101 /from 1200 /to 1400 /day Monday, Thursday  t/criminal`

### Listing all persons : `list`

Shows a list of all added friends.

Format: `list`

Successful Command:

Adds a person to your list. `Added {NAME} to the list.`

Unsuccessful Command:

Displays an error message. `To list, please run the command ‘list’`


### Editing a friend's information : `edit`

Edits an existing person in TimetaBRO.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL] [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Successful Command:

Changes the specified parameters of specified friend’s profile

Unsuccessful Command:

Displays an error message `Please specify the details to change! Correct syntax: edit INDEX [n/NAME] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL] [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]`


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

Format: `cft [n/NAME]`

* Finds common free times with friend of the specified `NAME`.
* Finds common free times with **all friends** in the list if `NAME` is not included.

Successful Command:

Found common free time with friend.
`Here are the contacts with the same free time as you:`
- `Andre [Monday 1230 1300]`
- `Owen [Sunday 1200 1400]`

You have no free time: `You have no free time!`

No common free times with friends: `You have no contacts with the same free time as you!`

Unsuccessful Command:

Displays an error message

Examples:
* `cft` lists all friends .
* `cft n/Betsy` finds people with the name `Betsy` and displays the common free times with this list.

### Clearing all entries : `clear`

Clears all entries from TimetaBRO.

Format: `clear`

Successful Command:

`All friends have been deleted`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

Successful Command:

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

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples                                                                                                                                                                                                                                         |
|--------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**            | `​add n/NAME u/NICKNAME p/PHONE_NUMBER t/TELEGRAM_HANDLE e/EMAIL [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]…​` <br> e.g., `add n/John Doe p/98765432 t/johndoe e/johnd@example.com m/CS2103T /from 1200 /to 1300 /day Wednesday t/police` |
| **Clear**          | `clear`                                                                                                                                                                                                                                                  |
| **Delete**         | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                      |
| **Edit**           | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL] [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                    |
| **List**           | `list`                                                                                                                                                                                                                                                   |
| **Help**           | `help`                                                                                                                                                                                                                                                   |
| **CommonFreeTime** | `cft [n/NAME]`                                                                                                                                                                                                                                           |
