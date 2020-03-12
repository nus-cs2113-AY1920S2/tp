package commands;

import reservation.Reservation;
import reservation.ReservationList;

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
        // TODO: may have delimiter missing exception
        // name
        int namePos = description.indexOf(RES_PERSON_MARKER);
        int nameEndPos = description.indexOf(DELIMITER, namePos); 
        String name = description.substring(namePos + RES_PERSON_MARKER.length(), nameEndPos).trim();
        
        // date
        int datePos = description.indexOf(RES_DATE_MARKER);
        int dateEndPos = description.indexOf(DELIMITER, datePos);
        LocalDateTime date = LocalDateTime.parse(description.substring(datePos + RES_DATE_MARKER.length(), 
                dateEndPos).trim(), 
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        
        // numberOfGuests
        int numberPos = description.indexOf(RES_NUM_MARKER);
        int numberEndPos = description.indexOf(DELIMITER, numberPos);
        int numberOfGuests = Integer.parseInt(description.substring(numberPos + RES_NUM_MARKER.length(), 
                numberEndPos).trim());
        
        // contact
        int contactPos = description.indexOf(RES_CONTACT_MARKER);
        int contactEndPos = description.indexOf(DELIMITER, contactPos);
        String contact = description.substring(contactPos + RES_CONTACT_MARKER.length(), contactEndPos).trim();
        
        // comments
        int commentsPos = description.indexOf(RES_COMMENT_MARKER);
        int commentsEndPos = description.indexOf(DELIMITER, commentsPos);
        String comments = description.substring(commentsPos + RES_COMMENT_MARKER.length(), commentsEndPos).trim();
        
        // reservationNumber
        int reservationNumber = reservations.getSize();

        Reservation reservation = new Reservation(reservationNumber, name, date, numberOfGuests, contact);
        reservations.addReservation(reservation);
        
        // TODO: display some message
    }
}
