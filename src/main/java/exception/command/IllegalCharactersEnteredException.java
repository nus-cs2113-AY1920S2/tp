package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 *  Exception is thrown if the user entered an illegal character.
 */
public class IllegalCharactersEnteredException extends CustomException {

    public static final String ILLEGAL_CHARACTERS_ENTERED = "Illegal character(s) entered (e.g. F6, Ctrl + Z ). "
            + "Please re-enter previous line.";

    public IllegalCharactersEnteredException() {
        super(ILLEGAL_CHARACTERS_ENTERED);
    }
}