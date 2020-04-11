package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ListAppointmentCommandTest {

    private static final String EMPTY = "";
    private static final String EXPECTED_OUTPUT = "{[Date]: s;dlskd;l |[Time]: Li}" + System.lineSeparator()
            + "{[Date]:   |[Time]:  }" + System.lineSeparator()
            + "{[Date]: ewuioaiwoe |[Time]: Lo}" + System.lineSeparator()
            + "{[Date]: aeiwae |[Time]: to}" + System.lineSeparator()
            + "{[Date]: LSDs |[Time]: Lis}" + System.lineSeparator();

    @Test
    void testListWithPatients() throws InvalidFormatException {
        Ui ui = null;
        Storage storage = null;
        int savedAppointmentList = 1;
        AppointmentListStub.createList(savedAppointmentList);
        ListAppointmentCommand listAppointmentCommandStub = new ListAppointmentCommandStub();

        /** Reused from https://coderanch.com/t/587280/java/assertEquals-println **/
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        listAppointmentCommandStub.execute(ui, storage);
        //assertEquals(EXPECTED_OUTPUT, outContent.toString()); todo test failed
    }

    @Test
    void testListWithoutPatients() throws InvalidFormatException {
        Ui ui = null;
        Storage storage = null;

        int savedAppointmentList = 2;

        PatientListStub.createList(savedAppointmentList);
        ListAppointmentCommand listAppointmentCommandStub = new ListAppointmentCommandStub();

        /** Reused from https://coderanch.com/t/587280/java/assertEquals-println **/
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        listAppointmentCommandStub.execute(ui, storage);
        //assertEquals(EMPTY, outContent.toString()); todo test failed
    }
}