package seedu.happypills.ui;

public class HelpTextUi extends TextUi {

    /**
     * Construct a string for incomplete commands, probably missing something.
     * @param helpString provide the help command that user can use to find the right format
     * @return a string for incomplete commands.
     */
    public static String incompleteCommandString(String helpString) {
        String msg = "    Command is incomplete. Please use the " + helpString + " command to find out more.";
        return msg;
    }

    public static final String GENERAL_HELP_MESSAGE = ""
            + "    HappyPills Commands\n"
            + "    ---------------------------------------------------\n"
            + "    General Commands:\n"
            + "      help | Displays all of HappyPill's Commands\n"
            + "      exit | Exits the program\n"
            + "    ---------------------------------------------------\n"
            + "    Patient Details Commands:\n"
            + "      add patient | Adds a patient into the program\n"
            + "      list patient | Lists all the patients in the program\n"
            + "      get patient | Retrieves the details of a given patient\n"
            + "      edit patient | Edits a patient's detail\n"
            + "      delete patient | Deletes a patient from the program\n"
            + "    ---------------------------------------------------\n"
            + "    Patient Medial Records Commands:\n"
            + "      add pr | Adds a prior patient records into the program\n"
            + "      edit pr | Edits a prior patient record\n"
            + "      delete pr | Deletes a prior patient record\n"
            + "      list pr | List down all records for a particular patient\n"
            + "      find pr | Retrieves a particular record\n"
            + "    ---------------------------------------------------\n"
            + "    Appointment Scheduling Commands:\n"
            + "      add appt | Adds an appointment into the program\n"
            + "      list appt | Lists all the appointments into the program\n"
            + "      edit appt | Edits an appointment in the program\n"
            + "      delete appt | Deletes an appointment from the program\n"
            + "      done appt | Marks an appointment as done\n"
            + "      find appt | Finds all the appointments under a patient\n"
            + "    ---------------------------------------------------\n"
            + "    For more specific command instructions, enter `help [COMMAND]`\n"
            + "      Example: help add appt\n";

    public static final String EXIT_HELP_MESSAGE = ""
            + "    To exit the program, run the following command:\n"
            + "      exit\n"
            + "    The above command will save the current patient records and terminate the program.\n";

    //Patient help Strings
    public static final String GENERAL_PATIENT_HELP_MESSAGE = ""
            + "    Patient Details Commands:\n"
            + "      add patient | Adds a patient into the program\n"
            + "      list patient | Lists all the patients in the program\n"
            + "      get patient | Retrieves the details of a given patient\n"
            + "      edit patient | Edits a patient's detail\n"
            + "      delete patient | Deletes a patient from the program\n"
            + "    For more specific command instructions, enter `help [COMMAND]`\n"
            + "      Example: help add patient\n";

    public static final String ADD_PATIENT_HELP_MESSAGE = ""
            + "    To add a new patient into the program, use the following command:\n"
            + "    Note: patient details are in UPPER CASE and optional details are in parenthesis [ ]\n"
            + "      add patient /ic NRIC /n NAME /p PHONE_NUMBER /dob DOB "
            + "/b BLOOD_TYPE /a[Allergies] /rm[Remarks]\n"
            + "    The command above adds a new patient.\n";

    public static final String LIST_PATIENT_HELP_MESSAGE = ""
            + "    To retrieve a list of all the patients within the program,\n"
            + "    run the following command:\n"
            + "      list patient\n";

    public static final String GET_PATIENT_HELP_MESSAGE = ""
            + "    To retrieve a patient's information, run the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "      get patient NRIC\n"
            + "    Example:\n"
            + "      get patient S9999999Z\n"
            + "    The command above will display information regarding the patient with NRIC S9999999Z.\n";

    public static final String EDIT_PATIENT_HELP_MESSAGE = ""
            + "    To edit a patient's information, run the following command:\n"
            + "    Note: patient details are in UPPER CASE and optional details are in parenthesis [ ]\n"
            + "      edit patient NRIC /n[NAME] to edit patient's name\n"
            + "      edit patient NRIC /dob[DOB] to edit patient's date of birth\n"
            + "      edit patient NRIC /b[BLOOD_TYPE] to edit patient's blood type\n"
            + "      edit patient NRIC /p[PHONE_NUMBER] to edit patient's phone number,\n"
            + "      edit patient NRIC /a[ALLERGIES] to edit patient's allergies,\n"
            + "      edit patient NRIC /r[REMARKS] to edit patient's remarks\n"
            + "    Do note that you can only edit one parameter at a time and "
            + " editing the patient's records will overwrite any previous information.\n"
            + "    Adding of allergies or remarks can be done with the add command.\n";

    public static final String DELETE_PATIENT_HELP_MESSAGE = ""
            + "    To delete a patient, run the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "      delete patient NRIC\n"
            + "    The user will be prompted to confirm if they would like to delete the patient's records.\n"
            + "    Do note that deletion cannot be undone.\n";

    //Patient Record Help Strings
    public static final String GENERAL_PATIENT_RECORD_HELP_MESSAGE = ""
            + "    Patient Medial Records Commands:\n"
            + "      add pr | Adds a prior patient records into the program\n"
            + "      edit pr | Edits a prior patient record\n"
            + "      delete pr | Deletes a prior patient record\n"
            + "      list pr | List down all records for a particular patient\n"
            + "      find pr | Retrieves a particular record\n"
            + "    For more specific command instructions, enter `help [COMMAND]`\n"
            + "      Example: help add pr\n";

    public static final String ADD_PATIENT_RECORD_HELP_MESSAGE = ""
            + "    To add a new patient record into the program, use the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "      add pr /ic NRIC /sym SYMPTOMS /diag DIAGNOSIS /d DATE /t TIME\n"
            + "    The command above adds a new patient medical record.\n";

    public static final String LIST_PATIENT_RECORD_HELP_MESSAGE = ""
            + "    To retrieve a list of all the patient records of a particular patient,\n"
            + "    run the following command:\n"
            + "      list pr NRIC\n";

    public static final String EDIT_PATIENT_RECORD_HELP_MESSAGE = ""
            + "    To edit a patient's records, run the following command:\n"
            + "    Note: patient details are in UPPER CASE and optional details are in parenthesis [ ]\n"
            + "    The INDEX can be found by running the list pr command.\n"
            + "      edit pr NRIC INDEX /sym [SYMPTOMS] /diag [DIAGNOSIS] /d [DATE] /t [TIME]\n"
            + "    You can also edit a single attribute: \n"
            + "      edit pr NRIC INDEX /sym [SYMPTOMS] to edit patient's symptoms,\n"
            + "    Do note that editing the patient's records will overwrite any previous information.\n";

    public static final String DELETE_PATIENT_RECORD_HELP_MESSAGE = ""
            + "    To delete a patient's records on a particular visit, run the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "    The INDEX can be found by running the list pr command.\n"
            + "      delete pr NRIC INDEX\n"
            + "    The user will be prompted to confirm if they would like to delete the patient's records.\n"
            + "    Do note that deletion cannot be undone.\n";

    public static final String FIND_PATIENT_RECORD_HELP_MESSAGE = ""
            + "    To get a better look at a patient's particular record, run the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "    The INDEX can be found by running the list pr command.\n"
            + "      find pr NRIC INDEX\n";

    //Appointment
    public static final String GENERAL_APPOINTMENT_HELP_MESSAGE = ""
            + "    Appointment Scheduling Commands:\n"
            + "      add appt | Adds an appointment into the program\n"
            + "      list appt | Lists all the appointments into the program\n"
            + "      edit appt | Edits an appointment in the program\n"
            + "      delete appt | Deletes an appointment from the program\n"
            + "      done appt | Marks an appointment as done\n"
            + "      find appt | Finds all the appointments under a patient\n"
            + "    For more specific command instructions, enter `help [COMMAND]`\n"
            + "      Example: help add appt\n";

    public static final String ADD_APPOINTMENT_HELP_MESSAGE = ""
            + "    To add a new appointment into the program, use the following command:\n"
            + "    Note: patient details are in UPPER CASE and optional details are in parenthesis [ ]\n"
            + "    A unique ID will be given to every appointment added.\n"
            + "      add appt /ic NRIC /d DATE /t TIME /r REASON\n"
            + "    The command above adds a new appointment.\n"
            + "    Please ensure that the date is formatted as DD-MM-YYYY and time as HH:mm:ss\n";

    public static final String LIST_APPOINTMENT_HELP_MESSAGE = ""
            + "    To retrieve a list of all the appointments within the program,\n"
            + "    run the following command:\n"
            + "      list appt\n";

    public static final String EDIT_APPOINTMENT_HELP_MESSAGE = ""
            + "    To edit a patient's appointment, run the following command:\n"
            + "    Note: patient details are in UPPER CASE and optional details are in parenthesis [ ]\n"
            + "      edit appt NRIC APPT_ID /d [DD/MM/YYYY] to edit appointment's date,\n"
            + "      edit appt NRIC APPT_ID /t [HH:MM] to edit appointment's time,\n"
            + "      edit appt NRIC APPT_ID /r [REASON] to edit appointment's reason\"\n"
            + "    Do note that editing the patient's records will overwrite any previous information.\n"
            + "    The apptID can be found using \"find appt NRIC\"\n";

    public static final String DELETE_APPOINTMENT_HELP_MESSAGE = ""
            + "    To delete an appointment, run the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "      delete appt NRIC APPT_ID\n"
            + "    The user will be prompted to confirm if they would like to delete the patient's records.\n"
            + "    Do note that deletion cannot be undone.\n";

    public static final String FIND_APPOINTMENT_HELP_MESSAGE = ""
            + "    To find all of a patient's appointments, run the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "      find appt NRIC\n";

    public static final String DONE_APPOINTMENT_HELP_MESSAGE = ""
            + "    To make an appointment as done, run the following command:\n"
            + "    Note: patient details are in UPPER CASE\n"
            + "      done appt NRIC APPT_ID\n";
}
