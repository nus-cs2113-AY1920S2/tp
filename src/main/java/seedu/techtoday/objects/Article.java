package seedu.techtoday.objects;

/**
 * Main class for Article objects.
 */
public class Article extends Information {

    public String url;
    public String category;
    
    /**
     * Constructor for article class.
     * @param title - The article title given by the user.
     * @param url - The url given by the user.
     * @param category - The category set as default initially.
     */
    public Article(String title, String url, String category) {
        super(title, "", "");
        this.url = url;
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

}
