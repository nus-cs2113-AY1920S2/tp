package exceptions;

public class InvalidReservationNumberException extends ReservationException {
    private int number;
    
    public InvalidReservationNumberException(int number) {
        this.number = number;
    }
    
    @Override
    public String getMessage() {
        return String.format("There is no Reservation[%d] in the list.", this.number);
    }
}
