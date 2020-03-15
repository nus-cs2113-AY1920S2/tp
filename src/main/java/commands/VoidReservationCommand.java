package commands;

import reservation.ReservationList;

import static utils.Constants.DELIMITER;
import static utils.Constants.RES_INDEX_MARKER;

/** Command object for "void reservation" command. */
public class VoidReservationCommand extends ReservationCommand {
    private String description;
    
    public VoidReservationCommand(String description) {
        this.description = description;
    }
    
    /**
     * Specifies the index number of the target reservations.
     * Marks the reservation as invalid in the case like reservation cancellation and etc.
     * 
     * @param reservations Existing reservation list.
     */
    @Override
    public void execute(ReservationList reservations) {
        // specifies the reservation number
        int numberPos = description.indexOf(RES_INDEX_MARKER);
        int numberEndPos = description.indexOf(DELIMITER, numberPos);
        int reservationNumber = Integer.parseInt(description.substring(numberPos + RES_INDEX_MARKER.length(),
                numberEndPos).trim());
        
        // voids the reservation
        reservations.voidReservation(reservationNumber);
    }
    
    @Override
    protected void parseInput(String description) {

    }
}
