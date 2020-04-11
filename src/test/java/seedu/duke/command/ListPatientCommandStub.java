package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.List;

/**
 * Stub class for ListPatientCommand.
 */
public class ListPatientCommandStub extends ListPatientCommand {
    public ListPatientCommandStub() throws InvalidFormatException {
    }

    /**
     * Stub to simulate adding patients into list.
     *
     * @param ui      stub ui
     * @param storage stub storage
     */
    public void execute(Ui ui, Storage storage) {
        List<PatientStub> patientListStub = PatientListStub.getPatientList(); //getPatientList() method by @Brandonnn
        for (PatientStub p : patientListStub) {
            System.out.println(p); //override Patient class toString by @Sammmmm
        }
    }
}
