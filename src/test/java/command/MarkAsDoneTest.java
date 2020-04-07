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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkAsDoneTest {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Test
    public void testMarkAsDone_success() {
        TaskList testTaskList = new TaskList();
        Task newTask = new Assignment(null, null, null, null);
        testTaskList.addTask(newTask);
        testTaskList.markTaskAsDone(0);
        assertTrue(testTaskList.getTask(0).getIsDone());
    }

    @Test
    public void testMarkAsDone_failure() {
        TaskList testTaskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> testTaskList.markTaskAsDone(0));
    }

    @Test
    public void testEmptyList() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        DoneCommand testDoneCommand = new DoneCommand(1);
        CommandResult result = testDoneCommand.execute(testTaskList, testUi);
        assertEquals(Messages.NO_TASKS_MSG, result.feedbackToUser);
    }

    @Test
    public void testTaskAlreadyCompleted() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        LocalDateTime localDateTime = LocalDateTime.parse("01/01/2020 10:00", dateTimeFormatter);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse("01/01/2020 12:00", dateTimeFormatter);
        Event testEvent = new Event("test", "test", localDateTime, localDateTimeEnd, "test");
        testEvent.setDone();
        testTaskList.addTask(testEvent);

        DoneCommand testDoneCommand = new DoneCommand(0);
        CommandResult result = testDoneCommand.execute(testTaskList, testUi);
        assertEquals(Messages.COMPLETED_TASK_ERROR, result.feedbackToUser);
    }

    @Test
    public void testDoneSuccess() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        LocalDateTime localDateTime = LocalDateTime.parse("01/01/2020 10:00", dateTimeFormatter);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse("01/01/2020 12:00", dateTimeFormatter);
        Event testEvent = new Event("test", "test", localDateTime, localDateTimeEnd, "test");
        testTaskList.addTask(testEvent);

        DoneCommand testDoneCommand = new DoneCommand(0);
        CommandResult result = testDoneCommand.execute(testTaskList, testUi);
        assertEquals(String.format(Messages.DONE_SUCCESS_MESSAGE, testEvent.getName()), result.feedbackToUser);
    }

    @Test
    public void testIndexOutOfBounds() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        LocalDateTime localDateTime = LocalDateTime.parse("01/01/2020 10:00", dateTimeFormatter);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse("01/01/2020 12:00", dateTimeFormatter);
        Event testEvent = new Event("test", "test", localDateTime, localDateTimeEnd, "test");
        testTaskList.addTask(testEvent);

        DoneCommand testDoneCommand = new DoneCommand(5);
        CommandResult result = testDoneCommand.execute(testTaskList, testUi);
        assertEquals(String.format(Messages.INVALID_ID_ERROR, testTaskList.getRangeOfValidIndex(testTaskList)), result.feedbackToUser);
    }
}
