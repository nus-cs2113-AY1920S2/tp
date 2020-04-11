package seedu.duke.command;


import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.HashMap;
import java.util.Map;

/**
 * Stub class for fake addCommand class.
 */
class AddPatientCommandStub extends AddPatientCommand {

    private static final String PATIENT_NAME = "name";
    private static final String AGE = "age";
    private static final String ADDRESS = "address";
    private static final String CONTACT_NUMBER = "phone";
    private String contactNumber;
    private String address;
    private String patientName;
    private int age;

    public AddPatientCommandStub(Map<String, String> patientInfo) throws InvalidFormatException {
        super(patientInfo);
        this.patientName = patientInfo.get(PATIENT_NAME);
        if (patientInfo.get(AGE).isBlank()) {
            this.age = -1;
        } else {
            try {
                this.age = Integer.parseInt(patientInfo.get(AGE));
            } catch (NumberFormatException e) {
                this.age = -1;
            }

        }
        this.address = patientInfo.get(ADDRESS);
        this.contactNumber = patientInfo.get(CONTACT_NUMBER);
    }

    /**
     * Just to generate the fake map of information.
     *
     * @param choice the type of fake map to generate
     * @return the fake map of information
     */
    public static Map<String, String> generateMap(int choice) {
        Map<String, String> tempMap = new HashMap<>();
        if (choice == 1) {
            tempMap.put("name", "asd");
            tempMap.put("age", "23");
            tempMap.put("address", "asdsds");
            tempMap.put("phone", "asdsadsad");
        } else if (choice == 2) {
            tempMap.put("name", "");
            tempMap.put("age", "");
            tempMap.put("address", "");
            tempMap.put("phone", "");
        }
        return tempMap;

    }


    /**
     * To act as a stub for execute.
     *
     * @param ui      ui object for displaying information
     * @param storage storage object to do auto saving
     */
    public void execute(Ui ui, Storage storage) {
        PatientStub newPatient = new PatientStub(this.patientName, this.age, this.address, this.contactNumber);

        PatientListStub.createList(1);
        PatientListStub.getPatientList().add(newPatient);

    }
}