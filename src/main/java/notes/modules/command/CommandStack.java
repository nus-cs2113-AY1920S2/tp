package notes.modules.command;

import ui.Constants;
import ui.Ui;

import java.util.LinkedList;

//@@author terrytay
public class CommandStack {
    /**
     * Adapted from: https://www.developer.com/design
     * /article.php/3720566/Working-With-Design-Patterns-Memento.htm
     */
    private LinkedList<Command> commandStack;
    private LinkedList<Command> redoStack;
    private Ui ui;

    /**
     * To be added by terry.
     * @param ui to be added by terry.
     */
    public CommandStack(Ui ui) {
        this.ui = ui;
        this.commandStack =  new LinkedList<>();
        this.redoStack = new LinkedList<>();
    }

    /**
     * Execute the given command, and add it to a undo stack and redo stack.
     * @param command command parsed
     */
    public void execute(Command command) {
        command.execute();
        commandStack.addFirst(command);
        redoStack.clear();
    }

    /**
     * Undo a command.
     */
    public void undo() {
        if (commandStack.isEmpty()) {
            this.ui.printLine();
            this.ui.printMessage(Constants.NOTHING_TO_UNDO);
            this.ui.printLine();
            return;
        }
        Command command = commandStack.removeFirst();
        command.undo();
        redoStack.addFirst(command);
    }

    /**
     * Redo a command.
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            this.ui.printLine();
            this.ui.printMessage(Constants.NOTHING_TO_REDO);
            this.ui.printLine();
            return;
        }
        Command command = redoStack.removeFirst();
        command.redo();
        commandStack.addFirst(command);
    }
}
