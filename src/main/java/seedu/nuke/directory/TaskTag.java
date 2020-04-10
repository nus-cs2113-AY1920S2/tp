package seedu.nuke.directory;

public class TaskTag extends Directory {
    private String tagInfo;
    private int tagIndex;

    /**
     * Constructs a new TaskTag object.
     * @param task the task that contains this tag
     * @param tagInfo the information of the tag
     * @param tagIndex the index of the tag in the list of tags of the task that contains this tag
     */
    public TaskTag(Task task, String tagInfo, int tagIndex) {
        super(task);
        this.tagInfo = tagInfo;
        this.tagIndex = tagIndex;
    }

    public String getTagInfo() {
        return tagInfo;
    }

    public int getTagIndex() {
        return tagIndex;
    }

    @Override
    public Task getParent() {
        return (Task) parent;
    }

    @Override
    public DirectoryLevel getLevel() {
        return null;
    }
}
