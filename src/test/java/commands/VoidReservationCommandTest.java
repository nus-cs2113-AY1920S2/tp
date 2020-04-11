package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import exceptions.InvalidReservationNumberException;
import org.junit.jupiter.api.Test;
import reservation.Reservation;
import reservation.ReservationList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class VoidReservationCommandTest {
    ReservationList reservationList; 
    Ui ui;
    
    public VoidReservationCommandTest() {
        ui = new Ui();
        reservationList = new ReservationList();
        new AddReservationCommand("p/Peter; d/2020-03-12 12:00; n/3; c/98955555;")
                .execute(reservationList, ui);
    }

    @Test
    void execute_normalVoidReservationCommand_success() {
        new VoidReservationCommand("r/1;").execute(reservationList, ui);
        Reservation reservation = reservationList.getReservation(reservationList.getSize());
        assertEquals("Invalid", reservation.getStatus());
    }

    @Test
    void parseInput_inputMissingVoidReservationCommand_exceptionThrown() {
        try {
            new VoidReservationCommand("").parseInput("");
            fail(); // the test should not reach this line
        } catch (InputMissingException e) {
            assertEquals("reservation number r/", e.getInput());
            assertEquals("Input Missing: reservation number r/ is missing.", e.getMessage());
        } catch (DelimiterMissingException e) {
            assertEquals("Delimiter Missing.", e.getMessage());
        } catch (InvalidReservationNumberException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    void parseInput_delimiterMissingVoidReservationCommand_exceptionThrown() {
        try {
            new VoidReservationCommand("r/1").parseInput("r/1");
            fail(); // the test should not reach this line
        } catch (InputMissingException e) {
            fail(); // the test should not reach this line
        } catch (DelimiterMissingException e) {
            assertEquals("Delimiter Missing.", e.getMessage());
        } catch (InvalidReservationNumberException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    void parseInput_invalidReservationNumberVoidReservationCommand_exceptionThrown() {
        try {
            new VoidReservationCommand("r/-1;").parseInput("r/-1;");
            fail(); // the test should not reach this line
        } catch (InputMissingException e) {
            fail(); // the test should not reach this line
        } catch (DelimiterMissingException e) {
            fail(); // the test should not reach this line
        } catch (InvalidReservationNumberException e) {
            assertEquals("There is no Reservation[-1] in the list.", e.getMessage());
        }
    }
}