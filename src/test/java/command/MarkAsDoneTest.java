package command;

import common.Messages;
import org.junit.jupiter.api.Test;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;
import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(String.format(Messages.INVALID_ID_ERROR, testTaskList.getRangeOfValidIndex(testTaskList)),
                result.feedbackToUser);
    }

    //@@author e0309556
    @Test
    public void repeatEvent_markedDoneButRepeated_taskNotDone() {
        LocalDateTime testDateTime = LocalDateTime.of(2020, 02, 14, 5, 30);
        RepeatEvent testRepeatEvent = new RepeatEvent("testRepeat", "home", testDateTime, testDateTime.plusMinutes(2),
                "-", 1, RepeatCommand.WEEKLY_ICON, testDateTime, 0);
        testRepeatEvent.setDone();
        assertEquals(testRepeatEvent.getIsDone(), true);
        testRepeatEvent.updateDate();
        assertEquals(testRepeatEvent.getIsDone(), false);
    }

    @Test
    public void event_markedDoneThenRepeated_taskNotDone() {
        LocalDateTime testDateTime = LocalDateTime.of(2020, 02, 14, 5, 30);
        Event testEvent = new Event("8 days ago", "CS2113T", testDateTime, testDateTime.plusHours(4),
                "testing");
        testEvent.setDone();

        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        testTaskList.addTask(testEvent);
        assertEquals(testEvent.getIsDone(), true);
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 5, RepeatCommand.DAILY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        assertEquals(testTaskList.getTask(0).getIsDone(), false);
    }
}
