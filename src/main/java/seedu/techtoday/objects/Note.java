package seedu.techtoday.objects;


public class Note extends Information {

    public String url;
    public String category;

    /**
     * Constructor for note class.
     * @param title - The note title given by the user.
     */
    public Note(String title) {
        super(title, "", "");
    }

    /**
     * Alternate constructor for Note class.
     * @param title - The note title.
     * @param extract - The note extractor.
     * @param epochSecond - The note epochSecond.
     */
    public Note(String title, String extract, String epochSecond) {
        super(title, extract, epochSecond);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }
}
