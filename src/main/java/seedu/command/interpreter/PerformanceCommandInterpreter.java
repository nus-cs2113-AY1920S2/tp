package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.performance.AddPerformanceList;
import seedu.command.performance.DeletePerformanceList;
import seedu.command.performance.EditPerformanceList;
import seedu.command.performance.ViewStudentPerformanceList;
import seedu.command.performance.SortPerformanceListByName;
import seedu.command.performance.SortPerformanceListByResult;
import seedu.event.EventList;
import seedu.exception.PacException;
import seedu.performance.PerformanceList;
import seedu.ui.UI;


public class PerformanceCommandInterpreter extends CommandInterpreter {
    PerformanceList performanceList;
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
     * @throws PacException If the command is undefined.
     */
    public Command decideCommand(String commandDescription) throws PacException {
        String commandType = getFirstWord(commandDescription);
        eventName = ui.getEventName(); // to know under which event the user want to edit the performance
        performanceList = getPerformanceList(eventName); //performance list to be edited
        switch (commandType) {
        case "add":
            return new AddPerformanceList(performanceList, eventName);
        case "delete":
            return new DeletePerformanceList(performanceList, eventName);
        case "edit":
            return new EditPerformanceList(performanceList, eventName);
        case "view":
            return new ViewStudentPerformanceList(performanceList);
        case "sort":
            return getSortCommand();
        default:
            throw new PacException("Performance: Unknown command.");
        }
    }

    /**
     * This method determines the type of Sorting command the user want, by
     * getting a String input and parse it to return respective sorting Command.
     *
     * @return A Command with respect to the type of sorting command the user intend to execute.
     * @throws PacException Throws PacException when the user input a not recognizable sorting
     *                       command.
     */
    private Command getSortCommand() throws PacException {
        String type = ui.getSortType();
        if (type.equals("name")) {
            return new SortPerformanceListByName(performanceList, eventName);
        } else if (type.equals("result")) {
            return new SortPerformanceListByResult(performanceList, eventName);
        }
        throw new PacException("Performance sort: Unknown sort type. Enter 'name' or 'student'.");
    }

    /**
     * This get the performance list under the event input by user.
     * @return The PerformanceList under specific event.
     * @throws PacException Throws PacException when the event is
     *                       not found in the EventList.
     */
    private PerformanceList getPerformanceList(String eventName) throws PacException {
        return eventList.getEvent(eventName).getPerformanceList();
    }
}
