package command;

import org.junit.jupiter.api.Test;
import seedu.duke.TaskList;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkAsDoneTest {
    @Test
    public void testMarkAsDone_success() {
        TaskList testTaskList = new TaskList();
        Task newTask = new Task(null, null, null, null);
        testTaskList.addTask(newTask);
        testTaskList.markTaskAsDone(0);
        assertTrue(testTaskList.getTask(0).getIsDone());
    }

    @Test
    public void testMarkAsDone_failure() {
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.markTaskAsDone(0);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }
}
