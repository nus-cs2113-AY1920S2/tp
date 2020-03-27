package seedu.dietmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileTest {

    @Test
    public void setName() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setName("Jane Doe");
        assertEquals("Jane Doe", profile.getName());
    }

    @Test
    public void setGender() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setGender("Female");
        assertEquals("Female", profile.getGender());
    }

    @Test
    public void setAge() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setAge(18);
        assertEquals(18, profile.getAge());
    }

    @Test
    public void setHeight() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setHeight(170);
        assertEquals(170, profile.getHeight());
    }

    @Test
    public void setWeightGoal() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setWeightGoal(50);
        assertEquals(50, profile.getWeightGoal());
    }

    @Test
    public void setProfileExist() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setProfileExist(false);
        assertEquals(false, profile.isProfileExist());
    }

    @Test
    public void isProfileExist() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(true, profile.isProfileExist());
    }

    @Test
    public void getHeight() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(180, profile.getHeight());
    }

    @Test
    public void getWeight() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(80, profile.getWeight());
    }

    @Test
    public void getWeightGoal() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(75, profile.getWeightGoal());
    }

    @Test
    public void getAge() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals(20, profile.getAge());
    }

    @Test
    public void getGender() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals("Male", profile.getGender());
    }

    @Test
    public void getName() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        assertEquals("John Doe", profile.getName());
    }
}