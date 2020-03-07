package seedu.duke;

import static seedu.duke.Parser.createStudent;
import static seedu.duke.Parser.getCommandWord;

public class AttendanceList {
    public static Command executeCommand(String userInput) throws DukeException {
        String commandWord = getCommandWord(userInput);
        switch (commandWord) {
            case "addAttendance":
                return new AddStudentCommand(createStudent(userInput));
            case "EXIT":
                return new ExitCommand();
            case "MARK":
                return new MarkAttendanceCommand(1);
            default:
                throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }
    }
}
