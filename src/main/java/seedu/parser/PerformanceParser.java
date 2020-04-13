package seedu.parser;

import seedu.exception.PacException;
import seedu.performance.Performance;

public class PerformanceParser {

    /**
     * This is the parser for Performance. It gets the parameters from the user
     * and parse them to studentName and result, and create a new Performance
     * with the two data. It then returns the Performance created.
     *
     * @param commandParameters A String contains information of the Performance, to be parsed.
     * @return                  A Performance containing information parsed from commandParameters.
     * @throws PacException    Throws PacException when the commandParameters contains wrong
     *                          tokens or insufficient parameter.
     */
    public Performance parsePerformance(String commandParameters) throws PacException {
        String[] dataToRead = commandParameters.split(" ", 5);
        String studentName = "";
        String result = "";
        for (String s : dataToRead) {
            if (s != null) {
                String[] data = s.split("/");
                if (data.length < 2) {
                    throw new PacException("Insufficient parameter or wrong command.");
                }
                switch (data[0]) {
                case "n":
                    studentName = data[1];
                    break;
                case "r":
                    result = data[1];
                    break;
                default:
                    throw new PacException("Wrong type of Performance data token.");
                }
            }
        }
        if (studentName.equals("") || result.equals("")) {
            throw new PacException("Insufficient variables to be saved as performance");
        }
        return new Performance(studentName, result);
    }
}
