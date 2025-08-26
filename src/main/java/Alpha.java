import java.util.Scanner;

public class Alpha {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int status = 1;
        System.out.println("____________________________________________________\n"
            + "Hello! I'm Alpha\n"
            + "What can I do for you?\n"
            + "____________________________________________________");
        do {
            status = executeCommand();
        } while (status != 0);
    }

    private static int executeCommand() {
        String command = scanner.nextLine();
        if (command.equals("bye")) {
            System.out.println("____________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________");
            return 0;
        }
        System.out.println("____________________________________________________\n"
            + command + "\n"
            + "____________________________________________________");
        return 1;
    }
}
