package seedu.happypills.controller.commands.visitcommands;

import seedu.happypills.model.data.*;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.view.ui.TextUi;

public class AddVisitCommand extends VisitCommand {

    protected String nric;
    protected String date;
    protected String time;
    protected String reason;

    /**
     * Constructor for AddVisitCommand Class.
     * It creates a new AddVisitCommand Object with the information provided.
     *
     * @param nric patient's nric.
     * @param date date of visit.
     * @param time time of visit.
     * @param reason reason for visit.
     */
    public AddVisitCommand(String nric, String date, String time, String reason) {
        this.nric = nric;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    @Override
    public String execute(PatientMap patients,AppointmentMap appointments, VisitMap visits) throws HappyPillsException {
        String message = "";
        if (!patients.containsKey(nric)) {
            message = "    Patient does not exist. Please try again.\n"
                    + TextUi.DIVIDER;
        } else {
            Visit visit = new Visit(nric,reason,date,time);
            visits.addVisit(visit);
            Patient patient = (Patient)patients.get(nric);
            message = "    Visit has been added.\n"
                    + TextUi.DIVIDER;
        }
        return message;
    }
}
