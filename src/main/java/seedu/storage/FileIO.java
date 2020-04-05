package seedu.storage;

import seedu.exception.PacException;
import seedu.ui.UI;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    private FileReader fileToReadFrom;
    private FileWriter fileToWriteTo;
    private Scanner scanner;
    private File file;

    /**
     * Open files to be modified, and scanner to read from file.
     * @param directory the file to be opened
     * @throws PacException if cannot initialise file
     */
    public FileIO(String directory) throws PacException {
        this.file = open(directory);

        try {
            this.fileToReadFrom = new FileReader(file);
        } catch (IOException e) {
            throw new PacException("FileIO: cannot initialise file");
        }

        this.scanner = new Scanner(fileToReadFrom);
    }

    /**
     * Opens a file, creating its relevant directory if necessary.
     * @param directory the abstract pathname of the file
     * @return a File object pointing to the file
     * @throws PacException if directory already exists while creating it
     */
    protected File open(String directory) throws PacException {
        File f = new File(directory);
        ensurePathExist(f);
        return f;
    }

    /**
     * Checks if the file denoted by this abstract pathname exists.
     * If it does not exist, create directories until that path.
     * Prints messages to show what it does.
     * @param f the abstract pathname
     * @throws PacException if directory already exists while creating it
     */
    private void ensurePathExist(File f) throws PacException {
        String output = "Storage file (" + f.getPath() + ") is ";

        if (!f.exists()) {
            output += "not found... ";
            try {
                new File(f.getParent()).mkdir();    // mkdir
                f.createNewFile();
                output += "created.";
            } catch (IOException m) {
                throw new PacException("FileIO: creating file that already exists");
            }
        } else {
            output += "found...";
        }

        UI.display(output);
    }

    /**
     * Read a line from current file.
     * @return the next line
     * @throws PacException if EOF is encountered
     */
    public String read() throws PacException {
        if (!scanner.hasNext()) {
            throw new PacException("FileIO: nothing to read anymore");
        }

        return scanner.nextLine();
    }

    /**
     * Write a string from start of file, replacing the content of file.
     * @param input the string to be written
     * @throws PacException if {@code IOException} occurs
     */
    public void write(String input) throws PacException {
        try {
            if (this.fileToWriteTo == null) {
                this.fileToWriteTo = new FileWriter(file);
            }
            fileToWriteTo.write(input);
        } catch (IOException m) {
            throw new PacException("FileIO.write: " + m.getMessage());
        }
    }

    /**
     * Close everything in FileIO.
     * @throws PacException if IOException occurs
     */
    public void close() throws PacException {
        try {
            if (this.fileToWriteTo != null) {
                this.fileToWriteTo.close();
            }
            this.fileToReadFrom.close();
            this.scanner.close();
        } catch (IOException m) {
            throw new PacException("FileIO.close: " + m.getMessage());
        }
    }
}
