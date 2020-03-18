package seedu.parser;

import seedu.exception.DukeException;
import seedu.performance.Performance;

public class PerformanceParser {
    public PerformanceParser() {
    }

    public Performance parsePerformance(String commandParameters) throws DukeException {
        String[] dataToRead = commandParameters.split(" ", 5);
        String studentName = "";
        String result = "";
        for (String s : dataToRead) {
            if (s != null) {
                String[] data = s.split("/");
                switch (data[0]) {
                case "n":
                    studentName = data[1];
                    break;
                case "r":
                    result = data[1];
                    break;
                default:
                    throw new DukeException("Wrong type of Performance data token.");
                }
            }
        }
        if (studentName.equals("") || result.equals("")) {
            throw new DukeException("Insufficient variables to be saved as performance");
        }
        return new Performance(studentName, result);
    }
}
