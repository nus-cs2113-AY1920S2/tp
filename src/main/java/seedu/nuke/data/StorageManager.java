package seedu.nuke.data;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import seedu.nuke.module.Module;

public class StorageManager {
    private String dataFileName;

    /**
     * Constructor with name of the data file as argument.
     *
     * @param dataFileName the name of the file in which the data is stored.
     */
    public StorageManager(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public ArrayList<Module> load() {
        String jsonStr;
        try {
            jsonStr = loadJsonStringFromFile(dataFileName);
            ArrayList<Module> moduleList = (ArrayList<Module>) JSON.parseArray(jsonStr, Module.class);
            return moduleList;
        } catch (IOException e) {
            System.out.println("error when reading from " + dataFileName);
            ArrayList<Module> moduleList = new ArrayList<>();
            return moduleList;
        }
    }

    private static String loadJsonStringFromFile(String dataFileName) throws IOException {
        String encoding = "utf8";
        File file = new File(dataFileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public void save(ArrayList<Module> moduleList){
        String jsonStr = JSON.toJSONString(moduleList);
        try {
            FileWriter fw = new FileWriter(dataFileName);
            fw.write(jsonStr);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("something wrong when writing to " + dataFileName);
        }
    }
}
