package seedu.techtoday.storage;

import java.io.IOException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonParser;
import seedu.techtoday.common.Messages;
import seedu.techtoday.notelist.SavedNoteList;
import seedu.techtoday.objects.Job;
import seedu.techtoday.objects.Note;

import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.objects.Article;

/**
 * Loads articles from memory.
 */
public class Loader {
    @SuppressWarnings("unchecked")

    public static int idxArticle = 1;
    public static int idxJob = 1;
    public static int idxNotes = 1;

    /**
     * Executes the loading of articles from memory.
     * @param fileNameArticle - Name of file containing articles.
     * @param fileNameJobs - Name of file containing jobs.
     * @param fileNameNotes - Name of file containing notes.
     */
    public static void execute(String fileNameArticle, String fileNameJobs, String fileNameNotes) {

        JsonArray articleList = jsonReader(fileNameArticle);
        JsonArray jobList = jsonReader(fileNameJobs);
        JsonArray noteList = jsonReader(fileNameNotes);

        //Iterate over articleList array
        articleList.forEach(obj -> parseArticleObject((JsonObject)obj));

        //Iterate over jobList array
        jobList.forEach(obj -> parseJobObject((JsonObject)obj));

        //Iterate over noteList array
        noteList.forEach(obj -> parseNoteObject((JsonObject)obj));
    }


    /**
     * Returns Json Array from JsonFile.
     * @param fileName - Name of JsonFile.
     * @return - JsonArray that represents file.
     */
    public static JsonArray jsonReader(String fileName) {

        try (FileReader reader = new FileReader(fileName)) {
            try {
                Object obj = JsonParser.parseReader(reader);
                JsonArray objectList = (JsonArray) obj;
                return objectList;
            } catch (JsonSyntaxException | ClassCastException e) {
                System.out.println(String.format("\"%s\" is corrupted. We will clear this "
                        + "file in particular and start fresh.", fileName));
                Messages.printStraightLine();
            }
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
        return new JsonArray();

    }

    /**
     * ADD JAVADOC HERE.
     * @param article - ADD HERE.
     */
    private static void parseArticleObject(JsonObject article) {

        String index = Integer.toString(idxArticle);
        //Get employee object within list
        JsonObject articleObject = (JsonObject) article.get(index);
        idxArticle += 1;

        //Get article title
        String title = articleObject.get("title").toString().replaceAll("\"", "");
        String url = articleObject.get("url").toString().replaceAll("\"", "");;
        String timeStamp = articleObject.get("timestamp").toString().replaceAll("\"", "");;
        String extract = articleObject.get("extract").toString().replaceAll("\"", "");;
        String category = articleObject.get("category").toString().replaceAll("\"", "");;

        if (category == null) {
            category = "default";
        }

        Article newArticle = new Article(title, url, category);
        newArticle.setTime(timeStamp);

        if (extract == null) {
            extract = "";
        }

        newArticle.setExtract(extract);

        SavedArticleList.savedArticleList.add(newArticle);

    }

    /**
     * ADD JAVADOC HERE.
     * @param job - ADD HERE.
     */
    private static void parseJobObject(JsonObject job) {

        String index = Integer.toString(idxJob);
        //Get employee object within list
        JsonObject jobObject = (JsonObject) job.get(index);
        idxJob += 1;

        //Get article title
        String title = jobObject.get("title").toString().replaceAll("\"", "");;
        String text = jobObject.get("text").toString().replaceAll("\"", "");;
        String timeStamp = jobObject.get("timestamp").toString().replaceAll("\"", "");;
        String extract = jobObject.get("extract").toString().replaceAll("\"", "");;
        String category = jobObject.get("category").toString().replaceAll("\"", "");;

        if (category == null) {
            category = "default";
        }

        if (extract == null) {
            extract = "";
        }

        //Creates a job and a
        Job newJob = new Job(title, text, category);
        newJob.setTime(timeStamp);
        newJob.setExtract(extract);

        SavedJobList.savedJobList.add(newJob);

    }

    /**
     * ADD JAVADOC HERE.
     * @param note - ADD HERE.
     */
    private static void parseNoteObject(JsonObject note) {

        String index = Integer.toString(idxNotes);
        //Get employee object within list
        JsonObject noteObject = (JsonObject) note.get(index);
        idxNotes += 1;

        // Get article title
        String title = noteObject.get("title").toString().replaceAll("\"", "");;
        String extract = noteObject.get("extract").toString().replaceAll("\"", "");;
        String timeStamp = noteObject.get("timestamp").toString().replaceAll("\"", "");;
        String url = noteObject.get("url").toString().replaceAll("\"", "");;
        String category = noteObject.get("category").toString().replaceAll("\"", "");;

        //Creates a note and a
        Note newNote = new Note(title, extract, timeStamp);
        newNote.setUrl(url);
        newNote.setCategory(category);

        // NotePrinter.printIsolatedNote(newNote);
        SavedNoteList.savedNoteList.add(newNote);
    }
}
