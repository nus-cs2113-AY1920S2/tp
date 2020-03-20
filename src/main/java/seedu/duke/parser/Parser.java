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
import seedu.duke.commands.FindCommand;

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

        case FindCommand.COMMAND_WORD:
            createFindCommand(arguments);
            break;

        case ClearCommand.COMMAND_WORD:
            createClearCommand(arguments);
            break;

        case SetBudgetCommand.COMMAND_WORD:
            createSetBudgetCommand(arguments);
            break;

        case ResetBudgetCommand.COMMAND_WORD:
            createResetBudgetCommand(arguments);
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
     *
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
                try {
                    double price = Double.parseDouble(prices);
                    newCommand = new AddCommand(description, price);
                } catch (NumberFormatException nfe) {
                    newCommand = new IncorrectCommand(System.lineSeparator()
                            + "Oops! For that to be done properly, check if these are met:"
                            + System.lineSeparator()
                            + " - Description of an item cannot be empty."
                            + System.lineSeparator()
                            + " - Price of an item has to be in numerical form."
                            + System.lineSeparator()
                            + " - At least 'i/' or 'p/' should be present."
                            + System.lineSeparator()
                            + "|| Example: ADD i/apple p/2.50"
                            + System.lineSeparator());
                }
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
        try {
            String[] words = arguments.trim().split(" ");
            if (words.length == 1) {
                int index = Integer.parseInt(words[0]) - 1;
                newCommand = new MarkCommand(index);
            } else {
                LOGGER.log(Level.WARNING, "(Mark command) Rejecting user command, too many or no arguments.");
                newCommand = new IncorrectCommand("Please provide a single numerical index number!");
            }

        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.log(Level.WARNING, "(Mark command) Rejecting user command, invalid command format entered.");
            newCommand = new IncorrectCommand("Please provide a single numerical index number!");
        }
    }

    /**
     * Initialises the Unmark Command.
     */
    private void createUnmarkCommand(String arguments) {
        try {
            String[] words = arguments.trim().split(" ");
            if (words.length == 1) {
                int index = Integer.parseInt(words[0]) - 1;
                newCommand = new UnmarkCommand(index);
            } else {
                LOGGER.log(Level.WARNING,
                        "(Unmark command) Rejecting user command, too many or no arguments.");
                newCommand = new IncorrectCommand("Please provide a single numerical index number!");
            }

        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.log(Level.WARNING,
                    "(Unmark command) Rejecting user command, invalid command format entered.");
            newCommand = new IncorrectCommand("Please provide a single numerical index number!");
        }
    }

    /**
     * Initialises the EditCommand.
     */
    private void createEditCommand(String arguments) {
        int indexOfItem;
        String newItemPrice;  //newItemPrice is String type as it need not be present (allows null).
        String newItemDescription;
        String newItemQuantity;
        try {
            String[] args = splitArgsForEditCommand(arguments);
            indexOfItem = Integer.parseInt(args[0]);
            newItemDescription = args[1];
            newItemPrice = args[2];
            newItemQuantity = args[3];
            newCommand = new EditCommand(indexOfItem, newItemDescription, newItemPrice, newItemQuantity);
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "(Edit command) Rejecting user command, invalid command format entered.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! For that to be done properly, check if these are met:"
                    + System.lineSeparator()
                    + " - Index of item must be a positive number."
                    + System.lineSeparator()
                    + " - Price of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - Quantity of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - At least 'i/' , 'p/' or 'q/' should be present."
                    + System.lineSeparator()
                    + " - If 'i/' , 'p/' or 'q/' is present, ensure i/[NEW DESCRIPTION], "
                    + "p/[NEW PRICE] or q/[QUANTITY] is present."
                    + System.lineSeparator()
                    + "|| Example: EDIT 2 i/apple p/2.50 q/5");
        }
    }

    /**
     * Split args for Edit Command.
     */
    private String[] splitArgsForEditCommand(String arguments) throws NullPointerException {
        String indexOfItem;
        String itemPrice = null;
        String itemDescription = null;
        String itemQuantity = null;
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String quantityDelimiter = "q/";

        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);
        boolean quantityPresent = arguments.contains(quantityDelimiter);

        int indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
        int indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
        int indexOfqPrefix = arguments.trim().indexOf(quantityDelimiter);

        indexOfItem = arguments.split(" ")[0];

        if (descriptionPresent | pricePresent | quantityPresent) {
            String[] splitArgs = arguments.split("i/|p/|q/");

            if (descriptionPresent & !pricePresent & !quantityPresent) { // e.g args EDIT 1 i/apples
                itemDescription = splitArgs[1].trim();
                if (itemDescription.equals("")) {
                    throw new NullPointerException();
                }

            } else if (pricePresent & !descriptionPresent & !quantityPresent) { // e.g args EDIT 1 p/2.50
                itemPrice = splitArgs[1].trim();
                if (itemPrice.equals("")) {
                    throw new NullPointerException();
                }

            } else if (quantityPresent & !descriptionPresent & !pricePresent) { // e.g args EDIT 1 q/10
                itemQuantity = splitArgs[1].trim();
                if (itemQuantity.equals("")) {
                    throw new NullPointerException();
                }

            } else if (descriptionPresent & pricePresent & !quantityPresent) { // e.g args EDIT 1 i/.. p/..
                itemDescription = splitArgs[1].trim();
                itemPrice = splitArgs[2].trim();

                if (indexOfiPrefix > indexOfpPrefix) { // e.g args EDIT 1 p/.. i/..
                    itemDescription = splitArgs[2].trim();
                    itemPrice = splitArgs[1].trim();
                }

                if (itemDescription.equals("") | itemPrice.equals("")) {
                    throw new NullPointerException();
                }
            } else if (descriptionPresent & quantityPresent & !pricePresent) {  // e.g args EDIT 1 i/.. q/..
                itemDescription = splitArgs[1].trim();
                itemQuantity = splitArgs[2].trim();

                if (indexOfiPrefix > indexOfqPrefix) { // e.g args EDIT 1 q/.. i/..
                    itemDescription = splitArgs[2].trim();
                    itemQuantity = splitArgs[1].trim();
                }
                if (itemDescription.equals("") | itemQuantity.equals("")) {
                    throw new NullPointerException();
                }
            } else if (pricePresent & quantityPresent & !descriptionPresent) { // e.g args EDIT 1 p/.. q/..
                itemPrice = splitArgs[1].trim();
                itemQuantity = splitArgs[2].trim();

                if (indexOfpPrefix > indexOfqPrefix) { // e.g args EDIT 1 q/.. p/..
                    itemPrice = splitArgs[2].trim();
                    itemQuantity = splitArgs[1].trim();
                }

                if (itemPrice.equals("") | itemQuantity.equals("")) {
                    throw new NullPointerException();
                }
            } else if (descriptionPresent & pricePresent & quantityPresent) { // e.g args EDIT 1 i/.. q/.. p/..
                if ((indexOfiPrefix < indexOfpPrefix) & (indexOfiPrefix < indexOfqPrefix)) {
                    itemDescription = splitArgs[1].trim();
                    if (indexOfqPrefix < indexOfpPrefix) { // e.g args EDIT 1 i/apple q/6 p/3.50
                        itemPrice = splitArgs[3].trim();
                        itemQuantity = splitArgs[2].trim();
                    } else { // e.g args EDIT 1 i/apple p/3.50  q/6
                        itemPrice = splitArgs[2].trim();
                        itemQuantity = splitArgs[3].trim();
                    }
                } else if ((indexOfpPrefix < indexOfqPrefix) & (indexOfpPrefix < indexOfiPrefix)) {
                    itemPrice = splitArgs[1].trim();
                    if (indexOfiPrefix < indexOfqPrefix) { // e.g args EDIT 1 p/3.50 i/apple q/6
                        itemDescription = splitArgs[2].trim();
                        itemQuantity = splitArgs[3].trim();
                    } else { // e.g args EDIT 1 p/3.50 q/6 i/apple
                        itemDescription = splitArgs[3].trim();
                        itemQuantity = splitArgs[2].trim();
                    }
                } else if ((indexOfqPrefix < indexOfpPrefix) & (indexOfqPrefix < indexOfiPrefix)) {
                    itemQuantity = splitArgs[1].trim();
                    if (indexOfpPrefix < indexOfiPrefix) { // e.g args EDIT 1 q/6 p/3.50 i/apple
                        itemDescription = splitArgs[3].trim();
                        itemPrice = splitArgs[2].trim();

                    } else { // e.g args EDIT 1 q/6 i/apple p/3.50
                        itemDescription = splitArgs[2].trim();
                        itemQuantity = splitArgs[3].trim();
                    }
                }
                if (itemDescription.equals("") | itemPrice.equals("") | itemQuantity.equals("")) {
                    throw new NullPointerException();
                }
            }
        } else { // e.g args EDIT 1
            throw new ArrayIndexOutOfBoundsException();
        }
        if (itemPrice != null) {
            if (Double.parseDouble(itemPrice) < 0.0) { //check for negative price input
                throw new NumberFormatException();
            }

        }

        if (itemQuantity != null) {
            if (Integer.parseInt(itemQuantity) < 0.0) { //check for negative qty input
                throw new NumberFormatException();
            }
        }
        String[] argsArray = new String[]{indexOfItem, itemDescription, itemPrice, itemQuantity};
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
            LOGGER.log(Level.WARNING, "(Delete command) Rejecting user command, "
                    + "user did not enter a number for index.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter an index"
                    + System.lineSeparator()
                    + "Example: DEL 3");
        }

    }

    /**
     * Initialises the ListCommand.
     */
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

    /**
     * Initialises the ClearCommand.
     */
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
                LOGGER.log(Level.WARNING, "(Set budget) Rejecting user command, no \"b/\" substring.");
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
            LOGGER.log(Level.WARNING, "(Set budget) Rejecting user command, no number was specified for budget.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter an amount for your budget"
                    + System.lineSeparator()
                    + "Example: SET b/300");
        }
    }

    /**
     * Initialises the ResetBudgetCommand.
     */
    private void createResetBudgetCommand(String arguments) {
        if (arguments != null) {
            LOGGER.log(Level.WARNING,
                    "(Reset Budget command) Rejecting user command, should not have arguments.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "To reset your budget, just input \"RES\".");
        } else {
            newCommand = new ResetBudgetCommand();
        }
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

    private void createFindCommand(String arguments) {
        if (arguments == null) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter a keyword after FIND"
                    + System.lineSeparator()
                    + "Example: FIND apples");
            LOGGER.log(Level.INFO,"(Find command) User did not supply keyword for FIND");
        } else {
            assert arguments != null;
            LOGGER.log(Level.INFO,"(Find command) User supplied keyword: " + arguments);
            newCommand = new FindCommand(arguments);
        }
    }

}
