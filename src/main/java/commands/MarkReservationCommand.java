package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import reservation.ReservationList;

import static utils.Constants.DELIMITER;
import static utils.Constants.RES_INDEX_MARKER;

/** Command object for "mark reservation" command. */
public class MarkReservationCommand extends ReservationCommand {
    private String description;
    private int reservationNumber;
    
    public MarkReservationCommand(String description) {
        this.description = description;
    }

    /**
     * Specifies the index number of the target reservations.
     * Marks the reservation as done.
     * 
     * @param reservations Existing reservation list.
     */
    @Override
    public void execute(ReservationList reservations) {
        try {
            parseInput(this.description);

            // mark the reservation as done
            reservations.markReservationAsServed(reservationNumber);

            // TODO: change to ui.showMessage()
            System.out.println(String.format("Successfully mark Reservation[%d] as served", this.reservationNumber));
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid positive integer.");
        } catch (InputMissingException e) {
            System.out.println(String.format("Input Missing: %s is missing.", e.getInput()));
        } catch (DelimiterMissingException e) {
            System.out.println("Delimiter Missing.");
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
        int numberEndPos = description.indexOf(DELIMITER, numberPos);
        if (numberEndPos == -1) {
            throw new DelimiterMissingException();
        }
        
        this.reservationNumber = Integer.parseInt(description.substring(numberPos + RES_INDEX_MARKER.length(),
                numberEndPos + 1).trim());
    }
}
