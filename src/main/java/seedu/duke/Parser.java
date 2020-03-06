package seedu.duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
    /**
     * The user input typically include three categories,
     * the command type, command, and data. There is one
     * parser for each of the category respectively.
     */
    public Parser() {
    }

    /**
     * Main parser for command types, it parses the command to its
     * corresponding type of command: Task, Attendance, or Performance.
     *
     * @param instruction A string input command by user, to be parsed
     *                    in the method.
     */
    public void parseTypeOfInstruction(String instruction) {
        String[] instructions = instruction.split(" ");
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
            System.out.println("wrong command type");
        }
    }

    /**
     * Main parser for the performance command, it parses the command to
     * the specific action to be done under the performance category.
     *
     * @param instructions A string input performance command by user,
     *                     to be parsed in the method.
     */
    private void parsePerformanceInstruction(String[] instructions) {
        /* e.g. initial instruction = result add a/Assignment n/STUDENT_NAME g/GRADE m/MARK
         *      input instructions = add a/Assignment n/STUDENT_NAME g/GRADE m/MARK
         */
        String performanceInstruction = instructions[0];
        String[] data = Arrays.copyOfRange(instructions, 1, instructions.length);
        //input instructions = a/Assignment n/STUDENT_NAME g/GRADE m/MARK
        switch (performanceInstruction) {
        case "add":
            AddPerformanceCommand.execute(data);
            break;
        case "delete":
            DeletePerformanceCommand.execute(data);
            break;
        case "view_student_result":
            ViewStudentResultCommand.execute(data);
            break;
        case "view_assignment_result":
            ViewAssignmentResultCommand.execute(data);
            break;
        default:
            System.out.println("wrong command");
        }

    }

    private void parseAttendanceInstruction(String[] instructions) {
    }

    private void parseTaskInstruction(String[] instructions) {
    }
}
