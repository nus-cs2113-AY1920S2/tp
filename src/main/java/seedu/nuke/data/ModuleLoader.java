package seedu.nuke.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.nuke.exception.NotFoundException;
import seedu.nuke.module.DummyModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is to load all the modules scraping from the nusmods,
 */
public class ModuleLoader {
    public static ArrayList<DummyModule> load(String dataFileName) throws FileNotFoundException {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
        ArrayList<DummyModule> moduleList = extractModules(jsonStr);
        return moduleList;
    }

    private static String loadJsonStringFromFile(String dataFileName) throws FileNotFoundException {
        String jsonStr;
        try {
            File f = new File(dataFileName);
            Scanner s = new Scanner(f);
            jsonStr = s.nextLine();
            s.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(dataFileName);
        }
        return jsonStr;
    }

    private static ArrayList<DummyModule> extractModules(String jsonStr) {
        Type listType = new TypeToken<ArrayList<DummyModule>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }
}
