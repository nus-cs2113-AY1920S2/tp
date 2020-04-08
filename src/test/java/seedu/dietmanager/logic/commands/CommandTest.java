package seedu.dietmanager.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.commands.profile.CheckWeightRecordCommand;
import seedu.dietmanager.logic.commands.profile.DeleteWeightCommand;
import seedu.dietmanager.logic.commands.profile.SetWeightCommand;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CommandTest {

    @Test
    void setWeightCommand() {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John",20, "Male", 180, 80, 75);
        Command command = new SetWeightCommand("set-weight","50");
        command.execute(profile, ui);
        assertEquals("Your weight has been changed to 50.00.", command.resultString);
    }

    @Test
    void deleteWeightCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John",20, "Male", 180, 80, 75);
        Command command = new DeleteWeightCommand("delete-weight","1");
        command.execute(profile, ui);
        assertEquals("Weight Record: 80.0kg  has been removed successfully!", command.resultString);
    }

    @Test
    void checkWeightRecordCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John",20, "Male", 180, 80, 75);
        Command command = new CheckWeightRecordCommand("check-weight-progress", "John");
        command.execute(profile, ui);
        assertEquals("\nNo Pain No Gain! You have not lost weight yet! Strive on!\n\n"
                + "0.00 kg more to go to meet your dream girl/boy!", command.resultString);
    }
}
