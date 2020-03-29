package seedu.storage;

import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class Storage {
    protected FileIO fileIO;

    /**
     * Creates a Storage object that saves to the specified directory.
     * Note that an EventList object has to be passed for each relevant Storage function.
     * @param directory the filepath to save to
     * @throws DukeException if cannot initialise filepath
     */
    public Storage(String directory) throws DukeException {
        this.fileIO = new FileIO(directory);
    }

    /**
     * Load all events to EventList.
     * @return an EventList object with all events loaded
     * @throws DukeException if EOF is encountered
     */
    public EventList loadAll() throws DukeException {
        EventList eventList = new EventList();
        String input = null;
        do {
            try {
                input = fileIO.readOneEvent();
                Event newEvent = Event.parseStorable(input);
                eventList.add(newEvent);
            } catch (DukeException m) {
                if (m.getMessage().equals("FileIO: nothing to read anymore")) {
                    break;
                }
            }
        } while (!input.isBlank());

        return eventList;
    }

    /**
     * Save all events to Storage.
     * @throws DukeException if IOException occurs
     */
    public void saveAll(EventList eventList) throws DukeException {
        for (Event event : eventList.list) {
            fileIO.write(event.toStorable());
        }
    }

    /**
     * Close every opened objects.
     * @throws DukeException if IOException occurs
     */
    public void close() throws DukeException {
        fileIO.close();
    }
}
