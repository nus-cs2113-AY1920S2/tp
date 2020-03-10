package seedu.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.commands.Command;
import seedu.commands.QuizCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.AddCardCommand;
import seedu.commands.ExitCommand;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void parse_emptyQuery_exceptionThrown() {
        String emptyQuery = "";
        try {
            parser.parse(emptyQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals("Incorrect Command.",e.getMessage());
        }
    }

    @Test
    void parse_listQuery_ListCommandReturned() throws Exception {
        String listQuery = "list";
        Command returnedCommand = parser.parse(listQuery);
        assertTrue(returnedCommand instanceof ListCommand);
    }

    @Test
    void parse_helpQuery_HelpCommandReturned() throws Exception {
        String helpQuery = "help";
        Command returnedCommand = parser.parse(helpQuery);
        assertTrue(returnedCommand instanceof HelpCommand);
    }

    @Test
    void parse_quizQuery_QuizCommandReturned() throws Exception {
        String quizQuery = "quiz";
        Command returnedCommand = parser.parse(quizQuery);
        assertTrue(returnedCommand instanceof QuizCommand);
    }

    @Test
    void parse_exitQuery_ExitCommandReturned() throws Exception {
        String exitQuery = "exit";
        Command returnedCommand = parser.parse(exitQuery);
        assertTrue(returnedCommand instanceof ExitCommand);
    }

    @Test
    void parse_addQueryWithNoArgs_exceptionThrown() {
        String[] addQueries = {
            "add",
            "add "};
        for (String query : addQueries) {
            try {
                parser.parse(query);
                fail("Empty query should have thrown an exception.");
            } catch (Exception e) {
                assertEquals("Incorrect Command.",e.getMessage());
            }
        }
    }

    /*    @Test
    void parse_addQueryWithInvalidArgs_exceptionThrown() {
        String[] addQueries = {
            "add q/",
            "add a/",
            "add q/ a/",
            "add q/something a/",
            "add q/ a/something"};
        for (String query : addQueries) {
            try {
                parser.parse(query);
                fail("Empty query should have thrown an exception.");
            } catch (Exception e) {
                assertEquals("Incorrect Command.",e.getMessage());
            }
        }
    }*/

    @Test
    void parse_addQueryCorrectFormat_AddCommandReturned() throws Exception {
        String addQuery = "add q/What does HTTP stand for? a/HyperText Transfer Protocol";
        Command returnedCommand = parser.parse(addQuery);
        assertTrue(returnedCommand instanceof AddCardCommand);
    }


    /*    @Test
    void parse_deleteQueryWithNoArgs_exceptionThrown() {
        String addQuery = "delete ";
        try {
            parser.parse(addQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals("Incorrect Command.",e.getMessage());
        }
    }*/


    @Test
    void parse_deleteQueryWithNonInteger_exceptionThrown() {
        String addQuery = "delete a";
        try {
            parser.parse(addQuery);
            fail("Empty query should have thrown an exception.");
        } catch (Exception e) {
            assertEquals("The task item has to be an integer.",e.getMessage());
        }
    }

    @Test
    void parse_deleteQueryCorrectFormat_DeleteCommandReturned() throws Exception {
        String deleteQuery = "delete 1";
        Command returnedCommand = parser.parse(deleteQuery);
        assertTrue(returnedCommand instanceof DeleteCommand);
    }
}