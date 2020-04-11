package reservation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static utils.Constants.LARGE_TABLE;
import static utils.Constants.LOG_FOLDER;
import static utils.Constants.MEDIUM_TABLE;
import static utils.Constants.NOT_SERVED;
import static utils.Constants.SMALL_TABLE;

/** Reservation of the restaurant. */
public class Reservation {
    private int reservationNumber;
    private String name;
    private LocalDateTime date;
    private int numberOfGuests;
    private String contact;
    private String comments;
    
    private String status;
    private Character tableSize;
    
    private final String ls = System.lineSeparator();

    private static final Logger LOGGER = Logger.getLogger(Reservation.class.getName());
    private static final String FILE_PATH = LOG_FOLDER + "Reservation.log";

    /**
     * Defines the constructor for a Reservation.
     * Notes that comments for reservation are optional.
     */
    public Reservation(int reservationNumber, String name, LocalDateTime date, int numberOfGuests, String contact) {
        this.reservationNumber = reservationNumber;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
        this.contact = contact;
        
        this.comments = "No comments";
        this.status = NOT_SERVED;
        
        setTableSize(this.numberOfGuests);
    }

    /**
     * Sets up the logger. 
     * Calls once at the start of the program.
     *
     * @throws IOException When logger set up failed.
     */
    public static void setLogger() throws IOException {
        Locale.setDefault(Locale.UK);
        
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        FileHandler fileHandler = new FileHandler(FILE_PATH, true); // let it append
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setEncoding("UTF-8");
        LOGGER.addHandler(fileHandler);
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
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gets the date of the reservation.
     * 
     * @return Date of the reservation.
     */
    public LocalDateTime getDate() {
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
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the status of the reservation, served, not served, or invalid.
     * 
     * @return Status of the reservation.
     */
    public String getStatus() {
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
        return String.format("Reservation [%d]"
                + ls
                + "Status: %s"
                + ls
                + "Contact person: %s"
                + ls
                + "Date: %s"
                + ls
                + "Number of guests: %d"
                + ls
                + "Table size: %c"
                + ls
                + "Contact details: %s"
                + ls
                + "Comments: %s"
                + ls, 
                this.reservationNumber, this.status, this.name, 
                this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.numberOfGuests, this.tableSize, this.contact, this.comments);
    }
}
