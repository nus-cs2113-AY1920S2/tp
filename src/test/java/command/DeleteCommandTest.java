package command;

import org.junit.jupiter.api.Test;
import seedu.atas.TaskList;
import tasks.Assignment;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    @Test
    public void testDelete_success() {
        TaskList testTaskList = new TaskList();
        Task newTask = new Assignment(null, null, null, null);
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
        assertThrows(IndexOutOfBoundsException.class, () -> testTaskList.deleteTask(0));
    }
}
