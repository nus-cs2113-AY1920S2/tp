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
        boolean noServed = true;
        
        for (int i = 1; i <= reservations.getSize(); i++) {
            if (!reservations.getReservation(i).getStatus().equals(SERVED)) {
                continue;
            }
            
            ui.showMessage(reservations.getReservation(i).toString());
            noServed = false;
        }
        
        if (noServed) {
            ui.showMessage("There is no served reservation currently in the list.");
        }
    }

    @Override
    protected void parseInput(String description) {

    }
}
