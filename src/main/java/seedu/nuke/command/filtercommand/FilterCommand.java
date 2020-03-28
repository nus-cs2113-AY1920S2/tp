package seedu.nuke.command.filtercommand;

import seedu.nuke.command.Command;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.ALL_FLAG;
import static seedu.nuke.parser.Parser.CATEGORY_NAME_PREFIX;
import static seedu.nuke.parser.Parser.EXACT_FLAG;
import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;

public abstract class FilterCommand extends Command {
    public static final Pattern MODULE_REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)+)"
            + "(?<optional>(?:\\s+-[ea])*)"
            + "(?<invalid>.*)"
    );
    public static final Pattern CATEGORY_REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)+)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<optional>(?:\\s+-[ea])*)"
            + "(?<invalid>.*)"
    );
    public static final Pattern TASK_REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)+)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<optional>(?:\\s+-[ea])*)"
            + "(?<invalid>.*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
    );

    protected ArrayList<Directory> createFilteredModuleList(String moduleKeyword, boolean isExact) {
        ArrayList<Module> filteredModuleList =
                isExact ? ModuleManager.filterExact(moduleKeyword) : ModuleManager.filter(moduleKeyword);
        return new ArrayList<>(filteredModuleList);
    }

    protected ArrayList<Directory> createFilteredCategoryList(String moduleKeyword, String categoryKeyword,
                                                              boolean isExact, boolean isAll) {
        ArrayList<Category> filteredCategoryList;
        if (isAll || !moduleKeyword.isEmpty()) {
            filteredCategoryList = isExact ? ModuleManager.filterExact(moduleKeyword, categoryKeyword) :
                    ModuleManager.filter(moduleKeyword, categoryKeyword);
            return new ArrayList<>(filteredCategoryList);
        }
        try {
            Module baseModule = DirectoryTraverser.getBaseModule();
            filteredCategoryList = isExact ? baseModule.getCategories().filterExact(categoryKeyword) :
                    baseModule.getCategories().filter(categoryKeyword);
            return new ArrayList<>(filteredCategoryList);
        } catch (IncorrectDirectoryLevelException e) {
            // Current directory is too high to get module information, i.e. at root level
            filteredCategoryList = isExact ? ModuleManager.filterExact(moduleKeyword, categoryKeyword) :
                    ModuleManager.filter(moduleKeyword, categoryKeyword);
            return new ArrayList<>(filteredCategoryList);
        }
    }

    protected ArrayList<Directory> createFilteredTaskList(String moduleKeyword, String categoryKeyword,
                                                          String taskKeyword, boolean isExact, boolean isAll) {
        if (isAll || !moduleKeyword.isEmpty()) {
            return new ArrayList<>(filterAll(moduleKeyword, categoryKeyword, taskKeyword, isExact));
        }

        if (!categoryKeyword.isEmpty()) {
            try {
                return new ArrayList<>(filterFromModule(categoryKeyword, taskKeyword, isExact));
            } catch (IncorrectDirectoryLevelException e) {
                // Current directory is too high to get module information, i.e. at root level
                return new ArrayList<>(filterAll(moduleKeyword, categoryKeyword, taskKeyword, isExact));
            }
        }

        try {
            return new ArrayList<>(filterFromCategory(taskKeyword, isExact));
        } catch (IncorrectDirectoryLevelException e) {
            try {
                // Current directory is too high to get category information, i.e. at root / module level
                return new ArrayList<>(filterFromModule(categoryKeyword, taskKeyword, isExact));
            } catch (IncorrectDirectoryLevelException f) {
                // Current directory is too high to get module information, i.e. at root level
                return new ArrayList<>(filterAll(moduleKeyword, categoryKeyword, taskKeyword, isExact));
            }
        }
    }

    private ArrayList<Task> filterAll(String moduleKeyword, String categoryKeyword, String taskKeyword,
                                      boolean isExact) {
        return isExact ? ModuleManager.filterExact(moduleKeyword, categoryKeyword, taskKeyword) :
                ModuleManager.filter(moduleKeyword, categoryKeyword, taskKeyword);
    }

    private ArrayList<Task> filterFromModule(String categoryKeyword, String taskKeyword, boolean isExact)
            throws IncorrectDirectoryLevelException {
        Module baseModule = DirectoryTraverser.getBaseModule();
        return isExact ? baseModule.getCategories().filterExact(categoryKeyword, taskKeyword) :
                baseModule.getCategories().filter(categoryKeyword, taskKeyword);
    }

    private ArrayList<Task> filterFromCategory(String taskKeyword, boolean isExact)
            throws IncorrectDirectoryLevelException {
        Category baseCategory = DirectoryTraverser.getBaseCategory();
        return isExact ? baseCategory.getTasks().filterExact(taskKeyword) :
                baseCategory.getTasks().filter(taskKeyword);
    }


}
