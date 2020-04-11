package seedu.nuke.directory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleTest {
    private Module module = new Module("CS2113T", "Software Engineering & Object-Oriented Programming", "");

    @Test
    void getAndSetModuleCode() {
        assertEquals("CS2113T", module.getModuleCode());
        module.setModuleCode("CS2113");
        assertEquals("CS2113", module.getModuleCode());
    }

    @Test
    void getAndSetTitle() {
        assertEquals("Software Engineering & Object-Oriented Programming", module.getTitle());
        module.setTitle("SE & OOP");
        assertEquals("SE & OOP", module.getTitle());
    }

    @Test
    void getAndSetDescription() {
        assertEquals("", module.getDescription());
        module.setDescription("Most Exciting Module of 2020!");
        assertEquals("Most Exciting Module of 2020!", module.getDescription());
    }

    @Test
    void getCategories() {
        // Default 4 categories inside: Lecture, Tutorial, Lab and Assignment
        assertEquals(4, module.getCategories().getCategoryList().size());
    }

    @Test
    void getParent() {
        assertEquals(DirectoryLevel.ROOT, module.getParent().getLevel());
    }

    @Test
    void getLevel() {
        assertEquals(DirectoryLevel.MODULE, module.getLevel());
    }

    @Test
    void isSameModule() {
        assertTrue(module.isSameModule("CS2113T"));
        assertTrue(module.isSameModule("cs2113t"));
        assertFalse(module.isSameModule("CS2113"));
        assertFalse(module.isSameModule("CS"));
    }

    @Test
    void testToString() {
        String expected = "Module Code: CS2113T\nModule Title: Software Engineering & Object-Oriented Programming\n"
                + "Number of Categories: 4\n";
        assertEquals(expected, module.toString());
    }
}