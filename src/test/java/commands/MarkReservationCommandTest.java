package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import org.junit.jupiter.api.Test;
import reservation.Reservation;
import reservation.ReservationList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MarkReservationCommandTest {

    ReservationList reservationList;
    Ui ui;

    public MarkReservationCommandTest() {
        ui = new Ui();
        reservationList = new ReservationList();
        new AddReservationCommand("p/Peter; d/2020-03-12 12:00; n/3; c/98955555;")
                .execute(reservationList, ui);
    }

    @Test
    void execute_normalMarkReservationCommand_success() {
        Reservation reservation = reservationList.getReservation(reservationList.getSize() - 1);
        assertEquals("Unserved", reservation.getStatus());
        new MarkReservationCommand("r/0;").execute(reservationList, ui);
        assertEquals("Served", reservation.getStatus());
    }
    
    @Test
    void parseInput_inputMissingMarkReservationCommand_exceptionThrown() {
        try {
            new MarkReservationCommand("").parseInput("");
            fail(); // the test should not reach this line
        } catch (InputMissingException e) {
            assertEquals("reservation number r/", e.getInput());
            assertEquals("Input Missing: reservation number r/ is missing.", e.getMessage());
        } catch (DelimiterMissingException e) {
            assertEquals("Delimiter Missing.", e.getMessage());
        }
    }
    
    @Test
    void parseInput_delimiterMissingMarkReservationCommand_exceptionThrown() {
        try {
            new MarkReservationCommand("r/0").parseInput("r/0");
            fail(); // the test should not reach this line
        } catch (InputMissingException e) {
            fail(); // the test should not reach this line
        } catch (DelimiterMissingException e) {
            assertEquals("Delimiter Missing.", e.getMessage());
        }
    }
    
    @Test
    void parseInput_invalidReservationNumberMarkReservationCommand_exceptionThrown() {
        try {
            new MarkReservationCommand("r/-1;").parseInput("r/-1;");
            fail(); // the test should not reach this line
        } catch (NumberFormatException e) {
            ui.showMessage("Please enter a valid positive integer.");
        } catch (InputMissingException e) {
            fail(); // the test should not reach this line
        } catch (DelimiterMissingException e) {
            fail(); // the test should not reach this line
        }
    }
}