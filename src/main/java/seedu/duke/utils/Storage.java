package seedu.duke.utils;

import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.data.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String fileName;
    boolean fileExists = true;
    Command command;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Item> loadItem() {
        String str;

        try {
            str = loadStringFromFile();
        } catch (IOException e) {
            ////////////////////////////////////////////////////////
        }

        List<Item> tempItemList = extractTempList(str);
        ArrayList<Item> shoppingList = new ArrayList<>();

        Item currentItem;
        for (Item i : tempItemList) {
            try {
                currentItem = convertGeneralItemtoSpecificItem(i);
                shoppingList.add(currentItem);
            } catch {
                /////////////////////////////////////////////////
            }
        }

        return shoppingList;
    }


    private String loadStringFromFile() throws IOException{
        String str = null;
        File f = new File(fileName);
        if (!f.exists()) {
            fileExists = false;
            f.createNewFile();
        } else {
            Scanner s = new Scanner(f);
            str = s.nextLine();
            s.close();
        }
        return str;
    }

}
