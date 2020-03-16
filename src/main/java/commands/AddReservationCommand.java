package commands;

import exceptions.DelimiterMissingException;
import exceptions.InputMissingException;
import reservation.Reservation;
import reservation.ReservationList;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.Constants.DELIMITER;
import static utils.Constants.RES_PERSON_MARKER;
import static utils.Constants.RES_DATE_MARKER;
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
     */
    @Override
    public void execute(ReservationList reservations) {
        try {
            parseInput(this.description);
            
            // reservationNumber
            this.reservationNumber = reservations.getSize();

            Reservation reservation = new Reservation(reservationNumber, name, date, numberOfGuests, contact);
            if (comments != null) {
                reservation.setComments(this.comments);
            }
            reservations.addReservation(reservation);

            // TODO: change to ui.showMessage()
            System.out.println(String.format("Reservation[%d] has been added into the list", this.reservationNumber));
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid positive integer.");
        } catch (DateTimeException e) {
            System.out.println("Please follow the date time format strictly: yyyy-MM-dd HH:mm; eg. 2000-01-01 12:00");
        } catch (InputMissingException e) {
            System.out.println(e.getMessage());
        } catch (DelimiterMissingException e) {
            System.out.println(e.getMessage());
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
        
        // name
        int namePos = description.indexOf(RES_PERSON_MARKER);
        if (namePos == -1) {
            throw new InputMissingException("name " + RES_PERSON_MARKER);
        }
        int nameEndPos = description.indexOf(DELIMITER, namePos);
        if (nameEndPos == -1) {
            throw new DelimiterMissingException();
        }
        
        delimiterMissing = hasDelimiterInBetween(namePos + RES_PERSON_MARKER.length(), nameEndPos);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        
        this.name = description.substring(namePos + RES_PERSON_MARKER.length(), nameEndPos).trim();

        // date
        int datePos = description.indexOf(RES_DATE_MARKER);
        if (datePos == -1) {
            throw new InputMissingException("date " + RES_DATE_MARKER);
        }
        int dateEndPos = description.indexOf(DELIMITER, datePos);
        if (dateEndPos == -1) {
            throw new DelimiterMissingException();
        }
        
        delimiterMissing = hasDelimiterInBetween(datePos + RES_DATE_MARKER.length(), dateEndPos);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        
        this.date = LocalDateTime.parse(
                description.substring(datePos + RES_DATE_MARKER.length(), dateEndPos).trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // numberOfGuests
        int numberPos = description.indexOf(RES_NUM_MARKER);
        if (numberPos == -1) {
            throw new InputMissingException("number of guests " + RES_NUM_MARKER);
        }
        int numberEndPos = description.indexOf(DELIMITER, numberPos);
        if (numberEndPos == -1) {
            throw new DelimiterMissingException();
        }
        
        delimiterMissing = hasDelimiterInBetween(numberPos + RES_NUM_MARKER.length(), numberEndPos);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        
        this.numberOfGuests = Integer.parseInt(description.substring(
                numberPos + RES_NUM_MARKER.length(), numberEndPos).trim());
        if (numberOfGuests <= 0) {
            throw new NumberFormatException();
        }

        // contact
        int contactPos = description.indexOf(RES_CONTACT_MARKER);
        if (contactPos == -1) {
            throw new InputMissingException("contact " + RES_CONTACT_MARKER);
        }
        int contactEndPos = description.indexOf(DELIMITER, contactPos);
        if (contactEndPos == -1) {
            throw new DelimiterMissingException();
        }
        
        delimiterMissing = hasDelimiterInBetween(contactPos + RES_CONTACT_MARKER.length(), contactEndPos);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        
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

        delimiterMissing = hasDelimiterInBetween(commentsPos + RES_COMMENT_MARKER.length(), commentsEndPos);
        if (delimiterMissing) {
            throw new DelimiterMissingException();
        }
        
        this.comments = description.substring(
                commentsPos + RES_COMMENT_MARKER.length(), commentsEndPos).trim();        
    }

    /**
     * Checks if there is another marker between the subcommand.
     * Checks for delimiter missing.
     *
     * @param startPos Starting position after the original marker.
     * @param endPos Position of the delimiter;
     * @return True if there is another marker, False otherwise.
     */
    private boolean hasDelimiterInBetween(int startPos, int endPos) {
        String[] markers = {RES_PERSON_MARKER, RES_DATE_MARKER, RES_NUM_MARKER, 
            RES_CONTACT_MARKER, RES_CONTACT_MARKER, RES_COMMENT_MARKER};

        String targetSubstring = this.description.substring(startPos, endPos);
        for (String marker: markers) {
            if (targetSubstring.contains(marker)) {
                return true;
            }
        }
        return false;
    }
}
