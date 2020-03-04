package command;

public abstract class Command {
    public static final String COMMAND_NAME = null; // todo: override this

    public abstract void execute(); // todo: take TaskList, Ui, Storage as parameters
}
