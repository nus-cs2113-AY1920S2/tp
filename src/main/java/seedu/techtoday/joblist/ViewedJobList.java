package seedu.techtoday.joblist;

import seedu.techtoday.objects.Job;

import java.util.ArrayList;

/** Represents the data structure, i.e a list of Tasks, that stores the tasks.*/
public class ViewedJobList {

    public static ArrayList<Job> viewedJobList;

    /** Initializes a new taskList.  */
    public ViewedJobList() {
        viewedJobList = new ArrayList<Job>();
    }

}