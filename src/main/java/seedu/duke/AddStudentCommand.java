package seedu.duke;


/**
 * Class representing a command to add a new task.
 * Types of task that can be created - Todo, Deadline, Event
 */
public class AddStudentCommand extends Command {

    private final Student student;

    public AddStudentCommand(Student student) {
        this.student = student;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        Ui.displayAddStudent();
        storage.getTasks().add(student);
        storage.write();
    }
}
