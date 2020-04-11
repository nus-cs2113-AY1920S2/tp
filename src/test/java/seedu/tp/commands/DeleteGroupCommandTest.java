package seedu.tp.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.exceptions.UnrecognizedFlashcardGroupException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.group.FlashcardGroup;
import seedu.tp.group.GroupList;
import seedu.tp.utils.InputTestUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.tp.utils.ExampleInputConstants.DESCRIPTION;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.GROUP_NAME_1;
import static seedu.tp.utils.ExampleInputConstants.GROUP_NAME_2;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_1;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_2;
import static seedu.tp.utils.ExampleInputConstants.INDEX_1;
import static seedu.tp.utils.ExampleInputConstants.INDEX_2;
import static seedu.tp.utils.ExampleInputConstants.INDEX_3;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;

public class DeleteGroupCommandTest {

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
    private static final FlashcardList FLASHCARD_LIST = new FlashcardList(Arrays.asList(EVENT_FLASHCARD,
        PERSON_FLASHCARD, OTHER_FLASHCARD));
    private static final FlashcardGroup GROUP_1 = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, FLASHCARD_LIST,
        InputTestUtil.convertStringIndexesToIntArray(INDEXES_1));
    private static final FlashcardGroup GROUP_2 = new FlashcardGroup(GROUP_NAME_2, DESCRIPTION, FLASHCARD_LIST,
        InputTestUtil.convertStringIndexesToIntArray(INDEXES_2));

    private GroupList emptyGroupList;
    private GroupList groupList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        emptyGroupList = new GroupList();

        List<FlashcardGroup> groups = Arrays.asList(GROUP_1, GROUP_2);
        groupList = new GroupList(groups);
    }

    @Test
    public void execute_deleteGroupFromEmptyList_throwsUnrecognizedFlashcardGroupException() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(emptyGroupList, INDEX_2);

        assertThrows(
            UnrecognizedFlashcardGroupException.class,
            deleteGroupCommand::execute,
            "Expected UnrecognizedFlashcardGroupException"
        );
    }

    @Test
    public void execute_deleteGroupByName_success() throws HistoryFlashcardException {
        GroupList expectedGroupList = new GroupList(Arrays.asList(GROUP_2));

        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(groupList, GROUP_NAME_1);
        deleteGroupCommand.execute();

        assertEquals(expectedGroupList, groupList);
    }

    @Test
    public void execute_deleteGroupByIndex_success() throws HistoryFlashcardException {
        GroupList expectedGroupList = new GroupList(Arrays.asList(GROUP_1));

        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(groupList, INDEX_1);
        deleteGroupCommand.execute();

        assertEquals(expectedGroupList, groupList);
    }

    @Test
    public void execute_deleteGroupByIndex_throwsUnrecognizedFlashcardGroupException() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(emptyGroupList, INDEX_3);

        assertThrows(
            UnrecognizedFlashcardGroupException.class,
            deleteGroupCommand::execute,
            "Expected UnrecognizedFlashcardGroupException"
        );
    }
}
