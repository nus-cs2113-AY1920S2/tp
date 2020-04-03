package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.ExtraParametersException;
import jikan.exception.NameTooLongException;
import jikan.ui.Ui;

import jikan.exception.InvalidTimeFrameException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

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
    public void executeCommand(ActivityList activityList) {
        // If no time frame is specified, print the entire list
        if (parameters == null) {
            listAll(activityList);
        } else {
            try {
                listInterval(activityList);
            } catch (DateTimeParseException e) {
                Ui.printDivider("Please enter a valid date in the format dd/MM/yyyy or yyyy-MM-dd\n"
                        + "Or use day / week / month to view tasks in the respective time period.");
            } catch (InvalidTimeFrameException e) {
                Ui.printDivider("Please enter a valid time frame; the end date must come after the start date.");
            } catch (ExtraParametersException e) {
                Ui.printDivider("Extra parameters detected!\n"
                        + "Use day / week / month to view tasks in the respective time period.");
            }
        }
    }

    private void listAll(ActivityList activityList) {
        lastShownList.activities.clear();
        Ui.printList(activityList);

        // Can't do lastShownList = activityList, otherwise we just copy
        lastShownList.activities.addAll(activityList.activities);
    }

    private void listInterval(ActivityList activityList)
            throws InvalidTimeFrameException, DateTimeParseException, ExtraParametersException {
        String[] listInputs;
        listInputs = parameters.split(" ", 2);

        lastShownList.activities.clear();

        LocalDate startDate;
        LocalDate endDate = null;

        // Parse either format
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd]");

        // Check if the user has given a verbal input
        // (User can either say day or daily and get the same output)
        switch (listInputs[0]) {
        case "today":
            // Fallthrough
        case "day":
            // Fallthrough
        case "daily":
            checkExtraParameters(listInputs);
            startDate = LocalDate.now();
            break;
        case "yesterday":
            checkExtraParameters(listInputs);
            startDate = LocalDate.now().minusDays(1);
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
            // If user has input a specific month, use that;
            // Otherwise get current date
            if (listInputs.length == 2) {
                try {
                    startDate = getMonth(listInputs[1]);
                } catch (IllegalArgumentException e) {
                    Ui.printDivider("Please specify the full month name.");
                    return;
                }
            } else {
                startDate = LocalDate.now();
                startDate = startDate.withDayOfMonth(1);
            }
            endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
            break;
        default:
            // date / date range is given
            startDate = LocalDate.parse(listInputs[0], parser);

            if (listInputs.length == 2) {
                endDate = LocalDate.parse(listInputs[1], parser);
            }
            break;
        }
        printList(activityList, startDate, endDate);
    }

    private void printList(ActivityList activityList, LocalDate startDate, LocalDate endDate) throws InvalidTimeFrameException {
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

    private LocalDate getMonth(String listInput) {
        LocalDate startDate;
        Month month = Month.valueOf(listInput.toUpperCase());
        YearMonth yearMonth = YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), month.getValue());
        startDate = yearMonth.atDay(1);
        return startDate;
    }

    private void checkExtraParameters(String[] listInputs) throws ExtraParametersException {
        if (listInputs.length > 1) {
            throw new ExtraParametersException();
        }
    }
}
