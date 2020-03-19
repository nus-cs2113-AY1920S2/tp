package seedu.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.commands.*;
import seedu.exception.EscException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private Parser parser;

    private EscException expectedException;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void parse_emptyQuery_exceptionThrown() {
        String emptyQuery = "";
        String resultMessage = parser.INCORRECT_COMMAND + HelpCommand.MESSAGE_USAGE;
        EscException resultException = new EscException(resultMessage);
        try {
            parser.parse(emptyQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(resultException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_listSubjectQuery_ListSubjectCommandReturned() throws Exception {
        String listQuery = "listsubject";
        Command returnedCommand = parser.parse(listQuery);
        assertTrue(returnedCommand instanceof ListSubjectCommand);
    }

    @Test
    void parse_helpQuery_HelpCommandReturned() throws Exception {
        String helpQuery = "help";
        Command returnedCommand = parser.parse(helpQuery);
        assertTrue(returnedCommand instanceof HelpCommand);
    }

    @Test
    void parse_exitQuery_ExitCommandReturned() throws Exception {
        String exitQuery = "exit";
        Command returnedCommand = parser.parse(exitQuery);
        assertTrue(returnedCommand instanceof ExitCommand);
    }

    @Test
    void parse_addQueryWithNoArgs_exceptionThrown() {
        expectedException = new EscException(AddCardCommand.MESSAGE_USAGE);
        String[] addCardQueries = {
            "addcard",
            "addcard "};
        for (String query : addCardQueries) {
            try {
                parser.parse(query);
                fail("Empty query should have thrown an exception.");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_addCardQueryWithInvalidArgs_exceptionThrown() {
        expectedException = new EscException(AddCardCommand.MESSAGE_USAGE);
        String[] addCardQueries = {
            "addcard q/",
            "addcard a/",
            "addcard s/",
            "addcard s/ q/",
            "addcard s/1 q/ a/",
            "addcard s/1 q/something a/",
            "addcard s/1 q/ a/something"};
        for (String query : addCardQueries) {
            try {
                parser.parse(query);
                fail("Empty query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_addCardQueryWithNonInteger_exceptionThrown() {
        String addCardQuery = "addcard s/something q/something a/something";
        expectedException = new EscException("The subject index has to be an integer.");
        try {
            parser.parse(addCardQuery);
            fail("Query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(expectedException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_addCardQueryCorrectFormat_AddCardCommandReturned() throws Exception {
        String addCardQuery = "addcard s/1 q/What does HTTP stand for? a/HyperText Transfer Protocol";
        Command returnedCommand = parser.parse(addCardQuery);
        assertTrue(returnedCommand instanceof AddCardCommand);
    }

    @Test
    void parse_addSubjectQueryWithNoArgs_exceptionThrown() {
        expectedException = new EscException(AddSubjectCommand.MESSAGE_USAGE);
        String[] addSubjectQueries = {
                "addsubject",
                "addsubject "};
        for (String query : addSubjectQueries) {
            try {
                parser.parse(query);
                fail("Empty query should have thrown an exception.");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_addSubjectQueryWithInvalidArgs_exceptionThrown() {
        expectedException = new EscException("A subject name is required.");
        String[] addSubjectQueries = {
                "addsubject s/",
                "addsubject s/ "};
        for (String query : addSubjectQueries) {
            try {
                parser.parse(query);
                fail("Empty query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_addSubjectQueryCorrectFormat_AddCardCommandReturned() throws Exception {
        String addSubjectQuery = "addsubject s/biology";
        Command returnedCommand = parser.parse(addSubjectQuery);
        assertTrue(returnedCommand instanceof AddSubjectCommand);
    }

    @Test
    void parse_deleteCardQueryWithInvalidArgs_exceptionThrown() {
        expectedException = new EscException(DeleteCardCommand.MESSAGE_USAGE);
        String[] deleteCardQueries = {
                "deletecard",
                "deletecard "};
        for (String query : deleteCardQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }


    @Test
    void parse_deleteCardQueryWithNonIntegerSubject_exceptionThrown() {
        String deleteCardQuery = "deletecard s/something c/1";
        expectedException = new EscException("The subject index has to be an integer.");
        try {
            parser.parse(deleteCardQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(expectedException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_deleteCardQueryWithNonIntegerCard_exceptionThrown() {
        String deleteCardQuery = "deletecard s/1 c/something";
        expectedException = new EscException("The card index has to be an integer.");
        try {
            parser.parse(deleteCardQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(expectedException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_deleteCardQueryCorrectFormat_DeleteCommandReturned() throws Exception {
        String deleteCardQuery = "deletecard s/1 c/1";
        Command returnedCommand = parser.parse(deleteCardQuery);
        assertTrue(returnedCommand instanceof DeleteCardCommand);
    }

    @Test
    void parse_deleteSubjectQueryWithInvalidArgs_exceptionThrown() {
        expectedException = new EscException(DeleteSubjectCommand.MESSAGE_USAGE);
        String[] deleteSubjectQueries = {
                "deletesubject",
                "deletesubject "};
        for (String query : deleteSubjectQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_deleteSubjectQueryWithoutSubjectIndex_exceptionThrown() {
        expectedException = new EscException("The subject index is required.");
        String[] deleteSubjectQueries = {
                "deletesubject s/"};
        for (String query : deleteSubjectQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_deleteSubjectQueryWithNonIntegerSubject_exceptionThrown() {
        String deleteSubjectQuery = "deletesubject s/something";
        expectedException = new EscException("The subject index has to be an integer.");
        try {
            parser.parse(deleteSubjectQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(expectedException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_deleteSubjectQuery_DeleteSubjectCommandReturned() throws Exception {
        String deleteSubjectQuery = "deletesubject s/1";
        Command returnedCommand = parser.parse(deleteSubjectQuery);
        assertTrue(returnedCommand instanceof DeleteSubjectCommand);
    }

    @Test
    void parse_listCardQueryWithInvalidArgs_exceptionThrown() {
        expectedException = new EscException(ListCardCommand.MESSAGE_USAGE);
        String[] listQueries = {
                "listcard",
                "listcard "};
        for (String query : listQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_listCardQueryWithoutSubjectIndex_exceptionThrown() {
        expectedException = new EscException("The subject index is required.");
        String[] listQueries = {
                "listcard s/"};
        for (String query : listQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_listCardQueryWithNonIntegerSubject_exceptionThrown() {
        String listCardQuery = "listcard s/something";
        expectedException = new EscException("The subject index has to be an integer.");
        try {
            parser.parse(listCardQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(expectedException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_listCardQuery_ListCardCommandReturned() throws Exception {
        String listQuery = "listcard s/1";
        Command returnedCommand = parser.parse(listQuery);
        assertTrue(returnedCommand instanceof ListCardCommand);
    }

    @Test
    void parse_quizQueryWithInvalidArgs_exceptionThrown() {
        expectedException = new EscException(QuizCommand.MESSAGE_USAGE);
        String[] quizQueries = {
                "quiz",
                "quiz "};
        for (String query : quizQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_quizQueryWithoutSubjectIndex_exceptionThrown() {
        expectedException = new EscException("The subject index is required.");
        String[] quizQueries = {
                "quiz s/"};
        for (String query : quizQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_quizQueryWithNonIntegerSubject_exceptionThrown() {
        String quizQuery = "quiz s/something";
        expectedException = new EscException("The subject index has to be an integer.");
        try {
            parser.parse(quizQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(expectedException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_quizQuery_QuizCommandReturned() throws Exception {
        String quizQuery = "quiz s/1";
        Command returnedCommand = parser.parse(quizQuery);
        assertTrue(returnedCommand instanceof QuizCommand);
    }

    @Test
    void parse_scoreQueryWithInvalidArgs_exceptionThrown() {
        expectedException = new EscException(ScoreCommand.MESSAGE_USAGE);
        String[] scoreQueries = {
                "score",
                "score "};
        for (String query : scoreQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_scoreQueryWithoutSubjectIndex_exceptionThrown() {
        expectedException = new EscException("The subject index is required.");
        String[] scoreQueries = {
                "score s/"};
        for (String query : scoreQueries) {
            try {
                parser.parse(query);
                fail("Invalid query should have thrown an exception.\n");
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(),e.getMessage());
            }
        }
    }

    @Test
    void parse_scoreQueryWithNonIntegerSubject_exceptionThrown() {
        String scoreQuery = "score s/something";
        expectedException = new EscException("The subject index has to be an integer.");
        try {
            parser.parse(scoreQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(expectedException.getMessage(),e.getMessage());
        }
    }

    @Test
    void parse_scoreQuery_ScoreCommandReturned() throws Exception {
        String scoreQuery = "score s/1";
        Command returnedCommand = parser.parse(scoreQuery);
        assertTrue(returnedCommand instanceof ScoreCommand);
    }
}