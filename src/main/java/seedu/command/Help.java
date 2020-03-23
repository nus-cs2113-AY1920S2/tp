package seedu.command;

import seedu.ui.UI;

public class Help extends Command {
    UI ui;

    public Help() {
        this.ui = new UI();
    }

    public void execute() {
        ui.printGetHelp();
    }
}
