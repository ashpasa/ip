import java.io.FileNotFoundException;

public class Alpha {
    static Ui ui = new Ui();
    static TaskList taskList = new TaskList();
    static Storage storage = new Storage("data/taskList.txt");
    
    public static void main(String[] args) {
        ui.sendWelcomeMessage();
        try {
            storage.readTasksFromFile(ui, taskList);
            ui.sendMessage("Ooh, you have a saved task list!"
                + System.lineSeparator()
                + "Let me get it for you...");
        } catch (FileNotFoundException e) {
            System.out.println("It seems you don't have any saved tasks, let me create a new file for you...");
        }
        while (true) {
            String command = ui.readCommand();
            Parser.carryOutCommand(ui, taskList, storage, command);
        }
    }
}
