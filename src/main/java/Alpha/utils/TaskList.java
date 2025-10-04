package utils;
import java.io.IOException;
import java.util.ArrayList;
import tasks.Task;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    // Adds an element of type Task to the list, with a success message
    public void addTask(Ui ui, Task task) {
        this.tasks.add(task);
        ui.sendMessage("Got it. I've added this task:"
            + System.lineSeparator()
            + task.toString());
    }

    // creates a list of every stored task in the form of a string
    public String listTasks() {
        String output = "";
        Integer indexOfTask;
        String taskNumber;
        for (Task task : tasks) {
            indexOfTask = tasks.indexOf(task);
            taskNumber = String.valueOf(indexOfTask + 1);
            output += taskNumber + "." + task.toString() + System.lineSeparator();
        }
        return output;
    }

    // Removes a task from the list
    void deleteTask(Ui ui, String taskNumber) throws ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IndexOutOfBoundsException, IOException {
        int taskNum = Integer.parseInt(taskNumber) - 1;
        Task removedTask = tasks.get(taskNum);
        tasks.remove(taskNum);
        ui.sendMessage("Alrighty! This task is gone with the wind:"
            + System.lineSeparator()
            + removedTask.toString()
            + System.lineSeparator()
            + "Now you have " + tasks.size() + " tasks in the list.");
    }

    // Takes in a string as input and returns a string out of the tasks that contain the input string
    String listTasksWithString(Ui ui, String specifiedString) {
        String tasksWithString = "";
        for (Task task : tasks) {
            if (task.getDescription().contains(specifiedString)) {
                Integer indexOfTaskWithString = tasks.indexOf(task);
                String taskNumber = String.valueOf(indexOfTaskWithString + 1);
                tasksWithString += taskNumber + "." + task.toString() + System.lineSeparator();
            }
        }
        return tasksWithString;
    }
}