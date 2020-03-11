package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.parser.PerformanceDataParser;
import seedu.storage.Storage;
import seedu.ui.Ui;

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

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {

    }
}