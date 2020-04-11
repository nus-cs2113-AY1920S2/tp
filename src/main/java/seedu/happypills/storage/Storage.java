package seedu.happypills.storage;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.exception.HappyPillsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * A class that manages read and write operations to file.
 */
public class Storage {
    public static final String PATIENT_FILEPATH = "data/patient.txt";
    public static final String APPOINTMENT_FILEPATH = "data/appointment.txt";
    public static final String PATIENT_RECORD_FILEPATH = "data/patientrecord.txt";

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Overwrite file with a formatted string of the entire list provided. Creates file if it does not exist.
     * Used for edit and delete commands
     *
     * @author janicetyy
     * @param filePath location of file to save to, requires directory/file.
     * @param dataString  a formatted string of a list of items to be saved to file.
     * @throws IOException if unable to save to file, possibly due to interruptions.
     */
    public static void writeAllToFile(String filePath, String dataString) throws IOException {
        File d = new File(filePath.substring(0, filePath.lastIndexOf('/')));
        if (!d.exists()) {
            d.mkdir();
        }

        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(dataString);
        fw.close();
    }

    /**
     * Add new single line formatted strings to file. Creates file if it does not exist.
     *
     * @author janicetyy
     * @param filePath location of file to save to, requires directory/file.
     * @param dataString  single item as string to be saved to file.
     * @throws IOException if unable to save to file, possibly due to interruptions.
     */
    public static void addSingleItemToFile(String filePath, String dataString) throws IOException {
        File d = new File(filePath.substring(0, filePath.lastIndexOf('/')));
        if (!d.exists()) {
            d.mkdir();
        }

        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(filePath,true);
        fw.write(dataString);
        fw.close();
    }

    /**
     * Read and send file data to parse line by line as string.
     * Returns a list of historical patients patient list
     *
     * @author janicetyy
     * @param filePath location of file to read from.
     * @return patientList of all patients found in the file.
     * @throws FileNotFoundException if the file specified by directory/filename does not exist.
     */
    public static PatientMap loadPatientsFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        PatientMap storedPatients = new PatientMap();

        while (s.hasNext()) {
            String stringInput = s.nextLine();
            parsePatientFileContent(stringInput, storedPatients);
        }

        return storedPatients;
    }

    /**
     * convert a single line data into values of a patients and add it back to the provided patientList.
     * @author janicetyy
     * @param savedString a single string with all the data required for a patient.
     * @param storedPatients a list which the patient details retrieved should be added into.
     */
    private static void parsePatientFileContent(String savedString, PatientMap storedPatients) {
        String[] dataString = savedString.split("[|]", 7);
        Patient tempPatient = new Patient(dataString[0], dataString[1],
                Integer.parseInt(dataString[2]), dataString[3], dataString[4],
                dataString[5], dataString[6]);
        try {
            storedPatients.add(tempPatient);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read and send file data to parse line by line as string.
     * Returns a list of historical appointment list
     *
     * @author janicetyy
     * @param filePath location of file to read from.
     * @param patients Shared map of all patient
     * @return patientList of all patients found in the file.
     * @throws FileNotFoundException if the file specified by directory/filename does not exist.
     */
    public static AppointmentMap loadAppointmentFromFile(String filePath, PatientMap patients)
            throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        AppointmentMap storedAppt = new AppointmentMap();

        while (s.hasNext()) {
            String stringInput = s.nextLine();
            parseAppointmentFileContent(stringInput, storedAppt, patients);
        }

        return storedAppt;
    }

    /**
     * convert a single line data into values of an appointment and add it back to the provided apptList.
     * @author janicetyy
     * @param savedString a single string with all the data required for a appointment.
     * @param storedAppt a list which the appointment details retrieved should be added into.
     */
    private static void parseAppointmentFileContent(String savedString,
                                                    AppointmentMap storedAppt, PatientMap patients) {
        String[] dataString = savedString.split("[|]", 7);
        Boolean isDone = dataString[5].equals("T");
        Appointment tempAppt = new Appointment(dataString[0], dataString[1],
                dataString[2], dataString[3], dataString[4], isDone);
        try {
            Patient patient = (Patient) patients.get(dataString[1]);
            if (patient != null) {
                patient.addAppointment(tempAppt);
                storedAppt.addAppointment(tempAppt);
            }
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read and send file data to parse line by line as string.
     * Returns a list of historical patients record list
     *
     * @author janicetyy
     * @param filePath location of file to read from.
     * @param patients Shared map of all patients
     * @return patientList of all patients found in the file.
     * @throws FileNotFoundException if the file specified by directory/filename does not exist.
     */
    public static PatientRecordMap loadPatientRecordFromFile(String filePath, PatientMap patients)
            throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        PatientRecordMap storedPr = new PatientRecordMap();

        while (s.hasNext()) {
            String stringInput = s.nextLine();
            parsePatientRecordFileContent(stringInput, storedPr, patients);
        }

        return storedPr;
    }

    /**
     * convert a single line data into values of an appointment and add it back to the provided apptList.
     * @author janicetyy
     * @param savedString a single string with all the data required for a appointment.
     * @param storedPr a list which the patient record details retrieved should be added into.
     */
    private static void parsePatientRecordFileContent(String savedString,
                                                    PatientRecordMap storedPr, PatientMap patients) {
        String[] dataString = savedString.split("[|]", 5);
        PatientRecord tempPr = new PatientRecord(dataString[0], dataString[1],
                dataString[2], dataString[3], dataString[4]);
        try {
            Patient patient = (Patient) patients.get(dataString[0]);
            if (patient != null) {
                storedPr.add(tempPr,dataString[0]);
            }
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
    }
}
