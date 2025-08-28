public class Task {
    private String description;
    private boolean isDone;
    private Integer order;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.order = 0;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return Integer.toString(order) + "." + (isDone ? "[X] " : "[ ] ") + description;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
