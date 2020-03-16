package seedu.happypills.storage;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * A class that manages read and write operations to file
 */
public class Storage {
    /**
     * Save individual patient data as strings to file. Creates file if it does not exist.
     *
     * @param filePath location of file to save to, requires directory/file
     * @param patientDataString  single patient data as string to be saved to file
     * @throws IOException if unable to save to file, possibly due to interruptions
     */
    public static void addPatientToFile(String filePath, String patientDataString) throws IOException {
        File d = new File(filePath.substring(0, filePath.lastIndexOf('/')));
        if (!d.exists()) {
            d.mkdir();
        }

        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(patientDataString);
        fw.close();
    }

    /**
     * Read in file as strings
     *
     * @param filePath location of file to read from
     * @return <code>ArrayList</code> of each line read from the file
     * @throws FileNotFoundException if the file specified by <code>filePath</code> does not exist
     */
    public static PatientList loadFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        PatientList storedPatients = new PatientList();

        while (s.hasNext()) {
            String stringInput = s.nextLine();
            parseFileContent(stringInput, storedPatients);
        }

        return storedPatients;
    }

    private static void parseFileContent(String savedString, PatientList storedPatients) {
        String[] dataString = savedString.split("[|]", 7);
        Patient tempPatient = new Patient(dataString[0], dataString[1], Integer.parseInt(dataString[2]), dataString[3], dataString[4], dataString[5], dataString[6]);
        storedPatients.add(tempPatient);
    }
}
