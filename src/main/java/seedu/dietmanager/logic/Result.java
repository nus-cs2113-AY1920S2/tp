package seedu.dietmanager.logic;

public class Result {

    private String commandResult;

    public Result(String commandResult) {
        this.commandResult = commandResult;
    }

    public String showResult() {
        return this.commandResult;
    }

}
