package seedu.duke;

public class Person {
    private String Name;
    private int Age;
    private double Height; //Height in meter
    private double Weight; //Wight in Kg
    private String Gender;
    private double weightGoal;

    public Person(String Name, int Age, String Gender, double Height, double Weight, double Goal_Weight){
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.Height = Height / 100;
        this.Weight = Weight;
        this.weightGoal = weightGoal;
    }

    public double getBMI(){
        return Weight / Height * Height;
    }
    public void setWeightGoal(double weightGoal){
        this.weightGoal = weightGoal;
    }

}
