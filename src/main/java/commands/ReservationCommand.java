package commands;

import exceptions.ReservationException;
import reservation.ReservationList;
import ui.Ui;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static utils.Constants.LOG_FOLDER;

public abstract class ReservationCommand {
    private static final Logger LOGGER = Logger.getLogger(ReservationCommand.class.getName());
    private static final String FILE_PATH = LOG_FOLDER + "ReservationCommand.log";

    /**
     * Sets up the logger. 
     * Calls once at the start of the program.
     * 
     * @throws IOException When logger set up failed.
     */
    public static void setLogger() throws IOException {
        Locale.setDefault(Locale.UK);
        
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        FileHandler fileHandler = new FileHandler(FILE_PATH, true); // let it append
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setEncoding("UTF-8");
        LOGGER.addHandler(fileHandler);
    }
    
    public abstract void execute(ReservationList reservations, Ui ui);
    
    protected abstract void parseInput(String description) throws ReservationException;

    /**
     * Checks if there is another marker between the subcommand.
     * Checks for delimiter missing.
     * 
     * @param startPos Starting position after the original marker.
     * @param endPos Position of the delimiter.
     * @param markers Markers involved.
     * @param description Raw description of the command.
     * @return True if there is another marker, False otherwise.
     */
    protected boolean hasDelimiterInBetween(int startPos, int endPos, String[] markers, String description) {
        String targetSubstring = description.substring(startPos, endPos);
        for (String marker: markers) {
            if (targetSubstring.contains(marker)) {
                return true;
            }
        }
        return false;
    }
}
