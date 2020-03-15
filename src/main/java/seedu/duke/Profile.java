package seedu.duke;

public class Profile {
    private String name;
    private int age;
    private String gender;
    private double height; //Height in meter
    private double weight; //Wight in Kg
    private double weightGoal;
    private boolean profileExist;

    public Profile() {
        this.profileExist = false;
    }

    /**
     * Sets the User Profile with the relevant information required.
     */

    public void setProfile(String name, int age, String gender, double height, double weight, double weightGoal) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.profileExist = true;
    }

    public void setName(String name) {
        this.name = name;
        this.profileExist = true;
    }

    public void setGender(String gender) {
        this.gender = gender;
        this.profileExist = true;
    }

    public void setAge(int age) {
        this.age = age;
        this.profileExist = true;
    }

    public void setHeight(double height) {
        this.height = height;
        this.profileExist = true;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.profileExist = true;
    }

    public void setWeightGoal(double weightGoal) {
        this.weightGoal = weightGoal;
        this.profileExist = true;
    }

    public void setProfileExist(boolean profileExist) {
        this.profileExist = profileExist;
    }

    public boolean isProfileExist() {
        return profileExist;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public double getBmi() {
        return weight / Math.pow(height / 100, 2);
    }

}
