package seedu.command.performance;

import seedu.command.Command;
import seedu.parser.PerformanceDataParser;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;

public class AddPerformance extends Command {
    Performance performance;
    public String userInput;

    /**
     * Constructor for AddPerformanceCommand. Takes String userInput
     * and parse it to get the Performance to be added.
     * @param userInput A String to be parsed.
     */
    public AddPerformance(String userInput) {
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
        String eventName = performance.getEvent();
        String[] dataToParser = PerformanceDataParser.performanceDataToParse(userInput);
        for (String s : dataToParser) {
            if (s != null) {
                String[] data = s.split("/");
                switch (data[0]) {
                case "g":
                    grade = data[1];
                    performance.recordGrade(grade);
                    PerformanceList.addToList(performance, eventName);
                    break;
                case "m":
                    mark = Integer.parseInt(data[1]);
                    performance.recordMark(mark);
                    PerformanceList.addToList(performance, eventName);
                    break;
                default:
                    PerformanceList.addToList(performance, eventName);
                    System.out.println("No grade or mark input.");
                }
            }
        }
    }

    public void execute() {
    }
}