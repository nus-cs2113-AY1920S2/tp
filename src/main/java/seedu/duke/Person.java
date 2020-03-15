package seedu.duke;

import java.util.ArrayList;

public class Person {
    private String name;
    private int age;
    private double height; //Height in meter
    private double weight; //Wight in Kg
    private String gender;
    private double weightGoal;
    private ArrayList<DailyFoodRecord> personalFoodRecord;

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
        this.personalFoodRecord = new ArrayList<DailyFoodRecord>();
    }

    public double getBMI(){
        return weight / Math.pow(height/100, 2);
    }

    public void setWeightGoal(double weightGoal){
        this.weightGoal = weightGoal;
    }

    public DailyFoodRecord getRecordOfDay(String date){
        boolean recordExists = false;
        DailyFoodRecord record = null;
        for(DailyFoodRecord r:personalFoodRecord){
            if(r.isDate(date)){
                record = r;
                recordExists = true;
                break;
            }
        }
        if(!recordExists) addNewRecord(date);
        return record;
    }

    public void addNewRecord(String date){
        DailyFoodRecord record = new DailyFoodRecord(date);
        personalFoodRecord.add(record);
    }

    public DailyFoodRecord getRecordOfDay(int index){
        return personalFoodRecord.get(index);
    }

    public void setRecordOfDay(DailyFoodRecord record, String mealType, ArrayList<Food> foodList){
        record.recordMeals(mealType,foodList);
    }

    public void showRecordOfDay(DailyFoodRecord record){
        record.showDailyRecord();
    }

    public void showBreakfastOfDay(DailyFoodRecord record){
        record.showBreakfast();
    }

    public void showLunchOfDay(DailyFoodRecord record){
        record.showLunch();
    }

    public void showDinnerDay(DailyFoodRecord record){
        record.showDinner();
    }

    public boolean isName(String name){
        return this.name.equals(name);
    }

    public int getDays(){
        return personalFoodRecord.size();
    }

    public String[] getPersonInfo(){
        int totalLines = 4*getDays()+1;
        String[] info = new String[totalLines];
        info[0] = "user "+this.name+" "+this.age+" "+this.gender+" "
                    +this.height+" "+this.weight+" "+this.weightGoal;
        for(int i=0;i<=getDays()-1;i++){
            DailyFoodRecord currentRecord = getRecordOfDay(i);
            info[i*4+1] = currentRecord.getDate();
            info[i*4+2] = "breakfast"+currentRecord.getBreakfast();
            info[i*4+3] = "lunch"+currentRecord.getLunch();
            info[i*4+4] = "lunch"+currentRecord.getDinner();
        }

        return info;
    }
}
