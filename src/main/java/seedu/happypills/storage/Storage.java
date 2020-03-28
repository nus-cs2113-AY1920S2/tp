package seedu.happypills.storage;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * A class that manages read and write operations to file.
 */
public class Storage {
    public static final String PATIENT_FILEPATH = "data/patient.txt";
    public static final String APPOINTMENT_FILEPATH = "data/appointment.txt";
    public static final String SYMPTOM_FILEPATH = "data/symptoms.txt";

    /**
     * Overwrite file with a formatted string of the entire list provided. Creates file if it does not exist.
     * Used for edit and delete commands
     *
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
}
