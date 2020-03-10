package reservation;

import java.time.LocalDate;

import static utils.Constants.NOT_SERVED;
import static utils.Constants.SMALL_TABLE;
import static utils.Constants.MEDIUM_TABLE;
import static utils.Constants.LARGE_TABLE;

/** Reservation of the restaurant. */
public class Reservation {
    private int reservationNumber;
    private String name;
    private LocalDate date;
    private int numberOfGuests;
    private String contact;
    private String comments;
    
    private Character status;
    private Character tableSize;

    /**
     * Defines the constructor for a Reservation.
     * Notes that comments for reservation are optional.
     */
    public Reservation(int reservationNumber, String name, LocalDate date, int numberOfGuests, String contact) {
        this.reservationNumber = reservationNumber;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
        this.contact = contact;
        
        this.comments = "No comments.";
        this.status = NOT_SERVED;
        
        setTableSize(this.numberOfGuests);
    }

    /**
     * Gets the index number of the reservation.
     * 
     * @return Index number of the reservation
     */
    public int getReservationNumber() {
        return this.reservationNumber;
    }

    /**
     * Sets the contact person's name in the case that 
     * the group who makes the reservation wants to change the contact person.
     * 
     * @param name Contact person's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact person of the reservation.
     * 
     * @return Name of the contact person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the date in the case that 
     * the group who makes the reservation wants to change the date.
     * 
     * @param date When the reservation is. Format: yyyy-MM-dd HH:mm
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the date of the reservation.
     * 
     * @return Date of the reservation.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Sets the number of the guests in the case that 
     * the group who makes the reservation wants to change.
     *
     * @param numberOfGuests The number of people attending the reserved meal.
     */
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
        
        // whenever the guest number changes, the table size should change accordingly
        setTableSize(this.numberOfGuests);
    }

    /**
     * Sets the number of the guests in the case that 
     * the group who makes the reservation wants to change.
     * 
     * @param contact Contact, can be either phone number or email or etc.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Get the contact details.
     * 
     * @return Contact details.
     */
    public String getContact() {
        return this.contact;
    }

    /**
     * Sets the miscellaneous comments, such as preference, food allergy, etc.
     * 
     * @param comments Comments of the reservation.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets any comments of the reservation.
     * 
     * @return Comments of the reservation.
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * Sets the status of the reservation as served, not served, or invalid.
     * 
     * @param status Status character of the reservation.
     */
    public void setStatus(Character status) {
        this.status = status;
    }

    /**
     * Gets the status of the reservation, served, not served, or invalid.
     * 
     * @return Status of the reservation.
     */
    public Character getStatus() {
        return this.status;
    }

    /**
     * Assigns the table according to the number of people attending the reserved meal.
     * 
     * @param numberOfGuests The number of people attending the meal.
     */
    private void setTableSize(int numberOfGuests) {
        if (numberOfGuests <= 4) {
            this.tableSize = SMALL_TABLE;
        } else if (numberOfGuests <= 8) {
            this.tableSize = MEDIUM_TABLE;
        } else {
            this.tableSize = LARGE_TABLE;
        }
    }

    /**
     * Gets the table size.
     * @return Table size.
     */
    public Character getTableSize() {
        return this.tableSize;
    }

    @Override
    public String toString() {
        return String.format("Reservation [%d]\n"
                        + "contact person: %s\n"
                        + "date: %s\n" 
                        + "number of guests: %d\n" 
                        + "table size: %c\n" 
                        + "contact details: %s\n" 
                        + "comments: %s", 
                this.reservationNumber, this.name, this.date,
                this.numberOfGuests, this.tableSize, this.contact, this.comments);
    }
}
