package task;

import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author GanapathySanathBalaji

/**
 * Contains all the test related to TaskList class.
 */
class TaskListTest {

    Ui ui = new Ui();
    task.TaskList taskList;

    public TaskListTest() throws Exception {
        taskList = new task.TaskList();
        taskList.addTask(new Event("Team Meeting", "2021-03-21", "09:00",
                "17:00", "1"),ui);
    }

    @Test
    void deleteTask() throws Exception {
        if (taskList.getTaskListSize() > 0) {
            try {
                taskList.deleteTask(1, ui);
            } catch (Exception e) {
                fail("Deletion at valid index causes an error");
            }
            taskList.addTask(new Event("Team Meeting", "2021-03-21", "09:00",
                    "17:00", "1"),ui);
        }
    }

    @Test
    void deleteTask_indexMoreThanSize() {
        try {
            taskList.deleteTask(2,ui);
        } catch (Exception e) {
            fail("Deletion at an index greater than size not handled");
        }
    }

    @Test
    void deleteTask_negativeIndex() {
        try {
            taskList.deleteTask(-2,ui);
        } catch (Exception e) {
            fail("Deletion at a negative index not handled");
        }
    }

    @Test
    void deleteTask_zeroIndex() {
        try {
            taskList.deleteTask(0,ui);
        } catch (Exception e) {
            fail("Deletion at a zero index not handled");
        }
    }

    @Test
    void editTask_zeroIndex() {
        try {
            taskList.editTask(0,ui);
        } catch (Exception e) {
            fail("Editing a task at index zero not handled");
        }
    }

    @Test
    void editTask_indexMoreThanSize() {
        try {
            taskList.editTask(2,ui);
        } catch (Exception e) {
            fail("Editing a task at an index greater than size not handled");
        }
    }

    @Test
    void editTask_negativeIndex() {
        try {
            taskList.editTask(-2,ui);
        } catch (Exception e) {
            fail("Editing a task at negative index not handled");
        }
    }

    @Test
    void editTaskAtIndex_negativeIndex_throwException() {
        try {
            taskList.editTaskAtIndex(-1,ui);
            fail("Editing a task at negative index not handled");
        } catch (Exception e) {
            assertEquals("Index -1 out of bounds for length 1", e.getMessage());
        }
    }

    @Test
    void editTaskAtIndex_indexMoreThanSize_throwException() {
        try {
            taskList.editTaskAtIndex(2,ui);
            fail("Editing a task at index greater than size-1 not handled");
        } catch (Exception e) {
            assertEquals("Index 2 out of bounds for length 1", e.getMessage());
        }
    }

    @Test
    void searchEvents_emptySearchKeyword_throwException() {
        try {
            taskList.searchTasks("    ",ui);
            fail("Empty string search not identified");
        } catch (Exception exception) {
            assertEquals("Keyword to be used for search is empty", exception.getMessage());
        }
    }

    @Test
    void checkForMatchedTasks_emptyKeyword_throwException() {
        try {
            taskList.checkForMatchedTasks("   ");
            fail("Empty string search not identified");
        } catch (Exception e) {
            assertEquals("Keyword to be used for search is empty", e.getMessage());
        }
    }

    @Test
    void marksAsDone_MisuseOfSetDoneWithEvent_throwException() {
        try {
            taskList.marksAsDone(1, ui);
            fail("Misuse of set done with event task not identified");
        } catch (Exception e) {
            assertEquals("Task of event type cannot be marked as done", e.getMessage());
        }
    }

    @Test
    void marksAsDone_zeroIndex_throwException() {
        try {
            taskList.marksAsDone(0, ui);
            fail("Trying to mark task at index zero not handled");
        } catch (Exception e) {
            assertEquals("Invalid index entered. Please enter a valid index to be marked as done",
                    e.getMessage());
        }
    }

    @Test
    void marksAsDone_negativeIndex_throwException() {
        try {
            taskList.marksAsDone(-2, ui);
            fail("Trying to mark task at a negative index not handled");
        } catch (Exception e) {
            assertEquals("Invalid index entered. Please enter a valid index to be marked as done",
                    e.getMessage());
        }
    }

    @Test
    void marksAsDone_indexGreaterThanSize_throwException() {
        try {
            taskList.marksAsDone(3, ui);
            fail("Trying to mark task at an index larger than size of list not handled");
        } catch (Exception e) {
            assertEquals("Invalid index entered. Please enter a valid index to be marked as done",
                    e.getMessage());
        }
    }

    @Test
    void checkIndexBounds_validIndex() {
        if (taskList.getTaskListSize() > 0) {
            try {
                taskList.checkIndexBounds(0);
            } catch (Exception e) {
                fail("Valid index identified as invalid");
            }
        }
    }

    @Test
    void checkIndexBounds_negativeIndex_throwException() {
        try {
            taskList.checkIndexBounds(-1);
            fail("Negative index not identified to be invalid");
        } catch (Exception e) {
            assertEquals("Invalid index entered. Please enter a "
                    + "valid index to be marked as done",e.getMessage());
        }
    }

    @Test
    void checkIndexBounds_indexLargerThanSizeMinus1_throwException() {
        if (taskList.getTaskListSize() > 0) {
            try {
                taskList.checkIndexBounds(taskList.getTaskListSize());
                fail("Index larger than size - 1 not identified to be invalid");
            } catch (Exception e) {
                assertEquals("Invalid index entered. Please enter a "
                        + "valid index to be marked as done", e.getMessage());
            }
        }
    }
}
