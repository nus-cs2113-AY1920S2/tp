package notes;


import ui.Constants;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//@@author terrytay
public class Notes {
    ModulesList modulesList;
    Ui ui;

    /**
     * To be added by Terry.
     * @param ui to be added by terry.
     */
    public Notes(Ui ui) {

        this.ui = ui;
        modulesList = ModulesList.getInstance(ui);
        importModules();
    }

    /**
     * Creates a module.
     *
     * @param code Module Code.
     */
    public void createModule(String code) {
        code = code.toUpperCase();
        modulesList.createModule(code);
    }

    /**
     * Deletes a module.
     *
     * @param code Module Code.
     */
    public void deleteModule(String code) {
        code = code.toUpperCase();
        modulesList.deleteModule(code);
    }

    /**
     * To enter into the module notes.
     *
     * @param code Module Code.
     * @throws Exception for moduleManager
     */
    public void enterModule(String code) throws Exception {
        code = code.toUpperCase();
        modulesList.enterModule(code);

    }

    private void importModules() {
        try {
            File f = new File(Constants.NOTES_PATH);
            if (f.createNewFile()) {
                ui.printLine();
                ui.printMessage(Constants.NOTES_FILE_NOT_FOUND);
            }
            HashMap<String, ArrayList<String>> modules = new HashMap<>();
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                String code = input.nextLine();
                ArrayList<String> messages = new ArrayList<>();
                String message = input.nextLine();
                while (!message.equals(Constants.NOTES_KEY_WORD)) {
                    messages.add(message);
                    message = input.nextLine();
                }
                modules.putIfAbsent(code, messages);
            }
            modulesList.importModules(modules);
        } catch (Exception e) {
            ui.printLine();
            ui.printMessage(Constants.IMPORT_ERROR);
            ui.printLine();
        }
    }

    /**
     * Export modules when exiting app.
     * @throws IOException exception raised when exporting modules
     */
    public void exportModules() throws IOException {
        FileWriter fw = new FileWriter(Constants.NOTES_PATH);
        HashMap<String, ArrayList<String>> modules = modulesList.exportModules();
        modules.forEach((key, messages) -> {
            try {
                fw.write(key + "\n");
                for (String message : messages) {
                    fw.write(message + "\n");
                }
                fw.write(Constants.NOTES_KEY_WORD + "\n");
            } catch (IOException e) {
                ui.printLine();
                e.printStackTrace();
                ui.printLine();
            }
        });
        fw.close();
        ui.printMessage(Constants.EXPORT_SUCCESS);
    }

    /**
     * Print all saved modules.
     */
    public void listModules() {
        modulesList.listModules();
    }
}
