package commands;

import exceptions.ReservationException;
import reservation.ReservationList;
import ui.Ui;

public abstract class ReservationCommand {
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
