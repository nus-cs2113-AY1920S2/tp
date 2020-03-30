package seedu.nuke.tag;

public interface Tag {
    public void setTag(String info);

    public void removeTag(String tag);

    public void removeAllTags();

    public String getTag();
}
