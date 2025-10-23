import java.io.FileNotFoundException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class Alpha {
    static Ui ui;
    static TaskList taskList;
    static Storage storage;

    public Alpha(String filePath) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(filePath);
        try {
            storage.readTasksFromFile(ui, taskList);
            System.out.println("Saved task list found! Loading tasks...");
        } catch (FileNotFoundException e) {
            System.out.println("It seems you don't have any saved tasks, let me create a new file for you...");
        }
        return;
    }

    /**
     * Runs the Alpha application
     */
    public void run() {
        ui.sendWelcomeMessage();
        while (true) {
            String command = ui.readCommand();
            Parser.carryOutCommand(ui, taskList, storage, command);
        }
    }

    /**
     * The main entry point for the Alpha application, initialising the data folder path
     */
    public static void main(String[] args) {
        new Alpha("data/taskList.txt").run();
    }
}
