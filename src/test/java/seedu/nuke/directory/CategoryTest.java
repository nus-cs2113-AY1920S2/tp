package seedu.nuke.directory;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryTest {
    private Module module = new Module("CS2113T", "Software Engineering & Object-Oriented Programming", "");
    private Category category = new Category(module, "Lab", 4);

    @Test
    void getAndSetCategoryName() {
        assertEquals("Lab", category.getCategoryName());
        category.setCategoryName("Tutorial");
        assertEquals("Tutorial", category.getCategoryName());
    }

    @Test
    void getAndSetCategoryPriority() {
        assertEquals(4, category.getCategoryPriority());
        category.setCategoryPriority(7);
        assertEquals(7, category.getCategoryPriority());
    }

    @Test
    void getTasks() {
        assertEquals(0, category.getTasks().getTaskList().size());
        assertEquals(new ArrayList<Task>(), category.getTasks().getTaskList());
    }

    @Test
    void getParent() {
        assertEquals(module, category.getParent());
    }

    @Test
    void getLevel() {
        assertEquals(DirectoryLevel.CATEGORY, category.getLevel());
    }


    @Test
    void isSameCategory() {
        assertTrue(category.isSameCategory("Lab"));
        assertFalse(category.isSameCategory("lab"));
        assertFalse(category.isSameCategory("La"));
    }

    @Test
    void testToString() {
        String expected = "Category Name: Lab\nModule Code: CS2113T\nPriority: 4\nNumber of Tasks: 0\n";
        assertEquals(expected, category.toString());
    }
}