package seedu.dietmanager;

import java.util.ArrayList;

public class Profile {
    private String name;
    private int age;
    private String gender;
    private double height; //Height in meter
    private double weight; //Wight in Kg
    private double weightGoal;
    private boolean profileExist;
    private ArrayList<DailyFoodRecord> personalFoodRecord;
    private ArrayList<Double> weightRecord = new ArrayList<>();

    public Profile() {
        this.personalFoodRecord = new ArrayList<>();
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
        weightRecord.add(weight);
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

    public void addWeightProgress(double newWeight) {
        weightRecord.add(newWeight);
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

    /**
     * Returns a daily record of a certain date.
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
     * @param date the date of the new record.
     * @return a newly generated daily record.
     */

    public DailyFoodRecord addNewRecord(String date) {
        DailyFoodRecord record = new DailyFoodRecord(date);
        personalFoodRecord.add(record);
        return record;
    }

    public int getNumOfRecordedDays() {
        return personalFoodRecord.size();
    }

    public void setRecordOfDay(DailyFoodRecord record, String mealType, ArrayList<Food> foodList) {
        record.recordMeals(mealType,foodList);
    }

    public void showRecordOfDay(DailyFoodRecord record) {
        record.showDailyRecord();
    }

    public void showBreakfastOfDay(DailyFoodRecord record) {
        record.showBreakfast();
    }

    public void showLunchOfDay(DailyFoodRecord record) {
        record.showLunch();
    }

    public void showDinnerDay(DailyFoodRecord record) {
        record.showDinner();
    }

    public ArrayList<Double> getWeightProgress() {
        return weightRecord;
    }

}
