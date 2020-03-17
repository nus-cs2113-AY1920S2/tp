package seedu.nuke.directory;

import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.TaskManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Module extends Directory {
    private String moduleCode;
    private String title;
    private String description;
    private CategoryManager categories;


    /**
     * Constructs the module
     *
     * @param root
     *  The root of the directory
     * @param moduleCode
     *  The module code of the module
     * @param title
     *  The title of the module
     * @param description
     *  The description of the module
     */
    public Module(Root root, String moduleCode, String title, String description) {
        super(root);
        this.moduleCode = moduleCode.toUpperCase();
        this.title = title;
        this.description = description;
        this.categories = new CategoryManager(this);
    }

    /**
     * Constructs the module but with limited information
     *
     * @param moduleCode
     *  The module code of the module
     */
    public Module(String moduleCode) {
        this(null, moduleCode, null, "NIL");
    }

    /**
     * Return the module code of the module.
     *
     * @return
     *  The module code of the module
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns the title of the module.
     *
     * @return
     *  The title of the module
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of the module.
     *
     * @return
     *  The description of the module
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the Category List of the module.
     *
     * @return
     *  The Category List of the module
     */
    public CategoryManager getCategories() {
        return categories;
    }

    /**
     * Edits the module code of the module.
     *
     * @param moduleCode
     *  The module code of the module
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Edits the title of the module.
     *
     * @param title
     *  The title of the module
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Edits the description of the module.
     *
     * @param description
     *  The description of the module
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Edit the Category List of the module.
     *
     * @param categories
     *  The Category List of the module
     */
    public void setCategories(CategoryManager categories) {
        this.categories = categories;
    }

    public int countTasks() {
        return new TaskManager().countTotalTasks();
    }

    /**
     * The method to check the deadline of the tasks in a module.
     * @return an ArrayList of String representing the deadline of tasks in order.
     */
    public ArrayList<String> checkDeadline() {
        ArrayList<String> deadlines = new ArrayList<>();
        ArrayList<Task> tasks = new TaskManager().getTaskList();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                String t1Deadline = t1.getDeadline() == null ? "" : t1.getDeadline().toString();
                String t2Deadline = t2.getDeadline() == null ? "" : t2.getDeadline().toString();
                return t1Deadline.compareToIgnoreCase(t2Deadline);
            }
        });
        for (Task task: tasks) {
            String deadline = task.getDeadline() == null ? "" : task.getDeadline().toString();
            deadlines.add(String.format("%-30s", task.getDescription()) + "   Deadline: " + task.getDeadline());
        }
        return deadlines;
    }

    /**
     * Checks if one module has the same module code as another.
     *
     * @param module
     *  The module to check
     * @return
     *  <code>TRUE</code> if they are the same, and <code>FALSE</code> otherwise
     */
    public boolean isSameModule(Module module) {
        return this.moduleCode.equalsIgnoreCase(module.moduleCode);
    }

}
