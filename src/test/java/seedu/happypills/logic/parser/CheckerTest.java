package seedu.happypills.logic.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@@author itskesin
/**
 * Contains all the tests for Validation class.
 */
class CheckerTest {

    @Test
    void invalidPhoneNumber() {
        assertFalse(Checker.isValidPhoneNum("123456789"));
        assertFalse(Checker.isValidPhoneNum("       "));
        assertFalse(Checker.isValidPhoneNum("qwerty"));
        assertFalse(Checker.isValidPhoneNum("-12345678"));
        assertFalse(Checker.isValidPhoneNum(""));
    }

    @Test
    void invalidDate() {
        assertFalse(Checker.isValidDate("18June"));
        assertFalse(Checker.isValidDate("4/20/2020"));
        assertFalse(Checker.isValidDate("4/-20/2020"));
        //assertFalse(Validation.isValidDate("4/04/2020"));
        //assertFalse(Validation.isValidDate("04/4/2020"));
        assertFalse(Checker.isValidDate("444/4/2020"));
        assertFalse(Checker.isValidDate("-44/4/2020"));
        assertFalse(Checker.isValidDate("04/04/20202"));
        assertFalse(Checker.isValidDate("04/04/-44"));
        assertFalse(Checker.isValidDate("04/04"));
        assertFalse(Checker.isValidDate("04/04///////"));
        assertFalse(Checker.isValidDate("04/04/              2020"));
        assertFalse(Checker.isValidDate("       "));
        assertFalse(Checker.isValidDate(""));
    }

    @Test
    void invalidTime() {
        assertFalse(Checker.isValidTime("HH:MM"));
        assertFalse(Checker.isValidTime("     "));
        assertFalse(Checker.isValidTime(""));
        assertFalse(Checker.isValidTime("22:2222"));
        assertFalse(Checker.isValidTime("22:-12"));
        assertFalse(Checker.isValidTime("22:"));
        assertFalse(Checker.isValidTime("24:12"));
        assertFalse(Checker.isValidTime("24:00"));
        assertFalse(Checker.isValidTime("-12:00"));
        assertFalse(Checker.isValidTime(":00"));
        assertFalse(Checker.isValidTime("123456:00"));
        assertFalse(Checker.isValidTime("00:00:00"));
        assertFalse(Checker.isValidTime("00:00:"));
        assertFalse(Checker.isValidTime("00:59.4356789"));
        assertFalse(Checker.isValidTime("12.324567543:59"));
    }

    @Test
    void invalidBloodType() {
        assertFalse(Checker.isValidBloodType("        "));
        assertFalse(Checker.isValidBloodType("OS"));
        assertFalse(Checker.isValidBloodType("O"));
        assertFalse(Checker.isValidBloodType(""));
        assertFalse(Checker.isValidBloodType("1234567"));
        assertFalse(Checker.isValidBloodType("AB"));
        //assertFalse(Checker.isValidBloodType("AB+"));
    }

    @Test
    void invalidNric() {
        assertFalse(Checker.isValidNric("        "));
        assertFalse(Checker.isValidNric(""));
        assertFalse(Checker.isValidNric("S9820463"));
        assertFalse(Checker.isValidNric("S12345679"));
        assertFalse(Checker.isValidNric("-123456"));
        assertFalse(Checker.isValidNric("qwerty"));
        assertFalse(Checker.isValidNric("12345"));
        assertFalse(Checker.isValidNric("99999999F"));
        assertFalse(Checker.isValidNric("999999999"));
    }

    @Test
    void invalidNumber() {
        assertFalse(Checker.isNumeric("-12354678"));
        assertFalse(Checker.isNumeric("qwerty"));
        assertFalse(Checker.isNumeric("1.11111"));
        assertFalse(Checker.isNumeric("!@#$%^&*"));
    }

    @Test
    void invalidInteger() {
        assertFalse(Checker.isNumeric("-12354678"));
        assertFalse(Checker.isNumeric("qwerty"));
        assertFalse(Checker.isNumeric("1.11111"));
        assertFalse(Checker.isNumeric("!@#$%^&*"));
    }
}