package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if an empty string is used to match with the description of a task during search.
 */
public class SearchKeywordEmptyException extends CustomException {

    public static final String KEYWORD_TO_BE_USED_FOR_SEARCH_IS_EMPTY = "Keyword to be used for search is empty";

    public SearchKeywordEmptyException() {
        super(KEYWORD_TO_BE_USED_FOR_SEARCH_IS_EMPTY);
    }
}