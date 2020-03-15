package exceptions;

public class InputMissingException extends ReservationException {
    private String input;
    
    public InputMissingException(String input) {
        this.input = input;
    }
    
    public String getInput() {
        return this.input;
    }
}
