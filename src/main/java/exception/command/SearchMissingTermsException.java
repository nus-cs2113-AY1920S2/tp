package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user enters an empty string as the search keyword.
 */
public class SearchMissingTermsException extends CustomException {

    public static final String THE_SEARCH_STRING_ENTERED_IS_EMPTY = "The search string entered is empty.";

    public SearchMissingTermsException() {
        super(THE_SEARCH_STRING_ENTERED_IS_EMPTY);
    }
}