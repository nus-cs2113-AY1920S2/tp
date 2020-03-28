package reservation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationListTest {
    ReservationList reservationList;
    
    public ReservationListTest() {
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
        reservationList.markReservationAsServed(0);
        Reservation reservation = reservationList.getReservation(0);
        assertEquals("Served", reservation.getStatus());
    }
    
    @Test
    void voidReservation_normalInput_success() {
        reservationList.voidReservation(0);
        Reservation reservation = reservationList.getReservation(0);
        assertEquals("Invalid", reservation.getStatus());
    }
    
    @Test
    void getSize_normalInput_success() {
        assertEquals(1, reservationList.getSize());
    }
}