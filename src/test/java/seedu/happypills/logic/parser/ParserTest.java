package seedu.happypills.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.Command;
import seedu.happypills.model.exception.HappyPillsException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author itskesin
/**
 * Contains all the tests for Parser class.
 */
class ParserTest {
    Command command;
    Parser parser = new Parser();

    @Test
    void parseCommand_invalidCommand_throwException() {
        try {
            command = parser.parse("list");
            fail("Invalid command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Command is invalid. Enter help to know how to use HappyPills.",e.getMessage());
        }
    }

    @Test
    void parseCommand_emptyCommand_throwException() {
        try {
            command = parser.parse("");
            fail("Invalid command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Command is invalid. Enter help to know how to use HappyPills.",e.getMessage());
        }
    }
}