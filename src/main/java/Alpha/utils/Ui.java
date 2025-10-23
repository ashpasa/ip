package utils;
import java.util.Scanner;

public class Ui {
    String userInput;
    Scanner input = new Scanner(System.in);

    public Ui() {
        this.userInput = null;
    }

    /**
     * Reads the user input from the command line
     */
    public String readCommand() {
        userInput = input.nextLine();
        return userInput;
    }

    /**
     * @return a string signalling the start of dialogue from the chatbot
     */
    private String startDialogue() {
        return "___________________________________________________________"
            + System.lineSeparator()
            + "==========================================================|"
            + System.lineSeparator();
    }

    /**
     * @return a string signalling the end of dialogue from the chatbot
     */
    private String endDialogue() {
        return System.lineSeparator()
            + "__________________________________________________________|";
    }

    /**
     * Formats a message by wrapping it with the dialogue starting and ending indicators
     * @param message a string message to be displayed to the user
     * @return the formatted message wrapped by the dialogue starting and ending indicators
     */
    private String formatMessage(String message) {
        return startDialogue() + message + endDialogue();
    }

    /**
     * Displays a formatted message to the user
     * @param message a string message to be displayed to the user
     */
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

    // Displays the startup message to the user
    public void sendWelcomeMessage() {
        sendMessage("Hello! I'm" + System.lineSeparator() + logo() + "Happy to see you!");
    }

    // Displays an error message to the user
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

    /**
     * Displays the list of tasks to the user
     * @param listOfTasks a string representation of the current list of tasks
     */
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