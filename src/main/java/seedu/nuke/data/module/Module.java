package seedu.nuke.data.module;

import seedu.nuke.data.taskCategory.TaskCategoryList;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private TaskCategoryList taskCategories;

    public Module(String moduleCode, String title, String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
        this.taskCategories = new TaskCategoryList();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskCategoryList getTaskCategories() {
        return taskCategories;
    }

    public boolean isSameModule(Module module) {
        return this.moduleCode.equals(module.moduleCode);
    }
}
