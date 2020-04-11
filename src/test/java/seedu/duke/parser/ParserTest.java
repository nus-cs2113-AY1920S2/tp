package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddAppointmentCommand;
import seedu.duke.command.AddPatientCommand;
import seedu.duke.command.ClearAllCommand;
import seedu.duke.command.ClearAppointmentCommand;
import seedu.duke.command.ClearPatientCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteAppointmentCommand;
import seedu.duke.command.DeletePatientCommand;
import seedu.duke.command.EditAppointmentCommand;
import seedu.duke.command.EditPatientCommand;
import seedu.duke.command.FindAppointmentCommand;
import seedu.duke.command.FindPatientCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListAppointmentCommand;
import seedu.duke.command.ListPatientCommand;
import seedu.duke.exceptions.IndexNotIntegerException;
import seedu.duke.exceptions.InvalidIndexException;
import seedu.duke.exceptions.NoFieldCommandException;
import seedu.duke.exceptions.PidEmptyException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.record.Appointment;
import seedu.duke.record.Patient;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.PatientList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    private static Parser p = new Parser();

    @Test
    void testParseCommand_unknownCommand_exceptionCaught() {
        String userInputWithUnknownCommand = "factorial 100000";
        try {
            p.parseCommand(userInputWithUnknownCommand);
        } catch (Exception e) {
            assertEquals("Unknown command", e.getLocalizedMessage());
        }
    }

    @Test
    void testParseCommand_addPatientCommand_isAddCommand() {
        String addPatientUserInput1 = "addp \\name Justin \\age \\address";
        String addPatientUserInput2 = "addp                        \\unknown \\age 23 \\name \\12333";

        try {
            Command type1 = p.parseCommand(addPatientUserInput1);
            Command type2 = p.parseCommand(addPatientUserInput2);

            assertTrue(type1 instanceof AddPatientCommand);
            assertTrue(type2 instanceof AddPatientCommand);
        } catch (Exception e) {
            fail("Should not have thrown any exceptions");

        }
    }

    @Test
    void testParseCommand_addPatientCommand_isNotAddCommand() {
        final String[] testInputs = {"deletep \\index 3", "edip \\index -100 \\name Justin \\age 23 \\address Pasir "
                + "Ris", "listp"};

        for (String userInputs : testInputs) {
            try {
                Command type = p.parseCommand(userInputs);
                assertFalse(type instanceof AddPatientCommand);

            } catch (Exception e) {
                assertEquals("Unknown command", e.getLocalizedMessage());
            }
        }
    }

    @Test
    void tetParseCommand_editPatientCommand_isEditCommand() {
        String editPatientUserInput1 = "editp \\index 5 \\name Himiko";
        String editPatientUserInput2 = "editp       \\index 3 \\name \\age 23 \\address pasir ris";
        String editPatientUserInput3 = "         editp  \\index 4 \\phone 97283449";

        PatientList stub = new PatientList();
        Patient newPatient = new Patient("1", 1, "1", "1", 1);
        for (int i = 0; i < 10; i += 1) {
            PatientList.getPatientList().add(newPatient);
        }

        try {
            Command type1 = p.parseCommand(editPatientUserInput1);
            Command type2 = p.parseCommand(editPatientUserInput2);
            Command type3 = p.parseCommand(editPatientUserInput3);

            assertTrue(type1 instanceof EditPatientCommand);
            assertTrue(type2 instanceof EditPatientCommand);
            assertTrue(type3 instanceof EditPatientCommand);

        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    void testParseCommand_editPatientCommand_isNotEditCommand() {
        final String[] testInputs = {"adda \\index 3", "addp               \\unknown \\age \\name J \\12333", "list"};

        for (String userInput : testInputs) {
            try {
                Command type = p.parseCommand(userInput);
                assertFalse(type instanceof EditPatientCommand);

            } catch (UnknownCommandException e) {
                assertEquals("Unknown command", e.getLocalizedMessage());
            } catch (NoFieldCommandException e) {
                assertEquals("Please ensure that at least one field for adda is provided", e.getLocalizedMessage());
            } catch (Exception e) {
                assertTrue(true);
            }
        }
    }

    @Test
    void testParseCommand_deletePatientCommand_isDeleteCommand() {
        String deletePatientUserInput1 = "         deletep  \\index 4      \\\\\\\\\\";
        String deletePatientUserInput2 = "           deletep \\index 10";
        String deletePatientUserInput3 = "deletep               \\index 1";

        PatientList stub = new PatientList();
        Patient newPatient = new Patient("1", 1, "1", "1", 1);
        for (int i = 0; i < 10; i += 1) {
            PatientList.getPatientList().add(newPatient);
        }

        try {
            Command type1 = p.parseCommand(deletePatientUserInput1);
            Command type2 = p.parseCommand(deletePatientUserInput2);
            Command type3 = p.parseCommand(deletePatientUserInput3);

            assertTrue(type1 instanceof DeletePatientCommand);
            assertTrue(type2 instanceof DeletePatientCommand);
            assertTrue(type3 instanceof DeletePatientCommand);

        } catch (Exception e) {
            fail("Should not have thrown any exceptions");
        }
    }

    @Test
    void testParseCommand_deletePatientCommand_isNotDeleteCommand() {
        final String[] testInputs = {"addp   \\unknown \\age 23 \\name \\12333", "     listp", "edipt      \\index 0"};

        for (String userInput : testInputs) {
            try {
                Command type = p.parseCommand(userInput);
                assertFalse(type instanceof DeletePatientCommand);

            } catch (Exception e) {
                assertEquals("Unknown command", e.getLocalizedMessage());
            }
        }
    }

    @Test
    void testParseCommand_listPatientCommand() {

        final String listPatientUserInput1 = "listp";
        final String listPatientUserInput2 = "          listp                  ";
        final String listPatientUserInput3 = "listp \\name Justin";
        final String listPatientUserInput4 = "listp \\\\";
        final String listPatientUserInput5 = "addp \\name Justin \\address";
        final String listPatientUserInput6 = "list\\";

        try {
            Command type1 = p.parseCommand(listPatientUserInput1);
            assertTrue(type1 instanceof ListPatientCommand);

            Command type2 = p.parseCommand(listPatientUserInput2);
            assertTrue(type2 instanceof ListPatientCommand);

            Command type3 = p.parseCommand(listPatientUserInput3);
            assertTrue(type3 instanceof ListPatientCommand);

            Command type4 = p.parseCommand(listPatientUserInput4);
            assertFalse(type4 instanceof DeletePatientCommand);

            Command type5 = p.parseCommand(listPatientUserInput5);
            assertFalse(type5 instanceof DeletePatientCommand);

            Command type6 = p.parseCommand(listPatientUserInput6);
            assertFalse(type6 instanceof DeletePatientCommand);

        } catch (Exception e) {
            assertEquals("Unknown command", e.getLocalizedMessage());
        }
    }

    @Test
    void testParseCommand_clearPatientListCommand() {
        final String[] testInputs = {"clearp", "    clearp", "clearp     ", "clearp \\name Justin"};

        for (String userInputs : testInputs) {
            try {
                Command type = p.parseCommand(userInputs);
                assertTrue(type instanceof ClearPatientCommand);
            } catch (Exception e) {
                fail("Should not have caught any exceptions");
            }
        }
    }

    @Test
    void testParseCommand_findPatientCommand_isFindPatientCommand() {
        final String testInput1 = "findp James";

        try {
            Command type = p.parseCommand(testInput1);
            assertTrue(type instanceof FindPatientCommand);
        } catch (Exception e) {
            fail("should not have failed");
        }
    }

    @Test
    void testParseCommand_findPatientCommand_isNotFindPatientCommand() {
        final String testInput1 = "findp";

        try {
            Command type = p.parseCommand(testInput1);
            assertFalse(type instanceof FindPatientCommand);
        } catch (NoFieldCommandException e) {
            assertEquals("Please ensure that at least one field for findp is provided", e.getLocalizedMessage());
        } catch (Exception e) {
            fail("Should not have any other errors");
        }
    }

    @Test
    void testParseCommand_addAppointmentCommand_isAppointmentCommand() {
        final String input1 = "adda \\pid 1 \\time 1234 \\date 22/05/2020";
        final String input2 = "adda \\time   1234   \\date 22/05/2020 \\pid   3  ";
        final String input3 = "adda \\time 2359          \\pid 4            \\date 22/05/2020 ";
        final String input4 = "adda \\date 22/05/2020 \\time 1111            \\pid 4";

        ArrayList<String> testInputs = new ArrayList<>();
        testInputs.add(input1);
        testInputs.add(input2);
        testInputs.add(input3);
        testInputs.add(input4);

        for (String userInput : testInputs) {
            try {
                Command type = p.parseCommand(userInput);
                assertTrue(type instanceof AddAppointmentCommand);
            } catch (Exception e) {
                fail("Should not have caught any exception");
            }
        }
    }

    @Test
    void testParseCommand_addAppointmentCommand_isNotAppointmentCommand() {
        final String input1 = "adda";
        final String input2 = "adda \\time   1234   \\date 22/05/2020 \\pid   ";
        final String input3 = "adda \\pid 4   ";
        final String input4 = "addp \\name Samantha \\age 21";

        ArrayList<String> testInputs = new ArrayList<>();
        testInputs.add(input1);
        testInputs.add(input2);
        testInputs.add(input3);
        testInputs.add(input4);

        for (String userInput : testInputs) {
            try {
                Command type = p.parseCommand(userInput);
                assertFalse(type instanceof AddAppointmentCommand);
            } catch (PidEmptyException e) {
                assertEquals("Please ensure that pid is not empty.", e.getLocalizedMessage());
            } catch (NoFieldCommandException e) {
                assertEquals("Please ensure that at least one field for adda is provided", e.getLocalizedMessage());
            } catch (Exception e) {
                fail("Should not have thrown any other exceptions");
            }
        }
    }

    @Test
    void testParseCommand_editAppointmentCommand_isEditAppointmentCommand() {

        ArrayList<String> testInputs = new ArrayList<>();

        final String input1 = "edita \\index 1 \\date 22/05/2020 \\time 2131";
        final String input2 = "edita \\date 12/12/2020 \\index 4 \\time     1200    ";
        final String input3 = "edita \\time 0000 \\date 21/05/2020 \\index 10   ";
        final String input4 = "edita \\time 2359 \\date 12/12/2020 \\index 2";

        testInputs.add(input1);
        testInputs.add(input2);
        testInputs.add(input3);
        testInputs.add(input4);

        new AppointmentList();

        try {
            Appointment newAppointment = new Appointment("22/05/2020", "1234", 0);
            for (int i = 0; i < 10; i += 1) {
                AppointmentList.getAppointmentList().add(newAppointment);
            }
        } catch (Exception e) {
            fail("should not have failed");
        }

        for (String userInput : testInputs) {
            try {
                Command type = p.parseCommand(userInput);
                assertTrue(type instanceof EditAppointmentCommand);
            } catch (Exception e) {
                fail("Should not have failed");
            }
        }
    }

    @Test
    void testParseCommand_editAppointmentCommand_isNotEditAppointmentCommand() {

        final String input1 = "edita \\index a \\date 22/05/2020 \\time 2131";
        final String input2 = "edita \\date 12/12/2020 \\index -1 \\time     1200    ";
        final String input3 = "edita \\index 10   ";
        final String input4 = "editaa \\time 2359 \\date 12/12/2020 \\index 2";

        ArrayList<String> testInputs = new ArrayList<>();
        testInputs.add(input1);
        testInputs.add(input2);
        testInputs.add(input3);
        testInputs.add(input4);

        for (String userInput : testInputs) {
            try {
                Command type = p.parseCommand(userInput);
                assertFalse(type instanceof EditAppointmentCommand);

            } catch (InvalidIndexException e) {
                assertEquals("Please ensure that the index for edita is valid", e.getLocalizedMessage());

            } catch (IndexNotIntegerException e) {
                assertEquals("Please input a valid integer that is within range of the list as the index for the "
                                + "command edita",
                        e.getLocalizedMessage());

            } catch (NoFieldCommandException e) {
                assertEquals("Please ensure that at least one field for edita is provided",
                        e.getLocalizedMessage());

            } catch (UnknownCommandException e) {
                assertEquals("Unknown command", e.getLocalizedMessage());
            } catch (Exception e) {
                fail("Should not have any other failures");
            }
        }
    }

    @Test
    void testParseCommand_deleteAppointmentCommand() {

        final String input1 = "deletea \\index 1            ";
        final String input2 = "   deletea \\index 3  ";
        final String input3 = "deletea \\index        10";
        final String input4 = "deletea         \\index 1";

        ArrayList<String> testInputsCorrect = new ArrayList<>();
        testInputsCorrect.add(input1);
        testInputsCorrect.add(input2);
        testInputsCorrect.add(input3);
        testInputsCorrect.add(input4);

        final String wInput1 = "deletea ";
        final String wInput2 = "   deletea \\index -6  ";
        final String wInput3 = "deletea \\index        b";
        final String wInput4 = "addp \\name KanBenShite";

        ArrayList<String> testInputsWrong = new ArrayList<>();
        testInputsWrong.add(wInput1);
        testInputsWrong.add(wInput2);
        testInputsWrong.add(wInput3);
        testInputsWrong.add(wInput4);

        new AppointmentList();
        try {
            Appointment newAppointment = new Appointment("22/05/2020", "1234", 0);
            for (int i = 0; i < 10; i += 1) {
                AppointmentList.getAppointmentList().add(newAppointment);
            }
        } catch (Exception e) {
            fail("should not have failed");
        }

        for (String correctUserInput : testInputsCorrect) {
            try {
                Command type = p.parseCommand(correctUserInput);
                assertTrue(type instanceof DeleteAppointmentCommand);
            } catch (Exception e) {
                fail("should not have failed");
            }
        }

        for (String wrongUserInput : testInputsWrong) {
            try {
                Command type = p.parseCommand(wrongUserInput);
                assertFalse(type instanceof DeleteAppointmentCommand);

            } catch (InvalidIndexException e) {
                assertEquals("Please ensure that the index for deletea is valid", e.getLocalizedMessage());

            } catch (IndexNotIntegerException e) {
                assertEquals("Please input a valid integer that is within range of the list as the index for the "
                                + "command deletea",
                        e.getLocalizedMessage());

            } catch (Exception e) {
                fail("Should not have caught any other errors");
            }
        }
    }

    @Test
    void testParseCommand_listAppointmentCommand() {
        final String testInput1 = "lista";
        final String testInput2 = "lista         \\someWeirdStuff";
        final String testInput3 = "         lista";

        ArrayList<String> testInputs = new ArrayList<>();
        testInputs.add(testInput1);
        testInputs.add(testInput2);
        testInputs.add(testInput3);

        for (String userInput : testInputs) {
            try {
                Command type = p.parseCommand(userInput);
                assertTrue(type instanceof ListAppointmentCommand);

            } catch (Exception e) {
                fail("Should not have failed");
            }
        }
    }

    @Test
    void testParseCommand_clearAppointmentCommand() {
        final String testInput1 = "cleara";

        try {
            Command type = p.parseCommand(testInput1);
            assertTrue(type instanceof ClearAppointmentCommand);

        } catch (Exception e) {
            fail("Should not have failed");
        }
    }

    @Test
    void testParseCommand_findAppointmentCommand_isFindAppointmentCommand() {
        final String testInput1 = "finda 22/05/2020";

        try {
            Command type = p.parseCommand(testInput1);
            assertTrue(type instanceof FindAppointmentCommand);
        } catch (Exception e) {
            fail("should not have failed");
        }
    }

    @Test
    void testParseCommand_findAppointmentCommand_isNotFindAppointmentCommand() {
        final String testInput1 = "finda";

        try {
            Command type = p.parseCommand(testInput1);
            assertFalse(type instanceof FindAppointmentCommand);
        } catch (NoFieldCommandException e) {
            assertEquals("Please ensure that at least one field for finda is provided", e.getLocalizedMessage());
        } catch (Exception e) {
            fail("Should not have any other errors");
        }
    }

    @Test
    void testParseCommand_clearAllCommand() {
        final String testInput1 = "clearall";

        try {
            Command type = p.parseCommand(testInput1);
            assertTrue(type instanceof ClearAllCommand);

        } catch (Exception e) {
            fail("Should not have failed");
        }
    }

    @Test
    void testParseCommand_helpCommand() {
        final String helpUserInput1 = "help";
        final String helpUserInput2 = "       help";
        final String helpUserInput3 = "help          \\name Justin \\age 23 \\address pasir ris";
        final String helpUserInput4 = "help ppppppp";
        final String helpUserInput5 = "addp \\name Justin \\age 23 \\address pasir ris \\phone 999";

        try {
            Command type1 = p.parseCommand(helpUserInput1);
            assertTrue(type1 instanceof HelpCommand);

            Command type2 = p.parseCommand(helpUserInput2);
            assertTrue(type2 instanceof HelpCommand);

            Command type3 = p.parseCommand(helpUserInput3);
            assertTrue(type3 instanceof HelpCommand);

            Command type4 = p.parseCommand(helpUserInput4);
            assertTrue(type4 instanceof HelpCommand);

            Command type5 = p.parseCommand(helpUserInput5);
            assertFalse(type5 instanceof HelpCommand);

        } catch (Exception e) {
            assertEquals("Patient's phone numbers should contain 8 numbers and no whitespaces between digits",
                    e.getLocalizedMessage());
        }
    }

    @Test
    void testParseCommand_exitCommand() {
        final String[] testInputs = {"bye", "bye \\age", "exit", "exit2", "exit \\name Justin \\age"};

        for (String userInputs : testInputs) {
            try {
                Command type = p.parseCommand(userInputs);
            } catch (Exception e) {
                assertEquals("Unknown command", e.getLocalizedMessage());
            }
        }
    }
}