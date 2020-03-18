package seedu.command;

import seedu.command.performance.AddPerformance;
import seedu.command.performance.DeletePerformance;
import seedu.command.performance.ListStudentPerformance;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.performance.PerformanceList;
import seedu.ui.UI;


public class PerformanceCommandInterpreter extends CommandInterpreter {
    PerformanceList performances;
    String eventName;
    UI ui;

    public PerformanceCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    /**
     * Execute the command from userInput.
     *
     * @param commandDescription The userInput from the Ui.
     * @throws DukeException If the command is undefined.
     */
    public Command decideCommand(String commandDescription) throws DukeException {
        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        performances = getPerformances();
        switch (commandType) {
        case "add":
            return new AddPerformance(performances, eventName);
        case "delete":
            return new DeletePerformance(performances, eventName);
        case "list":
            return new ListStudentPerformance(performances);
        default:
            throw new DukeException("Performance: Unknown command.");
        }
    }

    private PerformanceList getPerformances() throws DukeException {
        eventName = ui.getEventName();
        return eventList.getEvent(eventName).getPerformanceList();
    }
}
