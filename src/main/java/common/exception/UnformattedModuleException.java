package common.exception;

/**
 * Exception is thrown whenever a particular module a student is taking
 * doesn't follow the conventional JSON format pulled from nusmods API.
 */
public class UnformattedModuleException extends Exception {

    public UnformattedModuleException(String s) {
        super(s);
    }
}
