package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import reservation.ReservationList;
import ui.Ui;

import static utils.Constants.DELIMITER;
import static utils.Constants.RES_INDEX_MARKER;

/** Command object for "mark reservation" command. */
public class MarkReservationCommand extends ReservationCommand {
    private String description;
    private int reservationNumber;
    private int validMaxRange;
    
    public MarkReservationCommand(String description) {
        this.description = description;
    }

    /**
     * Specifies the index number of the target reservations.
     * Marks the reservation as done.
     * 
     * @param reservations Existing reservation list.
     * @param ui Interaction with users.
     */
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        try {
            validMaxRange = reservations.getSize() - 1;
            parseInput(this.description);

            // mark the reservation as done
            reservations.markReservationAsServed(reservationNumber);
            
            ui.showMessage(String.format("Successfully mark Reservation[%d] as served", this.reservationNumber));
        } catch (NumberFormatException e) {
            ui.showMessage("Please enter a valid positive integer.");
        } catch (InputMissingException e) {
            ui.showMessage(e.getMessage());
        } catch (DelimiterMissingException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Parses the input.
     *
     * @param description Input from the user excluding the command.
     * @throws InputMissingException If there is input missing.
     * @throws DelimiterMissingException If there is delimiter missing.
     */
    @Override
    protected void parseInput(String description) throws InputMissingException, DelimiterMissingException {
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

        if (this.reservationNumber < 0 || this.reservationNumber > validMaxRange) {
            throw new NumberFormatException();
        }
        assert 0 <= this.reservationNumber && this.reservationNumber <= validMaxRange : "Invalid Reservation Number";
    }
}
