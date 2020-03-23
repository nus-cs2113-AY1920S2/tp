package seedu.dietmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileTest {

    @Test
    void setName() {
        Profile profile = new Profile();
        profile.setProfile("John Doe",20, "Male", 180, 80, 75);
        profile.setName("Jane Doe");
        assertEquals("Jane Doe", profile.getName());
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
}