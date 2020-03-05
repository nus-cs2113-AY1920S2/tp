public class Activity {
    protected String name;
    protected String startTime;
    protected String endTime;

    public Activity(String name, String startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public String endActivity(String endTime) {
        this.endTime = endTime;
        return ("You have ended " + this.name + " . Great job !\n");
    }
}
