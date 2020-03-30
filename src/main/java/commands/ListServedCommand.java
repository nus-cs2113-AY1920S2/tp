package commands;

import reservation.ReservationList;
import ui.Ui;

import static utils.Constants.SERVED;

/** Command object for "list served reservations" command. */
public class ListServedCommand extends ReservationCommand {
    /**
     * Lists all served reservations.
     * 
     * @param reservations Existing reservation list.
     * @param ui Interaction with users.
     */
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        for (int i = 0; i < reservations.getSize(); i++) {
            if (!reservations.getReservation(i).getStatus().equals(SERVED)) {
                continue;
            }
            
            ui.showMessage(reservations.getReservation(i).toString());
        }
    }

    @Override
    protected void parseInput(String description) {

    }
}
