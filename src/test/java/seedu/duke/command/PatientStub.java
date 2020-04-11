package seedu.duke.command;

/**
 * Stub class for fake patient class.
 */
public class PatientStub {
    private String name;
    private int age;
    private String address;
    private String contactNumber;

    /**
     * Default constructor for stub class.
     *
     * @param name          name of patient
     * @param age           age of patient
     * @param address       address of patient
     * @param contactNumber contact number of patient
     */
    public PatientStub(String name, int age, String address, String contactNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    /**
     * Stub method to get patient name.
     *
     * @return patient name
     */
    public String getName() {
        return name;
    }

    /**
     * Stub method to get patient age.
     *
     * @return patient age
     */
    public int getAge() {
        return age;
    }

    /**
     * Stub method to get patient address.
     *
     * @return patient address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Stub method to get patient number.
     *
     * @return patient number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Override toString method for stub class.
     *
     * @return formatted string to print
     */
    @Override
    public String toString() {
        String newToString = "{" + "[Name]:" + " " + getName() + " " + "|" + " " + "[Age]:" + " " + getAge() + " " + "|"
                + " " + "[Address]:" + " " + getAddress() + " " + "|" + " " + "[Contact Number]:" + " "
                + getContactNumber() + "}";
        return newToString;
    }
}
