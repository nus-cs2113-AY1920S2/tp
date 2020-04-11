package seedu.dietmanager.model;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String name;
    private int age;
    private String gender;
    private double height; //Height in centimeter
    private double weight; //Weight in kg
    private double weightGoal;
    private boolean profileExist;
    private List<DailyFoodRecord> personalFoodRecord;
    private List<Double> weightRecord;

    /**
     * Constructs a new Profile object.
     */

    public Profile() {
        this.personalFoodRecord = new ArrayList<>();
        this.weightRecord = new ArrayList<>();
        this.profileExist = false;
    }

    /**
     * Sets the User Profile with the relevant information required.
     */
    public void setProfile(String name, int age, String gender, double height, double weight, double weightGoal) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setHeight(height);
        weightRecord.clear();
        this.setWeight(weight);
        this.setWeightGoal(weightGoal);
        this.setProfileExist(true);
    }

    /**
     * Returns a daily record of a certain date.
     *
     * @param date the date of the wanted record.
     * @return a record of wanted date.
     */

    public DailyFoodRecord getRecordOfDay(String date) {
        boolean recordExists = false;
        DailyFoodRecord record = null;
        for (DailyFoodRecord r : personalFoodRecord) {
            if (r.isDate(date)) {
                record = r;
                recordExists = true;
                break;
            }
        }
        if (!recordExists) {
            record = addNewRecord(date);
        }
        return record;
    }

    public DailyFoodRecord getRecordOfDay(int index) {
        return personalFoodRecord.get(index);
    }

    /**
     * Creates a new record of a certain date.
     *
     * @param date the date of the new record.
     * @return a newly generated daily record.
     */

    public DailyFoodRecord addNewRecord(String date) {
        DailyFoodRecord record = new DailyFoodRecord(date);
        personalFoodRecord.add(record);
        return record;
    }

    public void clearAllFoodRecords() {
        personalFoodRecord.clear();
    }

    public List<Double> getWeightRecord() {
        return this.weightRecord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.weightRecord.add(weight);
    }

    public void setWeightGoal(double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public void setProfileExist(boolean profileExist) {
        this.profileExist = profileExist;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public boolean isProfileExist() {
        return this.profileExist;
    }

}
