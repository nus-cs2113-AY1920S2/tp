package seedu.techtoday.storage;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import seedu.techtoday.objects.Job;
import java.io.IOException;
import java.io.FileWriter;

import com.google.gson.JsonObject;

public class JobToFileSaver {

    /**
     * Saves a list of jobs to a file.
     * @param jobArrayList - List representing jobs.
     * @param filePath - The path wo where the job is to be saved.
     */
    public static void execute(ArrayList<Job> jobArrayList, String filePath) {

        int index = 1;
        JsonArray jobList = new JsonArray();

        for (int i = 0; i < jobArrayList.size(); i++) {
            Job job = jobArrayList.get(i);
            String title = job.getTitle();
            String text = job.getText(); //gets text
            String category = job.getCategory();
            String extract = job.getExtract();
            String timeStamp = job.getTimeStamp();

            //job Details
            JsonObject jobDetails = new JsonObject();
            jobDetails.addProperty("title", title);
            jobDetails.addProperty("text", text);
            jobDetails.addProperty("timestamp", timeStamp);
            jobDetails.addProperty("category", category);
            jobDetails.addProperty("extract", extract);

            //Index: job Details
            JsonObject jobObject = new JsonObject();
            jobObject.add(Integer.toString(index), jobDetails);
            index += 1;

            jobList.add(jobObject);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(filePath)) {

            String jsonString = jobList.toString();

            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
