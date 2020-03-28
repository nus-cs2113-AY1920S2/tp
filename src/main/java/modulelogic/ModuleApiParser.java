package modulelogic;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handles the HTTP requests from NUSMOD API server.
 * Data grabbed from NUSMODS API: https://api.nusmods.com/v2/2019-2020/modules/MODULECODE.json
 */
public class ModuleApiParser {
    String apiUrl;
    private final String templateUrl = "https://api.nusmods.com/v2/2019-2020/modules/";

    public ModuleApiParser(String moduleName) {
        apiUrl = templateUrl + moduleName + ".json";
    }

    /**
     * @return 1 NUS module in JSONArray format from API,
     *         if unable to access API, returns an empty JsonArray.
     */
    public JsonArray parse() {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            //Convert the input stream to a json element
            JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            assert rootObj != null;
            return (JsonArray) rootObj.get("semesterData");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new JsonArray();
        }
    }
}
