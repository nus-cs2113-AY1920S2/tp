package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    public void execute(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        String result = generateResult(selectedModulesList, availableModulesList);
        Ui.showFindMessage(result);
    }

    private String generateResult(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        ModuleList listOfSelectedModulesToDisplay = new ModuleList();
        for (SemModulesList semModulesList : selectedModulesList) {
            for (Module module : semModulesList) {
                if (module.getName().toLowerCase().contains(keyword) || module.getId().toLowerCase().contains(keyword)) {
                    listOfSelectedModulesToDisplay.add(module);
                }
            }
        }

        ModuleList listOfAvailableModulesToDisplay = new ModuleList();
        for (Module module : availableModulesList) {
            if (module.getName().toLowerCase().contains(keyword) || module.getId().toLowerCase().contains(keyword)) {
                listOfAvailableModulesToDisplay.add(module);
            }
        }

        StringBuilder output = new StringBuilder();
        output.append("\nList of selected modules:\n");
        for (Module module : listOfSelectedModulesToDisplay) {
            output.append(module.toString())
                    .append("\n");
        }

        output.append("\nList of available modules\n");

        for (Module module : listOfAvailableModulesToDisplay) {
            output.append(module.toString())
                    .append("\n");
        }
        return output.toString();
    }
}
