package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String storageFile) {
        this.filePath = storageFile;
    }

    public void setFilePath(String storageFile) {
        this.filePath = storageFile;
    }

    public void writeTasksToFile(TaskList taskList) throws IOException {
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(taskList.listTasks());
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

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

    void checkIfTaskIsMarked(Ui ui, TaskList taskList, String storedLine) {
        String[] splitStoredLine = storedLine.split(".");
        String taskNumber = splitStoredLine[0];
        String taskDetails = splitStoredLine[1];
        if (isMarked(taskDetails)) {
            String command = "mark " + taskNumber;
            Parser.carryOutCommand(ui, taskList, this, command);
        }
    }

    boolean isMarked(String task) {
        return task.charAt(4) == 'X';
    }
}
