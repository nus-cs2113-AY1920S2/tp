package seedu.happypills.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.Command;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author itskesin
/**
 * Contains all the tests for Patient Record Parser class.
 */
class PatientRecordParserTest {
    Command command;
    PatientRecordParser parser = new PatientRecordParser();
    private static PatientMap filledPatientMap;
    private static PatientMap emptyPatientMap;

    @Test
    void parseDeletePatientRecordCommand_invalidIndex_throwException() {
        try {
            command = parser.parse("delete pr S9876543F abc");
            fail("Invalid delete pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Invalid index.",e.getMessage());
        }
    }

    @Test
    void parseDeletePatientRecordCommand_invalidNric_throwException() {
        try {
            command = parser.parse("delete pr abc 1");
            fail("Invalid delete pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Invalid NRIC format.",e.getMessage());
        }
    }

    @Test
    void parseEditPatientRecordCommand_invalidIndex_throwException() {
        try {
            command = parser.parse("edit pr S9876543F abc");
            fail("Invalid edit pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Invalid index.",e.getMessage());
        }
    }

    @Test
    void parseEditPatientRecordCommand_insufficientInformation_throwException() {
        try {
            command = parser.parse("edit pr S9876543F");
            fail("Invalid edit pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Please input all necessary fields for the edit command.",e.getMessage());
        }
    }

    @Test
    void parseEditPatientRecordCommand_invalidNric_throwException() {
        try {
            command = parser.parse("edit pr abc 1");
            fail("Invalid edit pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Invalid NRIC format.",e.getMessage());
        }
    }

    @Test
    void parsePatientRecordCommand_invalidCommand_throwException() {
        try {
            command = parser.parse("get pr");
            fail("Invalid pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Command is invalid. Enter help to know how to use HappyPills.",e.getMessage());
        }
    }

    @Test
    void parseListPatientRecordCommand_invalidNric_throwException() {
        try {
            command = parser.parse("list pr 1234");
            fail("Invalid list pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Invalid NRIC format.",e.getMessage());
        }
    }

    @Test
    void parseFindPatientRecordCommand_invalidNric_throwException() {
        try {
            command = parser.parse("find pr S9999 1");
            fail("Invalid add pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Invalid NRIC format.",e.getMessage());
        }
    }

    @Test
    void parseFindPatientRecordCommand_invalidIndex_throwException() {
        try {
            command = parser.parse("find pr S9876543F abc");
            fail("Invalid add pr command not identified");
        } catch (HappyPillsException e) {
            assertEquals("    Invalid index.",e.getMessage());
        }
    }
}