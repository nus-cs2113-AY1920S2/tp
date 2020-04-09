package commands;

import reservation.ReservationList;
import ui.Ui;

public class ClearReservationCommand extends ReservationCommand {
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        reservations.clearReservation();
        ui.showMessage("The reservation list has been cleared.");
    }
    
    @Override
    protected void parseInput(String description) {

    }
}
