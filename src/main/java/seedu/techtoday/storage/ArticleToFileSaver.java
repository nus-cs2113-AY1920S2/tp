package seedu.techtoday.storage;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import seedu.techtoday.objects.Article;
import java.io.IOException;
import java.io.FileWriter;

import com.google.gson.JsonObject;

/** Represents class that saves articles to file. */
public class ArticleToFileSaver {

    /**
     * Executes saving og articles to file.
     * @param articleArrayList - List of articles.
     * @param filePath - Path where articles are saved.
     */
    public static void execute(ArrayList<Article> articleArrayList, String filePath) {

        int index = 1;
        JsonArray articleList = new JsonArray();

        for (int i = 0; i < articleArrayList.size(); i++) {
            Article article = articleArrayList.get(i);
            String title = article.getTitle();
            String url = article.getUrl();
            String category = article.getCategory();
            String extract = article.getExtract();
            String timeStamp = article.getTimeStamp();

            //article Details
            JsonObject articleDetails = new JsonObject();
            articleDetails.addProperty("title", title);
            articleDetails.addProperty("url", url);
            articleDetails.addProperty("timestamp", timeStamp);
            articleDetails.addProperty("category", category);
            articleDetails.addProperty("extract", extract);

            //Index: article Details
            JsonObject articleObject = new JsonObject();
            articleObject.add(Integer.toString(index), articleDetails);
            index += 1;

            articleList.add(articleObject);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(filePath)) {

            String jsonString = articleList.toString();

            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
