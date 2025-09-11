package alpha.task;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.type = Tasktypes.TODO;
    }

    public String toString() {
        return super.toString();
    }
}
