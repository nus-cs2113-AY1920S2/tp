package seedu.nuke.directory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskFileManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DirectoryTraversalOutOfBoundsException;
import seedu.nuke.exception.DuplicateDataException;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.exception.ModuleNotProvidedException;
import seedu.nuke.util.DateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DirectoryTraverserTest {
    private Root root = new Root();
    private Module module = new Module("CS2113T", "Software Engineering & Object-Oriented Programming", "");
    private Category category = new Category(module, "Lab Work", 4);
    private Task task = new Task(category, "do sequence diagram", new DateTime(), 3);
    private TaskFile file = new TaskFile(task, "seq_diag", "filePath", "originalFilePath");

    private final String noKeyword = "";

    @BeforeEach
    void initialise() throws DuplicateDataException, ModuleNotProvidedException {
        ModuleManager.initialise();
        ModuleManager.add(module);
        module.getCategories().add(category);
        category.getTasks().add(task);
        task.getFiles().add(file);

        DirectoryTraverser.traverseTo(root);
    }

    @Test
    void getCurrentDirectory() throws DirectoryTraversalOutOfBoundsException {
        assertEquals(DirectoryTraverser.getCurrentDirectoryLevel(), DirectoryLevel.ROOT);

        DirectoryTraverser.traverseDown(module);
        assertEquals(DirectoryLevel.MODULE, DirectoryTraverser.getCurrentDirectoryLevel());
        assertEquals(module, DirectoryTraverser.getCurrentDirectory());

        DirectoryTraverser.traverseDown(category);
        assertEquals(DirectoryLevel.CATEGORY, DirectoryTraverser.getCurrentDirectoryLevel());
        assertEquals(category, DirectoryTraverser.getCurrentDirectory());

        DirectoryTraverser.traverseTo(file);
        assertEquals(DirectoryLevel.FILE, DirectoryTraverser.getCurrentDirectoryLevel());
        assertEquals(file, DirectoryTraverser.getCurrentDirectory());

        DirectoryTraverser.traverseUp();
        assertEquals(DirectoryLevel.TASK, DirectoryTraverser.getCurrentDirectoryLevel());
        assertEquals(task, DirectoryTraverser.getCurrentDirectory());

        DirectoryTraverser.traverseUp();
        assertEquals(DirectoryLevel.CATEGORY, DirectoryTraverser.getCurrentDirectoryLevel());
        assertEquals(category, DirectoryTraverser.getCurrentDirectory());

        DirectoryTraverser.traverseTo(root);
        assertEquals(DirectoryLevel.ROOT, DirectoryTraverser.getCurrentDirectoryLevel());
    }

    @Test
    void traverse_invalid() {
        try {
            DirectoryTraverser.traverseUp();
            fail("Should throw an exception here!");
        } catch (DirectoryTraversalOutOfBoundsException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(file);

        try {
            DirectoryTraverser.traverseDown(file);
            fail("Should throw an exception here!");
        } catch (DirectoryTraversalOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    void findNextDirectory() throws DirectoryTraversalOutOfBoundsException, DataNotFoundException {
        assertEquals(module, DirectoryTraverser.findNextDirectory("CS2113T"));

        DirectoryTraverser.traverseTo(task);
        assertEquals(file, DirectoryTraverser.findNextDirectory("seq_diag"));

        DirectoryTraverser.traverseTo(category);
        assertEquals(task, DirectoryTraverser.findNextDirectory("do sequence diagram"));

        DirectoryTraverser.traverseTo(file);
        try {
            DirectoryTraverser.findNextDirectory("directoryName");
            fail("Should throw an exception here since this is the furthest directory.");
        } catch (DirectoryTraversalOutOfBoundsException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(module);
        assertEquals(category, DirectoryTraverser.findNextDirectory("Lab Work"));
        try {
            DirectoryTraverser.findNextDirectory("Lab Wok");
            fail("Should throw an exception here since incorrect name.");
        } catch (DataNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    void getFullPath() {
        String expectedRootPath = "root";
        assertEquals(expectedRootPath, DirectoryTraverser.getFullPath());

        DirectoryTraverser.traverseTo(module);
        String expectedModulePath = "root / CS2113T";
        assertEquals(expectedModulePath, DirectoryTraverser.getFullPath());

        DirectoryTraverser.traverseTo(category);
        String expectedCategoryPath = "root / CS2113T / Lab Work";
        assertEquals(expectedCategoryPath, DirectoryTraverser.getFullPath());

        DirectoryTraverser.traverseTo(task);
        String expectedTaskPath = "root / CS2113T / Lab Work / do sequence diagram";
        assertEquals(expectedTaskPath, DirectoryTraverser.getFullPath());

        DirectoryTraverser.traverseTo(file);
        String expectedFilePath = "root / CS2113T / Lab Work / do sequence diagram / seq_diag";
        assertEquals(expectedFilePath, DirectoryTraverser.getFullPath());
    }

    @Test
    void getBaseModule() throws IncorrectDirectoryLevelException {
        try {
            assertEquals(module, DirectoryTraverser.getBaseModule());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(module);
        assertEquals(module, DirectoryTraverser.getBaseModule());

        DirectoryTraverser.traverseTo(category);
        assertEquals(module, DirectoryTraverser.getBaseModule());

        DirectoryTraverser.traverseTo(task);
        assertEquals(module, DirectoryTraverser.getBaseModule());

        DirectoryTraverser.traverseTo(file);
        assertEquals(module, DirectoryTraverser.getBaseModule());
    }

    @Test
    void getBaseCategory() throws IncorrectDirectoryLevelException {
        try {
            assertEquals(category, DirectoryTraverser.getBaseCategory());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(module);
        try {
            assertEquals(category, DirectoryTraverser.getBaseCategory());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(category);
        assertEquals(category, DirectoryTraverser.getBaseCategory());

        DirectoryTraverser.traverseTo(task);
        assertEquals(category, DirectoryTraverser.getBaseCategory());

        DirectoryTraverser.traverseTo(file);
        assertEquals(category, DirectoryTraverser.getBaseCategory());
    }

    @Test
    void getBaseTask() throws IncorrectDirectoryLevelException {
        try {
            assertEquals(task, DirectoryTraverser.getBaseTask());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(module);
        try {
            assertEquals(task, DirectoryTraverser.getBaseTask());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(category);
        try {
            assertEquals(task, DirectoryTraverser.getBaseTask());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(task);
        assertEquals(task, DirectoryTraverser.getBaseTask());

        DirectoryTraverser.traverseTo(file);
        assertEquals(task, DirectoryTraverser.getBaseTask());
    }

    @Test
    void getBaseFile() throws IncorrectDirectoryLevelException {
        try {
            assertEquals(file, DirectoryTraverser.getBaseFile());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(module);
        try {
            assertEquals(file, DirectoryTraverser.getBaseFile());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(category);
        try {
            assertEquals(file, DirectoryTraverser.getBaseFile());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(task);
        try {
            assertEquals(file, DirectoryTraverser.getBaseFile());
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }

        DirectoryTraverser.traverseTo(file);
        assertEquals(file, DirectoryTraverser.getBaseFile());
    }

    @Test
    void getModuleDirectory() throws ModuleManager.ModuleNotFoundException, IncorrectDirectoryLevelException {
        assertEquals(module, DirectoryTraverser.getModuleDirectory("CS2113T"));
        try {
            DirectoryTraverser.getModuleDirectory("CS1101");
            fail("Exception should be thrown since no such module exist.");
        } catch (ModuleManager.ModuleNotFoundException e) {
            assertTrue(true);
        }
        try {
            DirectoryTraverser.getModuleDirectory(noKeyword);
            fail("Exception should be thrown since directory is too low.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }
        DirectoryTraverser.traverseTo(module);
        assertEquals(module, DirectoryTraverser.getModuleDirectory(noKeyword));
    }

    @Test
    void getCategoryDirectory() throws ModuleManager.ModuleNotFoundException,
            CategoryManager.CategoryNotFoundException, IncorrectDirectoryLevelException {
        assertEquals(category, DirectoryTraverser.getCategoryDirectory("CS2113T", "Lab Work"));
        DirectoryTraverser.traverseTo(category);
        assertEquals(category, DirectoryTraverser.getCategoryDirectory(noKeyword, noKeyword));
        try {
            DirectoryTraverser.getCategoryDirectory("cs1010", noKeyword);
            fail("Exception should be thrown since incorrect module.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }
    }

    @Test
    void getTaskDirectory() throws ModuleManager.ModuleNotFoundException, CategoryManager.CategoryNotFoundException,
            TaskManager.TaskNotFoundException, IncorrectDirectoryLevelException {
        assertEquals(task, DirectoryTraverser.getTaskDirectory("CS2113T", "Lab Work",
                "do sequence diagram"));
        DirectoryTraverser.traverseTo(task);
        assertEquals(task, DirectoryTraverser.getTaskDirectory(noKeyword, noKeyword, noKeyword));
        try {
            DirectoryTraverser.getTaskDirectory("cs1010", noKeyword, noKeyword);
            fail("Exception should be thrown since incorrect module.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }
        try {
            DirectoryTraverser.getTaskDirectory("CS2113T", "No Work", noKeyword);
            fail("Exception should be thrown since incorrect category.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }
    }

    @Test
    void getFileDirectory() throws ModuleManager.ModuleNotFoundException, CategoryManager.CategoryNotFoundException,
            TaskManager.TaskNotFoundException, TaskFileManager.TaskFileNotFoundException,
            IncorrectDirectoryLevelException {
        assertEquals(file, DirectoryTraverser.getFileDirectory("CS2113T", "Lab Work",
                "do sequence diagram", "seq_diag"));
        DirectoryTraverser.traverseTo(file);
        assertEquals(file, DirectoryTraverser.getFileDirectory(noKeyword, noKeyword, noKeyword, noKeyword));
        try {
            DirectoryTraverser.getFileDirectory("cs1010", noKeyword, noKeyword, noKeyword);
            fail("Exception should be thrown since incorrect module.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }
        try {
            DirectoryTraverser.getFileDirectory("CS2113T", "No Work", noKeyword, noKeyword);
            fail("Exception should be thrown since incorrect category.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }
        try {
            DirectoryTraverser.getFileDirectory("CS2113T", "Lab Work", "do class diagram", noKeyword);
            fail("Exception should be thrown since incorrect task.");
        } catch (IncorrectDirectoryLevelException e) {
            assertTrue(true);
        }
    }
}