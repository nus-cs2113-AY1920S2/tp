package seedu.command.interpreter;

import seedu.command.Bye;
import seedu.command.Command;
import seedu.command.Help;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class CommandInterpreter {
    protected EventList eventList;

    public CommandInterpreter(EventList eventList) {
        this.eventList = eventList;
    }

    /**
     * Returns the first word in lower cases.
     * @param userInput raw user input
     * @return the first word in lower cases
     */
    protected String getFirstWord(String userInput) {
        String commandType = userInput.split(" ")[0];
        commandType = commandType.trim();
        commandType = commandType.toLowerCase();
        return commandType;
    }

    /**
     * Returns the 2nd to last words.
     * @param userInput raw user input
     * @return the 2nd to last words
     * @throws DukeException if there is only 1 word from the input
     */
    protected String getSubsequentWords(String userInput) throws DukeException {
        userInput = userInput.trim();
        int startIndexOfSpace = userInput.indexOf(" ");

        if (startIndexOfSpace == -1) {
            throw new DukeException("Argument is required for command '" + userInput + "'");
        }

        int startIndexOfParameter = startIndexOfSpace + 1;
        return userInput.substring(startIndexOfParameter);
    }

    /**
     * Decide the command from userInput.
     *
     * @param userInput The userInput from the Ui.
     * @throws DukeException If the command is undefined.
     */
    public Command decideCommand(String userInput) throws DukeException {
        Command command;

        String commandCategory = getFirstWord(userInput);
        String commandDescription = "";
        // only look for 2nd to last words if commandCategory requires.
        if (!commandCategory.equals("bye")) {
            commandDescription = getSubsequentWords(userInput);
        }

        switch (commandCategory) {
        case "bye":
            command = new Bye();
            break;
        case "event":
            EventCommandInterpreter eci = new EventCommandInterpreter(eventList);
            command = eci.decideCommand(commandDescription);
            break;
        case "seminar":
            SeminarCommandInterpreter sci = new SeminarCommandInterpreter(eventList);
            command = sci.decideCommand(commandDescription);
            break;
        case "attendance":
            AttendanceCommandInterpreter aci = new AttendanceCommandInterpreter(eventList);
            command = aci.decideCommand(commandDescription);
            break;
        case "performance":
            PerformanceCommandInterpreter pci = new PerformanceCommandInterpreter(eventList);
            command = pci.decideCommand(commandDescription);
            break;
        case "student":
            StudentCommandInterpreter ssci = new StudentCommandInterpreter(eventList);
            command = ssci.decideCommand(commandDescription);
            break;
        case "help":
            return new Help();
        case "calendar":
            CalendarCommandInterpreter cci = new CalendarCommandInterpreter(eventList);
            command = cci.decideCommand(commandDescription);
            break;
        default:
            assert (!commandCategory.equals("bye") && !commandCategory.equals("event")
                    && !commandCategory.equals("seminar") && !commandCategory.equals("attendance")
                    && !commandCategory.equals("performance") && !commandCategory.equals("calendar"))
                    : "accepted command category is not further interpreted!";
            throw new DukeException("Unknown command category is provided");
        }
        if (command == null) {
            throw new DukeException("Duke is null.");
        }
        return command;
    }
}

