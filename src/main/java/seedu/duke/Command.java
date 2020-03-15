package seedu.duke;

public class Command {

    private String commandType;
    private String description;

    public boolean isExit() {
        return this.commandType.equals("exit");
    }

    /**
     *
     * @param personList all operations should work on this list;
     */
    public void execute(PersonList personList) {

    }
}
