package seedu.nuke.command;

import seedu.nuke.task.Task;

import java.util.ArrayList;
import java.util.Collections;

public class CheckDeadlineCommand extends Command {

    ArrayList<Task> tasks;

    public CheckDeadlineCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;

    }

    public ArrayList<String> checkAllDeadlines() {
        ArrayList<String> deadlines = new ArrayList<>();
        for(Task task: tasks) {
            deadlines.add("Task: " + task.getDescription()+"  Deadline: " + task.getDeadline().toString());
        }
        return deadlines;
    }

    public void sortAllTasks() {
        Collections.sort(tasks);
    }

    @Override
    public CommandResult execute() {
        return null;
    }
}
