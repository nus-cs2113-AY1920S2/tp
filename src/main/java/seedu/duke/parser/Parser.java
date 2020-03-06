package seedu.duke.parser;

import seedu.duke.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
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
            return new IncorrectCommand(String.format("Invalid input. Please try again or type 'help' to show a list of instructions.\n"));
        }
        final String commandWord = matcher.group("commandWord").trim();
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {

        case "edit":
            return prepareEdit(arguments);

        default:
            return new HelpCommand();
        }
    }

    private Command prepareEdit(String arguments) {
        int indexOfItem;
        String newItemPrice;
        String newItemDescription;
        try {
            String[] args = splitArgs(arguments);
            indexOfItem = Integer.parseInt(args[0]);
            newItemDescription = args[1];
            newItemPrice = args[2];
            return new EditCommand(indexOfItem, newItemDescription, newItemPrice);
        } catch (NumberFormatException | NullPointerException e) {
            return new IncorrectCommand(System.lineSeparator()
                    + "Error! Index of item must be a positive number and the price of an item"
                    + "has to be in decimal form\n  Example: edit 2 i/apple p/2.50");
        }
    }
    private String[] splitArgs(String arguments) throws NullPointerException{
        String[] ArgsArray;
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String  indexOfItem, itemPrice, itemDescription;

        int buffer = 2;
        int indexOfiPrefix, indexOfpPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);

        if(descriptionPresent && !pricePresent) { //e.g args: edit 2 i/apple
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
            indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
            ArgsArray = new String[]{indexOfItem, itemDescription, null};

        } else if(pricePresent && !descriptionPresent) { //e.g args: edit 2 p/4.50
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
            itemPrice = arguments.trim().substring(indexOfpPrefix + buffer);
            indexOfItem = arguments.substring(0, indexOfpPrefix).trim();
            ArgsArray = new String[]{indexOfItem, null, itemPrice};

        } else if (descriptionPresent && pricePresent) { //e.g args: edit 2 i/apple p/4.50
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);

            if(indexOfpPrefix < indexOfiPrefix) { //e.g args: edit 2 p/4.50 i/apple
                indexOfItem = arguments.substring(0,indexOfpPrefix).trim();
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
                itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix);
            } else {
                indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer, indexOfpPrefix);
                itemPrice = arguments.substring(indexOfpPrefix + buffer);
            }
            ArgsArray = new String[]{indexOfItem, itemDescription, itemPrice};
        } else{
            ArgsArray = new String[]{null, null, null};
        }
        if(ArgsArray[1] == null && ArgsArray[2] == null){
            throw new NullPointerException();
        }
        return ArgsArray;
    }
}
