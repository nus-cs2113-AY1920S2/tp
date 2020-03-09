import java.util.ArrayList;
import java.util.Arrays;

public class TeamMember {
    private String myName;
    private String mySchedule[][]; //String[7][48]; 7 days, separated into 30mins within 24 hours period.
    private ArrayList<String> myBlockedDates;

    public TeamMember(String name) {
        this.myName = name;
        this.mySchedule = new String[7][48];
        for (int i=0; i<7; i++) {
            Arrays.fill(mySchedule[i], "FREESLOT"); // fill every 48 index of the 7 days with string FREESLOT
        }
        this.myBlockedDates = new ArrayList<>();
    }
    public TeamMember(String name, String[][] schedule) { //Overloaded constructor for user to put in their timetables into String[][] schedule.
        this.myName = name;
        this.mySchedule = schedule;
        this.myBlockedDates = new ArrayList<>();
    }
    public void addBlockedDates(String date) {
        myBlockedDates.add(date);
        //TODO modify mySchedule according to date.
    }
    public void deleteBlockedDates(String date) {
        myBlockedDates.remove(date);
        //TODO modify mySechdule according to date.
    }
    public String getName() {
        return this.myName;
    }
    public String[][] getSchedule() {
        return this.mySchedule;
    }
    public ArrayList<String> getBlockedDates() {
        return this.myBlockedDates;
    }

    //TODO create an ASCII ART timetable based on schedule and blocked dates.

}
