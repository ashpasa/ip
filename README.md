# Alpha project

This is a chatbot project for CS2113. It can be used as a task tracker or to do list.

## Setting up

Upon running, the programme will detect if a file for the task list exists.
   - If it exists, the programme will load from the file and update it accordingly as the user uses the programme.
   - If there is no existing file, the programme will create a new one and re-use it for subsequent runs.

When startup is successful, a welcome message will be displayed as follows:

```
Hello! I'm
    _    _       _
   / \  | |_ __ | |__   __ _ 
  / _ \ | | '_ \| '_ \ / _` |
 / ___ \| | |_) | | | | (_| |
/_/   \_\_| .__/|_| |_|\__,_|
          |_|
Happy to see you!
```

## Features

Notes about the command format:

   1. Words in `UPPER_CASE` are parameters to be input by the user
      e.g. In `todo TASK`, `TASK` can be specified by the user, as in `todo do laundry`

   2. Parameters must follow the order specified
      e.g. In `event EVENT_DESCRIPTION /from START_TIME /to END_TIME`, inputting the command `event lunch /to 1pm /from 2pm` will result in an error

   3. Extraneous parameters for commands that do not require parameters will be ignored
      e.g. A command `list 5` will result in the same behaviour as the command `list`

   4. Command words are case-sensitive
      e.g. For command `list`, inputting `List` will result in an error

### Add a task: `todo`

Adds a task to the task list as a description in text, with no additional details

Format: `todo TASK`

Examples:
`todo do laundry`
`todo clean room`

### Add a task with a deadline: `deadline`

Adds a task to the task list with a description and a specified completion time

Format: `deadline TASK /by DEADLINE`

Examples:
`deadline finish homework /by tomorrow`
`deadline take medicine /by 10pm`

### Add a task with start and end time: `event`

Adds an item with a task list with 3 details - a description, starting time, and ending time

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Examples:
`event birthday party /from 7pm /to 10pm`
`event holiday /from 24th December /to 25th December`

### Delete a task from the list: `delete`

Removes a task from the task list according to its number in the list

Format: `delete TASK_NUMBER`

Example:
`delete 5`

### Mark a task: `mark`

Marks a task as complete according to its number in the list

Format `mark TASK_NUMBER`

Example:
`mark 2`

### Unmark a task: `unmark`

Marks as task as undone according to its number in the list

Format: `unmark TASK_NUMBER`

Example:
`unmark 3`

### Search for a task: `find`

Search for a task according to its description

Format: `find SEARCH_TERM`

Examples:
`find homework`
`find laundry`

### List all tasks: `list`

Displays a list of all current tasks

Format: `list`

### Exit the programme: `bye`

Exits the programme

Format: `bye`

### Saving the task list

The task list is automatically saved after any command that edits its data. There is no need to save manually.