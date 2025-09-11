import java.util.Scanner;

public class Alpha {
    static Scanner input = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

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
                printTasks(taskList);
                break;
            case "bye":
                System.out.println(sendMessage("Bye. Hope to see you again soon!"));
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
            default:
                sendError();
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(sendError());
            return;
        } catch (NullPointerException e) {
            System.out.println(sendError());
            return;
        }
    }

    private static void addTask(Task task) {
        taskList[taskCount] = task;
        taskList[taskCount].setOrder(taskCount + 1);
        System.out.println(
            sendMessage("Got it. I've added this task:"
            + System.lineSeparator()
            + taskList[taskCount].toString())
            );
        taskCount++;
    }

    private static void markTask(String taskNumber) {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        checkBounds(taskNum);
        taskList[taskNum].markAsDone();
        System.out.println(sendMessage("Alrighty! This task is now marked complete!:"
            + System.lineSeparator()
            + taskList[taskNum].toString()));
    }

    private static void unmarkTask(String taskNumber) {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        checkBounds(taskNum);
        taskList[taskNum].markAsNotDone();
        System.out.println(sendMessage("Aww man! I've marked the task as incomplete :( :"
            + System.lineSeparator()
            + taskList[taskNum].toString()));
    }

    private static void checkBounds(int taskNum) {
        if (taskNum <= 0 || taskNum > taskCount) {
            System.out.println(sendError());
            return;
        }
    }

    private static void printTasks(Task[] taskList) {
        System.out.println(startDialogue() + "Here are the tasks in your list:" + System.lineSeparator());
        if (taskCount == 0) {
            System.out.println(sendMessage("You have no tasks in your list, nice!"));
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.println(taskList[i].toString());
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

    private static String sendMessage(String message) {
        return startDialogue() + message + endDialogue();
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
        System.out.println(
            sendMessage("Hello! I'm" + System.lineSeparator() + logo() + "What can I do for you?")
            );
    }

    private static String sendError() {
        return startDialogue()
            + "Oops! I'm not sure how to react to that, have an ice cream instead!"
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
            + "(_/"
            + endDialogue();
    }
}
