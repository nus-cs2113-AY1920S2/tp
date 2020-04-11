package seedu.techtoday.api;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.net.URL;
import java.nio.charset.Charset;

// Source: https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
/** Represents a class that parses JSON objects that are required for the project. */
public class UrlJsonParser {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Returns an array of the words in a string seperated by a comma.
     *
     * @param array String of words that ill be converted into an array
     * @return arrOfIndices Array representing the indices of the news articles that are to be saved.
     */
    public static int[] stringToArray(String array) {
        String[] arrayStr = array.split(",");
        int[] arrOfIndices = new int[6];
        for (int i = 1; i < 7; i++) {
            int a = Integer.parseInt(arrayStr[i].trim());
            arrOfIndices[i - 1] = a;
        }
        return arrOfIndices;
    }

    /**
     * Returns a string from a news url.
     *
     * @param url url representing a news article.
     * @return String representing news in the form of a json text.
     * @throws IOException IO expection.
     * @throws JSONException JSON exception.
     */
    public static String readJsonArrayfromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }

    /**
     * Reads a JSON object from a url.
     *
     * @param url url representing news
     * @return json JSON object representing news.
     * @throws IOException IO exception.
     * @throws JSONException JSON exception.
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
