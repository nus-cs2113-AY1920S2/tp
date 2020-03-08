package seedu.duke.parser;

import seedu.duke.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.SetBudgetCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.EditCommand;


public class Parser {

    private static Command newCommand;
    private static int index;
    private static double amount;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>^[\\S]+)(?<arguments>[\\d\\s\\S]*$)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            newCommand = new IncorrectCommand("Invalid input. Please try again or type 'help' to show a list of instructions.\n");
        }
        final String commandWord = matcher.group("commandWord").trim();
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {

            case AddCommand.COMMAND_WORD:

                return prepareAdd(arguments);

        case EditCommand.COMMAND_WORD:
            createEditCommand(arguments);
            break;
        case DeleteCommand.COMMAND_WORD:
            createDeleteCommand(arguments);
            break;
        case SetBudgetCommand.COMMAND_WORD:
            createSetBudgetCommand(arguments);
            break;
        case ExitCommand.COMMAND_WORD:
            createExitCommand();
            break;
        default:
            createHelpCommand();
        }
        return newCommand;
    }


    private Command prepareAdd(String arguments) {

        String[] args = splitArgsForAddCommand(arguments);
        String description;
        String prices;
        description = args[1];
        prices = args[2];
        double price = Double.parseDouble(prices);
        return new AddCommand(description,price);
    }

    private String[] splitArgsForAddCommand(String arguments) throws NullPointerException{
        String[] ArgsArray;
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String  itemPrice, itemDescription;

        int buffer = 2;
        int indexOfiPrefix, indexOfpPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);

        if(descriptionPresent && !pricePresent) { //eg: add i/apple
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
            ArgsArray = new String[]{itemDescription, null};
        } else if (descriptionPresent && pricePresent) {
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
            if(indexOfpPrefix < indexOfiPrefix) { //e.g args: edit 2 p/4.50 i/apple
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
                itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix);
            } else {
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer, indexOfpPrefix);
                itemPrice = arguments.substring(indexOfpPrefix + buffer);
            }
            ArgsArray = new String[]{itemDescription, itemPrice};
        } else{
            ArgsArray = new String[]{null, null};
        }
        if(ArgsArray[1] == null && ArgsArray[2] == null){
            throw new NullPointerException();
        }
        return ArgsArray;
    }

    /**
     * Initialises the EditCommand.
     */
    private void createEditCommand(String arguments) {
        int indexOfItem;
        String newItemPrice;
        String newItemDescription;
        try {
            String[] args = splitArgsforEditCommand(arguments);
            indexOfItem = Integer.parseInt(args[0]);
            newItemDescription = args[1];
            newItemPrice = args[2];
            newCommand = new EditCommand(indexOfItem, newItemDescription, newItemPrice);
        } catch (NumberFormatException | NullPointerException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Error! Index of item must be a positive number and the price of an item"
                    + "has to be in decimal form\n  Example: edit 2 i/apple p/2.50");
        }
    }

    /**
     * Split args for Edit Command
     */
    private String[] splitArgsforEditCommand(String arguments) throws NullPointerException {
        String[] ArgsArray;
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String indexOfItem, itemPrice, itemDescription;

        int buffer = 2;
        int indexOfiPrefix, indexOfpPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);

        if (descriptionPresent && !pricePresent) { //e.g args: edit 2 i/apple
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
            indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
            ArgsArray = new String[]{indexOfItem, itemDescription, null};

        } else if (pricePresent && !descriptionPresent) { //e.g args: edit 2 p/4.50
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
            itemPrice = arguments.trim().substring(indexOfpPrefix + buffer);
            indexOfItem = arguments.substring(0, indexOfpPrefix).trim();
            ArgsArray = new String[]{indexOfItem, null, itemPrice};

        } else if (descriptionPresent && pricePresent) { //e.g args: edit 2 i/apple p/4.50
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);

            if (indexOfpPrefix < indexOfiPrefix) { //e.g args: edit 2 p/4.50 i/apple
                indexOfItem = arguments.substring(0, indexOfpPrefix).trim();
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
                itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix);
            } else {
                indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer, indexOfpPrefix);
                itemPrice = arguments.substring(indexOfpPrefix + buffer);
            }
            ArgsArray = new String[]{indexOfItem, itemDescription, itemPrice};
        } else {
            ArgsArray = new String[]{null, null, null};
        }
        if (ArgsArray[1] == null && ArgsArray[2] == null) {
            throw new NullPointerException();
        }
        return ArgsArray;
    }

    /**
     * Initialises the ExitCommand.
     */
    public static void createExitCommand() {
        newCommand = new ExitCommand();
    }

    /**
     * Initialises the SetBudgetCommand.
     */
    public static void createSetBudgetCommand(String arguments) {
        amount = Double.parseDouble(arguments.substring(2));
        newCommand = new SetBudgetCommand(amount);
    }

    /**
     * Initialises the DeleteCommand.
     */
    public static void createDeleteCommand(String arguments) {
        index = Integer.parseInt(arguments);
        newCommand = new DeleteCommand(index);
    }
    /**
     * Initialises the HelpCommand.
     */
    public static void createHelpCommand(){
        newCommand = new HelpCommand();
    }

}
