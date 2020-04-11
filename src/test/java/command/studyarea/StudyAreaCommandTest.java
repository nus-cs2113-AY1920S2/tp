package command.studyarea;

import org.junit.jupiter.api.Test;
import studyarea.StudyAreaList;
import ui.Ui;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static ui.Constants.EMPTY_LOCATION;
import static ui.Constants.SHORT_DESCRIPTION;
import static ui.Constants.WRONG_FLAG_USAGE;

//@@author NizarMohd

class StudyAreaCommandTest {

    /**
     * throws an exception pertaining to blank commands.
     */
    @Test
    void validateCommand_throwsException_blankCommand() {
        try {
            String command = "";
            command = StudyAreaCommand.validateCommand(command);
            fail("should have raised an exception");
        } catch (Exception e) {
            assertEquals(EMPTY_LOCATION, e.getMessage());
        }

    }

    /**
     * throws an exception pertaining to short commands.
     */
    @Test
    void validateCommand_throwsException_shortCommand() {
        try {
            String command = "a";
            command = StudyAreaCommand.validateCommand(command);
            fail("should have raised an exception");
        } catch (Exception e) {
            assertEquals(SHORT_DESCRIPTION, e.getMessage());
        }
    }

    /**
     * throws an exception pertaining to wrong commands.
     */
    @Test
    void filterCommand_throwsException_wrongCommand() {
        try {
            String command = "-z";
            boolean b = StudyAreaCommand.filterCommand(command, new StudyAreaList(new ArrayList<>()), new Ui());
        } catch (Exception e) {
            assertEquals(WRONG_FLAG_USAGE, e.getMessage());
        }
    }

}