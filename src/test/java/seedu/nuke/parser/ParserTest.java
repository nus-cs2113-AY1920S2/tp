package seedu.nuke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuke.command.Command;
import seedu.nuke.command.HelpCommand;
import seedu.nuke.command.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.nuke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    /*
     * Note how the names of the test methods does not follow the normal naming convention.
     * That is because our coding standard allows a different naming convention for test methods.
     */

    @Test
    public void parse_emptyInput_returnsIncorrect() throws Parser.InputLengthExceededException, Parser.EmptyInputException, Parser.InvalidCommandException {
        final String[] emptyInputs = { "", "  ", "\n  \n" };
        final String resultMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, emptyInputs);
    }

    /*
     * Utility methods ====================================================================================
     */

    /**
     * Asserts that parsing the given inputs will return IncorrectCommand with the given feedback message.
     */
    private void parseAndAssertIncorrectWithMessage(String feedbackMessage, String... inputs) throws Parser.EmptyInputException, Parser.InputLengthExceededException, Parser.InvalidCommandException {
        for (String input : inputs) {
            final IncorrectCommand result = parseAndAssertCommandType(input, IncorrectCommand.class);
            assertEquals(result.getFeedbackToUSer(), feedbackMessage);
        }
    }

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input to be parsed
     * @param expectedCommandClass expected class of returned command
     * @return the parsed command object
     */
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) throws Parser.InputLengthExceededException, Parser.EmptyInputException, Parser.InvalidCommandException {
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}