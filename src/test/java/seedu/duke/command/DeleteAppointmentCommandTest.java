package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Appointment;
import seedu.duke.storage.AppointmentList;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteAppointmentCommandTest {

    @Test
    void execute() throws Exception {
        AppointmentList appointmentList = new AppointmentList();
        AppointmentList.getAppointmentList().add(new Appointment("14/03/2020", "1000", 1));
        AppointmentList.getAppointmentList().add(new Appointment("15/03/2020", "1100", 1));
        AppointmentList.getAppointmentList().add(new Appointment("16/03/2020", "0000", 1));
        AppointmentList.getAppointmentList().add(new Appointment("17/03/2020", "1300", 1));
        AppointmentList.getAppointmentList().add(new Appointment("18/03/2020", "1400", 1));
        Map<String, String> fieldsToChange = new HashMap<>();
        fieldsToChange.put("index", "3");
        DeleteAppointmentCommand command = new DeleteAppointmentCommand(fieldsToChange);
        assertEquals(3, command.getIndex());
    }
}