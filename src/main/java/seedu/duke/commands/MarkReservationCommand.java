package seedu.duke.commands;

import seedu.duke.reservation.ReservationList;

import static seedu.duke.utils.Constants.DELIMITER;
import static seedu.duke.utils.Constants.RES_INDEX_MARKER;

/* Command object for "mark reservation" command */
public class MarkReservationCommand extends ReservationCommand {
    private String description;
    
    public MarkReservationCommand(String description) {
        this.description = description;
    }

    /**
     * Specifies the index number of the target reservations.
     * Marks the reservation as done.
     * 
     * @param reservations Existing reservation list.
     */
    @Override
    public void execute(ReservationList reservations) {
        // specifies the reservation number
        int numberPos = description.indexOf(RES_INDEX_MARKER);
        int numberEndPos = description.indexOf(DELIMITER, numberPos);
        int reservationNumber = Integer.parseInt(description.substring(numberPos + RES_INDEX_MARKER.length(),
                numberEndPos + 1).trim());
        
        // mark the reservation as done
        reservations.markReservationAsServed(reservationNumber);
    }
}
