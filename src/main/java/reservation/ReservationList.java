package reservation;

import exceptions.ReservationException;
import exceptions.ReservationStatusException;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.INVALID;
import static utils.Constants.SERVED;

/** Contains the reservation list e.g., it has operations to add/delete/list reservations in the list. */
public class ReservationList {
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
     * Adds the reservation into the list.
     * 
     * @param reservation Reservation that needs to be added into the list.
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
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
}
