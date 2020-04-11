package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListPatientCommandTest {

    private static final String EXPECTED_OUTPUT =
            "{[Name]: s;dlskd;l | [Age]: 23 | [Address]: Li | [Contact Number]: 121}" + System.lineSeparator()
                    + "{[Name]:   | [Age]: 15 | [Address]:   | [Contact Number]: 15454455}" + System.lineSeparator()
                    + "{[Name]: ewuioaiwoe | [Age]: 33 | [Address]: Lo | [Contact Number]: 1989}"
                    + System.lineSeparator()
                    + "{[Name]: aeiwae | [Age]: 13 | [Address]: to | [Contact Number]:  }" + System.lineSeparator()
                    + "{[Name]: LSDs | [Age]: -1 | [Address]: Lis | [Contact Number]: 12}" + System.lineSeparator();
    private static final String EMPTY = "";

    @Test
    void testListWithPatients() throws InvalidFormatException {
        Ui ui = null;
        Storage storage = null;
        int savedPatientList = 1;
        PatientListStub.createList(savedPatientList);
        ListPatientCommandStub listPatientCommandStub = new ListPatientCommandStub();

        /** Reused from https://coderanch.com/t/587280/java/assertEquals-println **/
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        listPatientCommandStub.execute(ui, storage);
        assertEquals(EXPECTED_OUTPUT, outContent.toString());


    }

    @Test
    void testListWithoutPatients() throws InvalidFormatException {
        Ui ui = null;
        Storage storage = null;

        int savedPatientList = 2;

        PatientListStub.createList(savedPatientList);
        ListPatientCommandStub listPatientCommandStub = new ListPatientCommandStub();

        /** Reused from https://coderanch.com/t/587280/java/assertEquals-println **/
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        listPatientCommandStub.execute(ui, storage);
        assertEquals(EMPTY, outContent.toString());


    }
}