package commands;

import reservation.ReservationList;
import ui.Ui;

import static utils.Constants.NOT_SERVED;

/** Command object for "list unserved reservations" command. */
public class ListUnservedCommand extends ReservationCommand {
    /**
     * Lists all unserved reservations.
     * 
     * @param reservations Existing reservation list.
     * @param ui Interaction with users.
     */
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        boolean noUnserved = true;
        
        for (int i = 1; i <= reservations.getSize(); i++) {
            if (!reservations.getReservation(i).getStatus().equals(NOT_SERVED)) {
                continue;
            }

            ui.showMessage(reservations.getReservation(i).toString());
            noUnserved = false;
        }
        
        if (noUnserved) {
            ui.showMessage("There is no unserved reservation currently in the list.");
        }
    }
    
    @Override
    protected void parseInput(String description) {

    }
}