package seedu.storage;


import seedu.command.AddCommand;
import seedu.command.ListCommand;
import seedu.command.DeleteCommand;
import seedu.command.CommandResult;
import seedu.command.Command;
import seedu.exception.ProjException;
import seedu.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {

    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage();

    private String addCommandNonClass = "add n/name %c c/deadline";
    private String addCommandClass = "add n/name %c c/class";
    private String deleteCommand = "delete 1";
    private String listCommand = "list";

    private void initCommand(Command command) {
        command.setCommandVariables(taskList, storage, ui);
    }

    private void addOneNonClass() {

        String currInput = String.format(addCommandNonClass, ('A' + taskList.getListSize()));
        AddCommand command = new AddCommand(currInput);
        initCommand(command);

        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
    }

    private void addOneClass() {

        String input = String.format(addCommandClass, ('A' + taskList.getListSize()));
        AddCommand command = new AddCommand(input);
        initCommand(command);

        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
    }

    private void removeOneTask() {

        String input = deleteCommand;
        DeleteCommand command = new DeleteCommand(input);
        initCommand(command);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
    }


    private CommandResult getList(TaskList taskList) {

        ListCommand command = new ListCommand(listCommand);
        initCommand(command);

        try {
            return command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        return null;
    }


    @Test
    public void loadFromFile_nonClassTask_loaded() {

        addOneNonClass();
        addOneNonClass();
        addOneNonClass();
        addOneNonClass();

        TaskList testTaskList = new TaskList();
        storage.loadFromFile(testTaskList);

        CommandResult testResult = getList(testTaskList);
        CommandResult expectedResult = getList(taskList);

        String output = testResult.getFeedback();
        String expected = expectedResult.getFeedback();

        assertEquals(expected, output);

    }

    @Test
    public void loadFromFile_classTask_loaded() {

        addOneClass();
        addOneClass();
        addOneClass();

        TaskList testTaskList = new TaskList();
        storage.loadFromFile(testTaskList);

        CommandResult testResult = getList(testTaskList);
        CommandResult expectedResult = getList(taskList);

        String output = testResult.getFeedback();
        String expected = expectedResult.getFeedback();

        assertEquals(expected, output);
    }

    @Test
    public void overWriteFile_addAndDeleteTasks_updatedFile() {

        addOneClass();
        addOneNonClass();
        addOneClass();

        TaskList testTaskList = new TaskList();
        storage.loadFromFile(testTaskList);

        CommandResult testResult = getList(testTaskList);
        CommandResult expectedResult = getList(taskList);

        String output = testResult.getFeedback();
        String expected = expectedResult.getFeedback();

        assertEquals(expected, output);


        removeOneTask();
        removeOneTask();
        addOneNonClass();
        addOneClass();
        removeOneTask();

        testResult = getList(testTaskList);
        expectedResult = getList(taskList);

        output = testResult.getFeedback();
        expected = expectedResult.getFeedback();

        assertEquals(expected, output);

    }

}
