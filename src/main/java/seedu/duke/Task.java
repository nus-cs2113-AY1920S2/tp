package seedu.duke;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    public String getAsRow(String divider) throws Exception {
        if (divider.equals("")) {
            throw new Exception("empty divider not allowed");
        }
        return description + divider + (isDone ? ":-)" : ":-(");
    }
}
