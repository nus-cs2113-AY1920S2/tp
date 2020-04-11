package seedu.techtoday.joblist;

import java.util.ArrayList;
import seedu.techtoday.objects.Job;

/** Class representing a method used to add a job to a joblist. */
public class JobAdder extends ViewedJobList {

    public JobAdder() {
        super();
    }

    /** Adds a job to a jobList. */
    public static void execute(ArrayList<Job> jobList, Job job) {
        jobList.add(job);
    }
}
