package reservation;

import commands.MarkReservationCommand;
import commands.VoidReservationCommand;
import exceptions.ReservationCannotMarkException;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReservationListTest {
    ReservationList reservationList;
    Ui ui;
    
    public ReservationListTest() {
        ui = new Ui();
        reservationList = new ReservationList();
        reservationList.addReservation(new Reservation(0, "Lisa",
                LocalDateTime.parse("2020-03-15 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                3, "12345678"));
    }
    
    @Test
    void addReservation_normalInput_success() {
        Reservation reservation = reservationList.getReservation(0);
        
        assertEquals(0, reservation.getReservationNumber());
        assertEquals("Lisa", reservation.getName());
        assertEquals(LocalDateTime.parse("2020-03-15 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                reservation.getDate());
        assertEquals('S', reservation.getTableSize());
        assertEquals("12345678", reservation.getContact());
        assertEquals("No comments", reservation.getComments());
        assertEquals("Unserved", reservation.getStatus());
    }
    
    @Test
    void markReservationAsServed_normalInput_success() {
        try {
            reservationList.markReservationAsServed(0);
            Reservation reservation = reservationList.getReservation(0);
            assertEquals("Served", reservation.getStatus());
        } catch (ReservationCannotMarkException e) {
            fail(); // the test should not reach this line
        }
    }
    
    @Test
    void voidReservation_normalInput_success() {
        reservationList.voidReservation(0);
        Reservation reservation = reservationList.getReservation(0);
        assertEquals("Invalid", reservation.getStatus());
    }

    @Test
    void markReservationAsServed_invalidReservation_exceptionThrown() {
        try {
            reservationList.voidReservation(0);
            reservationList.markReservationAsServed(0);
            fail(); // the test should not reach this line
        } catch (ReservationCannotMarkException e) {
            ui.showMessage("Reservation[0] is invalid, so it cannot be marked as served.");
        }
    }
    
    @Test
    void getSize_normalInput_success() {
        assertEquals(1, reservationList.getSize());
    }
}