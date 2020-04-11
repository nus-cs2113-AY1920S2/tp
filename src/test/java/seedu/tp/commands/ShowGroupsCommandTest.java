package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.group.FlashcardGroup;
import seedu.tp.group.GroupList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tp.utils.ExampleInputConstants.DESCRIPTION;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_2;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_3;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;
import static seedu.tp.utils.InputTestUtil.convertStringIndexesToIntArray;

public class ShowGroupsCommandTest {
    private static final EventFlashcard EVENT_FLASHCARD = new EventFlashcard(
        "Event 1",
        START_LOCAL_DATE,
        END_LOCAL_DATE,
        "This is an event summary",
        DETAILS
    );
    private static final PersonFlashcard PERSON_FLASHCARD = new PersonFlashcard(
        "Person 1",
        START_LOCAL_DATE,
        END_LOCAL_DATE,
        "This is a person's summary",
        DETAILS
    );
    private static final OtherFlashcard OTHER_FLASHCARD = new OtherFlashcard(
        "Title 1",
        "This is a summary",
        DETAILS
    );
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream backupStdout = System.out;
    private FlashcardList fullFlashcardList;

    @BeforeEach
    public void setup() {
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
    }

    @BeforeEach
    public void captureStdout() {
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    public void restoreStdout() {
        System.setOut(backupStdout);
    }

    @Test
    public void showGroupsCommand_execute_listGroupsSuccessfully() {
        FlashcardGroup group1 = new FlashcardGroup("Group 1", DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_3));
        FlashcardGroup group2 = new FlashcardGroup("Group 2", DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_2));
        GroupList groupList = new GroupList();
        groupList.addFlashcardGroup(group1);
        groupList.addFlashcardGroup(group2);
        ShowGroupsCommand showGroupsCommand = new ShowGroupsCommand(groupList);
        final CommandFeedback showGroupsCommandFeedback = showGroupsCommand.execute();

        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Here are all existing groups:"
            + System.lineSeparator());
        expectedOutput.append("1. Group 1 | Description: Example description" + System.lineSeparator());
        expectedOutput.append("2. Group 2 | Description: Example description");

        assertEquals(expectedOutput.toString(), showGroupsCommandFeedback.toString());
    }
}
