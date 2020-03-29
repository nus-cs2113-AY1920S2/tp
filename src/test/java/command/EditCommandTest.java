package command;

import common.Messages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import seedu.atas.TaskList;
import tasks.Assignment;
import tasks.Event;
import java.time.LocalDateTime;
import seedu.atas.Parser;
import seedu.atas.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author jichngan
public class EditCommandTest {
    private static TaskList filledTaskList;
    private static String dateStringOne = "12/03/20 1600";
    private static String dateStringTwo = "12/03/20 1800";

    private static String dateStringThree = "13/03/20 1600";
    private static String dateStringFour = "13/03/20 1800";

    private static LocalDateTime dateOne = LocalDateTime.parse(dateStringOne, Parser.INPUT_DATE_TIME_FORMAT);
    private static LocalDateTime dateTwo = LocalDateTime.parse(dateStringTwo, Parser.INPUT_DATE_TIME_FORMAT);
    private static LocalDateTime dateThree = LocalDateTime.parse(dateStringThree, Parser.INPUT_DATE_TIME_FORMAT);
    private static LocalDateTime dateFour = LocalDateTime.parse(dateStringFour, Parser.INPUT_DATE_TIME_FORMAT);

    private static Ui ui;

    /**
     * Initialise TaskList for testing.
     */
    @BeforeAll
    public static void setup() {
        filledTaskList = new TaskList();
        Assignment firstAssignment = new Assignment("one", "cs2113", dateOne, "None");
        Assignment secondAssignment = new Assignment("two", "cs2113", dateOne, "None");
        Event firstEvent = new Event("meeting one", "cs2113", dateOne, dateTwo,"None");
        Event secondEvent = new Event("meeting two", "cs2113", dateThree, dateFour,"None");

        filledTaskList.addTask(firstAssignment);
        filledTaskList.addTask(secondAssignment);
        filledTaskList.addTask(firstEvent);
        filledTaskList.addTask(secondEvent);
    }

    @Test
    public void editAssignment_filledList_success() {
        Assignment editedAssignment = new Assignment("three", "cs2113", dateOne, "None");
        filledTaskList.editTask(0, editedAssignment);
        assertEquals(filledTaskList.getTask(0), editedAssignment);
    }

    @Test
    public void editEvent_filledList_success() {
        Event editedEvent = new Event("meeting three", "cs2113", dateOne, dateTwo, "None");
        filledTaskList.editTask(2, editedEvent);
        assertEquals(filledTaskList.getTask(2), editedEvent);
    }

    @Test
    public void editTask_emptyList_failure() {
        TaskList emptyTaskList = new TaskList();
        assertEquals(new EditCommand(1).execute(emptyTaskList,ui).feedbackToUser,
                Messages.NO_TASKS_MSG);
    }

    @Test
    public void editTask_filledList_failure() {
        assertEquals(new EditCommand(6).execute(filledTaskList, ui).feedbackToUser,
                "Please provide a valid task number from 1 to 4");
    }

}
