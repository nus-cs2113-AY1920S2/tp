package commands;

import reservation.ReservationList;

public abstract class ReservationCommand {
    public abstract void execute(ReservationList reservations);
    
    protected abstract void parseInput(String description);
}
