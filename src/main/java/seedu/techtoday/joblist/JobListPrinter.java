package seedu.techtoday.joblist;

import seedu.techtoday.common.Messages;
import seedu.techtoday.objects.Job;
import java.util.ArrayList;

public class JobListPrinter {
    /**
     * Prints all available tasks.
     * @param jobList List that stores the jobs mentioned until now.
     */
    public static void execute(ArrayList<Job> jobList) {
        if (jobList.size() == 0) {
            Messages.printInCenter("There is nothing in the list currently.");
        }
        int taskCounter = 1;
        for (Job job : jobList) {
            JobPrinter.execute(taskCounter, job);
            taskCounter += 1;
            System.out.println("\n");
        }
    }


}