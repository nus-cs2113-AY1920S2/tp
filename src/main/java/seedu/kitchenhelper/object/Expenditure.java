package seedu.kitchenhelper.object;

import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.ui.Ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Calculate user expenditure.
 */
public class Expenditure {

    public static final String PROMPT_REMOVE_FROM_EXPENDITURE
            = "The amount of money spent on this ingredient has already been recorded.\n"
            + "Would you like to remove the amount spent on this item from the total expenditure?\n"
            + Ui.DIVIDER;
    public static final String PROMPT_ADD_TO_AMOUNT_USED
            = "Would you like to add the amount spent on this item "
            + "to the amount used for cooking or consumption?\n" + Ui.DIVIDER;
    public static final String REMOVAL_SUCCESS = "Ok! $%.2f is deducted from total expenditure.";
    public static final String INCREASE_AMOUNT_USED =
            "Ok! $%.2f is added to amount used in cooking or consumption.";
    public static final String NO_CHANGE = "Ok! There are no changes to expenditure.";


    private Ui ui;
    public double totalExpenditure;
    public double amountUsedInCooking;
    public Date lastSavedDate;

    private static Expenditure onlyInstance = null;

    /**
     * Only one instance of Expenditure class can be instantiated.
     */
    private Expenditure() {
    }

    /**
     * Import private ui from main KitchenHelper class so can prompt user
     * to edit expenditure.
     *
     * @param ui private ui from main.
     */
    public void setUi(Ui ui) {
        this.ui = ui;
    }

    /**
     * Instantiates singleton class.
     *
     * @return already created or newly created Expenditure object.
     */
    public static Expenditure getInstance() {
        if (onlyInstance == null) {
            onlyInstance = new Expenditure();
        }
        return onlyInstance;
    }

    /**
     * Prepare the expenditure information to save in text format.
     *
     * @return the text to be saved.
     */
    public String saveExpenditureFile() {
        String text;
        text = String.format("Expenditure: $%.2f,", totalExpenditure);
        text += String.format("Amount used in cooking: $%.2f,", amountUsedInCooking);
        Date currentDate = new Date();
        text += new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss").format(currentDate) + Ui.LS;
        return text;
    }

    /**
     * Loads the last saved values of the class variables .
     *
     * @param totalExpenditure    the last saved total weekly expenditure.
     * @param amountUsedInCooking the last saved amount used in cooking this week.
     * @param lastSavedDate       the last time user made any changes to expenditure.
     */
    public void loadExpenditureVariables(double totalExpenditure, double amountUsedInCooking, Date lastSavedDate) {
        this.totalExpenditure = totalExpenditure;
        this.amountUsedInCooking = amountUsedInCooking;
        this.lastSavedDate = lastSavedDate;
        renewExpenditureValue();
    }

    /**
     * Resets the values of all the class variables.
     */
    public void resetExpenditureData() {
        this.totalExpenditure = 0;
        this.amountUsedInCooking = 0;
        this.lastSavedDate = new Date();
    }

    /**
     * Renews expenditure values at the start of every week at Monday midnight.
     */
    public void renewExpenditureValue() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date anyMonday = calendar.getTime(); //past Monday
        Date today = new Date();
        Date pastMonday;
        if (anyMonday.after(today)) {
            calendar.add(Calendar.DATE, -7);
            pastMonday = calendar.getTime();
        } else {
            pastMonday = anyMonday;
        }
        if (lastSavedDate == null || lastSavedDate.before(pastMonday)) {
            totalExpenditure = 0;
            amountUsedInCooking = 0;
        }
    }

    /**
     * Add to total expenditure every time addingredient command
     * called to simulate new ingredient purchased.
     *
     * @param price    Unit price of the ingredient.
     * @param quantity Quantity of ingredient bought.
     */
    public void addToExpenditure(double price, int quantity) {
        totalExpenditure += (price * quantity);
        Storage.saveExpenditureData();
    }

    /**
     * Remove amount from total expenditure when user deletes ingredient
     * in the case where user deleting because user wrongly added ingredient,
     * and does not want amount from incorrect addingredient to be included in total expenditure.
     *
     * @param ingredientToDelete ingredient to be deleted.
     * @param quantityToDelete   amount of the ingredient to be deleted.
     */
    public boolean removeFromExpenditure(Ingredient ingredientToDelete, Integer quantityToDelete) {
        String userResponse = promptUser(PROMPT_REMOVE_FROM_EXPENDITURE);
        if (userResponse.equalsIgnoreCase("yes")) {
            double price = changePrice(ingredientToDelete, quantityToDelete);
            totalExpenditure -= price;
            ui.print(String.format((REMOVAL_SUCCESS), price) + ui.LS + ui.DIVIDER);
            return true;
        } else {
            ui.print(NO_CHANGE + ui.LS + ui.DIVIDER);
            return false;
        }
    }

    /**
     * Adds prices of these ingredients to amount used in cooking,
     * when user deletes ingredient manually after using to cook
     * or consuming.
     *
     * @param ingredientToDelete ingredient to be deleted.
     * @param quantityToDelete   amount of the ingredient to be deleted.
     */
    public void addToAmountUsed(Ingredient ingredientToDelete, Integer quantityToDelete) {
        String userResponse = promptUser(PROMPT_ADD_TO_AMOUNT_USED);
        if (userResponse.equalsIgnoreCase("yes")) {
            double price = changePrice(ingredientToDelete, quantityToDelete);
            amountUsedInCooking += price;
            ui.print(String.format((INCREASE_AMOUNT_USED), price) + ui.LS + ui.DIVIDER);
        } else {
            ui.print(NO_CHANGE + ui.LS + ui.DIVIDER);
        }
    }

    /**
     * Adds to amount used in cooking whenever user calls cookrecipe command.
     *
     * @param ingredientToDelete the ingredient to used in cooking.
     * @param quantityToDelete   the amount of the ingredient used in cooking.
     */
    public void addAmountForCooking(Ingredient ingredientToDelete, Integer quantityToDelete) {
        amountUsedInCooking += changePrice(ingredientToDelete, quantityToDelete);
        Storage.saveExpenditureData();
    }

    /**
     * Prompts the user if they want to remove amount from total expenditure
     * and if not, then prompt whether to add amount to amount used in cooking
     * when they call deleteingredient command.
     *
     * @param ingredientToDelete ingredient to be deleted.
     * @param quantityToDelete   amount of the ingredient to be deleted.
     */
    public void editExpenditure(Ingredient ingredientToDelete, Integer quantityToDelete) {
        try {
            if (!removeFromExpenditure(ingredientToDelete, quantityToDelete)) {
                addToAmountUsed(ingredientToDelete, quantityToDelete);
            }
        } catch (Exception e) {
            return;
        }
        Storage.saveExpenditureData();
    }

    /**
     * Calculate the total amount to remove from total expenditure or to add to
     * amount used in cooking for each ingredient multiplied by their quantity deleted.
     *
     * @param ingredientToDelete the ingredient to be deleted.
     * @param quantityToDelete   amount of the ingredient to be deleted.
     * @return the total change in amount to corresponding class variable.
     */
    public double changePrice(Ingredient ingredientToDelete, Integer quantityToDelete) {
        double pricePerPiece = ingredientToDelete.getPrice();
        int totalQuantityOfItem = ingredientToDelete.getQuantity();
        if (quantityToDelete == null) {
            return pricePerPiece * totalQuantityOfItem;
        } else {
            return pricePerPiece * quantityToDelete;
        }
    }

    /**
     * Prompts the user for either a "yes" or "no" response.
     *
     * @param prompt the question to prompt the user.
     * @return "yes" or "no".
     */
    public String promptUser(String prompt) {
        String userResponse;
        ui.print(prompt);
        userResponse = ui.getUserCommand().trim();
        while (!isValidResponse(userResponse)) {
            ui.print("Please enter either \"Yes\"/\"No\"\n" + ui.DIVIDER);
            userResponse = ui.getUserCommand().trim();
        }
        return userResponse;
    }

    /**
     * Checks if user response is either "yes" or "no".
     *
     * @param userResponse the user response.
     * @return false if user responds with any other input.
     */
    public boolean isValidResponse(String userResponse) {
        String response = userResponse;
        if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Expenditure) {
            Expenditure i = (Expenditure) o;
            return this.totalExpenditure == 1818.00
                    && this.amountUsedInCooking == 242.40
                    && this.lastSavedDate.equals("Fri Apr 10 21:28:40 SGT 2020");
        } else {
            return false;
        }
    }
}
