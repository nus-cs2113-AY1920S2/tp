package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import exceptions.InvalidReservationNumberException;
import exceptions.ReservationStatusException;
import reservation.ReservationList;
import ui.Ui;

import static utils.Constants.DELIMITER;
import static utils.Constants.RES_INDEX_MARKER;

/** Command object for "void reservation" command. */
public class VoidReservationCommand extends ReservationCommand {
    private String description;
    private int reservationNumber;
    private int validMaxRange;
    
    public VoidReservationCommand(String description) {
        this.description = description;
    }
    
    /**
     * Specifies the index number of the target reservations.
     * Marks the reservation as invalid in the case like reservation cancellation and etc.
     * 
     * @param reservations Existing reservation list.
     * @param ui Interaction with users.
     */
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        try {
            validMaxRange = reservations.getSize();
            parseInput(this.description);
            
            // voids the reservation
            reservations.voidReservation(reservationNumber);
            
            ui.showMessage(String.format("Successfully void Reservation[%d]", this.reservationNumber));
        } catch (NumberFormatException e) {
            ui.showMessage("Please enter a valid positive integer.");
        } catch (InputMissingException e) {
            ui.showMessage(e.getMessage());
            ui.showMessage("Try the 'help' command for the list of commands");
        } catch (DelimiterMissingException e) {
            ui.showMessage(e.getMessage());
        } catch (InvalidReservationNumberException e) {
            ui.showMessage(e.getMessage());
        } catch (ReservationStatusException e) {
            ui.showMessage(String.format("Reservation[%d] is already %s. You cannot void a %s reservation.",
                    this.reservationNumber, e.getStatus(), e.getStatus()));
        }
    }

    /**
     * Parses the input.
     *
     * @param description Input from the user excluding the command.
     * @throws InputMissingException If there is input missing.
     * @throws DelimiterMissingException If there is delimiter missing.
     * @throws InvalidReservationNumberException If there is no such reservation number in the list.
     */
    @Override
    protected void parseInput(String description) 
            throws InputMissingException, DelimiterMissingException, InvalidReservationNumberException {
        // specifies the reservation number
        int numberPos = description.indexOf(RES_INDEX_MARKER);
        if (numberPos == -1) {
            throw new InputMissingException("reservation number " + RES_INDEX_MARKER);
        }
        assert numberPos != -1 : "Reservation Number Missing";
        
        int numberEndPos = description.indexOf(DELIMITER, numberPos);
        if (numberEndPos == -1) {
            throw new DelimiterMissingException();
        }
        assert numberEndPos != -1 : "Semicolon Missing";
        
        this.reservationNumber = Integer.parseInt(description.substring(numberPos + RES_INDEX_MARKER.length(),
                numberEndPos).trim());
        
        if (this.reservationNumber <= 0 || this.reservationNumber > validMaxRange) {
            throw new InvalidReservationNumberException(this.reservationNumber);
        }
        assert 0 < this.reservationNumber && this.reservationNumber <= validMaxRange : "Invalid Reservation Number";
    }
}
