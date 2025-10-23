package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    // File path of the faile where the task list will be stored as text
    String filePath;

    public Storage(String storageFile) {
        this.filePath = storageFile;
    }

    public void setFilePath(String storageFile) {
        this.filePath = storageFile;
    }

    // Saves the task list to the storage file by overriding its contents
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

    // Reads the storage file line by line and passes it in to the parser as a command
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

    // Takes in a single line from the storage file and restructures the information into a command string
    String rebuiltCommand(String storedLine) {
        String command = "";
        String fullTaskDetails = storedLine.split(".")[1];
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

    // Takes in a line from the storage file and passes a mark command to the parser if necessary
    void checkIfTaskIsMarked(Ui ui, TaskList taskList, String storedLine) {
        String[] splitStoredLine = storedLine.split(".");
        String taskNumber = splitStoredLine[0];
        String taskDetails = splitStoredLine[1];
        if (isMarked(taskDetails)) {
            String command = "mark " + taskNumber;
            Parser.carryOutCommand(ui, taskList, this, command);
        }
    }

    // Returns true if a task is marked
    boolean isMarked(String task) {
        return task.charAt(4) == 'X';
    }
}
