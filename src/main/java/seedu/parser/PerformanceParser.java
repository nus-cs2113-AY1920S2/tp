package seedu.parser;

import seedu.exception.DukeException;
import seedu.performance.Performance;
import seedu.ui.UI;

import java.util.Arrays;

public class PerformanceParser {
    int mark = -1;
    String grade = null;

    public PerformanceParser() {
    }

    public Performance parsePerformance(String commandParameters) throws DukeException {
        String[] dataToRead = commandParameters.split(" ", 5);
        String eventName = "";
        String studentName = "";
        String assignment = "";
        int r = 0;
        for (String s : dataToRead) {
            if (s != null) {
                String[] data = s.split("/");
                switch (data[0]) {
                case "a":
                    assignment = data[1];
                    break;
                case "e":
                    eventName = data[1];
                    break;
                case "n":
                    studentName = data[1];
                    break;
                case "g":
                    grade = data[1];
                    r = 1;
                    break;
                case "m":
                    mark = Integer.parseInt(data[1]);
                    r = 2;
                    break;
                default:
                    UI.printWrongInput(s);
                }
            }
        }
        if (eventName.equals("") || studentName.equals("") || assignment.equals("")) {
            throw new DukeException("Insufficient variables");
        }
        Performance performance = new Performance(eventName, studentName, assignment);
        setResult(performance, r);
        return performance;
    }
    
    private void setResult(Performance performance, int r) {
        if (r == 0) {
        } else if (r == 1) {
            performance.setGrade(grade);
        } else if (r == 2) {
            performance.setMark(mark);
        }
        assert false;
    }
}
