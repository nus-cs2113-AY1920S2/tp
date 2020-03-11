package seedu.command.performance;

import seedu.command.Command;
import seedu.module.performance.Performance;
import seedu.module.performance.PerformanceList;
import seedu.parser.PerformanceDataParser;

import java.util.List;

public class DeletePerformanceCommand extends Command {
    Performance performance;
    public String userInput;

    /**
     * Constructor for DeletePerformanceCommand. Takes String userInput
     * and parse it to get the Performance to be deleted.
     * @param userInput A String to be parsed.
     */
    public DeletePerformanceCommand(String userInput) {
        this.userInput = userInput;
        performance = new PerformanceDataParser(userInput).getPerformance();
        PerformanceList.deletePerformance(performance);
    }
}