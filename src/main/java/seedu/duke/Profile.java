package seedu.duke;

public class Profile {
    private String name;
    private int age;
    private double height; //Height in meter
    private double weight; //Wight in Kg
    private String gender;
    private double weightGoal;
    private boolean profileExist;

    public Profile() {
        this.profileExist = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWeightGoal(double weightGoal){
        this.weightGoal = weightGoal;
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

    public double getBMI(){
        return weight / Math.pow(height/100, 2);
    }

}
