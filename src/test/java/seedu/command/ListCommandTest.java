package seedu.command;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.common.Constants.TAB;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.common.Constants.TAB;

class ListCommandTest {

    @Test
    void checkListZeroException() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        Command command = new ListCommand("");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = "  [Alert][list] List is empty";

        assertEquals(expected, output);
    }

    @Test
    void checkListOne() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/11:15-13:15 d/2024-02-29";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
        command = new ListCommand("");
        command.setCommandVariables(tasks, storage, ui);

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 1 task.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n";

        assertEquals(expected, output);
    }


    @Test
    void checkListMul() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/11:15-13:15 d/2024-02-29";


        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new ListCommand("");
        command.setCommandVariables(tasks, storage, ui);

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 6 tasks.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    2. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    3. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    4. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    5. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    6. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n";

        assertEquals(expected, output);
    }


    @Test
    void testListByTime() {

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/11:15-13:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/13:00-15:00 d/2028-02-19";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new ListCommand("list t/11:15-13:00");
        command.setCommandVariables(tasks, storage, ui);

        try {

            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 2 tasks.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    2. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n";

        assertEquals(expected, output);
    }

    @Test
    void testListByDate() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task  t/11:15-13:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        //first input
        try {
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task2 t/13:00-15:00 d/2028-02-15";
        //second input
        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }


        input = "add n/task1 t/11:15-13:15 d/2024-02-27 c/deadline";
        //third input
        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/13:00-15:00 d/2028-02-19";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }
        command = new ListCommand("list d/2028-02-19 2024-02-29");
        command.setCommandVariables(tasks, storage, ui);

        try {

            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 2 tasks.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    2. [N] [TODO] Title: task | 2028-02-19 : 13:00 - 15:00\n";

        assertEquals(expected, output);
    }

    @Test
    void testListByDateTime() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/11:15-13:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/13:00-15:00 d/2024-02-29";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/11:15-13:15 d/2024-02-28";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new ListCommand("list t/12:15-12:30 d/2024-02-29");
        command.setCommandVariables(tasks, storage, ui);

        try {

            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 3 tasks.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    2. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    3. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n";

        assertEquals(expected, output);
    }


    @Test
    void testListByCategoryDateTime() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/11:15-13:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task i/cs2112 t/13:00-15:00 d/2024-02-29";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/11:15-13:15 d/2024-02-29 c/deadline";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }


        input = "add n/task t/11:15-13:15 d/2024-02-28";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new ListCommand("list t/12:15-12:30 d/2024-02-29 c/deadline");
        command.setCommandVariables(tasks, storage, ui);

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 3 tasks.\n"
                + "    1. [N] [DEADLINE] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    2. [N] [DEADLINE] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    3. [N] [DEADLINE] Title: task | 2024-02-29 : 11:15 - 13:15\n";
        assertEquals(expected, output);
    }

    @Test
    void testListByCategoryTime() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/11:15-13:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/13:00-15:00 d/2028-02-19";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new ListCommand("list t/11:15-12:30 c/todo");
        command.setCommandVariables(tasks, storage, ui);

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 2 tasks.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    2. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n";

        assertEquals(expected, output);
    }

    @Test
    void testListByCategoryTime2400() {
        //task the time 2400
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/21:15-23:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/13:00-15:00 d/2028-02-19";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new ListCommand("list t/23:00-24:00 c/todo");
        command.setCommandVariables(tasks, storage, ui);

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 1 task.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 21:15 - 23:15\n";

        assertEquals(expected, output);
    }


    @Test
    void testListByCategoryDate() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        String output = "";
        String input = "add n/task  t/11:15-13:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/13:00-15:00 d/2028-02-19";
        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }


        input = "add n/task t/11:15-13:15 d/2024-02-29 c/deadline";
        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/task t/13:00-15:00 d/2028-02-19";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();

        } catch (ProjException e) {
            assertTrue(false);
        }
        command = new ListCommand("list d/2028-02-19 2024-02-29 c/deadline");
        command.setCommandVariables(tasks, storage, ui);

        try {

            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 1 task.\n"
                + "    1. [N] [DEADLINE] Title: task | 2024-02-29 : 11:15 - 13:15\n";

        assertEquals(expected, output);
    }


    @Test
    void testListByCategory() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String output = "";
        String input = "add n/task t/11:15-13:15 d/2024-02-29";

        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add n/2113 v2.1 t/23:00-24:00 d/2020-05-16 c/deadline";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        input = "add t/11:00-12:00 01:00-03:00 n/2113 d/3 4 c/CLASS l/COM2 COM1";

        command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new ListCommand("list c/todo");
        command.setCommandVariables(tasks, storage, ui);

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + "There are 3 tasks.\n"
                + "    1. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    2. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n"
                + "    3. [N] [TODO] Title: task | 2024-02-29 : 11:15 - 13:15\n";

        assertEquals(expected, output);
    }
}