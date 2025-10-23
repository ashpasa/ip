package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    /**
     * File path of the storage file where the task list will be saved
     */
    String filePath;

    public Storage(String storageFile) {
        this.filePath = storageFile;
    }

    public void setFilePath(String storageFile) {
        this.filePath = storageFile;
    }

    /**
     * Writes the current tasks in the task list to the storage file by overriding its contents
     * @param taskList The TaskList instance containing the tasks to be saved
     * @throws IOException
     */
    public void writeTasksToFile(TaskList taskList) throws IOException {
        File f = new File(filePath);
        try {
            File parentDir = f.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(taskList.listTasks());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the storage file and restores the task list by passing each task as a command to the parser
     * @param ui UI instance for sending messages
     * @param taskList The TaskList instance to which the tasks will be added
     * @throws FileNotFoundException
     */
    public void readTasksFromFile(Ui ui, TaskList taskList) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String storedLine = s.nextLine();
            Parser.carryOutCommand(ui, taskList, this, rebuiltCommand(storedLine));
            checkIfTaskIsMarked(ui, taskList, storedLine);
        }
        s.close();
    }

    /**
     * Rebuilds a command string from a line stored in the storage file
     * @param storedLine A single line from the storage file representing a stored task
     * @return A command string that can be processed by the parser to recreate the task
     */
    String rebuiltCommand(String storedLine) {
        String command = "";
        String fullTaskDetails = storedLine.split("\\.")[1];
        String description = storedLine.split(" ")[1];
        switch (fullTaskDetails.charAt(1)) {
        case 'T':;
            command += "todo " + description;
            break;
        case 'D':
            String details = description.split(" \\(by: ")[0];
            String by = description.split(" \\(by: ")[1].replace(")", "").trim();
            command += "deadline " + details + " /by " + by;
            break;
        case 'E':
            String event = description.split(" \\(from: ")[0];
            String startTime = description.split(" \\(from: ")[1].split(" to: ")[0];
            String endTime = description.split(" \\(from: ")[1].split(" to: ")[1].replace(")", "").trim();
            command = "event " + event + " /from " + startTime + " /to " + endTime;
            break;
        }
        return command;
    }

    /**
     * Checks if a task from the storage file is marked and sends a mark command to the parser if it is
     * @param ui UI instance for sending messages
     * @param taskList The TaskList instance containing the tasks
     * @param storedLine A single line from the storage file representing a stored task
     */
    void checkIfTaskIsMarked(Ui ui, TaskList taskList, String storedLine) {
        String[] splitStoredLine = storedLine.split("\\.");
        String taskNumber = splitStoredLine[0];
        String taskDetails = splitStoredLine[1];
        if (isMarked(taskDetails)) {
            String command = "mark " + taskNumber;
            Parser.carryOutCommand(ui, taskList, this, command);
        }
    }

    /**
     * Determines if a task is marked as completed based on its stored line representation
     * @param task A single line from the storage file representing a stored task
     * @return true if the task is marked, false otherwise
     */
    boolean isMarked(String task) {
        return task.charAt(4) == 'X';
    }
}
