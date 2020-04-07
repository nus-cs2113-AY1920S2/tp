package seedu.nuke.util;

import org.junit.jupiter.api.Test;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCreatorTest {

    private final String divider = String.format("%s%s%s\n", "+", "-".repeat(98), "+");

    @Test
    void createGeneralListTable() {
        ArrayList<String> listToShow = new ArrayList<>(Arrays.asList("Test", "General", "List", "Table"));
        String expected = divider + "\nTest\nGeneral\nList\nTable\n" + divider;

        assertEquals(expected, ListCreator.createGeneralListTable(listToShow));
    }

    @Test
    void createModuleListTable() {
        ArrayList<Module> moduleList = new ArrayList<>(Arrays.asList(
                new Module("CS1231", "Discrete Structures", ""),
                new Module("CS2100", "Computer Organisation", ""),
                new Module("CS2113T", "Software Engineering & Object-Oriented Programming", "")
        ));
        String expected =
                "+--------------------------------------------------------------------------------------------------+\n"
                + " NO |  MODULE CODE   |                                 MODULE TITLE                                 "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + " 1  |     CS1231     |                             Discrete Structures                              "
                + "\n"
                + " 2  |     CS2100     |                            Computer Organisation                             "
                + "\n"
                + " 3  |    CS2113T     |              Software Engineering & Object-Oriented Programming              "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + "Total modules: 3\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n";

        assertEquals(expected, ListCreator.createModuleListTable(moduleList));
    }

    @Test
    void createCategoryListTable() {
        Module module = new Module("CS1231", "Discrete Structures", "");
        ArrayList<Category> categoryList = new ArrayList<>(Arrays.asList(
                new Category(module, "Lab", 3),
                new Category(module, "Lecture", 1),
                new Category(module, "Tutorial", 2)
        ));
        String expected =
                "+--------------------------------------------------------------------------------------------------+\n"
                + " NO |     MODULE     |                                CATEGORY                                | PTY "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + " 1  |     CS1231     |                                  Lab                                   |  3  "
                + "\n"
                + " 2  |     CS1231     |                                Lecture                                 |  1  "
                + "\n"
                + " 3  |     CS1231     |                                Tutorial                                |  2  "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + "Total categories: 3\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n";

        assertEquals(expected, ListCreator.createCategoryListTable(categoryList));
    }

    @Test
    void createTaskListTable() {
        Module module = new Module("CS1231", "Discrete Structures", "");
        Category category = new Category(module, "Lecture", 1);
        ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(
                new Task(category, "Lecture Quiz 6", new DateTime(LocalDate.of(2020, 6, 6)), 6),
                new Task(category, "read chapter 6 notes", new DateTime(), 1)
        ));
        String expected =
                "+--------------------------------------------------------------------------------------------------+\n"
                + " NO |  MODULE  |    CATEGORY    |          TASK           |          DEADLINE          | PTY | DONE "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + " 1  |  CS1231  |    Lecture     |     Lecture Quiz 6      |     06/06/2020 11:59PM     |  6  | [N]  "
                + "\n"
                + " 2  |  CS1231  |    Lecture     |  read chapter 6 notes   |           -NIL-            |  1  | [N]  "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + "Total tasks: 2\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n";

        assertEquals(expected, ListCreator.createTaskListTable(taskList));
    }

    @Test
    void createFileListTable() {
        Module module = new Module("CS1231", "Discrete Structures", "");
        Category category = new Category(module, "Lecture", 1);
        Task task = new Task(category, "read chapter 6 notes", new DateTime(), 1);
        ArrayList<TaskFile> fileList = new ArrayList<>(Arrays.asList(
                new TaskFile(task, "notes_6", "filePath", "originalFilePath")
        ));
        String expected =
                "+--------------------------------------------------------------------------------------------------+\n"
                + " NO |    MODULE     |      CATEGORY      |           TASK            |             FILE             "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + " 1  |    CS1231     |      Lecture       |   read chapter 6 notes    |           notes_6            "
                + "\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n"
                + "Total files: 1\n"
                + "+--------------------------------------------------------------------------------------------------+"
                + "\n";

        assertEquals(expected, ListCreator.createFileListTable(fileList));
    }
}