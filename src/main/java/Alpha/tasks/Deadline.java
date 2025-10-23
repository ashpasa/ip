package tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        super.type = Tasktypes.DEADLINE;
        this.by = by;
    }
    
    /**
     * @return String representation of the deadline task, with its due date
     */
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
