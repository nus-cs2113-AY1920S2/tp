package seedu.exception;

import java.lang.reflect.Executable;

public class CommandExceptions extends Exception {

    public static class FindEmptyPatternException extends Exception {

        public FindEmptyPatternException() {
            super();
        }

    }

    public static class TaskOutOfBoundsException extends Exception {

        public TaskOutOfBoundsException() {
            super();
        }
    }

    public static class EmptyTaskListException extends Exception {

        public EmptyTaskListException() {
            super();
        }
    }
}
