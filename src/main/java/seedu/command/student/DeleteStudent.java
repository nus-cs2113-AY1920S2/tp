package seedu.command.student;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.UI;

import static seedu.duke.Duke.studentListCollection;

public class DeleteStudent extends Command {

    protected int index;
    protected UI ui = new UI();

    public DeleteStudent(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws DukeException {
        try {
            studentListCollection.remove(index - 1);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        System.out.println("Updated Collection:");
        ui.displayStudentListCollection();
    }
}
