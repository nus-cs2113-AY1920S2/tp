package seedu.tp.commands;

public class CommandFeedback {
    private final String feedback;
    private final boolean isEmpty;

    public CommandFeedback() {
        feedback = null;
        isEmpty = true;
    }

    public CommandFeedback(String feedback) {
        this.feedback = feedback;
        isEmpty = false;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public String toString() {
        if (isEmpty) {
            return "";
        } else {
            return feedback;
        }
    }
}
