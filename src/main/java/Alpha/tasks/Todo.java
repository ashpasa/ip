package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.type = Tasktypes.TODO;
    }

    /**
     * @return String representation of the Todo task.
     */
    public String toString() {
        return super.toString();
    }
}
