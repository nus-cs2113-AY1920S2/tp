package seedu.duke.utils;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Command newCommand;
    private static final Pattern ITEM_ARGS_FORMAT =
            Pattern.compile("^(?<index>[\\d\\s]+)"
                    + "(?<descriptionArgs>(?:i/[a-zA-Z\\d\\s]+)|)"
                    + "(?<priceArgs>(?:p/[\\d*.?\\d* ]+|))"
                    + "(?<quantityArgs>(?:q/[\\d\\n]+)|)$");

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

        //@@author trishaangelica
        case EditCommand.COMMAND_WORD:
            createEditCommand(arguments);
            break;
        //@@author

        //@@author kokjoon97
        case DeleteCommand.COMMAND_WORD:
            createDeleteCommand(arguments);
            break;

        case FindCommand.COMMAND_WORD:
            createFindCommand(arguments);
            break;

        case SetBudgetCommand.COMMAND_WORD:
            createSetBudgetCommand(arguments);
            break;
        //@@author

        case ListCommand.COMMAND_WORD:
            createListCommand(arguments);
            break;


        case ClearCommand.COMMAND_WORD:
            createClearCommand(arguments);
            break;


        case ResetBudgetCommand.COMMAND_WORD:
            createResetBudgetCommand(arguments);
            break;

        case ExitCommand.COMMAND_WORD:
            createExitCommand();
            break;

        //@@author trishaangelica
        case HelpCommand.COMMAND_WORD: //fall through
        // @@author

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
            String quantity;
            description = args[0];
            prices = args[1];
            quantity = args[2];
            if (prices == null && quantity != null) {
                int quantityInInteger = Integer.getInteger(quantity);
                newCommand = new AddCommand(description, 0.0, quantityInInteger);
            } else if (prices != null && quantity == null) {
                try {
                    double price = Double.parseDouble(prices);
                    newCommand = new AddCommand(description, price,1);
                } catch (NumberFormatException nfe) {
                    newCommand = new IncorrectCommand(System.lineSeparator()
                            + "Oops! Invalid Command. Check if these are met:"
                            + System.lineSeparator()
                            + " - Price of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - Quantity of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - 'i/', 'p/' and 'q/' must be in alphabetical order."
                            + System.lineSeparator()
                            + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                            + "p/[PRICE] or q/[QUANTITY] must be present."
                            + System.lineSeparator()
                            + "|| Example: ADD i/apples p/9.90 q/9");
                } catch (ArrayIndexOutOfBoundsException e) {
                    newCommand = new IncorrectCommand(System.lineSeparator()
                            + "Oops! Invalid Command. Check if these are met:"
                            + System.lineSeparator()
                            + " - Price of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - Quantity of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - 'i/', 'p/' and 'q/' must be in alphabetical order."
                            + System.lineSeparator()
                            + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                            + "p/[PRICE] or q/[QUANTITY] must be present."
                            + System.lineSeparator()
                            + "|| Example: ADD i/apples p/9.90 q/9");
                }
            } else if (prices == null && quantity == null) {
                try {
                    newCommand = new AddCommand(description,0.0,1);
                } catch (StringIndexOutOfBoundsException e) {
                    newCommand = new IncorrectCommand(System.lineSeparator()
                            + "Oops! Invalid Command. Check if these are met:"
                            + System.lineSeparator()
                            + " - Price of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - Quantity of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - 'i/', 'p/' and 'q/' must be in alphabetical order."
                            + System.lineSeparator()
                            + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                            + "p/[PRICE] or q/[QUANTITY] must be present."
                            + System.lineSeparator()
                            + "|| Example: ADD i/apples p/9.90 q/9");
                }
            } else {
                try {
                    double price = Double.parseDouble(prices);
                    int quantityInInteger = Integer.parseInt(quantity);
                    newCommand = new AddCommand(description, price,quantityInInteger);
                } catch (NumberFormatException nfe) {
                    newCommand = new IncorrectCommand(System.lineSeparator()
                            + "Oops! Invalid Command. Check if these are met:"
                            + System.lineSeparator()
                            + " - Price of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - Quantity of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - 'i/', 'p/' and 'q/' must be in alphabetical order."
                            + System.lineSeparator()
                            + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                            + "p/[PRICE] or q/[QUANTITY] must be present."
                            + System.lineSeparator()
                            + "|| Example: ADD i/apples p/9.90 q/9");
                }
            }
        } catch (NullPointerException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Error! Description of an item cannot be empty."
                    + "\nExample: ADD i/apple p/4.50 q/9.90");
        } catch (ArrayIndexOutOfBoundsException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! Invalid Command. Check if these are met:"
                    + System.lineSeparator()
                    + " - Price of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - Quantity of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - 'i/', 'p/' and 'q/' must be in alphabetical order."
                    + System.lineSeparator()
                    + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                    + "p/[PRICE] or q/[QUANTITY] must be present."
                    + System.lineSeparator()
                    + "|| Example: ADD i/apples p/9.90 q/9");
        }
    }

    private String[] splitArgsForAddCommand(String arguments) throws NullPointerException {

        String[] argsArray = new String[]{};
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String quantityDelimiter = "q/";
        String itemPrice;
        String itemDescription;
        String itemQuantity;

        int buffer = 2;
        int indexOfiPrefix;
        int indexOfpPrefix;
        int indexOfqPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);
        boolean quantityPresent = arguments.contains(quantityDelimiter);

        try {
            if (descriptionPresent && !pricePresent && !quantityPresent) { //eg args: ADD i/apple
                indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
                itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
                argsArray = new String[]{itemDescription, null, null};
            } else if (descriptionPresent && pricePresent && !quantityPresent) {
                indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
                indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
                if (indexOfpPrefix < indexOfiPrefix) { //e.g args: ADD p/4.50 i/apple
                    itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
                } else { //e.g args: ADD i/apple p/4.50
                    itemDescription = arguments.substring(indexOfiPrefix + buffer, indexOfpPrefix).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer).trim();
                }
                argsArray = new String[]{itemDescription, itemPrice, null};
            } else if (descriptionPresent && !pricePresent && quantityPresent) {
                indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
                indexOfqPrefix = arguments.trim().indexOf(quantityDelimiter);
                if (indexOfqPrefix < indexOfiPrefix) { //e.g args: ADD q/1 i/apple
                    itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer, indexOfiPrefix).trim();
                } else { //e.g args: ADD i/apple q/1
                    itemDescription = arguments.substring(indexOfiPrefix + buffer, indexOfqPrefix).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer).trim();
                }
                argsArray = new String[]{itemDescription, null, itemQuantity};
            } else if (descriptionPresent && pricePresent && quantityPresent) {
                indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
                indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
                indexOfqPrefix = arguments.trim().indexOf(quantityDelimiter);
                if (indexOfqPrefix < indexOfpPrefix && indexOfpPrefix < indexOfiPrefix) {
                    //e.g args: ADD q/2 p/4.50 i/apple
                    itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer, indexOfpPrefix).trim();
                } else if (indexOfpPrefix < indexOfqPrefix && indexOfqPrefix < indexOfiPrefix) {
                    //e.g args: ADD p/2.5 q/4 i/apple
                    itemDescription = arguments.substring(indexOfiPrefix + buffer).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfqPrefix).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer, indexOfiPrefix).trim();
                } else if (indexOfpPrefix < indexOfiPrefix && indexOfiPrefix < indexOfqPrefix) {
                    //e.g args: ADD p/2.5 i/apple q/4
                    itemDescription = arguments.substring(indexOfiPrefix + buffer,indexOfqPrefix).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer).trim();
                } else if (indexOfqPrefix < indexOfiPrefix && indexOfiPrefix < indexOfpPrefix) {
                    //e.g args: ADD q/2 i/apple p/4.50
                    itemDescription = arguments.substring(indexOfiPrefix + buffer,indexOfpPrefix).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer, indexOfiPrefix).trim();
                } else if (indexOfiPrefix < indexOfqPrefix && indexOfqPrefix < indexOfpPrefix) {
                    //e.g args: ADD i/apple q/4 p/2.5
                    itemDescription = arguments.substring(indexOfiPrefix + buffer,indexOfqPrefix).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer, indexOfpPrefix).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer).trim();
                } else { //e.g args: ADD i/apple p/4.50 q/2
                    itemDescription = arguments.substring(indexOfiPrefix + buffer, indexOfpPrefix).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfqPrefix).trim();
                    itemQuantity = arguments.substring(indexOfqPrefix + buffer).trim();
                }
                argsArray = new String[]{itemDescription, itemPrice, itemQuantity};
            } else if (!descriptionPresent && pricePresent || quantityPresent) { //ADD p/3.50
                argsArray = new String[]{null, null, null};
            }

            if (argsArray[0] == null && argsArray[1] == null && argsArray[2] == null) {
                throw new NullPointerException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! Invalid Command. Check if these are met:"
                    + System.lineSeparator()
                    + " - Price of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - Quantity of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - 'i/', 'p/' and 'q/' must be in alphabetical order."
                    + System.lineSeparator()
                    + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                    + "p/[PRICE] or q/[QUANTITY] must be present."
                    + System.lineSeparator()
                    + "|| Example: ADD i/apples p/9.90 q/9");
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

    //@@author trishaangelica
    /**
     * Initialises the EditCommand.
     */
    private void createEditCommand(String arguments) {
        try {
            final Matcher matcher = ITEM_ARGS_FORMAT.matcher(arguments.trim());
            // Validate args string format
            if (!matcher.matches()) {
                LOGGER.log(Level.WARNING, "(Edit command) Rejecting user command, invalid command format entered.");
                newCommand = new IncorrectCommand(EditCommand.MESSAGE_FAILURE_INCORRECT_FORMAT);

            } else {
                String[] editCommandArgs = splitArgsForEditCommand(arguments, matcher);
                //{index, description, price, quantity}
                int itemIndex = Integer.parseInt(editCommandArgs[0]);
                String newItemDescription = editCommandArgs[1];
                String newItemPrice = editCommandArgs[2];
                String newItemQuantity = editCommandArgs[3];
                newCommand = new EditCommand(itemIndex, newItemDescription, newItemPrice, newItemQuantity);
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, "(Edit command) Rejecting user command, invalid command format entered.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid Command. Please provide a new description, price or quantity.");
        }
    }
    //@@author

    //@@author trishaangelica
    /**
     * Split arguments for Edit Command.
     */
    private String[] splitArgsForEditCommand(String arguments,Matcher matcher) throws NullPointerException {
        String indexOfItem;
        String itemDescription = null;
        String itemPrice = null;
        String itemQuantity = null;
        final boolean descriptionPresent = arguments.contains("i/");
        final boolean pricePresent = arguments.contains("p/");
        final boolean quantityPresent = arguments.contains("q/");

        indexOfItem = matcher.group("index").trim();
        if (descriptionPresent) { //group: i/...; split: [i/, ...]
            itemDescription = matcher.group("descriptionArgs").trim().split("i/")[1];
        }
        if (pricePresent) {
            itemPrice = matcher.group("priceArgs").trim().split("p/")[1];
        }
        if (quantityPresent) {
            itemQuantity = matcher.group("quantityArgs").trim().split("q/")[1];
        }
        return new String[]{indexOfItem,itemDescription,itemPrice,itemQuantity};
    }
    //@@author


    //@@author kokjoon97
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
    //@@author

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

    //@@author kokjoon97
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
    //@@author

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

    //@@author kokjoon97
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
    //@@author
}
