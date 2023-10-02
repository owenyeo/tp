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