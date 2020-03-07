package seedu.duke;

public class MarkAttendanceCommand extends Command {

    private int mode;
    public static int index;

    public MarkAttendanceCommand(int index) {
        this.index = index;
        this.mode = 1;
    }


    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        try {
            Attendance student = (Attendance) storage.getTasks().get(index);
            if (student.getAttendance() == "NO") {
                ui.displayMarkAttendance(student);
            } else {
                ui.displayAttendance(student);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.OUT_OF_BOUNDS);
        }
        storage.write();

    }
}
