package seedu.duke;

import seedu.commands.Command;
import seedu.exception.EscException;
import seedu.parser.Parser;
import seedu.subjects.SubjectList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static UI ui = new UI();
    private Storage storage = new Storage();
    private SubjectList subjectList;

    /**
     * Reads the user's commands and executes them until the user issues the exit command.
     */
    private void run() throws EscException {
        ui.showWelcome();
        boolean isExit = false;
        subjectList = new SubjectList(storage.loadObjects());
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(subjectList);
                storage.saveSubs(subjectList.getSubjects(), subjectList.getEventList().getEvents());
                isExit = c.isExit();
            } catch (EscException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws EscException {
        //assert false : "dummy to fail";
        new Duke().run();
    }
}