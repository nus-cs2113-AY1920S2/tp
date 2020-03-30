package seedu.nuke.tag;

import java.util.ArrayList;

public interface Tag {
    public void setTag(ArrayList<String> infos);

    public void removeTag(String tag);

    public void removeAllTags();

    public String getTag();
}
