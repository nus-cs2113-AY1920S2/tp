package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import reservation.Reservation;
import reservation.ReservationList;
import ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utils.Constants.DELIMITER;
import static utils.Constants.RES_DATE_MARKER;
import static utils.Constants.RES_INDEX_MARKER;

/** Command object for "search reservation" command. */
public class SearchReservationCommand extends ReservationCommand {
    private int reservationNumber;
    private LocalDate date; // yyyy-MM-dd
    private String description;
    private int validMaxRange;
    
    public SearchReservationCommand(String description) {
        this.description = description;
        this.reservationNumber = -1;
    }

    /**
     * Displays a list of reservations when users search by reservation number or date(yyyy-MM-dd). 
     * 
     * @param reservations Existing reservation list.
     * @param ui Interaction with users.
     */
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        try {
            validMaxRange = reservations.getSize() - 1;
            parseInput(this.description);
            
            if (reservationNumber != -1 && date != null) { // both reservation number and date exist
                Reservation reservation = reservations.getReservation(reservationNumber);
                if (reservation.getDate().equals(date)) {
                    ui.showMessage(reservation.toString());
                } else {
                    ui.showMessage("Sorry! No such reservation.");
                }
            } else if (reservationNumber != -1) { // only have reservation number
                Reservation reservation = reservations.getReservation(reservationNumber);
                ui.showMessage(reservation.toString());
            } else if (date != null) { // only have date
                System.out.println(String.format("Here comes the reservations on the date %s:", date));
                boolean emptyList = true;
                for (int i = 0; i < reservations.getSize(); i++) {
                    Reservation reservation = reservations.getReservation(i);
                    if (reservation.getDate().equals(date)) {
                        ui.showMessage(reservation.toString());
                        emptyList = false;
                    }
                }
                if (emptyList) {
                    ui.showMessage("Empty List.");
                }
            }
        } catch (NumberFormatException e) {
            ui.showMessage("Please enter a valid non-positive reservation number.");
        } catch (DateTimeException e) {
            ui.showMessage("Please follow the date time format strictly: yyyy-MM-dd; eg. 2000-01-01");
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
        boolean delimiterMissing;
        boolean inputMissing = true; // to see if the command has either "number" input or "date" input
        String[] markers = {RES_INDEX_MARKER, RES_DATE_MARKER};
        
        // reservationNumber
        int numberPos = description.indexOf(RES_INDEX_MARKER);
        if (numberPos != -1) {
            inputMissing = false;

            int numberEndPos = description.indexOf(DELIMITER, numberPos);
            if (numberEndPos == -1) {
                throw new DelimiterMissingException();
            }

            delimiterMissing = hasDelimiterInBetween(numberPos + RES_INDEX_MARKER.length(), numberEndPos,
                    markers, description);
            if (delimiterMissing) {
                throw new DelimiterMissingException();
            }

            this.reservationNumber = Integer.parseInt(description.substring(
                    numberEndPos + RES_INDEX_MARKER.length(), numberEndPos).trim());

            if (this.reservationNumber < 0 || this.reservationNumber > validMaxRange) {
                throw new NumberFormatException();
            }
        }        
        
        // date: yyyy-MM-dd
        int datePos = description.indexOf(RES_DATE_MARKER);
        if (datePos != -1) {
            inputMissing = false;
            int dateEndPos = description.indexOf(DELIMITER, datePos);
            if (dateEndPos == -1) {
                throw new DelimiterMissingException();
            }

            delimiterMissing = hasDelimiterInBetween(datePos + RES_DATE_MARKER.length(), dateEndPos,
                    markers, description);
            if (delimiterMissing) {
                throw new DelimiterMissingException();
            }

            this.date = LocalDate.parse(
                    description.substring(datePos + RES_DATE_MARKER.length(), dateEndPos).trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        
        if (inputMissing) {
            throw new InputMissingException(String.format("either reservation number %s or date %s", 
                    RES_INDEX_MARKER, RES_DATE_MARKER));
        }
    }
}
