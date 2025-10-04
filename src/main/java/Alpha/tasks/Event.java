package tasks;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        super.type = Tasktypes.EVENT;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    // Converts the task to a string with details of its starting and ending times
    public String toString() {
        return super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
