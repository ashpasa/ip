package tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        super.type = Tasktypes.DEADLINE;
        this.by = by;
    }
    
    // Converts the task to a string with details on its due date
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
