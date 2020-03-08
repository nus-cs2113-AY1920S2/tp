import java.time.Duration;
import java.time.LocalDateTime;

public class Activity {
    protected String name;
    protected String[] tags;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected Duration duration;

    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime, String[] tags) {
        this.name = name;
        this.startTime = startTime;
        this.tags = tags;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
    }

}
