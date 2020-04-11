package studyarea;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static ui.Constants.DUPLICATE_FLAGS;
import static ui.Constants.NON_POSITIVE_INTEGER;
import static ui.Constants.NOT_INTEGER;
import static ui.Constants.NO_SIZE_INDICATED;
import static ui.Constants.ONLY_FLAG;
import static ui.Constants.WRONG_FLAG_ARGUMENT_POSITION;
import static ui.Constants.WRONG_FLAG_USAGE;

//@@author terrytay
class StudyAreaListTest {

    StudyArea studyArea = new StudyArea("mac commons", "utown", "nil", true, true,
            5);

    /**
     * An exception pertaining to duplicate flag should be raised if the string at flag[index] is not null.
     * A dummy array of flags is created, and the first flag is accessed and used it to check duplicate
     * in the same location. The expected outcome is to return an exception since the string is not null.
     */
    @Test
    void checkDuplicate_gotDuplicate_throwsException() {
        try {
            String[] flag = {"-a", "-b"};
            StudyAreaList.checkDuplicate(flag, 0);
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertEquals(DUPLICATE_FLAGS, e.getMessage());
        }
    }

    /**
     * An exception pertaining to using only hyphen when declaring a flag.
     * A dummy array is made with a flag inside that has no hyphen character. The expected outcome is to return
     * an exception since the input flag has only hyphen.
     */
    @Test
    void checkOnlyFlag_onlyHyphenSign_raiseException() {
        try {
            String[] commands = {"-"};
            StudyAreaList.checkOnlyFlag(commands, 0);
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertEquals(ONLY_FLAG, e.getMessage());
        }
    }

    /**
     * An exception pertaining to either wrong flag position or invalid flag is raised when user
     * enters a flag that does not exist or user enters the flag before the search terms.
     * The expected outcome is to return an exception as this test case inputs an invalid flag.
     */
    @Test
    void checkFlag_FlagDoesNotExist_raiseException() {
        try {
            String[] commands = {"MacCommons", "-z"};
            String[] flags = {null, null, null, null, "MacCommons"};
            StudyAreaList.checkFlag(flags, commands,1, false);
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertEquals(WRONG_FLAG_USAGE, e.getMessage());
        }
    }

    //@@author NizarMohd
    /**
     * An exception pertaining to non Integer commands after using "-s".
     * The expected outcome is to return an exception as the size has to be an integer.
     */
    @Test
    void checkInteger_nonIntegerSize_raiseException() {
        String[] commands = { "-s", "nonInteger"};

        try {
            StudyAreaList.checkInteger(commands, 0);
            fail("Should have raised an exception");
        } catch (Exception e) {
            assertEquals(NOT_INTEGER, e.getMessage());
        }
    }

    /**
     * An exception pertaining to empty integer after using "-s".
     * The expected outcome is to return an exception as the size has to be an integer.
     */
    @Test
    void checkInteger_emptyInteger_raiseException() {
        String[] commands = { "-s"};
        try {
            StudyAreaList.checkInteger(commands,0);
            fail("Should have raised an exception");
        } catch (Exception e) {
            assertEquals(NO_SIZE_INDICATED, e.getMessage());
        }
    }

    /**
     * An exception pertaining to non positive Integer after using "-s".
     * The expected outcome is to return an exception as the size has to be an integer.
     */
    @Test
    void checkInteger_nonPositiveInteger_raiseException() {
        String[] commands = { "-s", "-1"};

        try {
            StudyAreaList.checkInteger(commands,0);
            fail("Should have raised an exception");
        } catch (Exception e) {
            assertEquals(NON_POSITIVE_INTEGER, e.getMessage());
        }
    }

    /**
     * An exception pertaining to wrong position of flags when entering commands.
     * The expected outcome is to return an exception as flags must come after location/name.
     */
    @Test
    void checkFlag_wrongFlagsPosition_raiseException() {
        String[] commands = { "-i", "EA"};
        String[] flags = { null, null, null, "-i", "EA"};
        try {
            StudyAreaList.checkFlag(flags, commands,1, false);
            fail("Should have raised an exception");
        } catch (Exception e) {
            assertEquals(WRONG_FLAG_ARGUMENT_POSITION, e.getMessage());
        }
    }

    /**
     * An exception pertaining wrong flag usage.
     */
    @Test
    void getFlagsInfo_throwsException_WrongFlag() {
        String[] commands = {"utown", "-z"};
        try {
            String[] expectedFlags = StudyAreaList.getFlagsInfo(commands);
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertEquals(WRONG_FLAG_USAGE, e.getMessage());
        }
    }

    /**
     * Test to check accuracy of method.
     */
    @Test
    void isAvailStudyArea_assertTrue_location() {
        String[] flags = {null, null, null, null, "utown"};
        assertTrue(StudyAreaList.isAvailStudyArea("utown", true, 4, studyArea, flags));
    }

    /**
     * Test to check accuracy of method.
     */
    @Test
    void isAvailStudyArea_assertFalse_location() {
        String[] flags = {null, null, null, null, "engine"};
        assertFalse(StudyAreaList.isAvailStudyArea("engine", true, 4, studyArea, flags));

    }

    /**
     * Test to check accuracy of method.
     */
    @Test
    void isAvailStudyArea_assertTrue_flag() {
        String[] flags = {null, null, "-i", null, null};
        assertTrue(StudyAreaList.isAvailStudyArea("-i", true, 2, studyArea, flags));
    }

    /**
     * Test to check accuracy of method.
     */
    @Test
    void isAvailStudyArea_assertFalse_flag() {
        String[] flags = {null, null, "-o", null, null};
        assertFalse(StudyAreaList.isAvailStudyArea("-o", true, 2, studyArea, flags));
    }

    /**
     * Test to check if exception is raised until searchList level.
     */
    @Test
    void searchList_throwsException() {
        String userIn = "-s"; // test empty integer exception
        try {
            ArrayList<StudyArea> availStudyAreas = new ArrayList<>();
            availStudyAreas.add(studyArea);
            StudyAreaList studyAreaList = new StudyAreaList(availStudyAreas);
            studyAreaList.searchList(userIn);
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertEquals(NO_SIZE_INDICATED, e.getMessage());
        }
    }
}