package seedu.kitchenhelper.common;

import seedu.kitchenhelper.ui.Ui;

/**
 * Container for user visible messages.
 */
public class Messages {
    
    // Common
    public static final int NEGATIVE_ONE = -1;
    
    // SearchCommand Common Message
    public static final String MESSAGE_SEARCH_EMPTY_LIST = "There are no matching %s in your list.";
    public static final String MESSAGE_SEARCH_NON_EMPTY_LIST = "Here are your matching %s in your list";
    public static final String MESSAGE_SEARCH_EMPTY_STRING = "No keyword input detected.\nEnter a keyword.";
    
    public static final String EMPTY_STRING = "";
    public static final String NUMBER_FORMAT = "%d.";
    public static final String SEARCH_INDEX = " [Location: Index %d]";
    
    public static final String MESSAGE_SEARCH_LOG_INFO = "Entering execution of finding matching %s";
    public static final String MESSAGE_SEARCH_LOG_INFO_EMPTY = "Has non-matching %s";
    public static final String MESSAGE_SEARCH_LOG_INFO_FOUND = "Found matching %s";
    
    // AddIngredient Command Message
    public static final String MESSAGE_EXPIRED_INGREDIENT =
            "Expired ingredient detected in input." + Ui.LS + "Please enter a non-expired expiry date.";
    public static final String MESSAGE_ZERO_QUANTITY = "Please enter a quantity more than 0.";
    public static final String MESSAGE_COMBINE_INGREDIENT =
            "Kitchen Helper has updated the quantity of %s to %d from %d";
    public static final String MESSAGE_ADD_INGREDIENT_LOG = "A new ingredient has been added";
    public static final String MESSAGE_INVALID_DATE = "Invalid date entered.";
    public static final String MESSAGE_ADD_INGREDIENT_SUCCESS =
            "KitchenHelper has added: Ingredient:%s Category:%s Quantity:%d Price:$%.2f Expiry:%s";

    //AddChore Command Message
    public static final String MESSAGE_OVERDUE_CHORE = "The deadline entered has already past."
            + Ui.LS + "Please enter a future date.";
}
