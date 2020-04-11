package exceptions;

public class ReservationStatusException extends ReservationException {
    private String status;
    
    public ReservationStatusException(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return this.status;
    }
}
