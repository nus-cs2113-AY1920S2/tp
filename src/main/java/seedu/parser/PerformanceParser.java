package seedu.parser;

import seedu.exception.DukeException;
import seedu.performance.Performance;

public class PerformanceParser {
    public PerformanceParser() {
    }

    /**
     * This is the parser for Performance. It gets the parameters from the user
     * and parse them to studentName and result, and create a new Performance
     * with the two data. It then returns the Performance created.
     *
     * @param commandParameters A String contains information of the Performance, to be parsed.
     * @return                  A Performance containing information parsed from commandParameters.
     * @throws DukeException    Throws DukeException when the commandParameters contains wrong
     *                          tokens or insufficient parameter.
     */
    public Performance parsePerformance(String commandParameters) throws DukeException {
        String[] dataToRead = commandParameters.split(" ", 5);
        String studentName = "";
        String result = "";
        for (String s : dataToRead) {
            if (s != null) {
                String[] data = s.split("/");
                if (data.length < 2) {
                    throw new DukeException("Insufficient parameter or wrong command.");
                }
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
