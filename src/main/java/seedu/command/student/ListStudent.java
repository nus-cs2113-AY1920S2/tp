package seedu.command.student;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.DisplayList;

public class ListStudent extends Command {
    private DisplayList displayList = new DisplayList();

    @Override
    public void execute() throws DukeException {
        displayList.printStudentListCollection();
    }
}
