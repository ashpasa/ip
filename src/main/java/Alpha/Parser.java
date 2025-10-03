import java.io.IOException;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

public final class Parser {
    public static void carryOutCommand(Ui ui, TaskList taskList, Storage storage, String userInput) {
        String[] command = splitCommandKeyword(userInput);
        parseCommand(ui, taskList, storage, command);
    }

    private static String[] splitCommandKeyword(String userInput) {
        return userInput.split(" ", 2);
    }

    static void parseCommand(Ui ui, TaskList taskList, Storage storage, String[] command) {
        String commandType = command[0];
        try {
            switch (commandType) {
            case "list":
                ui.printTasks(taskList.listTasks());
                break;
            case "bye":
                ui.sendMessage("Aww man! Hope to see u again soon! T^T");
                System.exit(0);
                break;
            case "todo":
                addTodo(ui, taskList, command[1]);
                saveTasks(taskList, storage);
                break;
            case "deadline":
                addDeadline(ui, taskList, command[1]);
                saveTasks(taskList, storage);
                break;
            case "event":
                addEvent(ui, taskList, command[1]);
                saveTasks(taskList, storage);
                break;
            case "mark":
                markTask(ui, taskList, command[1]);
                saveTasks(taskList, storage);
                break;
            case "unmark":
                unmarkTask(ui, taskList, command[1]);
                break;
            case "delete":
                taskList.deleteTask(ui, command[1]);
                saveTasks(taskList, storage);
                break;
            default:
                ui.sendError();
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.sendMessage("Hey, are you sure you actually finished typing what you wanted to,"
                + System.lineSeparator()
                + "or did you accidentally press 'enter'?");
            return;
        } catch (NullPointerException e) {
            ui.sendError();
            return;
        } catch (NumberFormatException e) {
            ui.sendMessage("Sorry, but the task number has to be an actual number!"
                + System.lineSeparator()
                + "Are you sure you didn't make an typo?");
            return;
        } catch (IndexOutOfBoundsException e) {
            ui.sendMessage("Hmm, seems like you got the wrong number or something,"
                + System.lineSeparator()
                + "are you trying to contact someone? :P");
            return;
        } catch (Exception e) {
            ui.sendError();
            return;
        }
    }

    private static void addTodo(Ui ui, TaskList taskList, String details) {
        Todo todo = new Todo(details);
        taskList.addTask(ui, todo);
    }

    private static void addDeadline(Ui ui, TaskList taskList, String details) {
        String[] parts = details.split(" /by ");
        String description = parts[0];
        String by = parts[1];
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(ui, deadline);
    }

    private static void addEvent(Ui ui, TaskList taskList, String details) {
        String[] parts = details.split(" /from ");
        String desc = parts[0];
        String startTime = parts[1].split(" /to ")[0];
        String endTime = parts[1].split(" /to ")[1];
        Event event = new Event(desc, startTime, endTime);
        taskList.addTask(ui, event);
    }

    private static void markTask(Ui ui, TaskList taskList, String taskNumber) throws ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IndexOutOfBoundsException {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        taskList.tasks.get(taskNum).markAsDone();
        ui.sendMessage("Yippee! This task is now marked complete!:"
            + System.lineSeparator()
            + taskList.tasks.get(taskNum).toString());
    }

    private static void unmarkTask(Ui ui, TaskList taskList, String taskNumber) throws ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IndexOutOfBoundsException {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        taskList.tasks.get(taskNum).markAsNotDone();
        ui.sendMessage("Aww man! I've marked the task as incomplete :( :"
            + System.lineSeparator()
            + taskList.tasks.get(taskNum).toString());
    }

    private static void saveTasks(TaskList taskList, Storage storage) throws IOException {
        try {
            storage.writeTasksToFile(taskList);
        } catch (IOException e) {
            System.out.println("Error writing to file... :(");
        }
    }
}
