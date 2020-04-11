package seedu.techtoday.objects;

/**
 * Main class for Job objects.
 */
public class Job extends Information {

    public String text;
    public String category;

    /**
     * Constructor for Job class.
     * @param title - The article title given by the user.
     * @param text - The text given by the user.
     * @param category - The category set as default initially.
     */
    public Job(String title, String text, String category) {
        super(title, "", "");
        this.text = text;
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public void setTime(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
