package reservation;

import exceptions.ReservationCannotMarkException;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.INVALID;
import static utils.Constants.SERVED;

/** Contains the reservation list e.g., it has operations to add/delete/list reservations in the list. */
public class ReservationList {
    List<Reservation> reservations;

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
     * @throws ReservationCannotMarkException If the reservation is originally invalid.
     */
    public void markReservationAsServed(int reservationNumber) throws ReservationCannotMarkException {
        Reservation reservation = reservations.get(reservationNumber);
        if (reservation.getStatus().equals(INVALID)) {
            throw new ReservationCannotMarkException();
        }
        reservations.get(reservationNumber).setStatus(SERVED);
    }

    /**
     * Marks the reservation as invalid, etc. the customer wants to cancel the reservation or wrong reservation
     * 
     * @param reservationNumber The index number of the reservation.
     */
    public void voidReservation(int reservationNumber) {
        reservations.get(reservationNumber).setStatus(INVALID);
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
     * @param reservationNumber Target reservation number.
     * @return Reservation indexed with the target number.
     */
    public Reservation getReservation(int reservationNumber) {
        return reservations.get(reservationNumber);
    }
}
