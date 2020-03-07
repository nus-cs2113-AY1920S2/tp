package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class EventCommandTest {
    @Test
    public void testExecute() {
        TaskList testTaskList = new TaskList();
        Ui ui = new Ui();
        EventCommand testEventCommand = new EventCommand("meeting", "Singapore", null, null);
        testEventCommand.execute(testTaskList, ui);
        assertEquals(testTaskList.getListSize(),1);
    }
}
