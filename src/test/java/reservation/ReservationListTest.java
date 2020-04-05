package reservation;

import exceptions.ReservationStatusException;
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
        reservationList.addReservation(new Reservation(1, "Lisa",
                LocalDateTime.parse("2020-03-15 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                3, "12345678"));
    }
    
    @Test
    void addReservation_normalInput_success() {
        Reservation reservation = reservationList.getReservation(1);
        
        assertEquals(1, reservation.getReservationNumber());
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
            reservationList.markReservationAsServed(1);
            Reservation reservation = reservationList.getReservation(1);
            assertEquals("Served", reservation.getStatus());
        } catch (ReservationStatusException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    void markReservationAsServed_invalidReservation_exceptionThrown() {
        try {
            reservationList.voidReservation(1);
            reservationList.markReservationAsServed(1);
            fail(); // the test should not reach this line
        } catch (ReservationStatusException e) {
            assertEquals("Invalid", e.getStatus());
        }
    }
    
    @Test
    void voidReservation_normalInput_success() {
        try {
            reservationList.voidReservation(1);
            Reservation reservation = reservationList.getReservation(1);
            assertEquals("Invalid", reservation.getStatus());
        } catch (ReservationStatusException e) {
            fail(); // the test should not reach this line
        }
    }
    
    @Test
    void voidReservation_invalidReservation_exceptionThrown() {
        try {
            reservationList.voidReservation(1);
            reservationList.voidReservation(1);
            fail(); // the test should not reach this line
        } catch (ReservationStatusException e) {
            assertEquals("Invalid", e.getStatus());
        }
    }

    @Test
    void voidReservation_servedReservation_exceptionThrown() {
        try {
            reservationList.markReservationAsServed(1);
            reservationList.voidReservation(1);
            fail(); // the test should not reach this line
        } catch (ReservationStatusException e) {
            assertEquals("Served", e.getStatus());
        }
    }
    
    @Test
    void getSize_normalInput_success() {
        assertEquals(1, reservationList.getSize());
    }
}