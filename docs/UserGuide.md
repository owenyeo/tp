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
=======
* `add n/John Doe p/98765432 t/johndoe e/johnd@example.com m/CS2103T /from 1200 /to 1300 /day Wednesday t/police`
* `add n/Betsy Crowe t/betsycrowe e/betsycrowe@example.com p/1234567 m/CS2101 /from 1200 /to 1400 /day Monday, Thursday  t/criminal`


### Listing all persons : `list`

Shows a list of all added friends.

Format: `list`

Successful Command:

Adds a person to your list. `Added {NAME} to the list.`

Unsuccessful Command:

Displays an error message. `To list, please run the command ‘list’`