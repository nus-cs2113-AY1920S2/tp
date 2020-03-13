package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ClearCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.ResetBudgetCommand;
import seedu.duke.commands.SetBudgetCommand;
import seedu.duke.commands.UnmarkCommand;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Command newCommand;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        String[] commandAndArgs = splitCommandAndArgs(userInput);
        String commandWord = commandAndArgs[0];
        String arguments;
        try {
            arguments = commandAndArgs[1];
        } catch (IndexOutOfBoundsException e) {
            arguments = null;
        }

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            createAddCommand(arguments);
            break;

        case MarkCommand.COMMAND_WORD:
            createMarkCommand(arguments);
            break;

        case UnmarkCommand.COMMAND_WORD:
            createUnmarkCommand(arguments);
            break;

        case EditCommand.COMMAND_WORD:
            createEditCommand(arguments);
            break;

        case DeleteCommand.COMMAND_WORD:
            createDeleteCommand(arguments);
            break;

        case ListCommand.COMMAND_WORD:
            createListCommand(arguments);
            break;

        case ClearCommand.COMMAND_WORD:
            createClearCommand(arguments);
            break;

        case SetBudgetCommand.COMMAND_WORD:
            createSetBudgetCommand(arguments);
            break;

        case ResetBudgetCommand.COMMAND_WORD:
            createResetBudgetCommand();
            break;

        case ExitCommand.COMMAND_WORD:
            createExitCommand();
            break;

        case HelpCommand.COMMAND_WORD: //fall through

        default:
            createHelpCommand();
        }
        assert newCommand != null : "newCommand should have been initialised";
        return newCommand;
    }

    /**
     * Splits Command and Args from full input string.
     * @param userInput full input string.
     */
    private String[] splitCommandAndArgs(String userInput) {
        return userInput.trim().split(" ", 2);
    }

    private void createAddCommand(String arguments) {
        try {
            String[] args = splitArgsForAddCommand(arguments);
            String description;
            String prices;
            description = args[0];
            prices = args[1];

            if (prices == null) {
                newCommand = new AddCommand(description, 0.0);
            } else {
                double price = Double.parseDouble(prices);
                newCommand = new AddCommand(description, price);
            }
        } catch (NullPointerException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Error! Description of an item cannot be empty."
                    + "\nExample: ADD 1 i/apple p/4.50");
        } catch (ArrayIndexOutOfBoundsException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! For that to be done properly, check if these are met:"
                    + System.lineSeparator()
                    + " - Description of an item cannot be empty."
                    + System.lineSeparator()
                    + " - Price of an item has to be in decimal form."
                    + System.lineSeparator()
                    + " - At least 'i/' or 'p/' should be present."
                    + System.lineSeparator()
                    + "|| Example: ADD i/apple p/2.50"
                    + System.lineSeparator());
        }
    }

    private String[] splitArgsForAddCommand(String arguments) throws NullPointerException {
        String[] argsArray = new String[]{};
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String itemPrice;
        String itemDescription;

        int buffer = 2;
        int indexOfiPrefix;
        int indexOfpPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);

        if (descriptionPresent && !pricePresent) { //eg args: ADD i/apple
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
            argsArray = new String[]{itemDescription, null};
        } else if (descriptionPresent && pricePresent) {
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
            if (indexOfpPrefix < indexOfiPrefix) { //e.g args: ADD p/4.50 i/apple
                itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
                itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
            } else { //e.g args: ADD i/apple p/4.50
                itemDescription = arguments.substring(indexOfiPrefix + buffer, indexOfpPrefix).trim();
                itemPrice = arguments.substring(indexOfpPrefix + buffer).trim();
            }
            argsArray = new String[]{itemDescription, itemPrice};
        } else if (!descriptionPresent && pricePresent) { //ADD p/3.50
            argsArray = new String[]{null, null};
        }

        if (argsArray[0] == null && argsArray[1] == null) {
            throw new NullPointerException();
        }
        return argsArray;
    }

    /**
     * Initialises the MarkCommand.
     */
    private void createMarkCommand(String arguments) {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            newCommand = new IncorrectCommand("Please provide an index number!");
        }
        int index = Integer.parseInt(words[0]) - 1;
        newCommand = new MarkCommand(index);
    }

    /**
     * Initialises the Unmark Command.
     */
    private void createUnmarkCommand(String arguments) {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            newCommand = new IncorrectCommand("Please provide an index number!");
        }
        int index = Integer.parseInt(words[0]) - 1;
        newCommand = new UnmarkCommand(index);
    }

    /**
     * Initialises the EditCommand.
     */
    private void createEditCommand(String arguments) {
        int indexOfItem;
        String newItemPrice;  //newItemPrice is String type as it need not be present (allows null).
        String newItemDescription;
        try {
            String[] args = splitArgsForEditCommand(arguments);
            indexOfItem = Integer.parseInt(args[0]);
            assert indexOfItem > 0 : "(Edit command) Rejecting user command, index provided must be a positive number";
            newItemDescription = args[1];
            newItemPrice = args[2];
            newCommand = new EditCommand(indexOfItem, newItemDescription, newItemPrice);
        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.log(Level.WARNING, "(Edit command) Rejecting user command, invalid command format entered.");
            newCommand = new IncorrectCommand(System.lineSeparator()
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
                    + "|| Example: EDIT 2 i/apple p/2.50");
        } catch (AssertionError e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            newCommand = new IncorrectCommand(System.lineSeparator()
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
                    + "|| Example: EDIT 2 i/apple p/2.50");
        }
    }

    /**
     * Split args for Edit Command.
     */
    private String[] splitArgsForEditCommand(String arguments) throws NullPointerException {
        String[] argsArray;
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String indexOfItem;
        String itemPrice;
        String itemDescription;

        int buffer = 2;
        int indexOfiPrefix;
        int indexOfpPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);

        if (descriptionPresent && !pricePresent) { //e.g args: EDIT 2 i/apple
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
            if (itemDescription.equals("")) { //e.g args: EDIT 2 i/
                throw new NullPointerException();
            } else {
                indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
                argsArray = new String[]{indexOfItem, itemDescription, null};
            }
        } else if (pricePresent && !descriptionPresent) { //e.g args: EDIT 2 p/4.50
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
            itemPrice = arguments.substring(indexOfpPrefix + buffer).trim();
            if (itemPrice.equals("")) { //e.g args: EDIT 2 p/
                throw new NullPointerException();
            }
            indexOfItem = arguments.substring(0, indexOfpPrefix).trim();
            argsArray = new String[]{indexOfItem, null, itemPrice};

        } else if (descriptionPresent && pricePresent) { //e.g args: EDIT 2 i/.. p/..
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);

            if (indexOfpPrefix < indexOfiPrefix) { //e.g args: EDIT 2 p/4.50 i/apple
                indexOfItem = arguments.substring(0, indexOfpPrefix).trim();
                itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
                itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
            } else { //e.g args: EDIT 2 i/apple p/4.50
                indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
                itemDescription = arguments.substring(indexOfiPrefix + buffer, indexOfpPrefix).trim();
                itemPrice = arguments.substring(indexOfpPrefix + buffer).trim();
            }
            if (itemDescription.equals("") || itemPrice.equals("")) { //e.g args: EDIT 2 p/i/apple OR EDIT 2 p/4 i/

                throw new NullPointerException();
            }
            argsArray = new String[]{indexOfItem, itemDescription, itemPrice};
        } else {
            argsArray = new String[]{null, null, null};
        }
        if (argsArray[1] == null && argsArray[2] == null) {
            throw new NullPointerException();
        }
        return argsArray;
    }

    /**
     * Initialises the DeleteCommand.
     */
    private void createDeleteCommand(String arguments) {
        try {
            int index = Integer.parseInt(arguments);
            newCommand = new DeleteCommand(index);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING,"(Delete command) Rejecting user command, user did not enter a number for index.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter an index"
                    + System.lineSeparator()
                    + "Example: DEL 3");
        }

    }

    private void createListCommand(String arguments) {
        if (arguments != null) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "To display your shopping list, just input \"DISPLAY\".");
        } else {
            newCommand = new ListCommand();
        }
    }

    private void createClearCommand(String arguments) {
        if (arguments != null) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "To clear your shopping list, just input \"CLEAR\".");
        } else {
            newCommand = new ClearCommand();
        }
    }

    /**
     * Initialises the SetBudgetCommand.
     */
    private void createSetBudgetCommand(String arguments) {
        try {
            if (!arguments.contains("b/")) {
                LOGGER.log(Level.WARNING,"(Set budget) Rejecting user command, no \"b/\" substring.");
                newCommand = new IncorrectCommand(System.lineSeparator()
                        + "Please enter the amount after the \"b/\" divider"
                        + System.lineSeparator()
                        + "Example: SET b/300");
            } else {
                assert arguments.indexOf("b/") != -1 : "Substring \"b/\" should be present";
                double amount = Double.parseDouble(arguments.substring(2));
                newCommand = new SetBudgetCommand(amount);
            }

        } catch (NumberFormatException | NullPointerException | StringIndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING,"(Set budget) Rejecting user command, no number was specified for budget.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter an amount for your budget"
                    + System.lineSeparator()
                    + "Example: SET b/300");
        }
    }

    /**
     * Initialises the ResetBudgetCommand.
     */
    private void createResetBudgetCommand() {
        newCommand = new ResetBudgetCommand();
    }

    /**
     * Initialises the HelpCommand.
     */
    private void createHelpCommand() {
        newCommand = new HelpCommand();
    }

    /**
     * Initialises the ExitCommand.
     */
    private void createExitCommand() {
        newCommand = new ExitCommand();
    }

}
