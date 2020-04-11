package seedu.tp.utils;

import java.io.File;

public class LoggerUtils {
    /**
     * Create a folder if it does not exist.
     *
     * @param folderName String representing the folder name
     */
    public static void createFolder(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}
