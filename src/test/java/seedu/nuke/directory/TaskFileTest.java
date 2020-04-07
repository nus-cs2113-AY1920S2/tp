package seedu.nuke.directory;

import org.junit.jupiter.api.Test;
import seedu.nuke.util.DateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskFileTest {
    private Module module = new Module("CS1231", "Discrete Structures", "");
    private Category category = new Category(module, "Lecture", 1);
    private Task task = new Task(category, "read chapter 6 notes", new DateTime(), 1);
    private TaskFile file = new TaskFile(task, "notes_6", "filePath", "originalFilePath");


    @Test
    void getAndSetFileName() {
        file.setFileName("notes_8");
        assertEquals("notes_8", file.getFileName());
    }

    @Test
    void getAndSetFilePath() {
        file.setFilePath("newFilePath");
        assertEquals("newFilePath", file.getFilePath());
    }

    @Test
    void getOriginalFilePath() {
        assertEquals("originalFilePath", file.getOriginalFilePath());
    }

    @Test
    void getParent() {
        assertEquals(task, file.getParent());
    }

    @Test
    void getLevel() {
        assertEquals(DirectoryLevel.FILE, file.getLevel());
    }

    @Test
    void isSameFile() {
        assertTrue(file.isSameFile("notes_6"));
        assertFalse(file.isSameFile("Notes_6"));
        assertFalse(file.isSameFile("x"));
    }

    @Test
    void testToString() {
        String expected =
                "File Name: notes_6\nModule Code: CS1231\nCategory Name: Lecture\n"
                + "Task Description: read chapter 6 notes\nOriginal File Path: originalFilePath\n";
        assertEquals(expected, file.toString());
    }
}