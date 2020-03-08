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
        final String commandWord = matcher.group("commandWord").trim();
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:

            return prepareAdd(arguments);

        default:
            return new HelpCommand();
        }
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




}
