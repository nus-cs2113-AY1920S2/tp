package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.Parser;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class AssignmentCommandTest {
    @Test
    public void testExecute() {
        TaskList testTaskList = new TaskList();
        Ui ui = new Ui();
        AssignmentCommand testAssignmentCommand = new AssignmentCommand(
                "assignment", "CS2113T", Parser.parseDate("20/03/20 0900"), "yes"
        );
        testAssignmentCommand.execute(testTaskList, ui);
        assertEquals(testTaskList.getListSize(),1);
    }
}
