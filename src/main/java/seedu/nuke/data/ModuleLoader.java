package seedu.nuke.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.alibaba.fastjson.JSON;
import seedu.nuke.exception.NotFoundException;
import seedu.nuke.module.DummyModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class is to load all the modules that NUS offer, scraping from the nusmods,
 * when users are trying to enrol into modules, the module code must
 * appear in the list of the list which load method return, or else fail.
 */
public class ModuleLoader {

    /**
     * load data from a jason file that contains all information about all NUS existing modules
     * @param dataFileName name of the jason file
     * @return HashMap<String, String> a map that map all modules with its information
     * @throws FileNotFoundException when cannot find the jason file
     */
    public static HashMap<String, String> load(String dataFileName) throws FileNotFoundException {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
        List<DummyModule> moduleList = JSON.parseArray(jsonStr, DummyModule.class);// extractModules(jsonStr);
        HashMap<String, String> modulesMap = convertToHashMap(moduleList);
        return modulesMap;
    }

    private static HashMap<String, String> convertToHashMap(List<DummyModule> moduleList) {
        HashMap<String, String> modulesMap = new HashMap<>();
        for (DummyModule i: moduleList) {
            modulesMap.put(i.getModuleCode(), i.getTitle());
        }
        return modulesMap;
    }

    private static String loadJsonStringFromFile(String dataFileName) throws FileNotFoundException {
        String encoding = "utf8";
        File file = new File(dataFileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }

    }

    private static ArrayList<DummyModule> extractModules(String jsonStr) {
        Type listType = new TypeToken<ArrayList<DummyModule>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }
}
