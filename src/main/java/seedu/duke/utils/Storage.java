package seedu.duke.utils;

import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@@ JLoh579
public class Storage {
    private String fileName;
    boolean fileExists = true;
    Command command;

    public Storage(String fileName) {
        this.fileName = fileName;
    }
    /*
    public ArrayList<Item> loadItem() {
        String str;
        ArrayList<Item> shoppingList = new ArrayList<>();

        try {
            str = loadStringFromFile();

            List<Item> tempItemList = extractTempList(str);

            Item currentItem;
            for (Item i : tempItemList) {
                try {
                    currentItem = convertGeneralItemtoSpecificItem(i);
                    shoppingList.add(currentItem);
                } catch (................................) {
                    /////////////////////////////////////////////////
                }
            }


        } catch (IOException e) {
            ////////////////////////////////////////////////////////
        }

        return shoppingList;
    }

    private List<Item> extractTempList(String str) {
        Type listType = new TypeToken<List<Item>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }


    private Item convertGeneralItemtoSpecificItem(Item item) {
        Item convertedItem;

        convertedItem = new Item(item.getDescription(), item.getPrice());
        if (item.getStatusIcon().equals("B")) {
            convertedItem.markAsBought();
        }

        return convertedItem;
    }


    private String loadStringFromFile() throws IOException {
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

    public void save (ShoppingList shoppingList) {
        try {
            saveItemAsString(fileName, ShoppingList.getList());
        } catch (........................) {

        }
    }

    static void saveItemAsString(String fileName, ArrayList<Item> shoppingList) throws IOException{
        Gson gson = new Gson();
        FileWriter fw = new FileWriter(fileName);
        String json = gson.toJson(shoppingList);
        fw.write(json);
        fw.flush();
        fw.close();
    }
*/
}
//@@ JLoh579
