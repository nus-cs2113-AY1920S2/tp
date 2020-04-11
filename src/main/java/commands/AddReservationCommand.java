package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import reservation.Reservation;
import reservation.ReservationList;
import ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.Constants.DELIMITER;
import static utils.Constants.RES_DATE_MARKER;
import static utils.Constants.RES_PERSON_MARKER;
import static utils.Constants.RES_NUM_MARKER;
import static utils.Constants.RES_CONTACT_MARKER;
import static utils.Constants.RES_COMMENT_MARKER;


/* Command object for "add reservation" command */
public class AddReservationCommand extends ReservationCommand {
    private String description;
    private String name;
    private LocalDateTime date;
    private int numberOfGuests;
    private String contact;
    private String comments;
    private int reservationNumber;
    
    public AddReservationCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a reservation into the list.
     * 
     * @param reservations Existing reservation list.
     * @param ui Interaction with users.
     */
    @Override
    public void execute(ReservationList reservations, Ui ui) {
        try {
            parseInput(this.description);
            
            // reservationNumber
            this.reservationNumber = reservations.getSize() + 1; // starting from 1
            assert this.reservationNumber >= 0 : "Invalid Reservation Number";

            Reservation reservation = new Reservation(reservationNumber, name, date, numberOfGuests, contact);
            if (comments != null) {
                reservation.setComments(this.comments);
            }
            reservations.addReservation(reservation);
            
            ui.showMessage(String.format("Reservation[%d] has been added into the list", this.reservationNumber));
        } catch (NumberFormatException e) {
            ui.showMessage("Please enter a valid positive integer.");
        } catch (DateTimeException e) {
            ui.showMessage("Please follow the date time format strictly: yyyy-MM-dd HH:mm; eg. 2000-01-01 12:00");
        } catch (InputMissingException e) {
            ui.showMessage(e.getMessage());
            ui.showMessage("Try the 'help' command for the list of commands");
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
        // name
        int namePos = description.indexOf(RES_PERSON_MARKER);
        if (namePos == -1) {
            throw new InputMissingException("name " + RES_PERSON_MARKER);
        }
        assert namePos != -1 : "Contact Name Missing";
        
        int nameEndPos = description.indexOf(DELIMITER, namePos);
        if (nameEndPos == -1) {
            throw new DelimiterMissingException();
        }
        assert nameEndPos != -1 : "Semicolon Missing";

        // enter no content for name
        if (namePos + RES_PERSON_MARKER.length() == nameEndPos) {
            throw new InputMissingException("name");
        }
        
        assert namePos + RES_PERSON_MARKER.length() != nameEndPos : "Contact Name Missing";

        boolean delimiterMissing;
        String[] markers = {RES_PERSON_MARKER, RES_DATE_MARKER, RES_NUM_MARKER,
            RES_CONTACT_MARKER, RES_CONTACT_MARKER, RES_COMMENT_MARKER};
        
        delimiterMissing = hasDelimiterInBetween(namePos + RES_PERSON_MARKER.length(), nameEndPos, 
                markers, description);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        assert !delimiterMissing : "Semicolon Missing";
        
        this.name = description.substring(namePos + RES_PERSON_MARKER.length(), nameEndPos).trim();

        // date
        int datePos = description.indexOf(RES_DATE_MARKER);
        if (datePos == -1) {
            throw new InputMissingException("date " + RES_DATE_MARKER);
        }
        assert datePos != -1 : "Date Missing";
        
        int dateEndPos = description.indexOf(DELIMITER, datePos);
        if (dateEndPos == -1) {
            throw new DelimiterMissingException();
        }
        assert dateEndPos != -1 : "Semicolon Missing";
        
        delimiterMissing = hasDelimiterInBetween(datePos + RES_DATE_MARKER.length(), dateEndPos, 
                markers, description);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        assert !delimiterMissing : "Semicolon Missing";
        
        this.date = LocalDateTime.parse(
                description.substring(datePos + RES_DATE_MARKER.length(), dateEndPos).trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // numberOfGuests
        int numberPos = description.indexOf(RES_NUM_MARKER);
        if (numberPos == -1) {
            throw new InputMissingException("number of guests " + RES_NUM_MARKER);
        }
        assert numberPos != -1 : "Number of Guests Missing";
        
        int numberEndPos = description.indexOf(DELIMITER, numberPos);
        if (numberEndPos == -1) {
            throw new DelimiterMissingException();
        }
        assert numberEndPos != -1 : "Semicolon Missing";
        
        delimiterMissing = hasDelimiterInBetween(numberPos + RES_NUM_MARKER.length(), numberEndPos,
                markers, description);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        assert !delimiterMissing : "Semicolon Missing";
        
        this.numberOfGuests = Integer.parseInt(description.substring(
                numberPos + RES_NUM_MARKER.length(), numberEndPos).trim());
        if (numberOfGuests <= 0) {
            throw new NumberFormatException();
        }
        assert numberOfGuests > 0 : "Negative Number of Guests";

        // contact
        int contactPos = description.indexOf(RES_CONTACT_MARKER);
        if (contactPos == -1) {
            throw new InputMissingException("contact " + RES_CONTACT_MARKER);
        }
        assert contactPos != -1 : "Contact Missing";
        
        int contactEndPos = description.indexOf(DELIMITER, contactPos);
        if (contactEndPos == -1) {
            throw new DelimiterMissingException();
        }
        assert contactEndPos != -1 : "Semicolon Missing";

        // enter no content for contact
        if (contactPos + RES_CONTACT_MARKER.length() == contactEndPos) {
            throw new InputMissingException("contact");
        }

        assert contactPos + RES_CONTACT_MARKER.length() != contactEndPos : "Contact Missing";
        
        delimiterMissing = hasDelimiterInBetween(contactPos + RES_CONTACT_MARKER.length(), contactEndPos,
                markers, description);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        assert !delimiterMissing : "Semicolon Missing";
        
        this.contact = description.substring(
                contactPos + RES_CONTACT_MARKER.length(), contactEndPos).trim();

        // comments
        int commentsPos = description.indexOf(RES_COMMENT_MARKER);
        if (commentsPos == -1) { // no comments
            return;
        }
        int commentsEndPos = description.indexOf(DELIMITER, commentsPos);
        if (commentsEndPos == -1) {
            throw new DelimiterMissingException();
        }
        assert commentsEndPos != -1 : "Semicolon Missing";

        delimiterMissing = hasDelimiterInBetween(commentsPos + RES_COMMENT_MARKER.length(), commentsEndPos,
                markers, description);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        assert !delimiterMissing : "Semicolon Missing";
        
        this.comments = description.substring(
                commentsPos + RES_COMMENT_MARKER.length(), commentsEndPos).trim();        
    }
}
