import java.util.Scanner;
import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Alpha {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        sendWelcomeMessage();
        while (true) {
            readCommand();
        }
    }

    private static void readCommand() {
        String command = input.nextLine();
        String[] commandWords = command.split(" ", 2);
        parseCommand(commandWords);
        return;
    }

    private static void parseCommand(String[] commandWords) {
        try {
            switch (commandWords[0]) {
            case "list":
                printTasks(tasks);
                break;
            case "bye":
                sendMessage("Bye. Hope to see you again soon!");
                System.exit(0);
                break;
            case "mark":
                markTask(commandWords[1]);
                break;
            case "unmark":
                unmarkTask(commandWords[1]);
                break;
            case "todo":
                addTask(new Todo(commandWords[1]));
                break;
            case "deadline":
                String description = commandWords[1].split(" /by ")[0];
                String by = commandWords[1].split(" /by ")[1];
                addTask(new Deadline(description, by));
                break;
            case "event":
                String desc = commandWords[1].split(" /from ")[0];
                String startTime = commandWords[1].split(" /from ")[1].split(" /to ")[0];
                String endTime = commandWords[1].split(" /from ")[1].split(" /to ")[1];
                addTask(new Event(desc, startTime, endTime));
                break;
            case "delete":
                deleteTask(commandWords[1]);
            default:
                sendError();
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            sendMessage("Hey, are you sure you actually finished typing what you wanted to,"
                + System.lineSeparator()
                + "or did you accidentally press 'enter'?");
            return;
        } catch (NullPointerException e) {
            sendError();
            return;
        } catch (NumberFormatException e) {
            sendMessage("Sorry, but the task number has to be an actual number!"
                + System.lineSeparator()
                + "Are you sure you didn't make an typo?");
            return;
        } catch (IndexOutOfBoundsException e) {
            sendMessage("Hmm, seems like you got the wrong number or something,"
                + System.lineSeparator()
                + "are you trying to contact someone? :P");
            return;
        } catch (Exception e) {
            sendError();
            return;
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        int length = tasks.size();
        tasks.get(length - 1).setOrder(length);
        sendMessage("Got it. I've added this task:"
            + System.lineSeparator()
            + tasks.get(length - 1).toString());
    }

    private static void deleteTask(String taskNumber) throws ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IndexOutOfBoundsException {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        Task removedTask = tasks.get(taskNum);
        tasks.remove(taskNum);
        sendMessage("Alrighty! This task is gone with the wind:"
            + System.lineSeparator()
            + removedTask.toString()
            + System.lineSeparator()
            + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markTask(String taskNumber) throws ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IndexOutOfBoundsException {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        tasks.get(taskNum).markAsDone();
        sendMessage("Yippee! This task is now marked complete!:"
            + System.lineSeparator()
            + tasks.get(taskNum).toString());
    }

    private static void unmarkTask(String taskNumber) throws ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IndexOutOfBoundsException {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        tasks.get(taskNum).markAsNotDone();
        sendMessage("Aww man! I've marked the task as incomplete :( :"
            + System.lineSeparator()
            + tasks.get(taskNum).toString());
    }

    private static void printTasks(ArrayList<Task> tasks) {
        System.out.println(startDialogue() + "Here are the tasks in your list:" + System.lineSeparator());
        if (tasks.size() == 0) {
            System.out.println("You have no tasks in your list, nice!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).toString());
        }
        System.out.println(endDialogue());
    }

    private static String startDialogue() {
        return "___________________________________________________" + System.lineSeparator()
            + "==================================================|" + System.lineSeparator();
    }

    private static String endDialogue() {
        return System.lineSeparator() + "__________________________________________________|";
    }

    private static void sendMessage(String message) {
        System.out.println(startDialogue() + message + endDialogue());
        // return startDialogue() + message + endDialogue();
    }

    private static String logo() {
        return "    _    _       _" + System.lineSeparator()
            + "   / \\  | |_ __ | |__   __ _ " + System.lineSeparator()
            + "  / _ \\ | | '_ \\| '_ \\ / _` |" + System.lineSeparator()
            + " / ___ \\| | |_) | | | | (_| |" + System.lineSeparator()
            + "/_/   \\_\\_| .__/|_| |_|\\__,_|" + System.lineSeparator()
            + "          |_|" + System.lineSeparator();
    }

    private static void sendWelcomeMessage() {
        sendMessage("Hello! I'm" + System.lineSeparator() + logo() + "What can I do for you?");
    }

    private static void sendError() {
        sendMessage("Uh oh! I'm not sure how to react to that, have an ice cream instead!"
            + System.lineSeparator()
            + "         _.-." + System.lineSeparator()
            + "       ,'/ //\\" + System.lineSeparator()
            + "      /// // /)" + System.lineSeparator()
            + "     /// // //|" + System.lineSeparator()
            + "    /// // ///" + System.lineSeparator()
            + "   /// // ///" + System.lineSeparator()
            + "  (`: // ///" + System.lineSeparator()
            + "   `;`: ///" + System.lineSeparator()
            + "   / /:`:/" + System.lineSeparator()
            + "  / /  `'" + System.lineSeparator()
            + " / /" + System.lineSeparator()
            + "(_/");
    }
}
