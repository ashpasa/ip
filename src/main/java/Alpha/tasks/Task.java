package tasks;

public abstract class Task {
    private String description;
    private boolean isDone;
    protected Tasktypes type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
        return typeIndicator + (isDone ? "[X] " : "[O] ") + description;
    }

    public String getDescription() {
        return description;
    }
}
