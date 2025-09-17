package tasks;

public class Task {
    private String description;
    private boolean isDone;
    private Integer order;
    protected Tasktypes type;

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
        String typeIndicator = "[ ]";
        switch (type) {
        case TODO:
            typeIndicator = "[T]";
            break;
        case DEADLINE:
            typeIndicator = "[D]";
            break;
        case EVENT:
            typeIndicator = "[E]";
            break;
        default:
            try {
                throw new Exception("Task type not recognized");
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
        return Integer.toString(order) + "." + typeIndicator + (isDone ? "[X] " : "[O] ") + description;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
