package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import org.junit.jupiter.api.Test;
import reservation.Reservation;
import reservation.ReservationList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddReservationCommandTest {
    
    ReservationList reservationList = new ReservationList();
    Ui ui = new Ui();

    @Test
    void execute_normalAddReservationCommand_success() {
        new AddReservationCommand("p/Peter; d/2020-03-12 12:00; n/3; c/98955555;").execute(reservationList, ui);
        Reservation reservation = reservationList.getReservation(reservationList.getSize() - 1);
        assertEquals("Peter", reservation.getName());
        assertEquals(LocalDateTime.parse("2020-03-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
                reservation.getDate());
        assertEquals("98955555", reservation.getContact());
        assertEquals("No comments", reservation.getComments());
        assertEquals('S', reservation.getTableSize());
    }

    @Test
    void execute_normalAddReservationCommandwithCommentnDifferentSequence_success() {
        new AddReservationCommand("p/Lisa; m/no spicy food please; d/2020-03-13 12:00; n/9; c/98889999;")
                .execute(reservationList, ui);
        Reservation reservation = reservationList.getReservation(reservationList.getSize() - 1);
        assertEquals("Lisa", reservation.getName());
        assertEquals(LocalDateTime.parse("2020-03-13 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                reservation.getDate());
        assertEquals("98889999", reservation.getContact());
        assertEquals("no spicy food please", reservation.getComments());
        assertEquals('L', reservation.getTableSize());
    }

    @Test
    void parseInput_inputMissingAddReservationCommand_exceptionThrown() {
        try {
            new AddReservationCommand("p/David; d/2020-03-12 12:00; c/98887777;")
                    .parseInput("p/David; d/2020-03-12 12:00; c/98887777;");
            fail(); // the test should not reach this line
        } catch (InputMissingException e) {
            assertEquals("number of guests n/", e.getInput());
            assertEquals("Input Missing: number of guests n/ is missing.", e.getMessage());
        } catch (DelimiterMissingException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    void parseInput_delimiterMissingAddReservationCommand_exceptionThrown() {
        try {
            new AddReservationCommand("p/David d/2020-03-12 12:00; n/3; c/98887777;")
                    .parseInput("p/David d/2020-03-12 12:00; n/3; c/98887777;");
            fail(); // the test should not reach this line
        } catch (InputMissingException e) {
            fail(); // the test should not reach this line
        } catch (DelimiterMissingException e) {
            assertEquals("Delimiter Missing.", e.getMessage());
        }
    }
}