package exceptions;

public class InputMissingException extends ReservationException {
    private String input;
    
    public InputMissingException(String input) {
        this.input = input;
    }
    
    public String getInput() {
        return this.input;
    }
    
    @Override
    public String getMessage() {
        return String.format("Input Missing: %s is missing.", this.input);
    }
}
