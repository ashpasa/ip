import java.util.Scanner;

public class Alpha {
    static Scanner input = new Scanner(System.in);
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
        if (command.equals("bye")) {
            System.out.println("____________________________________________________"
                + System.lineSeparator()
                + "Bye. Hope to see you again soon!"
                + System.lineSeparator()
                + "____________________________________________________");
            return 0;
        }
        System.out.println("____________________________________________________"
            + System.lineSeparator()
            + command
            + System.lineSeparator()
            + "____________________________________________________");
        return 1;
    }
}
