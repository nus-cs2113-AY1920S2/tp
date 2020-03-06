package seedu.duke;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Parser {
    /**
     * Main parser for user commands, it parses the command to its
     * corresponding type of command: Task, Attendance, or Performance.
     *
     * @param instruction A string input command by user, to be parsed
     *                    in the method.
     */
    public void parseTypeOfInstruction(String instruction) {
        String[] instructions = instruction.split(Pattern.quote(" "));
        String typeOfInstruction = instructions[0];
        instructions = Arrays.copyOfRange(instructions, 1, instructions.length);
        switch (typeOfInstruction) {
        case "task":
            parseTaskInstruction(instructions);
            break;
        case "attendance":
            parseAttendanceInstruction(instructions);
            break;
        case "performance":
            parsePerformanceInstruction(instructions);
            break;
        default:
            System.out.println("error");
        }
    }

    private void parsePerformanceInstruction(String[] instructions) {
    }

    private void parseAttendanceInstruction(String[] instructions) {
    }

    private void parseTaskInstruction(String[] instructions) {
    }
}
