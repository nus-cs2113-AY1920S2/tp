package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.ModuleList;
import seedu.duke.data.SemesterList;
import seedu.duke.data.SemModulesList;
import seedu.duke.exception.RuntimeException;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) {
        String result = generateResult(semesterList, availableModulesList);
        Ui.showFindMessage(result);
    }

    /**
     * This method finds for module Id and Name within all module list which contains keyword given and
     * appends the module Id and Name into a String to return.
     * @param selectedModulesList The module plan of the user.
     * @param availableModulesList The list of available modules
     */
    private String generateResult(SemesterList selectedModulesList, AvailableModulesList availableModulesList) {
        ModuleList listOfSelectedModulesToDisplay = new ModuleList();
        for (SemModulesList semModulesList : selectedModulesList) {
            for (Module module : semModulesList) {
                boolean doesModuleNameContainKeyword = module.getName().toLowerCase().contains(keyword);
                boolean doesModuleIdContainKeyword = module.getId().toLowerCase().contains(keyword);
                if (doesModuleNameContainKeyword || doesModuleIdContainKeyword) {
                    listOfSelectedModulesToDisplay.add(module);
                }
            }
        }

        ModuleList listOfAvailableModulesToDisplay = new ModuleList();
        for (Module module : availableModulesList) {
            boolean doesModuleNameContainKeyword = module.getName().toLowerCase().contains(keyword);
            boolean doesModuleIdContainKeyword = module.getId().toLowerCase().contains(keyword);
            if (doesModuleNameContainKeyword || doesModuleIdContainKeyword) {
                listOfAvailableModulesToDisplay.add(module);
            }
        }

        StringBuilder output = new StringBuilder();
        output.append(System.lineSeparator()
                + "List of selected modules:"
                + System.lineSeparator());
        for (Module module : listOfSelectedModulesToDisplay) {
            output.append(module.toString())
                    .append(System.lineSeparator());
        }

        output.append(System.lineSeparator()
                + "List of available modules:"
                + System.lineSeparator());

        for (Module module : listOfAvailableModulesToDisplay) {
            output.append(module.toString())
                    .append(System.lineSeparator());
        }
        return output.toString();
    }
}
