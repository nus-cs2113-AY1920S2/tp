package seedu.techtoday.joblist;

import seedu.techtoday.objects.Job;
import java.util.ArrayList;

/** Class representing a method used to save a job. */
public class JobSaver extends SavedJobList {

    public JobSaver() {
        super();
    }

    /**
     * Executes the saving of a job.
     * @param jobArrayList - List of jobs.
     * @param command - User command on the terminal.
     */
    public static void execute(ArrayList<Job> jobArrayList, String command) {
        String[] commandArray = command.split(" ");
        int commandNumber = Integer.parseInt(commandArray[2]) - 1;
        Job jobToSave = ViewedJobList.viewedJobList.get(commandNumber);
        //add the job
        System.out.println("Done, saved the job with the following details:");
        JobPrinter.printIsolatedJob(jobToSave);
        JobAdder.execute(SavedJobList.savedJobList, jobToSave);
    }
}
