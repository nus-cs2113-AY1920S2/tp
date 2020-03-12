package seedu.parser;

import seedu.performance.Performance;

import java.util.Arrays;

public class PerformanceDataParser {
    private String userInput;

    public PerformanceDataParser(String userInput) {
        this.userInput = userInput;
    }

    public static String[] performanceDataToParse(String userInput) {
        String[] instructions = userInput.split(" ",20);
        return Arrays.copyOfRange(instructions, 1, instructions.length);
    }

    public Performance getPerformance() {
        String[] dataToRead = performanceDataToParse(userInput);
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
        return new Performance(nameOfModule, nameOfStudent, assignment);
    }
}
