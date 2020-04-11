package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Appointment;
import seedu.duke.storage.AppointmentList;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditAppointmentCommandTest {

    //Test case 1
    @Test
    void execute_1() throws Exception {
        AppointmentList appointmentList = new AppointmentList();
        AppointmentList.getAppointmentList().add(new Appointment("14/03/2020", "1000", 1));
        AppointmentList.getAppointmentList().add(new Appointment("15/03/2020", "1100", 1));
        AppointmentList.getAppointmentList().add(new Appointment("16/03/2020", "1200", 1));
        AppointmentList.getAppointmentList().add(new Appointment("17/03/2020", "1300", 1));
        AppointmentList.getAppointmentList().add(new Appointment("18/03/2020", "1400", 1));
        Map<String, String> fieldsToChange = new HashMap<>();
        fieldsToChange.put("index", "3");
        fieldsToChange.put("date", "31/12/2020");
        fieldsToChange.put("time", "11:00 pm");
        EditAppointmentCommand command = new EditAppointmentCommand(fieldsToChange);
        assertEquals(3, command.getIndex());
        assertEquals("31/12/2020", command.getDate());
        assertEquals("11:00 pm", command.getTime());
    }


    //Test case 2
    @Test
    void execute_2() throws Exception {
        Map<String, String> fieldsToChange = new HashMap<>();
        fieldsToChange.put("index", "5");
        fieldsToChange.put("date", "");
        fieldsToChange.put("time", "11:00 pm");
        EditAppointmentCommand command = new EditAppointmentCommand(fieldsToChange);
        assertEquals(5, command.getIndex());
        assertEquals("", command.getDate());
        assertEquals("11:00 pm", command.getTime());
    }
}