package seedu.nuke.directory;

import org.junit.jupiter.api.Test;
import seedu.nuke.data.ModuleManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RootTest {
    private Root root = new Root();

    @Test
    void getParent() {
        assertNull(root.getParent());
    }

    @Test
    void getLevel() {
        assertEquals(DirectoryLevel.ROOT, root.getLevel());
    }

    @Test
    void testToString() {
        ModuleManager.initialise();
        String expected = "Root\nNumber of Modules: 0\n";
        assertEquals(expected, root.toString());
    }
}