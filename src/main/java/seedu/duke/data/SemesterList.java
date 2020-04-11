package seedu.duke.data;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SemesterList extends PriorityQueue<SemModulesList> {
    public SemesterList() {
        super(Comparator.comparing(SemModulesList::getSem));
    }
}