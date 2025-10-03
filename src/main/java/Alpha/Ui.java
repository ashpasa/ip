import java.util.Scanner;

public class Ui {
    String userInput;
    Scanner input = new Scanner(System.in);

    public Ui() {
        this.userInput = null;
    }

    public String readCommand() {
        userInput = input.nextLine();
        return userInput;
    }

    private String startDialogue() {
        return "___________________________________________________________"
            + System.lineSeparator()
            + "==========================================================|"
            + System.lineSeparator();
    }

    private String endDialogue() {
        return System.lineSeparator()
            + "__________________________________________________________|";
    }

    private String formatMessage(String message) {
        return startDialogue() + message + endDialogue();
    }

    public void sendMessage(String message) {
        String output = formatMessage(message);
        System.out.println(output);
    }

    private String logo() {
        return "    _    _       _" + System.lineSeparator()
            + "   / \\  | |_ __ | |__   __ _ " + System.lineSeparator()
            + "  / _ \\ | | '_ \\| '_ \\ / _` |" + System.lineSeparator()
            + " / ___ \\| | |_) | | | | (_| |" + System.lineSeparator()
            + "/_/   \\_\\_| .__/|_| |_|\\__,_|" + System.lineSeparator()
            + "          |_|" + System.lineSeparator();
    }

    public void sendWelcomeMessage() {
        sendMessage("Hello! I'm" + System.lineSeparator() + logo() + "Happy to see you!");
    }

    public void sendError() {
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

    public void printTasks(String listOfTasks) {
        if (listOfTasks == "") {
            sendMessage("You have no tasks in your list, nice!");
            return;
        }
        sendMessage("Here are the tasks in your list:"
            + System.lineSeparator()
            + listOfTasks);
    }
}