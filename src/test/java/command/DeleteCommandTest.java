package command;

import org.junit.jupiter.api.Test;
import seedu.duke.TaskList;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {
    @Test
    public void testDelete_success() {
        TaskList testTaskList = new TaskList();
        Task newTask = new Task(null, null, null, null);
        testTaskList.addTask(newTask);
        testTaskList.addTask(newTask);
        testTaskList.deleteTask(1);
        assertEquals(testTaskList.getListSize(), 1);
        testTaskList.deleteTask(0);
        assertEquals(testTaskList.getListSize(), 0);
    }

    @Test
    public void testDelete_failure() {
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.deleteTask(0);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }
}
