package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.parser.PerformanceParser;

public class DeletePerformance extends Command {
    Performance performance;
    public String userInput;

    /**
     * Constructor for DeletePerformanceCommand. Takes String userInput
     * and parse it to get the Performance to be deleted.
     * @param commandParameters A String to be parsed.
     * @throws DukeException throws DukeException when there is insufficient parameter
     */
    public DeletePerformance(String commandParameters) throws DukeException {
        this.userInput = commandParameters;
        performance = new PerformanceParser().parsePerformance(commandParameters);

    }

    @Override
    public void execute() {
        PerformanceList.deletePerformance(performance);
    }

}