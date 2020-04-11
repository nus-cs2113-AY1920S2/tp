package seedu.duke.command;

import seedu.duke.converter.TimeConverter;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.record.Appointment;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class deals with the command relating to finding the list of appointments containing a specific keyword.
 * <p></p>
 * <p>
 * This command utilises the functions in {@link seedu.duke.parser.Parser} and searches {@link Appointment} objects
 * within the saved {@link AppointmentList} that is managed by {@link Storage}.
 * </p>
 * <p></p>
 * <p>
 * To elaborate, it converts the output of {@link seedu.duke.parser.Parser} into a {@link Appointment} object, after
 * which the {@link Appointment} object is then
 * added into the {@link AppointmentList} list. Finally, the {@link Storage} class will be used to save the updated
 * list into offline data to reflect the change.
 * </p>
 *
 * @author Brandon Chong
 * @version v2.0
 * @since 28/3/2020
 */
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "finda";
    private static final String EXAMPLE = "finda 20/12/2020";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find an appointment by keyword.\n"
            + "Example: " + EXAMPLE;
    private String searchValue;
    private TimeConverter timeConverter;
    private static final int TIME_INPUT_LENGTH = 8;
    private static final int DATE_INPUT_LENGTH = 10;

    /**
     * Constructor for the find appointment command class.
     *
     * @param searchValue the <code>String</code> object representing the keyword to search for in
     *                    each Appointment object's fields
     */
    public FindAppointmentCommand(String searchValue) throws InvalidFormatException {
        this.searchValue = searchValue;
    }

    public String getSearchValue() {
        return searchValue;
    }

    /**
     * For this execution, the existing list of Appointment records is searched for a keyword.
     *
     * <p>
     * We check the searchValue to see if it is of the correct format (dd/mm/yyyy for date, hh:mm am/pm for time)
     * If input is invalid format, we exit. Else, we search the list of Appointments for the search keyword.
     * Records that contain the keyword will be added to a separate List and printed out in a readable format.
     * </p>
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException this exception is thrown by the {@link Storage} class if it fails to save the current
     *                     appointment list into offline data.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException {
        boolean isDateInput = false;
        boolean isTimeInput = false;
        /* the time string hh:mm (am/pm) should have 8 characters in total
         *  the date string dd/mm/yyyy should have 10 characters in total
         *  we only check strings of these two lengths if they are in the correct format
         */
        if (Integer.valueOf(this.getSearchValue().length()) == TIME_INPUT_LENGTH) {
            if (!checkValidTime(this.getSearchValue())) {
                ui.printInvalidAppointmentSearchTimeMessage();
                return;
            }
            isTimeInput = true;
        } else if (Integer.valueOf(this.getSearchValue().length()) == DATE_INPUT_LENGTH) {
            if (!checkValidDate(this.getSearchValue())) {
                ui.printInvalidAppointmentSearchDateMessage();
                return;
            }
            isDateInput = true;
        } else {
            ui.printInvalidAppointmentSearchFormatMessage();
            return;
        }

        String parsedSearchValue = null;

        if (isDateInput) {
            try {
                parsedSearchValue = TimeConverter.oldDate(this.getSearchValue());
            } catch (ParseException e) {
                ui.printSearchValueConversionErrorMessage();
                return;
            }
        }
        if (isTimeInput) {
            parsedSearchValue = this.getSearchValue();

        }

        assert parsedSearchValue != null : "The searchValue should contain a non-null value.";

        List<Appointment> searchResults = new ArrayList<>();
        /*get the list of all Appointments from Storage to conduct search*/
        for (Appointment appointment : AppointmentList.getAppointmentList()) {
            if (appointment.getDate().contains(parsedSearchValue)
                    || appointment.getTime().contains(parsedSearchValue)) {
                searchResults.add(appointment);
            }
        }
        ui.printAppointmentSearchResults(searchResults);

    }

    /**
     * This method checks if the search input is a valid date.
     *
     * @param dateInput the date input (determined to be a date due to its length)
     * @return a boolean indicating if the input is a valid date.
     */
    private boolean checkValidDate(String dateInput) {
        try {
            TimeConverter.oldDate(dateInput);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * This method checks if the search input is a valid time.
     *
     * @param timeInput the input (determined to be a time search value due to its length)
     * @return a boolean indicating if the value is a valid date.
     */
    private boolean checkValidTime(String timeInput) {
        try {
            TimeConverter.convertTime(timeInput);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

