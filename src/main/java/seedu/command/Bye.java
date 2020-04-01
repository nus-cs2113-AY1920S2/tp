package seedu.command;

import seedu.ui.UI;

public class Bye extends Command {
    @Override
    public void execute() {
        UI.display("Bye, see you again!");
    }
}
