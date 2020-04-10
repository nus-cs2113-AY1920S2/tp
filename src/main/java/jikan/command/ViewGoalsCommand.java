package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyTagException;
import jikan.exception.InvalidTimeFrameException;
import jikan.ui.Ui;
import static jikan.Jikan.tagFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a command to view goals for tags in the activity list.
 */
public class ViewGoalsCommand extends Command {

    File tagFile;
    private static final String TAG_FILE_PATH = "data/tag/tag.csv";

    /**
     * Constructor to create a new viewgoal command.
     * @param parameters the parameters of the goal command.
     */
    public ViewGoalsCommand(String parameters, File tagFile) {
        super(parameters);
        this.tagFile = tagFile;
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        HashMap<String, Duration> tagsGoals = new HashMap<>();
        populateTagList(tagFile, tagsGoals);
        getGoalData(activityList,tagsGoals);

    }

    private void getGoalData(ActivityList activityList, HashMap<String, Duration> tagsGoals) {
        HashMap<String, Duration> tagsActual = new HashMap<>();
        for (Activity activity : activityList.activities) {
            GraphCommand.extractTags(tagsActual, activity);
        }
        try {
            if (tagsActual.isEmpty() || tagsGoals.isEmpty()) {
                throw new EmptyTagException();
            }
            Ui.printGoals(tagsGoals, tagsActual);
        } catch (NullPointerException | EmptyTagException e) {
            Ui.printDivider("There are no tags to display.");
        }
    }

    /**
     * Populates task list from file.
     *
     * @param dataFile Data file to populate from.
     */
    private void populateTagList(File dataFile, HashMap<String, Duration> tagsGoals) {
        try {
            tagFile = new File(TAG_FILE_PATH);
            Scanner dataScanner = new Scanner(tagFile);
            while (dataScanner.hasNext()) {
                parseDataLine(dataScanner.nextLine(), tagsGoals);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Could not load into the current session's tag list.");
        } catch (NullPointerException e) {
            System.out.println("Error.");
        }
    }

    /**
     * Parses the current line in the tag file.
     *
     * @param s String to parse.
     */
    private void parseDataLine(String s, HashMap<String, Duration> tagsGoals) {
        if (!s.isEmpty()) {
            List<String> strings = Arrays.asList(s.split(","));
            Duration duration = Duration.parse(strings.get(1));
            tagsGoals.put(strings.get(0), duration);
        }
    }
}
