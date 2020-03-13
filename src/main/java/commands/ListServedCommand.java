package commands;

import reservation.ReservationList;

import static utils.Constants.SERVED;

/** Command object for "list served reservations" command. */
public class ListServedCommand extends ReservationCommand {
    /**
     * Lists all served reservations.
     * 
     * @param reservations Existing reservation list.
     */
    @Override
    public void execute(ReservationList reservations) {
        // TODO: wrap it into Ui class
        for (int i = 1; i <= reservations.getSize(); i++) {
            if (!reservations.getReservation(i).getStatus().equals(SERVED)) {
                continue;
            }
            
            System.out.println(reservations.getReservation(i));
        }
    }
}
