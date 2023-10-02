### Adding a person: `add`

Adds a person to TimetaBRO.

Format: `add n/NAME [u/NICKNAME] p/PHONE_NUMBER t/TELEGRAM_HANDLE e/EMAIL [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]`

<div markdown="span" class="alert alert-primary">Tip:
A person can have any number of tags (including 0)
</div><br />

Successful Command:

- `Added {NAME} to the list.`

Unsuccessful Command:

Format: `list`

### Editing a person : `edit`

Edits an existing person in TimetaBRO.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL] [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]…`

Successful Command:
Changes the specified parameters of [INDEX]’s profile

Unsuccessful Command: Displays an error message “Please specify the details to change! Correct syntax: edit INDEX [n/NAME] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL] [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]”


* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:

*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).

  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

Successful Command:
Delete person from user’s profile. Display message “(NAME) deleted”

Unsuccessful Command:
Display error message “Cannot delete (NAME)” or “Cannot find (INDEX)”

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

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

### Clearing all entries : `clear`


### Listing all persons : `list`

Shows a list of all added friends.

Format: `list`

Successful Command:

Adds a person to your list. `Added {NAME} to the list.`

Unsuccessful Command:

Displays an error message. `To list, please run the command ‘list’`