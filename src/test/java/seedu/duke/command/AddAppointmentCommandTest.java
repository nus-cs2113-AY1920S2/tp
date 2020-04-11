package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class AddAppointmentCommandTest {
    @Test
    void testNormalInput() {
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("date", "asdasd");
        tempMap.put("time", "lklklk");
        try {
            AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(tempMap);
            String date = "asdasd";
            String time = "lklklk";
            assertEquals(date, addAppointmentCommand.getDate());
            assertEquals(time, addAppointmentCommand.getTime());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testEmptyString() {
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("date", "");
        tempMap.put("time", "");
        try {
            AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(tempMap);
            assertTrue(true);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testEmptyStringValue() {
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("date", "");
        tempMap.put("time", "");
        try {
            String date = "";
            String time = "";
            AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(tempMap);
            assertEquals(date, addAppointmentCommand.getDate());
            assertEquals(time, addAppointmentCommand.getTime());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testAddNormalString() throws IOException, ParseException, InvalidFormatException {
        Ui ui = null;
        Storage storage = null;
        Map<String, String> tempMap = AddAppointmentCommandStub.generateMap(1);
        AddAppointmentCommand addCommand = new AddAppointmentCommandStub(tempMap);
        addCommand.execute(ui, storage);
        AppointmentStub tempAppointment =
                AppointmentListStub.getAppointmentList().get(AppointmentListStub.getTotalAppointments() - 1);
        AppointmentStub newAppointment = new AppointmentStub("asd", "asdsds");
        assertEquals(tempAppointment.getDate(), newAppointment.getDate());
        assertEquals(tempAppointment.getTime(), newAppointment.getTime());
    }

    @Test
    void testAddEmptyString() throws IOException, InvalidFormatException {
        Ui ui = null;
        Storage storage = null;
        Map<String, String> tempMap = AddAppointmentCommandStub.generateMap(2);
        AddAppointmentCommandStub addCommand = new AddAppointmentCommandStub(tempMap);
        addCommand.execute(ui, storage);
        AppointmentStub tempAppointment =
                AppointmentListStub.getAppointmentList().get(AppointmentListStub.getTotalAppointments() - 1);
        AppointmentStub newAppointment = new AppointmentStub("", "");
        assertEquals(tempAppointment.getDate(), newAppointment.getDate());
        assertEquals(tempAppointment.getTime(), newAppointment.getTime());
    }

}