package seedu.parser;

import seedu.module.performance.Performance;

public class PerformanceDataParser {

    private String userInput;
    public PerformanceDataParser(String userInput) {
        this.userInput = userInput;
    }

    public Performance getPerformance() {
        String[] dataToRead = Parser.performanceDataToParse(userInput);
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
