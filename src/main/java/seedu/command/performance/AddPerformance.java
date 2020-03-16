package seedu.command.performance;

import seedu.command.Command;
import seedu.parser.PerformanceParser;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;

public class AddPerformance extends Command {
    Performance performance;
    public String commandParameters;

    /**
     * Constructor for AddPerformanceCommand. Takes String userInput
     * and parse it to get the Performance to be added.
     * @param commandParameters A String to be parsed.
     */
    public AddPerformance(String commandParameters) {
        this.commandParameters = commandParameters;
        performance = new PerformanceParser().parsePerformance(commandParameters);
    }

    /**
     * Add the performance to performance list.
     */
    public void addToList() {
        String eventName = performance.getEvent();
        PerformanceList performanceList = new PerformanceList();
        //TODO: change this list to the performance list under event input
        performanceList.addToList(performance, eventName);
    }

    @Override
    public void execute() {
        addToList();
    }
}