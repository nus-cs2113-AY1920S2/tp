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
import static seedu.tp.utils.ExampleInputConstants.GROUP_NAME_1;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_3;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;
import static seedu.tp.utils.InputTestUtil.convertStringIndexesToIntArray;

public class ListFlashcardsInGroupCommandTest {
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
    public void showGroupsCommand_execute_listFlashcardsInGroupSuccessfullyUsingName() {
        FlashcardGroup group = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_3));
        GroupList groupList = new GroupList();
        groupList.addFlashcardGroup(group);
        ListFlashcardsInGroupCommand listFlashcardsInGroupCommand =
            new ListFlashcardsInGroupCommand(groupList, GROUP_NAME_1);
        final CommandFeedback listFlashcardsInGroupCommandFeedback = listFlashcardsInGroupCommand.execute();

        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Example flashcard group name 1 contains the following flashcards:"
            + System.lineSeparator());
        expectedOutput.append("- " + EVENT_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedOutput.append("- " + PERSON_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedOutput.append("- " + OTHER_FLASHCARD.getShortDescription());

        assertEquals(expectedOutput.toString(), listFlashcardsInGroupCommandFeedback.toString());
    }

    @Test
    public void showGroupsCommand_execute_listFlashcardsInGroupSuccessfullyUsingIndex() {
        FlashcardGroup group = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_3));
        GroupList groupList = new GroupList();
        groupList.addFlashcardGroup(group);
        ListFlashcardsInGroupCommand listFlashcardsInGroupCommand =
            new ListFlashcardsInGroupCommand(groupList, "1");
        final CommandFeedback listFlashcardsInGroupCommandFeedback = listFlashcardsInGroupCommand.execute();

        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("1 contains the following flashcards:"
            + System.lineSeparator());
        expectedOutput.append("- " + EVENT_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedOutput.append("- " + PERSON_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedOutput.append("- " + OTHER_FLASHCARD.getShortDescription());

        assertEquals(expectedOutput.toString(), listFlashcardsInGroupCommandFeedback.toString());
    }

    @Test
    public void execute_listGroupWithOutOfBoundIndex_catchesUnrecognizedFlashcardGroupException() {
        FlashcardGroup group = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_3));
        GroupList groupList = new GroupList();
        groupList.addFlashcardGroup(group);
        ListFlashcardsInGroupCommand listFlashcardsInGroupCommand =
            new ListFlashcardsInGroupCommand(groupList, "0");
        CommandFeedback listFlashcardsInGroupCommandFeedback = listFlashcardsInGroupCommand.execute();

        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter a valid flashcard group name or index."
            + " Use \"show-groups\" to view all groups.");
        assertEquals(expectedOutput.toString(), listFlashcardsInGroupCommandFeedback.toString());
    }

    @Test
    public void execute_listGroupWithFalseName_catchesUnrecognizedFlashcardGroupException() {
        FlashcardGroup group = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_3));
        GroupList groupList = new GroupList();
        groupList.addFlashcardGroup(group);
        ListFlashcardsInGroupCommand listFlashcardsInGroupCommand =
            new ListFlashcardsInGroupCommand(groupList, "badname");
        CommandFeedback listFlashcardsInGroupCommandFeedback = listFlashcardsInGroupCommand.execute();

        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter a valid flashcard group name or index."
            + " Use \"show-groups\" to view all groups.");
        assertEquals(expectedOutput.toString(), listFlashcardsInGroupCommandFeedback.toString());
    }
}
