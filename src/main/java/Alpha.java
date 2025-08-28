import java.util.Scanner;

public class Alpha {
    static Scanner input = new Scanner(System.in);
    private static String[] taskList = new String[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        int status = 1;
        System.out.println("____________________________________________________"
            + System.lineSeparator()
            + "Hello! I'm Alpha"
            + System.lineSeparator()
            + "What can I do for you?"
            + System.lineSeparator()
            + "____________________________________________________");
        do {
            status = executeCommand();
        } while (status != 0);
    }

    private static int executeCommand() {
        String command = input.nextLine();
        switch (command) {
        case "bye":
            System.out.println("____________________________________________________"
                + System.lineSeparator()
                + "Bye. Hope to see you again soon!"
                + System.lineSeparator()
                + "____________________________________________________");
        return 0;
        case "list":
            System.out.println("____________________________________________________"
                + System.lineSeparator()
                + "Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i]);
            }
            System.out.println("____________________________________________________");
            return 1;
        default:
            addTask(command, taskCount);
            taskCount++;
            System.out.println("____________________________________________________"
                + System.lineSeparator()
                + "added: " + command
                + System.lineSeparator()
                + "____________________________________________________");
            return 1;
        }
    }

    private static void addTask(String task, int taskCount) {
        taskList[taskCount] = task;
        taskCount++;
        System.out.println("Got it. I've added this task:"
            + System.lineSeparator()
            + "  " + task
            + System.lineSeparator()
            + "Now you have " + (taskCount) + " tasks in the list.");
    }
}
