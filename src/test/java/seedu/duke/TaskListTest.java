package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import tasks.Task;

public class TaskListTest {
    @Test
    public void testGetListSize() {
        TaskList testTaskList = new TaskList();
        assertEquals(testTaskList.getListSize(), 0);
    }

    @Test
    public void testAddTask() {
        TaskList testTaskList = new TaskList();
        Task newTask = new Task(null, null, null, null);
        testTaskList.addTask(newTask);
        assertEquals(testTaskList.getListSize(), 1);
        testTaskList.addTask(newTask);
        assertEquals(testTaskList.getListSize(), 2);
    }

    @Test
    public void testDeleteTask_success() {
        TaskList testTaskList = new TaskList();
        Task newTask = new Task(null, null, null, null);
        testTaskList.addTask(newTask);
        testTaskList.addTask(newTask);
        testTaskList.addTask(newTask);
        testTaskList.deleteTask(2);
        assertEquals(testTaskList.getListSize(), 2);
        testTaskList.deleteTask(1);
        assertEquals(testTaskList.getListSize(), 1);
    }
}
