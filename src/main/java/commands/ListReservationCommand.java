package commands;

import reservation.Reservation;
import reservation.ReservationList;
import ui.Ui;

/** Command object for "list reservations" command. */
public class ListReservationCommand extends ReservationCommand {
    /**
     * Lists all reservations, including served, unserved, and invalid reservations.
     * 
     * @param reservations Existing reservation list.
     * @param ui Interaction with users.
     */
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        if (reservations.getSize() <= 0) {
            ui.showMessage("Currently there is no reservation in the list.");
            return;
        }
        
        for (int i = 1; i <= reservations.getSize(); i++) {
            Reservation reservation = reservations.getReservation(i);
            ui.showMessage(reservation.toString());
        }
    }

    @Override
    protected void parseInput(String description) {

    }

}