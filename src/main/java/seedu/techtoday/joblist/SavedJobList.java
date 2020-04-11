package seedu.techtoday.joblist;

import seedu.techtoday.objects.Job;

import java.util.ArrayList;

/** Represents the data structure, i.e a list of Tasks, that stores the tasks.*/
public class SavedJobList {

    public static ArrayList<Job> savedJobList;

    /** Initializes a new taskList.  */
    public SavedJobList() {
        savedJobList = new ArrayList<Job>();
    }

}