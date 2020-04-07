package seedu.happypills.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.Command;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


//@@author NyanWunPaing
/**
 * Contains all the tests for Patient Parser class.
 */
class PatientParserTest {

    Command command;
    PatientParser parser = new PatientParser();
    private static PatientMap filledPatientMap;
    private static PatientMap emptyPatientMap;

    @Test
    void parseDeletePatientCommand_invalidNric_throwException() {
        try {
            command = parser.parse("delete patient");
            fail("Invalid delete patient command not identified");
        } catch (HappyPillsException e) {
            assertEquals(Messages.MESSAGE_INCORRECT_INPUT_FORMAT,e.getMessage());
        }
    }
}