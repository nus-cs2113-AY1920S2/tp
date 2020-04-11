package seedu.duke.exceptions;

import org.junit.jupiter.api.Test;
import seedu.duke.enums.AppointmentFieldKeys;
import seedu.duke.enums.PatientFieldKeys;
import seedu.duke.record.Appointment;
import seedu.duke.record.Patient;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.PatientList;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DukeExceptionsTest {

    @Test
    void doesKeyExist() {
        String[] testArray = {"1", "2"};
        try {
            DukeExceptions.doesKeyExist(testArray);
        } catch (Exception e) {
            fail("Should not have caught any errors");
        }

        String[] testArrayFail = null;

        try {
            DukeExceptions.doesKeyExist(testArrayFail);
            fail("Should not have reached this part");
        } catch (Exception e) {
            assertTrue(true);
        }

        String[] testArrayFail1 = {"1"};

        try {
            DukeExceptions.doesKeyExist(testArrayFail1);
            fail("Should not have reached this part");
        } catch (NoKeyExistException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkIndexValidity() {
        final String command = "test";

        final String correctIndex = "1";

        final String wrongIndex1 = "0";
        final String wrongIndex2 = "-1";
        final String wrongIndex3 = "a";

        try {
            DukeExceptions.checkIndexValidity(correctIndex, command);
        } catch (Exception e) {
            fail("Should not have thrown any exceptions");
        }

        try {
            DukeExceptions.checkIndexValidity(wrongIndex1, command);
        } catch (InvalidIndexException e) {
            assertEquals("Please ensure that the index for " + command + " is valid", e.getLocalizedMessage());

        } catch (Exception e) {
            fail("Should not have thrown any other exceptions");
        }

        try {
            DukeExceptions.checkIndexValidity(wrongIndex2, command);
        } catch (InvalidIndexException e) {
            assertEquals("Please ensure that the index for " + command + " is valid", e.getLocalizedMessage());

        } catch (Exception e) {
            fail("Should not have thrown any other exceptions");
        }

        try {
            DukeExceptions.checkIndexValidity(wrongIndex3, command);
        } catch (IndexNotIntegerException e) {
            assertEquals("Please input a valid integer that is within range of the list as the index for the command "
                    + command, e.getLocalizedMessage());

        } catch (Exception e) {
            fail("Should not have thrown any other exceptions");
        }
    }

    @Test
    void checkFieldEmptyAddPatient() {

        Map<String, String> testMap = new HashMap<>();

        for (PatientFieldKeys pf : PatientFieldKeys.values()) {
            String field = pf.toString();
            testMap.put(field, "");
        }

        try {
            DukeExceptions.checkFieldEmptyAddPatient(testMap);
            fail("Should not have reached here");
        } catch (NoFieldCommandException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        for (PatientFieldKeys pf : PatientFieldKeys.values()) {
            String field = pf.toString();
            testMap.put(field, "1");
        }

        try {
            DukeExceptions.checkFieldEmptyAddPatient(testMap);
        } catch (Exception e) {
            fail("Should not have thrown any errors");
        }
    }

    @Test
    void checkFieldEmptyEditPatient() {

        Map<String, String> testMap = new HashMap<>();

        for (PatientFieldKeys pf : PatientFieldKeys.values()) {
            String field = pf.toString();
            testMap.put(field, "");
        }

        try {
            DukeExceptions.checkFieldEmptyEditPatient(testMap);
            fail("Should not have reached here");
        } catch (NoFieldCommandException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        for (PatientFieldKeys pf : PatientFieldKeys.values()) {
            String field = pf.toString();
            testMap.put(field, "1");
        }

        try {
            DukeExceptions.checkFieldEmptyEditPatient(testMap);
        } catch (Exception e) {
            fail("Should not have thrown any errors");
        }
    }

    @Test
    void checkFieldEmptyAddAppointment() {
        Map<String, String> testMap = new HashMap<>();

        for (AppointmentFieldKeys af : AppointmentFieldKeys.values()) {
            String field = af.toString();
            testMap.put(field, "");
        }

        try {
            DukeExceptions.checkFieldEmptyAddAppointment(testMap);
            fail("Should not have reached here");
        } catch (NoFieldCommandException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        for (AppointmentFieldKeys af : AppointmentFieldKeys.values()) {
            String field = af.toString();
            testMap.put(field, "1");
        }

        try {
            DukeExceptions.checkFieldEmptyAddAppointment(testMap);
        } catch (Exception e) {
            fail("Should not have thrown any errors");
        }
    }

    @Test
    void checkFieldEmptyEditAppointment() {
        Map<String, String> testMap = new HashMap<>();

        for (AppointmentFieldKeys af : AppointmentFieldKeys.values()) {
            String field = af.toString();
            testMap.put(field, "");
        }

        try {
            DukeExceptions.checkFieldEmptyEditAppointment(testMap);
            fail("Should not have reached here");
        } catch (NoFieldCommandException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        for (AppointmentFieldKeys af : AppointmentFieldKeys.values()) {
            String field = af.toString();
            testMap.put(field, "1");
        }

        try {
            DukeExceptions.checkFieldEmptyEditAppointment(testMap);
        } catch (Exception e) {
            fail("Should not have thrown any errors");
        }
    }

    @Test
    void checkPidEmpty() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("pid", "");

        try {
            DukeExceptions.checkPidEmpty(testMap);
        } catch (PidEmptyException e) {
            assertEquals("Please ensure that pid is not empty.", e.getLocalizedMessage());
        }

        testMap.put("pid", "1");

        try {
            DukeExceptions.checkPidEmpty(testMap);
        } catch (Exception e) {
            fail("Should not have thrown any error.");
        }
    }

    @Test
    void checkEmptyLists() {
        new AppointmentList();
        new PatientList();

        try {
            DukeExceptions.checkEmptyLists();
        } catch (EmptyListsException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        Appointment appointment = null;

        try {
            appointment = new Appointment("22/05/2020", "1234", 1);
        } catch (Exception e) {
            fail();
        }

        AppointmentList.getAppointmentList().add(appointment);

        try {
            DukeExceptions.checkEmptyLists();
        } catch (Exception e) {
            fail();
        }

        AppointmentList.getAppointmentList().add(appointment);

        try {
            DukeExceptions.checkEmptyLists();
        } catch (Exception e) {
            fail();
        }

        AppointmentList.getAppointmentList().add(appointment);

        try {
            DukeExceptions.checkEmptyLists();
        } catch (Exception e) {
            fail();
        }

        int a = AppointmentList.getTotalAppointments();
        assertEquals(3, a);
    }

    @Test
    void checkEmptyAppointments() {
        new AppointmentList();
        Appointment appointment = null;

        try {
            DukeExceptions.checkEmptyAppointments();
        } catch (EmptyAppointmentsException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        try {
            appointment = new Appointment("22/05/2020", "1234", 1);
        } catch (Exception e) {
            fail();
        }

        AppointmentList.getAppointmentList().add(appointment);

        try {
            DukeExceptions.checkEmptyLists();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkEmptyPatients() {
        new PatientList();
        Patient patient = null;

        try {
            DukeExceptions.checkEmptyPatients();
        } catch (EmptyPatientsException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        PatientList.getPatientList().add(new Patient("Justin", 10, "Pasir Ris", "91234567", 1));

        try {
            DukeExceptions.checkEmptyPatients();
        } catch (Exception e) {
            fail();
        }
    }
}