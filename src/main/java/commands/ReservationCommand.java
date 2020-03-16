package commands;

import exceptions.ReservationException;
import reservation.ReservationList;

public abstract class ReservationCommand {
    // TODO: public abstract void execute(ReservationList reservations, Ui ui);
    public abstract void execute(ReservationList reservations);
    
    protected abstract void parseInput(String description) throws ReservationException;
}
