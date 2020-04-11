package seedu.nuke.data.storage;

import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;

import java.util.ArrayList;

public class Encoder {
    private static final String BEGIN_MODULE_INDICATOR = "--- MODULE ---\n";
    private static final String END_MODULE_INDICATOR = "--- END MODULE ---\n";
    private static final String BEGIN_CATEGORY_INDICATOR = "--- CATEGORY ---\n";
    private static final String END_CATEGORY_INDICATOR = "--- END CATEGORY ---\n";
    private static final String BEGIN_TASK_INDICATOR = "--- TASK ---\n";
    private static final String END_TASK_INDICATOR = "--- END TASK ---\n";
    private static final String BEGIN_FILE_INDICATOR = "--- FILE ---\n";
    private static final String END_FILE_INDICATOR = "--- END FILE ---\n";
    private static final String BEGIN_TAG_INDICATOR = "--- TAG ---\n";
    private static final String END_TAG_INDICATOR = "--- END TAG ---\n";
    private static final String DELIMITER = " -|||- ";
    private static final String LINE_BREAK = System.lineSeparator();

    private ArrayList<Module> moduleList;

    public Encoder(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * Encodes the Directory List to be saved into a file.
     *
     * @return
     *  The encoded Directory List
     */
    public String encode() {
        return encodeModuleList();
    }

    private String encodeModuleList() {
        StringBuilder encodedList = new StringBuilder(BEGIN_MODULE_INDICATOR);
        for (Module module : moduleList) {
            encodedList.append(getModuleInformation(module)).append(LINE_BREAK);
            encodedList.append(encodeCategoryList(module.getCategories().getCategoryList()));
        }
        encodedList.append(END_MODULE_INDICATOR);
        return encodedList.toString();
    }

    private String encodeCategoryList(ArrayList<Category> categories) {
        StringBuilder encodedList = new StringBuilder(BEGIN_CATEGORY_INDICATOR);
        for (Category category : categories) {
            encodedList.append(getCategoryInformation(category)).append(LINE_BREAK);
            encodedList.append(encodeTaskList(category.getTasks().getTaskList()));
        }
        encodedList.append(END_CATEGORY_INDICATOR);
        return encodedList.toString();
    }

    private String encodeTaskList(ArrayList<Task> tasks) {
        StringBuilder encodedList = new StringBuilder(BEGIN_TASK_INDICATOR);
        for (Task task : tasks) {
            encodedList.append(getTaskInformation(task)).append(LINE_BREAK);
            encodedList.append(encodeFileList(task.getFiles().getFileList()));
            encodedList.append(encodeTagList(task.getTags()));
        }
        encodedList.append(END_TASK_INDICATOR);
        return encodedList.toString();
    }

    private String encodeFileList(ArrayList<TaskFile> files) {
        StringBuilder encodedList = new StringBuilder(BEGIN_FILE_INDICATOR);
        for (TaskFile file : files) {
            encodedList.append(getFileInformation(file)).append(LINE_BREAK);
        }
        encodedList.append(END_FILE_INDICATOR);
        return encodedList.toString();
    }

    private String encodeTagList(ArrayList<String> tags) {
        StringBuilder encodedList = new StringBuilder(BEGIN_TAG_INDICATOR);
        for (String tag : tags) {
            encodedList.append(tag).append(LINE_BREAK);
        }
        encodedList.append(END_TAG_INDICATOR);
        return encodedList.toString();
    }

    private String getModuleInformation(Module module) {
        return String.join(DELIMITER, module.getModuleCode(), module.getTitle(), module.getDescription());
    }

    private String getCategoryInformation(Category category) {
        return String.join(DELIMITER, category.getCategoryName(), String.valueOf(category.getCategoryPriority()));
    }

    private String getTaskInformation(Task task) {
        String doneStatus = task.isDone() ? "Y" : "N";
        String deadline = task.getDeadline().isPresent() ? task.getDeadline().toString() : "";
        return String.join(DELIMITER, task.getDescription(), doneStatus, deadline, String.valueOf(task.getPriority()));
    }

    private String getFileInformation(TaskFile file) {
        return String.join(DELIMITER, file.getFileName(), file.getFilePath(), file.getOriginalFilePath());
    }
}
