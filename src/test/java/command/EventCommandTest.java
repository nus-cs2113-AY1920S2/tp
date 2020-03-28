package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;

//@@author
public class EventCommandTest {
    @Test
    public void testExecute() {
        TaskList testTaskList = new TaskList();
        Ui ui = new Ui();
        EventCommand testEventCommand = new EventCommand(
                "meeting", "Singapore", Parser.parseDate("20/03/20 0900"),
                Parser.parseDate("20/03/20 1100"), null
        );
        testEventCommand.execute(testTaskList, ui);
        assertEquals(testTaskList.getListSize(),1);
    }
}
