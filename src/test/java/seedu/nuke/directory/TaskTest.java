package seedu.nuke.directory;

import org.junit.jupiter.api.Test;
import seedu.nuke.util.DateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    private Module module = new Module("CS1231", "Discrete Structures", "");
    private Category category = new Category(module, "Lecture", 1);
    private Task task = new Task(category, "read chapter 6 notes", new DateTime(), 1);

    @Test
    void getAndSetTag() {
        assertEquals(0, task.getTags().size());
        assertEquals("[]", task.getTag());
        task.setTag(new ArrayList<>(Arrays.asList("urgent", "free time", "todo")));
        assertEquals(3, task.getTags().size());
        assertEquals("[urgent, free time, todo]", task.getTag());
        task.removeTag("urgent");
        assertEquals(2, task.getTags().size());
        assertEquals("[free time, todo]", task.getTag());
        task.removeAllTags();
        assertEquals(0, task.getTags().size());
        assertEquals("[]", task.getTag());
    }

    @Test
    void getFiles() {
        assertEquals(0, task.getFiles().getFileList().size());
        assertEquals(new ArrayList<TaskFile>(), task.getFiles().getFileList());
    }

    @Test
    void getAndSetDescription() {
        assertEquals("read chapter 6 notes", task.getDescription());
        task.setDescription("read chapter 7 notes");
        assertEquals("read chapter 7 notes", task.getDescription());
    }

    @Test
    void getAndSetDeadline() {
        assertFalse(task.getDeadline().isPresent());
        task.setDeadline(new DateTime(LocalDate.of(2020, 5, 5), LocalTime.of(12, 30)));
        assertEquals("05/05/2020 12:30PM", task.getDeadline().toString());
    }

    @Test
    void getAndSetPriority() {
        assertEquals(1, task.getPriority());
        task.setPriority(3);
        assertEquals(3, task.getPriority());
    }

    @Test
    void getAndSetDone() {
        assertFalse(task.isDone());
        assertEquals("[N]", task.getStatusIcon());
        task.setDone(true);
        assertTrue(task.isDone());
        assertEquals("[Y]", task.getStatusIcon());
    }

    @Test
    void getParent() {
        assertEquals(category, task.getParent());
    }

    @Test
    void getLevel() {
        assertEquals(DirectoryLevel.TASK, task.getLevel());
    }

    @Test
    void isSameTask() {
        assertTrue(task.isSameTask("read chapter 6 notes"));
        assertFalse(task.isSameTask("read chapter 20 notes"));
        assertFalse(task.isSameTask("x"));
    }

    @Test
    void testToString() {
        String expected =
                "Task Description: read chapter 6 notes\nModule Code: CS1231\nCategory Name: Lecture\n"
                + "Deadline: -\nPriority: 1\nDone Status: Incomplete\nTags: -\nNumber of Files: 0\n";
        assertEquals(expected, task.toString());

        task.setTag(new ArrayList<>(Arrays.asList("urgent", "free time")));
        String expectedSecond =
                "Task Description: read chapter 6 notes\nModule Code: CS1231\nCategory Name: Lecture\n"
                + "Deadline: -\nPriority: 1\nDone Status: Incomplete\nTags: urgent, free time\nNumber of Files: 0\n";
        assertEquals(expectedSecond, task.toString());
    }
}