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
     * Returns a parsed string from string read from Json.
     *
     * @param string - String extracted from Json object.
     * @return String that user sees.
     */
    private static String parseString(String string) {
        String stringWithoutQuotations = string.substring(1, string.length() - 1);
        String stringWithSlashesProcessed = stringWithoutQuotations.replace("\\\"", "\"");
        String finalString = stringWithSlashesProcessed.replace("\\\\", "\\");
        return finalString;
    }

    /**
     * Parses Json Object representing article into String.
     *
     * @param article - Json object representing article.
     */
    private static void parseArticleObject(JsonObject article) {

        String index = Integer.toString(idxArticle);
        //Get employee object within list
        JsonObject articleObject = (JsonObject) article.get(index);
        idxArticle += 1;

        String title = parseString(articleObject.get("title").toString());
        String url = parseString(articleObject.get("url").toString());
        String timeStamp = parseString(articleObject.get("timestamp").toString());
        String extract = parseString(articleObject.get("extract").toString());
        String category = parseString(articleObject.get("category").toString());

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
     * Parses Json Object representing job into String.
     *
     * @param job - Json object representing job.
     */
    private static void parseJobObject(JsonObject job) {

        String index = Integer.toString(idxJob);
        //Get employee object within list
        JsonObject jobObject = (JsonObject) job.get(index);
        idxJob += 1;

        //Get article title
        String title = parseString(jobObject.get("title").toString());
        String text = parseString(jobObject.get("text").toString());
        String timeStamp = parseString(jobObject.get("timestamp").toString());
        String extract = parseString(jobObject.get("extract").toString());
        String category = parseString(jobObject.get("category").toString());
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
     * Parses Json Object representing note into String.
     *
     * @param note - Json object representing note.
     */
    private static void parseNoteObject(JsonObject note) {

        String index = Integer.toString(idxNotes);
        //Get employee object within list
        JsonObject noteObject = (JsonObject) note.get(index);
        idxNotes += 1;

        String title = parseString(noteObject.get("title").toString());
        String extract = parseString(noteObject.get("extract").toString());
        String timeStamp = parseString(noteObject.get("timestamp").toString());
        String url = parseString(noteObject.get("url").toString());
        String category = parseString(noteObject.get("category").toString());

        //Creates a note and a
        Note newNote = new Note(title, extract, timeStamp);
        newNote.setUrl(url);
        newNote.setCategory(category);

        // NotePrinter.printIsolatedNote(newNote);
        SavedNoteList.savedNoteList.add(newNote);
    }
}
