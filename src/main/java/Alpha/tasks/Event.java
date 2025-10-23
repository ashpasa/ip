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
    
    /**
     * @return String representation of the event task, with start and end times
     */
    public String toString() {
        return super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
