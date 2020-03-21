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
 * Returns one school module in JSONArray format. Data grabbed from the NUSMOD API: https://api.nusmods.com/v2/2019-2020/modules/MODULECODE.json
 */
public class ModuleApiParser {
    String apiUrl;
    private String templateUrl = "https://api.nusmods.com/v2/2019-2020/modules/";

    public ModuleApiParser(String moduleName) {
        apiUrl = templateUrl + moduleName + ".json";
    }

    public JsonArray parse() throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        //Convert the input stream to a json element
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootObj = root.getAsJsonObject();
        return (JsonArray) rootObj.get("semesterData");
    }
}
