package task;

public abstract class Task {
    protected boolean isDone;
    protected String description;
    private static int totalNumberOfTasks;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalNumberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public static int getTotalNumberOfTasks() {
        return totalNumberOfTasks;
    }

    @Override
    public abstract String toString();

}
