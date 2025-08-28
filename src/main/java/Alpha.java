import java.util.Scanner;

public class Alpha {
    static Scanner input = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
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
        if (command.startsWith("mark")) {
            System.out.println("____________________________________________________"
                + System.lineSeparator()
                + "Nice! I've marked this task as done:"
                + System.lineSeparator());
            taskList[Integer.parseInt(command.split(" ")[1]) - 1].markAsDone();
            printTasks(taskList);
            System.out.println("____________________________________________________");
            return 1;
        } else if (command.startsWith("unmark")) {
            System.out.println("____________________________________________________"
                + System.lineSeparator()
                + "Alright, I've marked this task as undone:"
                + System.lineSeparator());
            taskList[Integer.parseInt(command.split(" ")[1]) - 1].markAsNotDone();
            printTasks(taskList);
            System.out.println("____________________________________________________");
            return 1;
        }
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
            printTasks(taskList);
            System.out.println("____________________________________________________");
            return 1;
        default:
            addTask(command);
            System.out.println("____________________________________________________"
                + System.lineSeparator()
                + "added: " + command
                + System.lineSeparator()
                + "____________________________________________________");
            return 1;
        }
    }

    private static void addTask(String task) {
        taskList[taskCount] = new Task(task);
        taskList[taskCount].setOrder(taskCount + 1);
        taskCount++;
    }

    private static void printTasks(Task[] taskList) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(taskList[i].toString());
        }
    }
}
