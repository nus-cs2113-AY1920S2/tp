package seedu.tp.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.commands.AddFlashcardToGroupCommand;
import seedu.tp.commands.ByeCommand;
import seedu.tp.commands.Command;
import seedu.tp.commands.DeleteCommand;
import seedu.tp.commands.DeleteStudyPlanCommand;
import seedu.tp.commands.DisplayStudyPlanCommand;
import seedu.tp.commands.EventFlashcardCommand;
import seedu.tp.commands.FindCommand;
import seedu.tp.commands.GroupCommand;
import seedu.tp.commands.ListCommand;
import seedu.tp.commands.ListReviewedCommand;
import seedu.tp.commands.OtherFlashcardCommand;
import seedu.tp.commands.PersonFlashcardCommand;
import seedu.tp.commands.PriorityCommand;
import seedu.tp.commands.ReviewedCommand;
import seedu.tp.commands.ShowCommand;
import seedu.tp.commands.UpdateStudyPlanCommand;
import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.exceptions.InvalidDateFormatException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.exceptions.InvalidInputFormatException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardFactory;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.group.GroupFactory;
import seedu.tp.group.GroupList;
import seedu.tp.studyplan.StudyPlanList;
import seedu.tp.ui.Ui;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Solution below referenced and adopted from:
// https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/parser/ParserTest.java
public class ParserTest {

    private Parser parser;
    private Ui ui;
    private FlashcardFactory flashcardFactory;
    private FlashcardList flashcardList;
    private GroupFactory groupFactory;
    private GroupList groupList;
    private StudyPlanList studyPlanList;

    /**
     * Initializes a new Parser for each test case.
     */
    @BeforeEach
    public void setUp() {
        ui = new Ui();
        flashcardFactory = new FlashcardFactory(ui);
        flashcardList = new FlashcardList();
        groupFactory = new GroupFactory(ui, flashcardList);
        groupList = new GroupList();
        studyPlanList = new StudyPlanList();
        parser = new Parser(flashcardFactory, flashcardList, groupFactory, groupList, studyPlanList, ui);
    }

    @Test
    public void parse_eventFlashcardCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("event");
        EventFlashcardCommand expectedEventFlashcardCommand = new EventFlashcardCommand(flashcardList,
            flashcardFactory);
        assertEquals(expectedEventFlashcardCommand, command);
    }

    @Test
    public void parse_eventFlashcardCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("EveNt");
        EventFlashcardCommand expectedEventFlashcardCommand = new EventFlashcardCommand(flashcardList,
            flashcardFactory);
        assertEquals(expectedEventFlashcardCommand, command);
    }

    @Test
    public void parse_personFlashcardCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("person");
        PersonFlashcardCommand expectedPersonFlashcardCommand =
            new PersonFlashcardCommand(flashcardList, flashcardFactory);
        assertEquals(expectedPersonFlashcardCommand, command);
    }

    @Test
    public void parse_personFlashcardCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("pERson");
        PersonFlashcardCommand expectedPersonFlashcardCommand =
            new PersonFlashcardCommand(flashcardList, flashcardFactory);
        assertEquals(expectedPersonFlashcardCommand, command);
    }

    @Test
    public void parse_otherFlashcardCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("other");
        OtherFlashcardCommand expectedOtherFlashcardCommand =
            new OtherFlashcardCommand(flashcardList, flashcardFactory);
        assertEquals(expectedOtherFlashcardCommand, command);
    }

    @Test
    public void parse_otherFlashcardCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("oThER");
        OtherFlashcardCommand expectedOtherFlashcardCommand =
            new OtherFlashcardCommand(flashcardList, flashcardFactory);
        assertEquals(expectedOtherFlashcardCommand, command);
    }

    @Test
    public void parse_listCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("list");
        ListCommand expectedListCommand = new ListCommand(flashcardList);
        assertEquals(expectedListCommand, command);
    }

    @Test
    public void parse_listCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("LISt");
        ListCommand expectedListCommand = new ListCommand(flashcardList);
        assertEquals(expectedListCommand, command);
    }

    @Test
    public void parse_showCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("show 1");
        ShowCommand expectedShowCommand = new ShowCommand(flashcardList, 0);
        assertEquals(expectedShowCommand, command);
    }

    @Test
    public void parse_showCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("SHow 1");
        ShowCommand expectedShowCommand = new ShowCommand(flashcardList, 0);
        assertEquals(expectedShowCommand, command);
    }

    @Test
    public void parse_priorityCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("priority 1 LOW");
        PriorityCommand expectedPriorityCommand = new PriorityCommand(flashcardList, 0,
            Flashcard.PriorityLevel.LOW);
        assertEquals(expectedPriorityCommand, command);
    }

    @Test
    public void parse_priorityCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("PriOriTY 1 LOW");
        PriorityCommand expectedPriorityCommand = new PriorityCommand(flashcardList, 0,
            Flashcard.PriorityLevel.LOW);
        assertEquals(expectedPriorityCommand, command);
    }

    @Test
    public void parse_reviewedCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("reviewed 1");
        ReviewedCommand expectedReviewedCommand = new ReviewedCommand(flashcardList, 0);
        assertEquals(expectedReviewedCommand, command);
    }

    @Test
    public void parse_reviewedCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("rEvIeWeD 1");
        ReviewedCommand expectedReviewedCommand = new ReviewedCommand(flashcardList, 0);
        assertEquals(expectedReviewedCommand, command);
    }

    @Test
    public void parse_deleteCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("delete 1");
        DeleteCommand expectedDeleteCommand = new DeleteCommand(flashcardList, 0);
        assertEquals(expectedDeleteCommand, command);
    }

    @Test
    public void parse_deleteCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("dELEte 1");
        DeleteCommand expectedDeleteCommand = new DeleteCommand(flashcardList, 0);
        assertEquals(expectedDeleteCommand, command);
    }

    @Test
    public void parse_deleteCommand_wrongNumberFormat() {
        // Solution below adopted from:
        // https://stackoverflow.com/questions/40268446/junit-5-how-to-assert-an-exception-is-thrown
        assertThrows(
            InvalidFlashcardIndexException.class,
            () -> parser.parseCommand("delete random"),
            "Expected InvalidFlashcardIndexException"
        );
    }

    @Test
    public void parse_byeCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("bye");
        ByeCommand expectedByeCommand = new ByeCommand();
        assertEquals(expectedByeCommand, command);
    }

    @Test
    public void parse_byeCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        Command command = parser.parseCommand("ByE");
        ByeCommand expectedByeCommand = new ByeCommand();
        assertEquals(expectedByeCommand, command);
    }

    @Test
    public void parse_groupCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        GroupCommand expectedGroupCommand = new GroupCommand(groupFactory, groupList);
        Command actualGroupCommand = parser.parseCommand("group");
        assertEquals(expectedGroupCommand, actualGroupCommand);
    }

    @Test
    public void parse_groupCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        GroupCommand expectedGroupCommand = new GroupCommand(groupFactory, groupList);
        Command actualGroupCommand = parser.parseCommand("gRouP");
        assertEquals(expectedGroupCommand, actualGroupCommand);
    }

    @Test
    public void parse_addFlashcardToGroupCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        AddFlashcardToGroupCommand expectedAddFlashcardToGroupCommand = new AddFlashcardToGroupCommand(ui,
            groupList, flashcardList);
        Command actualAddFlashcardToGroupCommand = parser.parseCommand("add");
        assertEquals(expectedAddFlashcardToGroupCommand, actualAddFlashcardToGroupCommand);
    }

    @Test
    public void parse_addFlashcardToGroupCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        AddFlashcardToGroupCommand expectedAddFlashcardToGroupCommand = new AddFlashcardToGroupCommand(ui,
            groupList, flashcardList);
        Command actualAddFlashcardToGroupCommand = parser.parseCommand("aDd");
        assertEquals(expectedAddFlashcardToGroupCommand, actualAddFlashcardToGroupCommand);
    }

    @Test
    public void parse_updateStudyPlanCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        UpdateStudyPlanCommand expectedUpdateStudyPlanCommand = new UpdateStudyPlanCommand(ui, studyPlanList,
            flashcardList);
        Command actualUpdateStudyPlanCommand = parser.parseCommand("plan");
        assertEquals(expectedUpdateStudyPlanCommand, actualUpdateStudyPlanCommand);
    }

    @Test
    public void parse_updateStudyPlanCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        UpdateStudyPlanCommand expectedUpdateStudyPlanCommand = new UpdateStudyPlanCommand(ui, studyPlanList,
            flashcardList);
        Command actualUpdateStudyPlanCommand = parser.parseCommand("pLAn");
        assertEquals(expectedUpdateStudyPlanCommand, actualUpdateStudyPlanCommand);
    }

    @Test
    public void parse_deleteStudyPlanCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        DeleteStudyPlanCommand expectedDeleteStudyPlanCommand = new DeleteStudyPlanCommand(ui, studyPlanList);
        Command actualDeleteStudyPlanCommand = parser.parseCommand("delete-plan");
        assertEquals(expectedDeleteStudyPlanCommand, actualDeleteStudyPlanCommand);
    }

    @Test
    public void parse_deleteStudyPlanCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        DeleteStudyPlanCommand expectedDeleteStudyPlanCommand = new DeleteStudyPlanCommand(ui, studyPlanList);
        Command actualDeleteStudyPlanCommand = parser.parseCommand("DelETe-plAn");
        assertEquals(expectedDeleteStudyPlanCommand, actualDeleteStudyPlanCommand);
    }

    @Test
    public void parse_displayStudyPlanCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        DisplayStudyPlanCommand expectedDisplayStudyPlanCommand = new DisplayStudyPlanCommand(studyPlanList,
            flashcardList);
        Command actualDisplayStudyPlanCommand = parser.parseCommand("show-plan");
        assertEquals(expectedDisplayStudyPlanCommand, actualDisplayStudyPlanCommand);
    }

    @Test
    public void parse_displayStudyPlanCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        DisplayStudyPlanCommand expectedDisplayStudyPlanCommand = new DisplayStudyPlanCommand(studyPlanList,
            flashcardList);
        Command actualDisplayStudyPlanCommand = parser.parseCommand("shOw-plAn");
        assertEquals(expectedDisplayStudyPlanCommand, actualDisplayStudyPlanCommand);
    }

    @Test
    public void parse_listReviewedCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        ListReviewedCommand expectedListReviewedCommand = new ListReviewedCommand(flashcardList);
        Command actualListReviewedCommand = parser.parseCommand("list-reviewed");
        assertEquals(expectedListReviewedCommand, actualListReviewedCommand);
    }

    @Test
    public void parse_listReviewedCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        ListReviewedCommand expectedListReviewedCommand = new ListReviewedCommand(flashcardList);
        Command actualListReviewedCommand = parser.parseCommand("liST-revIewEd");
        assertEquals(expectedListReviewedCommand, actualListReviewedCommand);
    }

    @Test
    public void parse_findCommand_lowerCaseCorrect() throws HistoryFlashcardException {
        FindCommand expectedFindCommand = new FindCommand(flashcardList, "word");
        Command actualFindCommand = parser.parseCommand("find word");
        assertEquals(expectedFindCommand, actualFindCommand);
    }

    @Test
    public void parse_findCommand_mixedCaseCorrect() throws HistoryFlashcardException {
        FindCommand expectedFindCommand = new FindCommand(flashcardList, "word");
        Command actualFindCommand = parser.parseCommand("FInD word");
        assertEquals(expectedFindCommand, actualFindCommand);
    }

    @Test
    public void parse_findCommand_throwsInvalidInputFormatException() {
        assertThrows(
            InvalidInputFormatException.class,
            () -> parser.parseCommand("find"),
            "Expected InvalidInputFormatException"
        );
    }

    @Test
    public void parse_dateStrings_successfully() throws InvalidDateFormatException {
        String[] dateStrings = {
            "31 07 1999",
            "31 7 1999",
            "3 7 1999",
            "03 7 1843",
            "07 0413",
            "1942",
            "1/8/2012",
            "6/2012",
            "1-8-2012",
            "8-2012"
        };

        LocalDate[] expectedLocalDates = {
            LocalDate.of(1999, 7, 31),
            LocalDate.of(1999, 7, 31),
            LocalDate.of(1999, 7, 3),
            LocalDate.of(1843, 7, 3),
            LocalDate.of(413, 7, 1),
            LocalDate.of(1942, 1, 1),
            LocalDate.of(2012, 8, 1),
            LocalDate.of(2012, 6, 1),
            LocalDate.of(2012, 8, 1),
            LocalDate.of(2012, 8, 1)
        };

        for (int i = 0; i < dateStrings.length; i++) {
            LocalDate actualLocalDate = Parser.parseDate(dateStrings[i]);
            assertEquals(expectedLocalDates[i], actualLocalDate);
        }
    }

    @Test
    public void parse_invalidDateStrings_throwsInvalidDateFormatException() {
        String[] invalidDateStrings = {
            "asdf",
            "31 13 1999",
            "32 12 1234",
            "07 413",
            "33 02 0512",
            "July 31 1999",
            "31/17 1999",
            "31/7-1999",
            "31 7-1999"
        };

        for (String invalidDateString : invalidDateStrings) {
            assertThrows(
                InvalidDateFormatException.class,
                () -> Parser.parseDate(invalidDateString)
            );
        }
    }
}
