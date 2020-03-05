package command;

public class IncorrectCommand extends Command {
    public final String description;

    public IncorrectCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute() {
        System.out.println("Oh no. " + description);
    }
}
