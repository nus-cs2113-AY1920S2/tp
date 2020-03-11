package seedu.storage;

import seedu.event.Event;
import seedu.exception.DukeException;
import seedu.attendance.Attendance;
import seedu.parser.ParserStorage;
import seedu.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private ArrayList<Event> events;
    private ArrayList<Attendance> attendances;
    private UI ui;

    /**
     * Constructs a Storage object that contains duke.tasks and duke.storage related operations.
     * Mainly save duke.tasks and get duke.tasks.
     *
     * @param filePath The filepath to the txt file.
     * @param ui The user interface displaying events on the task list.
     */
    public Storage(String filePath, UI ui) {
        this.filePath = filePath;
        this.ui = ui;
        readEvent();
        readAttendance();
    }

    /**
     * Reads events.tasks from filepath. Creates empty duke.tasks if file cannot be read.
     */
    private void readEvent() {
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
                newEvents.add(ParserStorage.createTaskFromStorageEvent(s.nextLine()));
            }
            s.close();
        } catch (DukeException | FileNotFoundException e) {
            UI.display("FILE_NOT_FOUND");
            UI.display("NEW_FILE_CREATED");
        }
        events = newEvents;
    }

    /**
     * Reads attendances.tasks from filepath. Creates empty duke.tasks if file cannot be read.
     */
    private void readAttendance() {
        ArrayList<Attendance> newAttendance = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            try {
                s = new Scanner(f);
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }
            while (s.hasNext()) {
                newAttendance.add(ParserStorage.createTaskFromStorageAttendance(s.nextLine()));
            }
            s.close();
        } catch (DukeException | FileNotFoundException e) {
            UI.display("FILE_NOT_FOUND");
            UI.display("NEW_FILE_CREATED");
        }
        attendances = newAttendance;
    }

    /**
     * Writes the duke.events into a file of the given filepath.
     */
    public void writeEvent() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Event event : events) {
                writer.write(ParserStorage.toStorageStringEvent(event) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            UI.display("FILE_NOT_SAVE");
        }
    }

    /**
     * Writes the duke.events into a file of the given filepath.
     */
    public void writeAttendance() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Attendance attendance : attendances) {
                writer.write(ParserStorage.toStorageStringAttendance(attendance) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            UI.display("FILE_NOT_SAVE");
        }
    }

    /**
     * Retrieves the existing events.
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Retrieves the existing attendances.
     */
    public ArrayList<Attendance> getAttendance() {
        return attendances;
    }



}
