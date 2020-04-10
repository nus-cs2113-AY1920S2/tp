package seedu.nuke.directory;

public class TaskTag extends Directory{
    private String tagInfo;
    private int tagIndex;

    public TaskTag(Task task, String tagInfo, int tagIndex) {
         super(task);
        this.tagInfo = tagInfo;
        this.tagIndex = tagIndex;
    }

    public String getTagInfo () {
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
