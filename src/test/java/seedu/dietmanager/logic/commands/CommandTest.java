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
    void AddFoodCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new AddFoodCommand("addf", "Fried-rice --550");
        command.execute(profile, ui);
        assertEquals(MessageBank.ADDED_FOOD_ALREADY_EXIST_MESSAGE, command.getResult(profile).showResult());

        command = new AddFoodCommand("addf", "Fried-chicken ---500");
        command.execute(profile, ui);
        assertEquals(MessageBank.INCORRECT_CALORIES_INFO_MESSAGE, command.getResult(profile).showResult());

        command = new AddFoodCommand("addf", "Fried-chicken --990");
        command.execute(profile, ui);
        assertEquals(MessageBank.NEW_FOOD_ADDED_MESSAGE + "Food: fried-chicken, Calories: 990.0",
                command.getResult(profile).showResult());
    }

    @Test
    void CheckRequiredCaloriesCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        Command command = new CheckRequiredCaloriesCommand("check-required-cal", "monday low");
        command.execute(profile, ui);
        assertEquals(MessageBank.INVALID_PROFILE_MESSAGE, command.getResult(profile).showResult());
    }

    @Test
    void CheckBmiCommand(){
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new CheckBmiCommand("check-bmi");
        command.execute(profile, ui);
        assertEquals(MessageBank.BMI_TABLE_MESSAGE + MessageBank.BMI_TABLE_LEGEND + MessageBank.BMI_TABLE,
                command.getResult(profile).showResult());
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
    void deleteWeightCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new DeleteWeightCommand("delete-weight", "1");
        command.execute(profile, ui);
        assertEquals("Weight Record: 80.0kg has been removed successfully!", command.resultString);
    }

    @Test
    void RecordMealCommand() throws InvalidFormatException {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new RecordMealCommand("record-meal", "monday morning fried rice");
        command.execute(profile, ui);
        assertEquals(MessageBank.BREAKFAST_RECORD_MESSAGE + "MONDAY.", command.getResult(profile).showResult());

        command = new RecordMealCommand("record-meal", "monday afternoon fried chicken --990");
        command.execute(profile, ui);
        assertEquals(MessageBank.LUNCH_RECORD_MESSAGE + "MONDAY.", command.getResult(profile).showResult());

        command = new RecordMealCommand("record-meal", "monday night apple --50 / pear --60");
        command.execute(profile, ui);
        assertEquals(MessageBank.DINNER_RECORD_MESSAGE + "MONDAY.", command.getResult(profile).showResult());

        command = new RecordMealCommand("record-meal", "everyday night fried rice");
        command.execute(profile, ui);
        assertEquals(MessageBank.INVALID_DATE_MESSAGE, command.getResult(profile).showResult());

        command = new RecordMealCommand("record-meal", "monday evening fried rice");
        command.execute(profile, ui);
        //assertEquals(MessageBank.INVALID_DATE_MESSAGE, command.getResult(profile).showResult());
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

    @Test
    void setGenderCommand() {
        Profile profile = new Profile();
        UI ui = new UI();

        Command command = new SetGenderCommand("set-weight", "male");
        command.execute(profile, ui);
        assertEquals(MessageBank.INVALID_PROFILE_MESSAGE, command.resultString);

        profile.setProfile("John", 20, "FeMaLe", 180, 80, 75);
        command = new SetGenderCommand("set-weight", "male");
        command.execute(profile, ui);
        assertEquals(MessageBank.GENDER_CHANGE_MESSAGE + "male.", command.resultString);

        command = new SetGenderCommand("set-weight", "shemale");
        command.execute(profile, ui);
        assertEquals(MessageBank.NO_DESCRIPTION_MESSAGE, command.resultString);
    }

    @Test
    void setWeightCommand() {
        Profile profile = new Profile();
        UI ui = new UI();
        profile.setProfile("John", 20, "Male", 180, 80, 75);
        Command command = new SetWeightCommand("set-weight", "50");
        command.execute(profile, ui);
        assertEquals("Your weight has been changed to 50.00.", command.resultString);
    }
}
