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

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Saving the data

TimetaBRO data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
=======
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


TimetaBRO data are saved automatically as a JSON file `[JAR file location]/data/timetabro.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">Caution:
If your changes to the data file makes its format invalid, TimetaBRO will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------
=======
### Clearing all entries : `clear`
=======
* `add n/John Doe p/98765432 t/johndoe e/johnd@example.com m/CS2103T /from 1200 /to 1300 /day Wednesday t/police`
* `add n/Betsy Crowe t/betsycrowe e/betsycrowe@example.com p/1234567 m/CS2101 /from 1200 /to 1400 /day Monday, Thursday  t/criminal`




**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TimetaBRO home folder.
=======
### Listing all persons : `list`


Shows a list of all added friends.

Format: `list`

Successful Command:

Adds a person to your list. `Added {NAME} to the list.`

Unsuccessful Command:

| Action     | Format, Examples                                                                                                                                                                                                                                         |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `​add n/NAME u/NICKNAME p/PHONE_NUMBER t/TELEGRAM_HANDLE e/EMAIL [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]…​` <br> e.g., `add n/John Doe p/98765432 t/johndoe e/johnd@example.com m/CS2103T /from 1200 /to 1300 /day Wednesday t/police` |
| **Clear**  | `clear`                                                                                                                                                                                                                                                  |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                      |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL] [m/MODULE /from START_TIME /to END_TIME /day DAY] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                    |
| **List**   | `list`                                                                                                                                                                                                                                                   |
| **Help**   | `help`                                                                                                                                                                                                                                                   |

