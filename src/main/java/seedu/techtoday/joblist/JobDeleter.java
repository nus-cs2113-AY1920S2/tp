package seedu.techtoday.joblist;

import seedu.techtoday.objects.Job;
import java.util.ArrayList;


/** Represents command that is used to call a method to delete job from taskList. */
public class JobDeleter {
    /**
     * Deletes the task at index taskNumber from the tasklist.
     *
     * @param savedJobList List of all jobs saved as of now.
     * @param command Command inputted by the user.
     */
    public static void execute(ArrayList<Job> savedJobList, String command) {
        String[] commandArray = command.split(" ");
        int jobNumber = Integer.parseInt(commandArray[2]) - 1;
        Job jobToDelete = SavedJobList.savedJobList.get(jobNumber);
        System.out.println("Deleting the following job:");
        JobPrinter.printIsolatedJob(jobToDelete);
        SavedJobList.savedJobList.remove(jobNumber);
    }
}