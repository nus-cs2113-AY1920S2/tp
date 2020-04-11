package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Appointment;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindAppointmentCommandTest {
    private Ui ui;
    private Storage storage;

    @Test
    void findAppointment_TimeException() throws Exception {
        AppointmentList appointmentList = new AppointmentList();
        boolean checkIsExceptionThrown = false;
        appointmentList.getAppointmentList().add(new Appointment("14/03/2020", "1000", 1));
        appointmentList.getAppointmentList().add(new Appointment("15/03/2020", "1100", 1));
        appointmentList.getAppointmentList().add(new Appointment("16/03/2020", "0000", 1));
        appointmentList.getAppointmentList().add(new Appointment("17/03/2020", "1300", 1));
        appointmentList.getAppointmentList().add(new Appointment("18/03/2020", "1400", 1));
        FindAppointmentCommand testCommand = new FindAppointmentCommand("2500");
        try {
            testCommand.execute(ui, storage);
        } catch (Exception e) {
            checkIsExceptionThrown = true;
        }
        assertTrue(checkIsExceptionThrown);
    }

    @Test
    void findAppointment_DateException() throws Exception {
        AppointmentList appointmentList = new AppointmentList();
        boolean checkIsExceptionThrown = false;
        appointmentList.getAppointmentList().add(new Appointment("14/03/2020", "1000", 1));
        appointmentList.getAppointmentList().add(new Appointment("15/03/2020", "1100", 1));
        appointmentList.getAppointmentList().add(new Appointment("16/03/2020", "0000", 1));
        appointmentList.getAppointmentList().add(new Appointment("17/03/2020", "1300", 1));
        appointmentList.getAppointmentList().add(new Appointment("18/03/2020", "1400", 1));
        FindAppointmentCommand testCommand = new FindAppointmentCommand("32/13/2020");
        try {
            testCommand.execute(ui, storage);
        } catch (Exception e) {
            checkIsExceptionThrown = true;
        }
        assertTrue(checkIsExceptionThrown);
    }
}
