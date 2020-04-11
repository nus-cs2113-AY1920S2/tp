package seedu.techtoday.objects;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteTest {

    @Test
    void constructorTest() {
        Note note = new Note("testTitle", "testExtract", "testEpoch");
        assertEquals(note.title, "testTitle");
        assertEquals(note.extract, "testExtract");
        assertEquals(note.timeStamp, "testEpoch");
    }

    @Test
    void setCategory() {
        Note note = new Note("testNote");
        note.setCategory("newCategory");
        assertEquals(note.category, "newCategory");
    }

    @Test
    void setUrl() {
        Note note = new Note("testNote");
        note.setUrl("newUrl");
        assertEquals(note.url, "newUrl");
    }

    @Test
    void getUrl() {
        Note note = new Note("testNote");
        note.url = "testUrl";
        assertEquals(note.getUrl(), "testUrl");
    }

    @Test
    void getCategory() {
        Note note = new Note("testNote");
        note.category = "testCategory";
        assertEquals(note.getCategory(), "testCategory");
    }
}