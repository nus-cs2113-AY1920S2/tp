package seedu.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

class MainTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    public void sampleTest() {
        String input = "add n/research paper d/2020-06-06 i/essay for writing 101";
        String expected = "[TODO] Title: research paper | "
                + "Description: essay for writing 101 | 2020-06-06";

        AddCommand command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String output = tasks.getTask(0).toString();
        assertEquals(expected, output);

    }

    @Test
    public void emptyCommandTest() {

        String testInput = "";
        Command command = new Parser().parseCommand(testInput);
        command.setCommandVariables(tasks, storage, ui);
        boolean checkIfHelpCommand = command instanceof HelpCommand;
        assertTrue(checkIfHelpCommand);
    }


}