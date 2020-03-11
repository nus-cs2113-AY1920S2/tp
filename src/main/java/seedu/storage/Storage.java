package seedu.storage;

import seedu.event.Event;
import seedu.exception.DukeException;
import seedu.parser.ParserStorage;
import seedu.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private ArrayList<Event> events;
    private Ui ui;

    /**
     * Constructs a Storage object that contains duke.tasks and duke.storage related operations.
     * Mainly save duke.tasks and get duke.tasks.
     *
     * @param filePath The filepath to the txt file.
     * @param ui The user interface displaying events on the task list.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
        read();
    }

    /**
     * Reads duke.tasks from filepath. Creates empty duke.tasks if file cannot be read.
     */
    private void read() {
        ArrayList<Event> newEvents = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            try {
                s = new Scanner(f);
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }
            while (s.hasNext()) {
                newEvents.add(ParserStorage.createTaskFromStorage(s.nextLine()));
            }
            s.close();
        } catch (DukeException | FileNotFoundException e) {
            Ui.displayError("FILE_NOT_FOUND");
            Ui.displayError("NEW_FILE_CREATED");
        }
        events = newEvents;
    }

    /**
     * Writes the duke.tasks into a file of the given filepath.
     */
    public void write() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Event event : events) {
                writer.write(ParserStorage.toStorageString(event) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Ui.displayError("FILE_NOT_SAVE");
        }
    }

    /**
     * Retrieves the existing tasks.
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

}
