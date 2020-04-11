package seedu.techtoday.joblist;


import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Job;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JobAdderTest {

    @Test
    void execute() {
        ArrayList<Job> jobList = new ArrayList<Job>();
        JobAdder.execute(jobList, new Job("Test", "www.test.com", "job"));
        assertEquals(1, jobList.size());
    }

    @Test
    public void constructorTest() {
        JobAdder job = new JobAdder();
        assertEquals(job.viewedJobList, new ArrayList<Job>());
    }
}