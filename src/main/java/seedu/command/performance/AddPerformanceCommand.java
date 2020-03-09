package seedu.command.performance;

import seedu.command.Command;
import seedu.module.performance.Performance;
import seedu.module.performance.PerformanceList;
import seedu.parser.Parser;
import seedu.parser.PerformanceDataParser;

import java.util.List;

public class AddPerformanceCommand extends Command {
    Performance performance;
    public String userInput;

    /**
     * Constructor for AddPerformanceCommand. Takes String userInput
     * and parse it to get the Performance to be added.
     * @param userInput A String to be parsed.
     */
    public AddPerformanceCommand(String userInput) {
        this.userInput = userInput;
        performance = new PerformanceDataParser(userInput).getPerformance();
        addToList();
    }


    /**
     * Determine whether the result of student is mark or grade and record the result,
     * add the performance to performance list.
     */
    public void addToList() {
        String grade;
        int mark;
        String[] dataToParser = Parser.performanceDataToParse(userInput);
        for (String s : dataToParser) {
            if (s != null) {
                String[] data = s.split("/");
                switch (data[0]) {
                case "g":
                    grade = data[1];
                    performance.recordGrade(grade);
                    PerformanceList.addToList(performance);
                    break;
                case "m":
                    mark = Integer.parseInt(data[1]);
                    performance.recordMark(mark);
                    PerformanceList.addToList(performance);
                    break;
                default:
                    PerformanceList.addToList(performance);
                    System.out.println("No grade or mark input.");
                }
            }
        }
    }
}