package seedu.techtoday.joblist;

import seedu.techtoday.common.CommonMethods;
import seedu.techtoday.common.TimeStampToDateConverter;
import seedu.techtoday.objects.Job;

/** Class representing a method used to print a job. */
public class JobPrinter {
    /**
     * Executes the printing of a job.
     * @param taskCounter - Index number of jobs in jobList.
     * @param job - Job object representing a job.
     */
    public static void execute(int taskCounter, Job job) {
        String timeStamp = job.getTimeStamp();
        String date = TimeStampToDateConverter.execute(timeStamp);
        String title = job.getTitle();
        String text = job.getText();
        String upToNCharactersText = CommonMethods.returnUptoNcharacters(text);
        String category = job.getCategory();
        String extract = job.getExtract();
        String upToNCharactersExtract = CommonMethods.returnUptoNcharacters(extract);
        System.out.println(taskCounter + ". Title: " + title + System.lineSeparator()
                            + "   Date: " + date + System.lineSeparator()
                            + "   Category: " + category + System.lineSeparator()
                            + "   Text: " + upToNCharactersText + System.lineSeparator()
                            + "   Extract: " + upToNCharactersExtract);
    }

    /**
     * Prints a job outside the context of a jobList.
     * @param job - Job object representing a job.
     */
    public static void printIsolatedJob(Job job) {
        String timeStamp = job.getTimeStamp();
        String date = TimeStampToDateConverter.execute(timeStamp);
        String title = job.getTitle();
        String text = job.getText();
        String upToNCharactersText = CommonMethods.returnUptoNcharacters(text);
        String category = job.getCategory();
        String extract = job.getExtract();
        String upToNCharactersExtract = CommonMethods.returnUptoNcharacters(extract);
        System.out.println("   Title: " + title + System.lineSeparator()
                + "   Date: " + date + System.lineSeparator()
                + "   Category: " + category + System.lineSeparator()
                + "   Text: " + upToNCharactersText + System.lineSeparator()
                + "   Extract: " + upToNCharactersExtract);
    }
}
