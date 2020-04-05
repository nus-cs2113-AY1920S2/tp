package seedu.dietmanager.logic;

public class Result {

    private String commandResult;

    public Result(String commandResult) {
        this.commandResult = commandResult;
    }

    @Override
    public String toString() {
        return this.commandResult;
    }

}
