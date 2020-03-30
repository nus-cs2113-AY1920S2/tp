package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.performance.AddPerformance;
import seedu.command.performance.DeletePerformance;
import seedu.command.performance.ListStudentPerformance;
import seedu.command.performance.SortPerformanceListByName;
import seedu.command.performance.SortPerformanceListByGrade;
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
        eventName = ui.getEventName(); // to know under which event the user want to edit the performance
        performances = getPerformances(eventName); //performance list to be edited
        switch (commandType) {
        case "add":
            return new AddPerformance(performances, eventName);
        case "delete":
            return new DeletePerformance(performances, eventName);
        case "list":
            return new ListStudentPerformance(performances);
        case "sort":
            return getSortCommand();
        default:
            throw new DukeException("Performance: Unknown command.");
        }
    }

    /**
     * This method determines the type of Sorting command the user want, by
     * getting a String input and parse it to return respective sorting Command.
     *
     * @return A Command with respect to the type of sorting command the user intend to execute.
     * @throws DukeException Throws DukeException when the user input a not recognizable sorting
     *                       command.
     */
    private Command getSortCommand() throws DukeException {
        String type = ui.getSortType();
        if (type.equals("name")) {
            return new SortPerformanceListByName(performances, eventName);
        } else if (type.equals("grade")) {
            return new SortPerformanceListByGrade(performances, eventName);
        }
        throw new DukeException("Performance sort: Unknown command. Enter 'name' or 'student'.");
    }

    /**
     * This get the performance list under the event input by user.
     * @return The PerformanceList under specific event.
     * @throws DukeException Throws DukeException when the event is
     *                       not found in the EventList.
     */
    private PerformanceList getPerformances(String eventName) throws DukeException {
        return eventList.getEvent(eventName).getPerformanceList();
    }
}
