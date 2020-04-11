package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Appointment;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentListTest {
    private AppointmentList testAppointmentList = new AppointmentList();
    private Appointment testAppointment1 = new Appointment("29/03/2020", "1300", 1);
    private Appointment testAppointment2 = new Appointment("28/03/2020", "1400", 1);
    private Appointment testAppointment3 = new Appointment("27/03/2020", "1500", 1);

    AppointmentListTest() throws ParseException {
    }

    @Test
    void appointmentListTest_Add() {
        AppointmentList.getAppointmentList().add(testAppointment1);
        AppointmentList.getAppointmentList().add(testAppointment2);
        AppointmentList.getAppointmentList().add(testAppointment3);
        assertEquals(3, AppointmentList.getTotalAppointments());
    }

    @Test
    void appointmentListTest_GetInfo() {
        AppointmentList.getAppointmentList().add(testAppointment1);
        assertEquals("Sun 29 Mar 2020", AppointmentList.getAppointmentRecord(0).getDate());
        assertEquals("01:00 PM", AppointmentList.getAppointmentRecord(0).getTime());
    }

    @Test
    void appointmentListTest_Delete() {
        AppointmentList.getAppointmentList().add(testAppointment1);
        AppointmentList.getAppointmentList().add(testAppointment2);
        AppointmentList.getAppointmentList().add(testAppointment3);
        testAppointmentList.removeAppointmentRecord(0);
        assertEquals(2, AppointmentList.getTotalAppointments());
        assertEquals("Sat 28 Mar 2020", AppointmentList.getAppointmentRecord(0).getDate());

    }
}