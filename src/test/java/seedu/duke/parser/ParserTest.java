package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.ClearCommand;
import seedu.duke.commands.ResetBudgetCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.ExitCommand;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }


    @Test
    public void parse_unknownCommandWord_returnsHelp() {
        final String input = "unknownCommandWord arguments";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    /*
     * Tests for 0-argument commands =======================================================================
     */

    @Test
    public void parse_helpCommand_parsedCorrectly() {
        final String input = "HELP";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    @Test
    public void parse_clearCommand_parsedCorrectly() {
        final String input = "CLEAR";
        parseAndAssertCommandType(input, ClearCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() {
        final String input = "DISPLAY";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_exitCommand_parsedCorrectly() {
        final String input = "BYE";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    @Test
    public void parse_resetBudgetCommand_parsedCorrectly() {
        final String input = "RES";
        parseAndAssertCommandType(input, ResetBudgetCommand.class);
    }

    /*
     * Tests for single index argument commands ===============================================================
     */

    @Test
    public void parse_deleteCommandNoArgs_errorMessage() {
        final String[] inputs = { "DEL", "DEL " };
        final String resultMessage = System.lineSeparator()
                + "Please enter an index"
                + System.lineSeparator()
                + "Example: DEL 3";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_deleteCommandArgsIsNotSingleNumber_errorMessage() {
        final String[] inputs = { "DEL notAnumber ", "DEL 8*wh12", "DEL 1 2 3 4 5" };
        final String resultMessage = System.lineSeparator()
                + "Please enter an index"
                + System.lineSeparator()
                + "Example: DEL 3";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    /*
    @Test
    public void parse_markCommandNoArgs_errorMessage() {
        final String[] inputs = { "MARK", "MARK " };
        final String resultMessage = "Please provide an index number!";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }
*/
    /*
    @Test
    public void parse_markCommandArgsIsNotSingleNumber_errorMessage() {
        final String[] inputs = { "MARK notAnumber ", "MARK 8*wh12", "MARK 1 2 3 4 5" };
        final String resultMessage = "Please provide an index number!";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }
*/
    /*
    @Test
    public void parse_unMarkCommandNoArgs_errorMessage() {
        final String[] inputs = { "UNMARK", "UNMARK " };
        final String resultMessage = "Please provide an index number!";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }
    */


    /*
    @Test
    public void parse_unMarkCommandArgsIsNotSingleNumber_errorMessage() {
        final String[] inputs = { "UNMARK notAnumber ", "UNMARK 8*wh12", "MARK 1 2 3 4 5" };
        final String resultMessage = "Please provide an index number!";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }
    */

    /*
     * Tests for add item command ==============================================================================
    */

    @Test
    public void parse_addCommandNoDescription_errorMessage() {
        final String[] inputs = { "ADD", "ADD ", "ADD p/2.50" };
        final String resultMessage = System.lineSeparator()
                + "Error! Description of an item cannot be empty."
                + "\nExample: ADD 1 i/apple p/4.50";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    /*
    @Test
    public void parse_addCommandIncorrectPriceFormat_errorMessage() {
        final String[] inputs = { "ADD i/apple p/8*wh12", "ADD i/apple p/ " };
        final String resultMessage = System.lineSeparator()
                + "Oops! For that to be done properly, check if these are met:"
                + System.lineSeparator()
                + " - Description of an item cannot be empty."
                + System.lineSeparator()
                + " - Price of an item has to be in numerical form."
                + System.lineSeparator()
                + " - At least 'i/' or 'p/' should be present."
                + System.lineSeparator()
                + "|| Example: ADD i/apple p/2.50"
                + System.lineSeparator();
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

 */


    /*
     * Tests for edit item command ==============================================================================
     */

    @Test
    public void parse_editCommandInvalidArgs_errorMessage() {
        final String[] inputs = { "EDIT", "EDIT ", "EDIT wrong args format", "EDIT i/apple p/2.50", "EDIT 1 i/p/2.50",
            "EDIT 1 i/apple p/", "EDIT 1 i/p/WERT", "EDIT 1 i/p/" };
        final String resultMessage = System.lineSeparator()
                + "Oops! For that to be done properly, check if these are met:"
                + System.lineSeparator()
                + " - Index of item must be a positive number."
                + System.lineSeparator()
                + " - Price of an item has to be in decimal form."
                + System.lineSeparator()
                + " - At least 'i/' or 'p/' should be present."
                + System.lineSeparator()
                + " - If 'i/' or 'p/' is present, ensure i/[NEW DESCRIPTION] or p/[NEW PRICE] is present."
                + System.lineSeparator()
                + "|| Example: EDIT 2 i/apple p/2.50";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    /*
     * Tests for add item command ==============================================================================
     */

    @Test
    public void parse_setBudgetCommandIncorrectFormat_errorMessage() {
        final String[] inputs = { "SET", "SET ", "SET b/ 8*wh12", "SET b/" };
        final String resultMessage = System.lineSeparator()
                + "Please enter an amount for your budget"
                + System.lineSeparator()
                + "Example: SET b/300";
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }


    /*
     * Utility methods ====================================================================================
     */

    private void parseAndAssertIncorrectWithMessage(String resultMessage, String[] emptyInputs) {
        for (String input : emptyInputs) {
            final IncorrectCommand result = parseAndAssertCommandType(input, IncorrectCommand.class);
            assertEquals(result.feedbackToUser, resultMessage);
        }
    }

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input to be parsed
     * @param expectedCommandClass expected class of returned command
     * @return the parsed command object
     */

    @SuppressWarnings("unchecked")
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) {
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }

}
