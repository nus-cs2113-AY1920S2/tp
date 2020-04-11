package utils;

import java.io.File;

/** Logger related setup. */
public class LoggerUtils {
    /**
     * Creates the folder for logger.
     * 
     * @param folderName Folder name for logger.
     */
    public static void createLogFolder(String folderName) {
        if (folderName.equals("")) {
            return;
        }
        
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}
