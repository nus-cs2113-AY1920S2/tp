package seedu.duke;

import seedu.command.CommandInterpreter;
import seedu.command.Command;
import seedu.event.EventList;
import seedu.io.IO;
import seedu.exception.DukeException;

public class Duke {
    protected IO io;    //TODO: change from io to ui
    protected CommandInterpreter interpreter;
    protected EventList eventList;

    public Duke() {
        io = new IO();  //TODO: change from io to ui
        eventList = new EventList();  //TODO: new Storage().load()
        interpreter = new CommandInterpreter(eventList);
    }

    public void run() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Command command;
        io.readUserInput(); //TODO: change from io to ui
        try {
            command = interpreter.decideCommand(io.getUserInput());  //TODO: change to ui.getUserInput()
            command.execute();
        } catch (DukeException m) {
            System.out.println(m);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
