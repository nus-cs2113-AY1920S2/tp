package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.List;

/**
 * Stub class for ListAppointmentCommandTest.
 */
public class ListAppointmentCommandStub extends ListAppointmentCommand {
    public ListAppointmentCommandStub() throws InvalidFormatException {
    }

    /**
     * Stub to simulate adding appointments into list.
     *
     * @param ui      stub ui
     * @param storage stub storage
     */
    public void execute(Ui ui, Storage storage) {
        List<AppointmentStub> appointmentStubList = AppointmentListStub.getAppointmentList();
        for (AppointmentStub a : appointmentStubList) {
            System.out.println(a);
        }
    }
}
