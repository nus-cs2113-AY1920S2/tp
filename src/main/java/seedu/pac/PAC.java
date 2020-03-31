package seedu.pac;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import seedu.student.StudentListCollection;
import seedu.command.Bye;
import seedu.command.interpreter.CommandInterpreter;
import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.PACException;
import seedu.storage.Storage;
import seedu.ui.UI;

public class PAC {
    public static final Logger logger = Logger.getLogger(PAC.class.getName());

    protected UI ui;
    protected CommandInterpreter interpreter;
    protected EventList eventList;
    public static StudentListCollection studentListCollection;
    protected Storage eventStorage;
    protected Storage studentListCollectionStorage;

    public PAC() throws PACException {
        setupLogger();

        ui = new UI();

        eventStorage = new Storage("./data/eventlist.txt");
        eventList = eventStorage.loadEventList();
        if (eventList == null) {
            eventList = new EventList();
        }

        studentListCollectionStorage = new Storage("./data/studentlist.txt");
        studentListCollection = studentListCollectionStorage.loadStudentListCollection();
        if (studentListCollection == null) {
            studentListCollection = new StudentListCollection();
        }

        interpreter = new CommandInterpreter(eventList);
    }

    private void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL); // print ALL log
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);  // only print SEVERE log to console
        logger.addHandler(ch);
        try {
            FileHandler fh = new FileHandler("PAC.log", true);
            fh.setLevel(Level.FINE);    // print FINE log and more severe log to log file
            logger.addHandler(fh);
        } catch (IOException m) {
            logger.severe("File logger not working");
            System.out.println(m.getMessage());
        }
    }

    public void run() throws PACException {
        ui.setUserName();
        Command command = null;
        do {
            ui.readUserInput();
            try {
                String input = ui.getUserInput().trim();
                if (input.isEmpty()) {
                    throw new PACException("Please provide a valid command.");
                }

                command = interpreter.decideCommand(input);
                command.execute();
            } catch (PACException m) {
                logger.log(Level.WARNING, "PACException at PAC.run()");
            }
        } while (isNotBye(command));

        eventStorage.saveEventList(eventList);
        eventStorage.close();

        studentListCollectionStorage.saveStudentListCollection(studentListCollection);
        studentListCollectionStorage.close();

        ui.close();
    }

    boolean isNotBye(Command command) {
        return !(command instanceof Bye);
    }

    /**
     * Main entry-point for the java.PAC.PAC application.
     */
    public static void main(String[] args) {
        try {
            PAC pac = new PAC();
            pac.run();
        } catch (PACException m) {
            UI.display(m.getMessage());
        }
    }
}
