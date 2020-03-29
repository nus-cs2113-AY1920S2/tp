package seedu.atas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import tasks.Assignment;
import tasks.Task;

public class TaskListTest {
    //@@author joelczk
    @Test
    public void taskListTest_GetListSize() {
        TaskList testTaskList = new TaskList();
        assertEquals(testTaskList.getListSize(), 0);
    }

    @Test
    public void taskListTest_AddTask() {
        TaskList testTaskList = new TaskList();
        Task newTask = new Assignment(null, null, null, null);
        testTaskList.addTask(newTask);
        assertEquals(testTaskList.getListSize(), 1);
        testTaskList.addTask(newTask);
        assertEquals(testTaskList.getListSize(), 2);
    }
    //@@author joelczk
}
