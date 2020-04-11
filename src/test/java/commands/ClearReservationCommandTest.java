package commands;

import org.junit.jupiter.api.Test;
import reservation.ReservationList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearReservationCommandTest {
    ReservationList reservationList;
    Ui ui;
    
    public ClearReservationCommandTest() {
        ui = new Ui();
        reservationList = new ReservationList();
        new AddReservationCommand("p/Peter; d/2020-03-12 12:00; n/3; c/98955555;")
                .execute(reservationList, ui);
        new AddReservationCommand("p/Mary; d/2020-03-11 12:00; n/8; c/99998888;")
                .execute(reservationList, ui);
    }

    @Test
    void execute_normalInput_success() {
        assertEquals(reservationList.getSize(), 2);
        new ClearReservationCommand().execute(reservationList, ui);
        assertEquals(reservationList.getSize(), 0);
    }
}