package reservation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {
    
    @Test
    void testGetterandSetter_normalInput_success() {
        final String ls = System.lineSeparator();
        
        Reservation reservation = new Reservation(2, "Lisa",
                LocalDateTime.parse("2020-03-15 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                3, "12345678");
        
        assertEquals(2, reservation.getReservationNumber());
        
        assertEquals("Lisa", reservation.getName());
        reservation.setName("Peter");
        assertEquals("Peter", reservation.getName());
        
        assertEquals(LocalDateTime.parse("2020-03-15 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
                reservation.getDate());
        reservation.setDate(LocalDateTime.parse("2020-03-15 13:00", 
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        assertEquals(LocalDateTime.parse("2020-03-15 13:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                reservation.getDate());
        
        assertEquals('S', reservation.getTableSize());
        reservation.setNumberOfGuests(8);
        assertEquals('M', reservation.getTableSize());
        
        assertEquals("12345678", reservation.getContact());
        reservation.setContact("11111111");
        assertEquals("11111111", reservation.getContact());
        
        assertEquals("No comments", reservation.getComments());
        reservation.setComments("no spicy food please");
        assertEquals("no spicy food please", reservation.getComments());
        
        assertEquals("Unserved", reservation.getStatus());
        reservation.setStatus("Served");
        assertEquals("Served", reservation.getStatus());
        
        assertEquals("Reservation [2]"
                + ls
                + "Status: Served"
                + ls
                + "contact person: Peter"
                + ls
                + "date: 2020-03-15 13:00"
                + ls
                + "number of guests: 8"
                + ls
                + "table size: M"
                + ls
                + "contact details: 11111111"
                + ls
                + "comments: no spicy food please"
                + ls, reservation.toString());
    }
}