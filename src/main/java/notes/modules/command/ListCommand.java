package notes.modules.command;

import ui.Constants;
import ui.Ui;
import java.util.ArrayList;

//@@author terrytay
public class ListCommand implements Command {
    private  Ui ui;
    private ArrayList<String> messages;

    public ListCommand(ArrayList<String> messages, Ui ui) {
        this.messages = messages;
        this.ui = ui;
    }

    /**
     * Displays the notes in the module.
     */
    public void execute() {
        this.ui.printLine();
        if (messages.isEmpty()) {
            this.ui.printMessage(Constants.NO_NOTES_YET);
        } else {
            for (String message : messages) {
                this.ui.printMessage(message);
            }
        }
        this.ui.printLine();
    }

    public void undo() {

    }

    public void redo() {

    }
}
