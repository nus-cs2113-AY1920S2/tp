package exceptions;

public class DelimiterMissingException extends ReservationException {
    @Override
    public String getMessage() {
        return "Delimiter Missing.";
    }
}
