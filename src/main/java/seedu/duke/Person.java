package seedu.duke;

public class Person {
    private String name;
    private int age;
    private double height; //Height in meter
    private double weight; //Wight in Kg
    private String gender;
    private double weightGoal;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age, String gender, double height, double weight, double weightGoal){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.weightGoal = weightGoal;
    }

    public double getBMI(){
        return weight / Math.pow(height/100, 2);
    }

    public void setWeightGoal(double weightGoal){
        this.weightGoal = weightGoal;
    }

}
