package seedu.duke.commands;

import seedu.duke.reservation.ReservationList;

public abstract class ReservationCommand {
    public abstract void execute(ReservationList reservations);
}
