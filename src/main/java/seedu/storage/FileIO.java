package seedu.storage;

import seedu.exception.DukeException;
import seedu.ui.UI;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    private FileReader fileToReadFrom;
    private FileWriter fileToWriteTo;

    public FileIO(String directory) throws DukeException {
        File f = open(directory);

        try {
            this.fileToReadFrom = new FileReader(f);
            this.fileToWriteTo = new FileWriter(f);
        } catch (IOException e) {
            throw new DukeException("FileIO: cannot initialise file");
        }
    }

    protected File open(String directory) throws DukeException {
        File f = new File(directory);
        ensurePathExist(f);
        return f;
    }

    /**
     * Checks if the file denoted by this abstract pathname exists.
     * If it does not exist, create directories until that path.
     * @param f the abstract pathname
     */
    private void ensurePathExist(File f) throws DukeException {
        if (!f.exists()) {
            UI.display("Storage file not found.");
            try {
                new File(f.getParent()).mkdir();    // mkdir
                f.createNewFile();
                UI.display("A storage file is created.");
            } catch (IOException m) {
                throw new DukeException("FileIO: creating file that already exists");
            }
        }
    }
}
