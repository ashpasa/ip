package utils;
import java.io.IOException;
import java.util.ArrayList;
import tasks.Task;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Ui ui, Task task) {
        this.tasks.add(task);
        ui.sendMessage("Got it. I've added this task:"
            + System.lineSeparator()
            + task.toString());
    }

    public String listTasks() {
        String output = "";
        for (Task task : tasks) {
            output += String.valueOf(tasks.indexOf(task) + 1) + "." + task.toString() + System.lineSeparator();
        }
        return output;
    }

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
}