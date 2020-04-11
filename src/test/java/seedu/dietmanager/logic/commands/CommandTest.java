package seedu.dietmanager.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    void setWeightCommand() {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new SetWeightCommand("set-weight", "50");
        command.execute(profile, ui);
        assertEquals("Your weight has been changed to 50.00.", command.resultString);
    }

    @Test
    void deleteWeightCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new DeleteWeightCommand("delete-weight", "1");
        command.execute(profile, ui);
        assertEquals("Weight Record: 80.0kg has been removed successfully!", command.resultString);
    }

    @Test
    void checkWeightRecordCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new CheckWeightRecordCommand("check-weight-progress");
        command.execute(profile, ui);
        assertEquals("There has been no change in your weight!" + System.lineSeparator()
                + "-5.00 kg more to go to meet your dream girl/boy!", command.resultString);
    }

    @Test
    void setAgeCommand() {
        Profile profile = new Profile();
        UI ui = new UI();

        Command command = new SetAgeCommand("set-age", "20");
        command.execute(profile, ui);
        assertEquals(Result.class, command.getResult(profile).getClass());
        assertEquals(MessageBank.INVALID_PROFILE_MESSAGE, command.getResult(profile).showResult());

        profile.setProfile("John", 20, "Male", 180, 80, 75);
        command = new SetAgeCommand("set-age", "20");
        command.execute(profile, ui);
        assertEquals(Result.class, command.getResult(profile).getClass());

        command = new SetAgeCommand("set-age", "-10");
        command.execute(profile, ui);
        assertEquals(Result.class, command.getResult(profile).getClass());
        assertEquals(MessageBank.INVALID_AGE_MESSAGE, command.getResult(profile).showResult());
    }


}
