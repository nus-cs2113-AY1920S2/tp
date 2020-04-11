package seedu.tp.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.exceptions.UnrecognizedFlashcardGroupException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.tp.utils.ExampleInputConstants.DESCRIPTION;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.GROUP_NAME_1;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_1;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_2;
import static seedu.tp.utils.ExampleInputConstants.INDEXES_3;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_1;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_2;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_3;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_4;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_5;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_6;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_GROUP_COMMAND_INPUT_1;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;
import static seedu.tp.utils.InputTestUtil.convertStringIndexesToIntArray;
import static seedu.tp.utils.InputTestUtil.getAddFlashcardToGroupCommandWithInput;
import static seedu.tp.utils.InputTestUtil.getGroupFactoryWithInput;

public class AddFlashcardToGroupCommandTest {

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
    public void addFlashcardToGroupCommand_execute_Successfully() throws HistoryFlashcardException {
        FlashcardGroup expectedGroup = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_3));
        GroupList expectedGroupList = new GroupList();
        expectedGroupList.addFlashcardGroup(expectedGroup);

        //Test case1: Adding flashcards in the order as expected
        FlashcardGroup actualGroup1 = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_1));
        GroupList actualGroupList1 = new GroupList();
        actualGroupList1.addFlashcardGroup(actualGroup1);

        AddFlashcardToGroupCommand addFlashcardToGroupCommand1 = getAddFlashcardToGroupCommandWithInput(
            SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_3, fullFlashcardList, actualGroupList1);
        addFlashcardToGroupCommand1.execute();

        assertEquals(expectedGroupList, actualGroupList1);

        //Test case2: Adding flashcards in an different order
        FlashcardGroup actualGroup2 = new FlashcardGroup(GROUP_NAME_1, DESCRIPTION, fullFlashcardList,
            convertStringIndexesToIntArray(INDEXES_2));
        GroupList actualGroupList2 = new GroupList();
        actualGroupList2.addFlashcardGroup(actualGroup2);

        AddFlashcardToGroupCommand addFlashcardToGroupCommand2 = getAddFlashcardToGroupCommandWithInput(
            SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_1, fullFlashcardList, actualGroupList2);
        addFlashcardToGroupCommand2.execute();

        assertEquals(expectedGroupList, actualGroupList2);
    }

    @Test
    public void addFlashcardToGroupCommand_invalidFlashcardIndex_throwsException()
        throws InvalidFlashcardIndexException {
        GroupList originalGroupList = new GroupList();
        GroupFactory groupFactory = getGroupFactoryWithInput(SIMULATED_GROUP_COMMAND_INPUT_1, fullFlashcardList);
        GroupCommand groupCommand = new GroupCommand(groupFactory, originalGroupList);
        groupCommand.execute();

        AddFlashcardToGroupCommand addFlashcardToGroupCommand = getAddFlashcardToGroupCommandWithInput(
            SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_2, fullFlashcardList, originalGroupList);
        assertThrows(
            InvalidFlashcardIndexException.class, addFlashcardToGroupCommand::execute
        );
    }

    @Test
    public void addFlashcardToGroupCommand_unrecognizedFlashcardGroupType_throwsException()
        throws InvalidFlashcardIndexException {
        GroupList originalGroupList = new GroupList();
        GroupFactory groupFactory = getGroupFactoryWithInput(SIMULATED_GROUP_COMMAND_INPUT_1, fullFlashcardList);
        GroupCommand groupCommand = new GroupCommand(groupFactory, originalGroupList);
        groupCommand.execute();

        //Test case1: Given an invalid group name
        AddFlashcardToGroupCommand addFlashcardToGroupCommand1 = getAddFlashcardToGroupCommandWithInput(
            SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_4, fullFlashcardList, originalGroupList);
        assertThrows(
            UnrecognizedFlashcardGroupException.class, addFlashcardToGroupCommand1::execute
        );

        //Test case2: Given an negative group index
        AddFlashcardToGroupCommand addFlashcardToGroupCommand2 = getAddFlashcardToGroupCommandWithInput(
            SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_5, fullFlashcardList, originalGroupList);
        assertThrows(
            UnrecognizedFlashcardGroupException.class, addFlashcardToGroupCommand2::execute
        );

        //Test case3: Given an group index out of bound
        AddFlashcardToGroupCommand addFlashcardToGroupCommand3 = getAddFlashcardToGroupCommandWithInput(
            SIMULATED_ADD_FLASHCARD_TO_GROUP_INPUT_6, fullFlashcardList, originalGroupList);
        assertThrows(
            UnrecognizedFlashcardGroupException.class, addFlashcardToGroupCommand3::execute
        );
    }
}
