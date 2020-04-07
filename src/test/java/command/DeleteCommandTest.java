package command;

import common.Messages;
import org.junit.jupiter.api.Test;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author joelczk
public class DeleteCommandTest {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Test
    public void executeMethod_success() {
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
    public void executeMethod_failure() {
        TaskList testTaskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> testTaskList.deleteTask(0));
    }
    //@@author Keith-JK

    @Test
    public void testEmptyList() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        DeleteCommand testDeleteCommand = new DeleteCommand(1);
        CommandResult result = testDeleteCommand.execute(testTaskList, testUi);
        assertEquals(Messages.NO_TASKS_MSG, result.feedbackToUser);
    }

    @Test
    public void testDeleteSuccess() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        LocalDateTime localDateTime = LocalDateTime.parse("01/01/2020 10:00", dateTimeFormatter);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse("01/01/2020 12:00", dateTimeFormatter);
        Event testEvent = new Event("test", "test", localDateTime, localDateTimeEnd, "test");
        testTaskList.addTask(testEvent);

        DeleteCommand testDeleteCommand = new DeleteCommand(0);
        CommandResult result = testDeleteCommand.execute(testTaskList, testUi);
        assertEquals(String.format(Messages.DELETE_SUCCESS_MESSAGE, testEvent.getName()), result.feedbackToUser);
    }

    @Test
    public void testDeleteOutOfRange() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        LocalDateTime localDateTime = LocalDateTime.parse("01/01/2020 10:00", dateTimeFormatter);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse("01/01/2020 12:00", dateTimeFormatter);
        Event testEvent = new Event("test", "test", localDateTime, localDateTimeEnd, "test");
        testTaskList.addTask(testEvent);

        DeleteCommand testDeleteCommand = new DeleteCommand(5);
        CommandResult result = testDeleteCommand.execute(testTaskList, testUi);
        assertEquals(String.format(Messages.INVALID_ID_ERROR, testTaskList.getRangeOfValidIndex(testTaskList)),
                result.feedbackToUser);
    }
}
