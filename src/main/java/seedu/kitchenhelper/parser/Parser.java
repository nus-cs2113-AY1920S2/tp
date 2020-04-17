package seedu.kitchenhelper.parser;

import seedu.kitchenhelper.command.AddChoreCommand;
import seedu.kitchenhelper.command.AddIngredientCommand;
import seedu.kitchenhelper.command.AddRecipeCommand;
import seedu.kitchenhelper.command.Command;
import seedu.kitchenhelper.command.CookRecipeCommand;
import seedu.kitchenhelper.command.DeleteChoreCommand;
import seedu.kitchenhelper.command.DeleteIngredientCommand;
import seedu.kitchenhelper.command.DeleteRecipeCommand;
import seedu.kitchenhelper.command.DisplayExpenditureCommand;
import seedu.kitchenhelper.command.DoneCommand;
import seedu.kitchenhelper.command.ExitCommand;
import seedu.kitchenhelper.command.HelpCommand;
import seedu.kitchenhelper.command.InvalidCommand;
import seedu.kitchenhelper.command.ListChoreCommand;
import seedu.kitchenhelper.command.ListIngredientCommand;
import seedu.kitchenhelper.command.ListRecipeCommand;
import seedu.kitchenhelper.command.ResetCommand;
import seedu.kitchenhelper.command.SaveStateCommand;
import seedu.kitchenhelper.command.SearchChoreCommand;
import seedu.kitchenhelper.command.SearchIngredientCommand;
import seedu.kitchenhelper.command.SearchRecipeCommand;
import seedu.kitchenhelper.common.Messages;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Expenditure;
import seedu.kitchenhelper.exception.ExpiredException;
import seedu.kitchenhelper.exception.QuantityException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse user input.
 */

public class Parser {

    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String LOG_WARNING_INDEX = "An IndexOutOfBounds exception has been caught";
    public final String warningPrepareRecipe = "An IO exception has been caught";

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string.
     * @return the command based on the user input.
     */
    public Command parseUserCommand(String input) throws KitchenHelperException {
        String[] userInputs = splitInputLine(input, " ");
        final String commandWord = userInputs[0];
        final String parameters = userInputs[1];
        switch (commandWord.toLowerCase()) {
        case AddRecipeCommand.COMMAND_WORD:
            return prepareAddRecipe(parameters);
        case AddIngredientCommand.COMMAND_WORD:
            return prepareAddIngredient(parameters);
        case AddChoreCommand.COMMAND_WORD:
            return prepareAddChore(parameters);
        case CookRecipeCommand.COMMAND_WORD:
            return prepareCookRecipe(parameters);
        case DeleteRecipeCommand.COMMAND_WORD:
            return prepareDeleteRecipe(parameters);
        case DeleteIngredientCommand.COMMAND_WORD:
            return prepareDeleteIngredient(parameters);
        case DeleteChoreCommand.COMMAND_WORD:
            return prepareDeleteChore(parameters);
        case DoneCommand.COMMAND_WORD:
            return prepareDoneChore(parameters);
        case DisplayExpenditureCommand.COMMAND_WORD:
            return prepareDisplayExpenditure(parameters);
        case ListIngredientCommand.COMMAND_WORD:
            return prepareListIngredient(parameters);
        case ListRecipeCommand.COMMAND_WORD:
            return prepareListRecipe(parameters);
        case ListChoreCommand.COMMAND_WORD:
            return prepareListChore(parameters);
        case SearchIngredientCommand.COMMAND_WORD:
            return new SearchIngredientCommand(parameters);
        case SearchRecipeCommand.COMMAND_WORD:
            return new SearchRecipeCommand(parameters);
        case SearchChoreCommand.COMMAND_WORD:
            return new SearchChoreCommand(parameters);
        case ResetCommand.COMMAND_WORD:
            return new ResetCommand();
        case SaveStateCommand.COMMAND_WORD:
            return new SaveStateCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Prepares the addition of ingredients into recipe.
     *
     * @param attributes full user input string.
     * @return hashmap of a formatted list of ingredients.
     * @throws KitchenHelperException if the command is invalid
     */
    public Command prepareAddRecipe(String attributes) throws KitchenHelperException {
        HashMap<String[], Integer> ingrAndQty = new HashMap<>();
        String ingredientList;
        AddRecipeCommand addCmd = new AddRecipeCommand();
        if (!checkAddRecipeCmdFormat(attributes)) {
            return new InvalidCommand(
                    String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, AddRecipeCommand.COMMAND_FORMAT));
        }
        try {
            ingredientList = attributes.substring(attributes.indexOf("/i") + 3);
            String[] splitedIngr = ingredientList.split("[,][\\s]");
            for (int i = 0; i < splitedIngr.length; i++) {
                String item = splitedIngr[i];
                String[] ingrContent = item.split(":");
                if (!checkAddRecipeIngrValidity(item, ingrContent)) {
                    return new InvalidCommand(
                            String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, AddRecipeCommand.COMMAND_FORMAT));
                }
                String[] nameAndType = new String[2];
                nameAndType[0] = ingrContent[0];
                nameAndType[1] = ingrContent[2].trim();
                ingrAndQty.put(nameAndType, Integer.parseInt(ingrContent[1]));
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            kitchenLogs.log(Level.WARNING, warningPrepareRecipe, e.toString());
            return new InvalidCommand(
                    String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, AddRecipeCommand.COMMAND_FORMAT));
        }
        addCmd.setAttributesOfCmd(attributes, ingrAndQty);
        return addCmd;
    }

    /**
     * Checker for command format for addrecipe command.
     *
     * @param command   The full user input without keyword.
     * @return  true if is of valid command, otherwise false.
     */
    public Boolean checkAddRecipeCmdFormat(String command) {
        Boolean isValid = true;
        String cmd = command;
        int sepCountForSlashN = cmd.length() - (cmd.replace("/n", "a").length());
        cmd = cmd.replace("/n", "a");
        int sepCountForSlashI = cmd.length() - (cmd.replace("/i", "a").length());
        cmd = cmd.replace("/i", "a");
        int sepCountForSlashOnly = cmd.length() - (cmd.replace("/", "").length());
        if (sepCountForSlashN > 1 || sepCountForSlashI > 1 || sepCountForSlashOnly > 0) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Checker for prepareAddRecipe on the user input.
     *
     * @param item          Original string.
     * @param ingrContent   List of ingredients.
     * @return true if none of the conditions are violated, false otherwise.
     */
    public Boolean checkAddRecipeIngrValidity(String item, String[] ingrContent) {
        Boolean isValid = true;
        int lenOfCmd = ingrContent[0].length();
        int qtyOfIngr = Integer.parseInt(ingrContent[1]);
        int separatorCounter = item.length() - item.replace(":", "").length();
        if (lenOfCmd < 1 || qtyOfIngr < 1 || separatorCounter > 2) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Prepares the addition of ingredient into inventory.
     *
     * @param attributes full user input string.
     * @return the prepared command.
     */
    public Command prepareAddIngredient(String attributes) {
        try {
            if (!isValidUserInputFormat(attributes, AddIngredientCommand.REGEX_FORMAT)) {
                throw new KitchenHelperException("Invalid Add Inventory Format");
            }
        
            String[] nameAndOthers = attributes.split("/c\\s", 2);
            String itemName = nameAndOthers[0].split("/n\\s+")[1].trim();
            assert itemName.length() > 0 : itemName;
        
            String[] categoryAndOthers = nameAndOthers[1].split("\\s+/q\\s+");
            String category = categoryAndOthers[0].trim();
            assert category.length() > 0 : category;
        
            String[] quantityAndOthers = categoryAndOthers[1].split("\\s+/p\\s+");
            int quantity = Integer.parseInt(quantityAndOthers[0]);
            assert quantity >= 0 : quantity;
        
            if (quantity <= 0) {
                throw new QuantityException();
            }
        
            String[] priceAndExpiry = quantityAndOthers[1].split("\\s+/e\\s+");
            double price = Double.parseDouble(priceAndExpiry[0]);
            assert price >= 0.00 : price;

            String expiry = parseDateFormat(priceAndExpiry[1]);
            if (!isExpiredIngredient(expiry)) {
                throw new ExpiredException();
            }

            Expenditure.getInstance().addToExpenditure(price, quantity);

            return new AddIngredientCommand(itemName, category, quantity, price, expiry);
        } catch (KitchenHelperException khe) {
            kitchenLogs.log(Level.WARNING, InvalidCommand.MESSAGE_INVALID + " " + attributes);
            return new InvalidCommand(
                    String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, AddIngredientCommand.COMMAND_FORMAT));
        } catch (DateTimeException dte) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_DATE);
        } catch (ExpiredException ee) {
            return new InvalidCommand(Messages.MESSAGE_EXPIRED_INGREDIENT);
        } catch (QuantityException qe) {
            return new InvalidCommand(Messages.MESSAGE_ZERO_QUANTITY);
        }
    }
    
    /**
     * Prepares the addition of a chore into chore list.
     *
     * @param attributes full user input string.
     * @return the prepared command.
     */
    public Command prepareAddChore(String attributes) {
        String description;
        String dateStr;
        try {
            String[] descriptionAndDate = attributes.split("/by");
            description = descriptionAndDate[0].trim();
            if (description.isEmpty()) {
                throw new KitchenHelperException(
                        String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, AddChoreCommand.COMMAND_FORMAT));
            }
            dateStr = descriptionAndDate[1].trim();
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand(
                    String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, AddChoreCommand.COMMAND_FORMAT));
        } catch (KitchenHelperException khe) {
            return new InvalidCommand(khe.getMessage());
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = dateFormat.parse(dateStr);
            if (isOverdueChore(date)) {
                throw new ExpiredException();
            }
            return new AddChoreCommand(description, date);
        } catch (ParseException e) {
            return new AddChoreCommand(description, dateStr);
        } catch (ExpiredException ee) {
            return new InvalidCommand(Messages.MESSAGE_OVERDUE_CHORE);
        }
    }

    /**
     * Prepares the parameters needed for the cookrecipe function.
     *
     * @param attributes full user input string.
     * @return the prepared command.
     */
    private Command prepareCookRecipe(String attributes) {
        CookRecipeCommand cookCmd = new CookRecipeCommand();
        try {
            String recipeName = attributes.substring(attributes.indexOf("/n") + 3, attributes.indexOf("/p") - 1);
            int numOfPax = Integer.parseInt(attributes.substring(attributes.indexOf("/p") + 3));
            if (numOfPax < 1) {
                return new InvalidCommand(
                        String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, cookCmd.COMMAND_FORMAT));
            }
            cookCmd.setRecipeName(recipeName);
            cookCmd.setRecipePax(numOfPax);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new InvalidCommand(
                    String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, cookCmd.COMMAND_FORMAT));
        }
        return cookCmd;
    }

    /**
     * Prepares the parameters needed for the list function.
     *
     * @param parameters full user input string.
     * @return the prepared command.
     */
    private Command prepareListIngredient(String parameters) throws KitchenHelperException {
        try {
            if (parameters.isEmpty()) {
                throw new KitchenHelperException("Invalid ListIngredient command.");
            } else {
                for (int i = 0; i < ListIngredientCommand.categoryArray.length; i++) {
                    if (ListIngredientCommand.categoryArray[i].equalsIgnoreCase(parameters)) {
                        break;
                    } else if (i == ListIngredientCommand.categoryArray.length - 1) {
                        throw new KitchenHelperException("Invalid ListIngredient Category.");
                    }
                }
            }
            return new ListIngredientCommand(parameters);
        } catch (KitchenHelperException e) {
            kitchenLogs.log(Level.WARNING, LOG_WARNING_INDEX, e.toString());
            throw new KitchenHelperException(ListIngredientCommand.COMMAND_FORMAT);
        }
    }

    /**
     * Prepares the parameters needed for the list function.
     *
     * @param parameters full user input string.
     * @return the prepared command.
     */
    private Command prepareListChore(String parameters) throws KitchenHelperException {
        try {
            if (! parameters.isEmpty()) {
                throw new KitchenHelperException("Invalid ListChore command.");
            }
            return new ListChoreCommand();
        } catch (KitchenHelperException e) {
            kitchenLogs.log(Level.WARNING, LOG_WARNING_INDEX, e.toString());
            throw new KitchenHelperException(ListChoreCommand.COMMAND_FORMAT);
        }
    }

    /**
     * Prepares the parameters needed for the list function.
     *
     * @param parameters full user input string.
     * @return the prepared command.
     */
    private Command prepareListRecipe(String parameters) throws KitchenHelperException {
        try {
            if (parameters.isEmpty()) {
                throw new KitchenHelperException("Invalid ListRecipe command.");
            }
            parameters = parameters.replaceAll("\\s+","");
            if (! parameters.equalsIgnoreCase("all")) {
                if (! parameters.matches("-?\\d+")) {
                    throw new KitchenHelperException("Invalid ListRecipe command.");
                } else if (Integer.parseInt(parameters) <= 0) {
                    throw new NumberFormatException("Invalid Ingredient Index. Must be > 0.");
                }
            }
            return new ListRecipeCommand(parameters);
        } catch (KitchenHelperException e) {
            kitchenLogs.log(Level.WARNING, LOG_WARNING_INDEX, e.toString());
            throw new KitchenHelperException(ListRecipeCommand.COMMAND_FORMAT);
        } catch (NumberFormatException e) {
            kitchenLogs.log(Level.WARNING, LOG_WARNING_INDEX, e.toString());
            throw new KitchenHelperException(ListRecipeCommand.COMMAND_PARAMETER_LIMIT);
        }
    }

    /**
     * Prepares the deletion of recipe from the lists.
     *
     * @param parameters full user input string.
     * @return hashmap of a formatted list of parameters to be deleted.
     * @throws KitchenHelperException if the command is invalid
     */
    public Command prepareDeleteRecipe(String parameters) throws KitchenHelperException {
        try {
            if (parameters.contains("/i")) {
                String [] typeAndName = parameters.split(("/i\\s"), 2);
                return new DeleteRecipeCommand(Integer.parseInt(typeAndName[1].trim()) - 1);
            } else {
                String [] typeAndName = parameters.split("/n\\s",2);
                return new DeleteRecipeCommand(typeAndName[1].trim());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            kitchenLogs.log(Level.WARNING, LOG_WARNING_INDEX, e.toString());
            throw new KitchenHelperException(DeleteRecipeCommand.COMMAND_FORMAT);
        }
    }

    /**
     * Prepares the deletion of ingredients from the lists.
     *
     * @param parameters full user input string.
     * @return hashmap of a formatted list of parameters to be deleted.
     * @throws KitchenHelperException if the command is invalid
     */
    public Command prepareDeleteIngredient(String parameters) throws KitchenHelperException {
        try {
            String [] typeAndName = parameters.split("/i\\s", 2);
            String [] nameAndQuantity = typeAndName[1].split("/q\\s", 2);
            if (nameAndQuantity.length > 1) {
                return new DeleteIngredientCommand(Integer.parseInt(nameAndQuantity[0].trim()) - 1,
                                                    Integer.parseInt(nameAndQuantity[1]));
            } else {
                return new DeleteIngredientCommand(Integer.parseInt(nameAndQuantity[0].trim()) - 1,
                                            null);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            kitchenLogs.log(Level.WARNING, LOG_WARNING_INDEX, e.toString());
            throw new KitchenHelperException(DeleteIngredientCommand.COMMAND_FORMAT);
        }
    }

    /**
     * Prepares the deletion of a chore from the list.
     *
     * @param parameters full user input string.
     * @return the prepared command.
     */
    public Command prepareDeleteChore(String parameters) {
        try {
            int indexToDelete = Integer.parseInt(parameters.trim());
            return new DeleteChoreCommand(indexToDelete);
        } catch (NumberFormatException e) {
            return new InvalidCommand(
                    String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, DeleteChoreCommand.COMMAND_FORMAT));
        }
    }

    /**
     * Prepares the command that marks chore as done.
     *
     * @param parameters full user input string.
     * @return the prepared command.
     */
    public Command prepareDoneChore(String parameters) {
        try {
            int indexToCheck = Integer.parseInt(parameters.trim());
            return new DoneCommand(indexToCheck);
        } catch (NumberFormatException e) {
            return new InvalidCommand(
                    String.format("%s\n%s", InvalidCommand.MESSAGE_INVALID, DoneCommand.COMMAND_FORMAT));
        }
    }


    /**
     * Prepares the display of expenditure amounts.
     *
     * @param parameters user input string following the command word.
     * @return the prepared command.
     * @throws KitchenHelperException if user adds input after command word.
     */
    public Command prepareDisplayExpenditure(String parameters) throws KitchenHelperException {
        try {
            if (! parameters.isEmpty()) {
                throw new KitchenHelperException("Invalid DisplayExpenditure command.");
            }
            return new DisplayExpenditureCommand();
        } catch (KitchenHelperException e) {
            kitchenLogs.log(Level.WARNING, LOG_WARNING_INDEX, e.toString());
            throw new KitchenHelperException(DisplayExpenditureCommand.COMMAND_FORMAT);
        }
    }


    //@@author AY1920S2-CS2113T-M16-2-reused
    //Reused from
    //https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java

    /**
     * Split the user input into two parts with a specific regex.
     *
     * @param rawUserInput full user input string.
     * @param regex        the quantifier to separate the string.
     * @return an array of size 2 separated by the quantifier.
     */
    public static String[] splitInputLine(String rawUserInput, String regex) {
        String[] split = rawUserInput.trim().split(regex, 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else no parameters
    }
    //@@author

    /**
     * Checks if the input string matches the regex.
     *
     * @param attributes the user input string.
     * @param regex      quantifier to check if valid.
     * @return true if it match, otherwise false.
     */
    public boolean isValidUserInputFormat(String attributes, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(attributes);
        return matcher.matches();
    }
    
    /**
     * Check if the user input a valid date.
     *
     * @param expiry the user input date.
     * @return the date in the form of dd/MM/yyyy.
     */
    public String parseDateFormat(String expiry) {
        LocalDate localDate = changeDateToJavaFormat(expiry);
        String formattedExpiry = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        formattedExpiry = formattedExpiry.replaceAll("-", "/");
        return formattedExpiry;
    }
    
    /**
     * Change to standard Java Date format.
     *
     * @param expiry the user input expiry date.
     * @return LocalDate of the expiry date.
     */
    public LocalDate changeDateToJavaFormat(String expiry) {
        String[] splitExpiry = expiry.split("/");
        String day = splitExpiry[0];
        String month = splitExpiry[1];
        String year = splitExpiry[2];
        return LocalDate.parse(year + "-" + month + "-" + day);
    }
    
    /**
     * Check if the ingredient prior to adding is expired.
     *
     * @param expiry the user input expiry date.
     * @return true is not expired, false if expired.
     */
    public boolean isExpiredIngredient(String expiry) {
        LocalDate dt1 = LocalDate.parse(changeDateToJavaFormat(expiry).toString());
        LocalDate dt2 = LocalDate.parse(LocalDate.now().toString());
        return dt2.isBefore(dt1);
    }

    /**
     * Check if the chore is overdue prior to adding.
     *
     * @param choreDate the user input chore deadline.
     * @return true if chore is overdue, false otherwise.
     */
    public boolean isOverdueChore(Date choreDate) {
        Date currentDate = new Date();
        if (choreDate.before(currentDate)) {
            return true;
        } else {
            return false;
        }
    }

}
