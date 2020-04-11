package reservation;

import exceptions.ReservationStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static utils.Constants.INVALID;
import static utils.Constants.LOG_FOLDER;
import static utils.Constants.SERVED;


/** Contains the reservation list e.g., it has operations to add/delete/list reservations in the list. */
public class ReservationList {
    private static final Logger LOGGER = Logger.getLogger(ReservationList.class.getName());
    private static final String FILE_PATH = LOG_FOLDER + "ReservationList.log";
    
    private List<Reservation> reservations;

    /**
     * Defines the constructor and starts with an empty list.
     */
    public ReservationList() { 
        reservations = new ArrayList<>();
    }

    /**
     * Defines the constructor and starts with a given ArrayList of reservations.
     * 
     * @param reservations Beginning list of reservations.
     */
    public ReservationList(List<Reservation> reservations) {
        this.reservations = reservations;
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
     * Adds the reservation into the list.
     * 
     * @param reservation Reservation that needs to be added into the list.
     */
    public void addReservation(Reservation reservation) {
        assert reservation != null : "Invalid Reservation!";
        
        reservations.add(reservation);
        LOGGER.info(String.format("Add Reservation[%d] to the list.", reservation.getReservationNumber()));
    }
    
    /**
     * Marks the reservation as served.
     * 
     * @param reservationNumber The index number of the reservation.
     * @throws ReservationStatusException If the reservation is originally invalid.
     */
    public void markReservationAsServed(int reservationNumber) throws ReservationStatusException {
        Reservation reservation = reservations.get(reservationNumber - 1);
        if (reservation.getStatus().equals(INVALID)) {
            throw new ReservationStatusException(INVALID);
        }
        
        assert !reservation.getStatus().equals(INVALID) : "Reservation Status Exception";
        
        reservation.setStatus(SERVED);
        LOGGER.info(String.format("Mark Reservation[%d] as Served.", reservationNumber));
    }

    /**
     * Marks the reservation as invalid, etc. the customer wants to cancel the reservation or wrong reservation
     * 
     * @param reservationNumber The index number of the reservation.
     * @throws ReservationStatusException If the reservation is originally invalid or served.
     */
    public void voidReservation(int reservationNumber) throws ReservationStatusException {
        Reservation reservation = reservations.get(reservationNumber - 1);
        if (reservation.getStatus().equals(INVALID)) {
            throw new ReservationStatusException(INVALID);
        }
        
        if (reservation.getStatus().equals(SERVED)) {
            throw new ReservationStatusException(SERVED);
        }
        
        assert !reservation.getStatus().equals(INVALID) & !reservation.getStatus().equals(SERVED) 
                : "Reservation Status Exception";
        
        reservation.setStatus(INVALID);
        LOGGER.info(String.format("Void Reservation[%d] as Invalid.", reservationNumber));
    }

    /**
     * Gets the size of the reservation list.
     * 
     * @return Size of reservation list.
     */
    public int getSize() {
        return this.reservations.size();
    }

    /**
     * Gets the reservation with the target reservation number.
     * 
     * @param reservationNumber Target reservation number (starting from 1).
     * @return Reservation indexed with the target number.
     */
    public Reservation getReservation(int reservationNumber) {
        return reservations.get(reservationNumber - 1);
    }

    /**
     * Clears the whole reservation list.
     */
    public void clearReservation() {
        reservations.clear();
    }
}
