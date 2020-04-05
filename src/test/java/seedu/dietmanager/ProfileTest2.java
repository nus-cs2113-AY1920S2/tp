package seedu.dietmanager;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest2 {

    @Test
    void setProfile() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals("John Doe", profile.getName());
    }

    @Test
    void setName() {
        Profile profile = new Profile();
        profile.setProfile("John Quack",20, "Male", 180, 80, 75);
        profile.setName("Jane Quack");
        assertEquals("Jane Quack", profile.getName());
    }

    @Test
    void setGender() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setGender("Female");
        assertEquals("Female", profile.getGender());
    }

    @Test
    void setAge() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setAge(18);
        assertEquals(18, profile.getAge());
    }

    @Test
    void setHeight() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setHeight(170);
        assertEquals(170, profile.getHeight());
    }

    @Test
    void setWeightGoal() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setWeightGoal(50);
        assertEquals(50, profile.getWeightGoal());
    }

    @Test
    void addWeightProgress() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.addWeightProgress(170.0,"wed");
        assertEquals(profile.getWeightProgress(), profile.getWeightProgress());
    }

    @Test
    void setProfileExist() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setProfileExist(false);
        assertEquals(false, profile.isProfileExist());
    }

    @Test
    void isProfileExist() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(true, profile.isProfileExist());
    }

    @Test
    void getHeight() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(180, profile.getHeight());
    }

    @Test
    void getWeight() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(80, profile.getWeight());
    }

    @Test
    void getWeightGoal() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(75, profile.getWeightGoal());
    }

    @Test
    void getAge() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(20, profile.getAge());
    }

    @Test
    void getGender() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals("Male", profile.getGender());
    }

    @Test
    void getName() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals("John Doe", profile.getName());
    }

    @Test
    void getRecordOfDay() {

    }

    @Test
    void testGetRecordOfDay() {
    }

    @Test
    void addNewRecord() {
    }

    @Test
    void getNumOfRecordedDays() {
    }

    @Test
    void setRecordOfDay() {
    }

    @Test
    void showRecordOfDay() {
    }

    @Test
    void showBreakfastOfDay() {
    }

    @Test
    void showLunchOfDay() {
    }

    @Test
    void showDinnerDay() {
    }

    @Test
    void getWeightProgress() {
    }

    @Test
    void getWeightProgressDays() {
    }
}