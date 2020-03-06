package seedu.duke;

import java.io.Serializable;
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
        String[] dataToParse = Arrays.copyOfRange(instructions, 1, instructions.length);
        //input instructions = a/Assignment n/STUDENT_NAME g/GRADE m/MARK
        switch (performanceInstruction) {
        case "add":
            parseAddPerformanceResult(dataToParse);
            break;
        case "delete":
            parseDeleteResult(dataToParse);
            break;
        case "view_student_result":
            ViewStudentResultCommand.execute(dataToParse);
            break;
        case "view_assignment_result":
            ViewAssignmentResultCommand.execute(dataToParse);
            break;
        default:
            System.out.println("wrong command");
        }
    }

    /**
     * Parser for the add performance result command. To record
     * the data base on its index and pass to AddPerformanceCommand
     * to add the result to performance set of module, assignment and
     * student.
     * @param dataToParse The string array to be parsed, each string
     *                    in the array is parsed by the character "/",
     *                    and store to String data[], where data[0]
     *                    determines which type of data that data[1]
     *                    belongs to.
     */
    private void parseAddPerformanceResult(String[] dataToParse) {
        //e.g. dataToParse = a/Assignment n/STUDENT_NAME [g/GRADE or m/MARK]
        String nameOfModule = "null";
        String nameOfStudent = "null";
        String assignment = "null";
        String grade = "null";
        int mark = -1;
        boolean isMark = false;
        boolean isGrade = false;
        for (String s : dataToParse) {
            String[] data = s.split("/");
            switch (data[0]) {
            case "a":
                assignment = data[1];
                break;
            case "c":
                nameOfModule = data[1];
                break;
            case "n":
                nameOfStudent = data[1];
                break;
            case "g":
                grade = data[1];
                isMark = true;
                break;
            case "m":
                mark = Integer.getInteger(data[1]);
                isGrade = true;
                break;
            default:
                System.out.println("Wrong data");
            }
        }
        if (isMark) {
            AddPerformanceCommand.addMark(nameOfModule, nameOfStudent, assignment, mark);
        } else if (isGrade) {
            AddPerformanceCommand.addGrade(nameOfModule, nameOfStudent, assignment, grade);
        }
    }

    /**
     * Parser for the delete performance result command. To record
     * the performance set of module, assignment and student to
     * be deleted.
     *
     * @param dataToParse The string array to be parsed, each string
     *                    in the array is parsed by the character "/",
     *                    and store to String data[], where data[0]
     *                    determines which type of data that data[1]
     *                    belongs to.
     */
    private void parseDeleteResult(String[] dataToParse) {
        //e.g. dataToParse = a/Assignment n/STUDENT_NAME
        String nameOfModule = "null";
        String nameOfStudent = "null";
        String assignment = "null";
        for (String s : dataToParse) {
            String[] data = s.split("/");
            switch (data[0]) {
            case "a":
                assignment = data[1];
                break;
            case "c":
                nameOfModule = data[1];
                break;
            case "n":
                nameOfStudent = data[1];
                break;
            default:
                System.out.println("Wrong data");
            }
        }
        DeletePerformanceCommand.execute(nameOfModule, assignment, nameOfStudent);
    }

    private void parseAttendanceInstruction(String[] instructions) {
    }

    private void parseTaskInstruction(String[] instructions) {
    }
}
