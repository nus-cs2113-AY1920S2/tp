package seedu.nuke.module;

import seedu.nuke.task.Stuff;
import seedu.nuke.task.Task;

import java.util.ArrayList;

public class ModuleStuffList {
    ArrayList<Stuff> stuffList;

    public ModuleStuffList() {
        stuffList = new ArrayList<>();
    }

    public void add(Stuff stuff) {
        stuffList.add(stuff);
    }

    public void delete(int index) {
        stuffList.remove(index);
    }

    public void delete(Task taskToDelete) {
        stuffList.remove(taskToDelete);
    }

    public boolean delete(String stuffName) {
        for (Stuff stuff : stuffList) {
                if (stuff.getDescription().equals(stuffName)) {
                    stuffList.remove(stuff);
                    return true;
            }
        }
        return false;
    }

}
