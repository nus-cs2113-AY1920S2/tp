package commands;

import reservation.ReservationList;

import static utils.Constants.NOT_SERVED;

/* Command object for "list unserved reservations" command */
public class ListUnservedCommand extends ReservationCommand {
    /**
     * Lists all unserved reservations.
     * 
     * @param reservations Existing reservation list.
     */
    @Override
    public void execute(ReservationList reservations) {
        // TODO: wrap it into Ui class
        for (int i = 1; i <= reservations.getSize(); i++) {
            if (!reservations.getReservation(i).getStatus().equals(NOT_SERVED)) {
                continue;
            }

            System.out.println(reservations.getReservation(i));
        }
    }
}
