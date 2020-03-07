package seedu.duke;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<Performance> performances;
    /**
     * The user input typically include three categories,
     * the command type, command, and data. There is one
     * parser for each of the category respectively.
     */

    public Parser() {
        performances = new PerformanceList().getPerformanceList();
    }

    /**
     * Main parser for command types, it parses the command to its
     * corresponding type of command: Task, Attendance, or Performance.
     *
     * @param instruction A string input command by user, to be parsed
     *                    in the method.
     */
    public void parseTypeOfInstruction(String instruction) {
        String[] instructions = instruction.split(" ",10);
        String typeOfInstruction = instructions[0];
        instructions = Arrays.copyOfRange(instructions, 1, instructions.length);
        switch (typeOfInstruction) {
        case "task":
            System.out.println("do task parse");
            break;
        case "attendance":
            System.out.println("do attendance parse");
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
            Performance performance = getPerformance(dataToParse);
            performances = DeletePerformanceCommand.execute(performances, performance);
            break;
        case "view_student_result":
            ViewResultCommand.studentPerformanceList(performances);
            break;
        case "view_assignment_result":
            ViewResultCommand.assignmentPerformanceList(performances);
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
        int mark = 999;
        boolean isMark = false;
        boolean isGrade = false;
        for (String s : dataToParse) {
            if (s != null) {
                String[] data = s.split("/");
                System.out.println(Arrays.toString(data));
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
                    isGrade = true;
                    break;
                case "m":
                    mark = Integer.parseInt(data[1]);
                    isMark = true;
                    break;
                default:
                    System.out.println("Wrong data");
                }
            }
        }
        System.out.println(nameOfModule+ nameOfStudent+ assignment+grade+mark);
        Performance performance = new Performance(nameOfModule, nameOfStudent, assignment);
        if (isMark) {
            performances = AddPerformanceCommand.addMark(performances, performance, mark);
        } else if (isGrade) {
            performances = AddPerformanceCommand.addGrade(performances, performance, grade);
        }
    }

    /**
     * Read the user input to get the performance data. It returns
     * the performance set of module, assignment and student for
     * further instructions.
     *
     * @param dataToRead The string array to be parsed, each string
     *                    in the array is parsed by the character "/",
     *                    and store to String data[], where data[0]
     *                    determines which type of data that data[1]
     *                    belongs to.
     * @return The performance result after reading, to be used in
     *         further instructions.
     */
    private Performance getPerformance(String[] dataToRead) {
        //e.g. dataToParse = a/Assignment n/STUDENT_NAME
        String nameOfModule = "null";
        String nameOfStudent = "null";
        String assignment = "null";
        for (String s : dataToRead) {
            if (s != null) {
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
        }
        System.out.println("performance = " + nameOfModule+ nameOfStudent+ assignment);
        return new Performance(nameOfModule, nameOfStudent, assignment);
    }
}
