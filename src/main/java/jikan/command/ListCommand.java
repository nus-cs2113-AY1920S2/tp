package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.ui.Ui;

import jikan.exception.InvalidTimeFrameException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static jikan.Jikan.lastShownList;

/**
 * Represents a command to list all activities in the activity list to the user.
 */
public class ListCommand extends Command {

    /**
     * Constructor to create a new list command.
     */
    public ListCommand(String parameters) {
        super(parameters);
    }

    /**
     * Parse a list command. The user can specify either a single date or a specific time frame.
     *
     * @param activityList The activity list to search for matching activities.
     */
    @Override
    public void executeCommand(ActivityList activityList) throws InvalidTimeFrameException {
        // If no time frame is specified, print the entire list
        if (parameters == null) {
            lastShownList.activities.clear();
            Ui.printList(activityList);

            // Can't do lastShownList = activityList, otherwise we just copy
            lastShownList.activities.addAll(activityList.activities);
        } else {
            listInterval(activityList);
        }
    }

    private void listInterval(ActivityList activityList) throws InvalidTimeFrameException {
        String[] listInputs;
        listInputs = parameters.split(" ", 2);

        lastShownList.activities.clear();

        LocalDate startDate = null;
        LocalDate endDate = null;

        // Parse either format
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd]");

        // Check if the user has given a verbal input
        // (User can either say day or daily and get the same output)
        switch (listInputs[0]) {
        case "day":
            // Fallthrough
        case "daily":
            startDate = LocalDate.now();
            break;
        case "week":
            // Fallthrough
        case "weekly":
            // If user has input a specific date to obtain the week from, use that;
            // (eg. the input is list week 2020-05-20)
            // Otherwise get current date
            if (listInputs.length == 2) {
                startDate = LocalDate.parse(listInputs[1], parser);
            } else {
                startDate = LocalDate.now();
            }

            // Set current Monday and Sunday as time range
            startDate = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            endDate = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            break;
        case "month":
            // Fallthrough
        case "monthly":
            // If user has input a specific date to obtain the month from, use that;
            // Otherwise get current date
            if (listInputs.length == 2) {
                startDate = LocalDate.parse(listInputs[1], parser);
            } else {
                startDate = LocalDate.now();
            }

            // Set first and last day of month as time range
            startDate = startDate.withDayOfMonth(1);
            endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
            break;
        default:
            startDate = LocalDate.parse(listInputs[0], parser);
            if (listInputs.length == 2) {
                endDate = LocalDate.parse(listInputs[1], parser);
            }
            break;
        }

        // Only one date is specified; return all entries with start date coinciding with that date
        if (endDate == null) {
            for (Activity i : activityList.activities) {
                if (i.getDate().equals(startDate)) {
                    lastShownList.activities.add(i);
                }
            }
            Ui.printList(lastShownList);
            // Both start and end dates are specified
        } else {

            if (endDate.isBefore(startDate)) {
                throw new InvalidTimeFrameException();
            }

            for (Activity i : activityList.activities) {
                if (i.isWithinDateFrame(startDate, endDate)) {
                    lastShownList.activities.add(i);
                }
            }
            Ui.printList(lastShownList);
        }
    }
}
