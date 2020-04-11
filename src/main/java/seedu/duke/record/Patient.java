package seedu.duke.record;

/**
 * This class contains the patient information such as name, age, address and contact number.
 *
 * @author yukilite
 */

public class Patient {
    private String name;
    private int age;
    private String address;
    private String contactNumber;
    private int patientID;

    /**
     * This method stores the patient information when the user enters an input.
     *
     * @param name          the name of the patient
     * @param age           the age of the patient
     * @param address       the address of the patient
     * @param contactNumber the phone number of the patient
     * @param patientID     the patientID number
     */
    public Patient(String name, int age, String address, String contactNumber, int patientID) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.contactNumber = contactNumber;
        this.patientID = patientID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        assert name != null;
        return name;
    }

    /**
     * Update the name if it is not null.
     *
     * @param name name that needs to be updated
     */
    public void setName(String name) {
        if (!name.isBlank()) {
            assert name != null;
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    /**
     * Update the age if it is a positive integer.
     *
     * @param age age that needs to be updated
     */
    public void setAge(int age) {
        if (age >= 0) {
            assert age >= 0;
            this.age = age;
        }
    }

    public String getAddress() {
        return address;
    }

    /**
     * This part is implemented for the EditPatientCommand class by Duc
     */

    /**
     * Update the address if it is not null.
     *
     * @param address address that needs to be updated
     */
    public void setAddress(String address) {
        if (!address.isBlank()) {
            assert address != null;
            this.address = address;
        }
    }

    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Update the contact number if it is not null.
     *
     * @param contactNumber contact number that needs to be updated
     */
    public void setContactNumber(String contactNumber) {
        if (!contactNumber.isBlank()) {
            assert contactNumber != null;
            this.contactNumber = contactNumber;
        }
    }

    /**
     * Overrides the default toString command so that the patient information can be printed
     * in a specific string format.
     *
     * @return newToString The formatted string
     */
    @Override
    public String toString() {
        String newToString =
                "{" + "[Name]:" + " " + getName() + " " + "|" + " " + "[Age]:" + " " + getAge() + " " + "|" + " "
                        + "[Address]:" + " " + getAddress() + " " + "|" + " " + "[Contact Number]:" + " "
                        + getContactNumber() + " | " + "[PatientID]: " + getPatientID() + "}";
        if (age <= -1) {
            newToString =
                    "{" + "[Name]:" + " " + getName() + " " + "|" + " " + "[Age]:" + "  " + "|" + " " + "[Address]:"
                            + " " + getAddress() + " " + "|" + " " + "[Contact Number]:" + " " + getContactNumber()
                            + " | " + "[PatientID]: " + getPatientID() + "}";
        }
        return newToString;
    }

    /**
     * Method to update all the patient's information.
     *
     * @param name          name that needs to be updated
     * @param age           age that needs to be updated
     * @param address       address that needs to be updated
     * @param contactNumber contact number that needs to be updated
     */
    public void setPatientInfo(String name, int age, String address, String contactNumber) {
        setName(name);
        setAge(age);
        setAddress(address);
        setContactNumber(contactNumber);
    }
}

