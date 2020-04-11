package exceptions;

public class IngredientMissingException extends Exception {
    private String input;

    public IngredientMissingException(String input) {
        this.input = input;
    }

    public String getInput() {
        return this.input;
    }

    @Override
    public String getMessage() {
        return String.format("Ingredient Missing: %s is missing.", this.input);
    }
}
