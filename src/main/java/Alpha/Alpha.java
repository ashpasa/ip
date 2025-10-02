import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Alpha {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        try {
            readTasksFromFile("data/taskList.txt");
        } catch (FileNotFoundException e) {
            System.out.println("No previous task list found, starting a new one...");
        }
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
                break;
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
        try {
            writeTasksToFile("data/taskList.txt", tasks);
        } catch (IOException e) {
            System.out.println("Error writing to file, please check if file exists.");
        }
    }

    private static void deleteTask(String taskNumber) throws ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IndexOutOfBoundsException, IOException {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        Task removedTask = tasks.get(taskNum);
        tasks.remove(taskNum);
        sendMessage("Alrighty! This task is gone with the wind:"
            + System.lineSeparator()
            + removedTask.toString()
            + System.lineSeparator()
            + "Now you have " + tasks.size() + " tasks in the list.");
        try {
            writeTasksToFile("data/taskList.txt", tasks);
        } catch (IOException e) {
            System.out.println("Error writing to file, please check if file exists.");
        }
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

    private static void writeTasksToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            try (FileWriter writer = new FileWriter(filePath)) {
                for (Task task : tasks)
                    if (task != null) {
                        writer.write(task.toString() + System.lineSeparator());
                    }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void readTasksFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String command = s.nextLine();
            String[] commandWords = savedTask(command);
            loadTask(commandWords);
        }
        s.close();
    }

    private static String[] savedTask(String savedTask) {
        return savedTask.split(" ", 2);
    }

    private static void loadTask(String[] commandWords) {
        switch (commandWords[0].charAt(3)) {
        case 'T':
            addTask(new Todo(commandWords[1]));
            break;
        case 'D':
            String description = commandWords[1].split(" \\(by: ")[0];
            String by = commandWords[1].split(" \\(by: ")[1].replace(")","").trim();
            addTask(new Deadline(description, by));
            break;
        case 'E':
            String desc = commandWords[1].split(" \\(from: ")[0];
            String startTime = commandWords[1].split(" \\(from: ")[1].split(" to: ")[0];
            String endTime = commandWords[1].split(" \\(from: ")[1].split(" to: ")[1].replace(")", "").trim();
            addTask(new Event(desc, startTime, endTime));
            break;
        default:
            sendError();
            break;
        }
        if (commandWords[0].charAt(6) == 'X') {
            markTask(String.valueOf(commandWords[0].charAt(0)));
        }
    }
}
