package seedu.tp.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.group.FlashcardGroup;
import seedu.tp.group.GroupFactory;
import seedu.tp.group.GroupList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tp.utils.ExampleInputConstants.DESCRIPTION;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.GROUP_NAME_1;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_1;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_GROUP_COMMAND_INPUT_1;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;
import static seedu.tp.utils.InputTestUtil.convertStringIndexesToIntArray;
import static seedu.tp.utils.InputTestUtil.getGroupFactoryWithInput;

public class GroupCommandTest {

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

    private FlashcardList fullFlashcardList;

    @BeforeEach
    public void setup() {
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
    }

    @Test
    public void groupCommand_execute_Successfully() throws InvalidFlashcardIndexException {
        FlashcardGroup expectedGroup = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_1));
        GroupList expectedGroupList = new GroupList();
        expectedGroupList.addFlashcardGroup(expectedGroup);

        GroupList actualGroupList = new GroupList();
        GroupFactory groupFactory = getGroupFactoryWithInput(SIMULATED_GROUP_COMMAND_INPUT_1, fullFlashcardList);
        GroupCommand groupCommand = new GroupCommand(groupFactory, actualGroupList);
        groupCommand.execute();

        assertEquals(expectedGroupList, actualGroupList);
    }
}
