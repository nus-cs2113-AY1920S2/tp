import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ActivityList {
    ArrayList<Activity> activities;
    //int size;

    public ActivityList() {
        this.activities = new ArrayList<>();
        //this.size = activities.size();
    }

    public void add(Activity activity) {
        activities.add(activity);
    }

    public int getSize() {
        return activities.size();
    }

    public void printList() {
        System.out.println("Your completed activities:");
        for (int i = 0; i < this.getSize(); i++) {
            long durationInNanos = (activities.get(i).duration).toNanos();
            String duration = String.format("%02d:%02d:%02d",
                    TimeUnit.NANOSECONDS.toHours(durationInNanos),
                    TimeUnit.NANOSECONDS.toMinutes(durationInNanos) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(durationInNanos)),
                    TimeUnit.NANOSECONDS.toSeconds(durationInNanos) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(durationInNanos)));
            System.out.print(i + 1 + ". " + activities.get(i).name + " " + duration);
            if(activities.get(i).tags != null) {
                System.out.println(" " + Arrays.toString(activities.get(i).tags));
            } else {
                System.out.print("\n");
            }
        }
    }
}
