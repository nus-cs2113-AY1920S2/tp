package seedu.command.student;

import seedu.StudentList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.UI;

import static seedu.duke.Duke.studentListCollection;

public class ListStudent extends Command {

    UI ui = new UI();

    @Override
    public void execute() throws DukeException {
        ui.displayStudentListCollection();
    }
}
