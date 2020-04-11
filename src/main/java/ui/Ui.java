package ui;


import exception.command.IllegalCharactersEnteredException;
import studyarea.StudyArea;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.lineSeparator;
import static ui.Constants.DAB;
import static ui.Constants.FLAGS;
import static ui.Constants.GOODBYE_MESSAGE;
import static ui.Constants.HELP_DESCRIPTION_1;
import static ui.Constants.HELP_DESCRIPTION_10;
import static ui.Constants.HELP_DESCRIPTION_11;
import static ui.Constants.HELP_DESCRIPTION_12;
import static ui.Constants.HELP_DESCRIPTION_13;
import static ui.Constants.HELP_DESCRIPTION_14;
import static ui.Constants.HELP_DESCRIPTION_15;
import static ui.Constants.HELP_DESCRIPTION_16;
import static ui.Constants.HELP_DESCRIPTION_17;
import static ui.Constants.HELP_DESCRIPTION_18;
import static ui.Constants.HELP_DESCRIPTION_19;
import static ui.Constants.HELP_DESCRIPTION_2;
import static ui.Constants.HELP_DESCRIPTION_20;
import static ui.Constants.HELP_DESCRIPTION_21;
import static ui.Constants.HELP_DESCRIPTION_22;
import static ui.Constants.HELP_DESCRIPTION_23;
import static ui.Constants.HELP_DESCRIPTION_24;
import static ui.Constants.HELP_DESCRIPTION_3;
import static ui.Constants.HELP_DESCRIPTION_4;
import static ui.Constants.HELP_DESCRIPTION_5;
import static ui.Constants.HELP_DESCRIPTION_7;
import static ui.Constants.HELP_DESCRIPTION_8;
import static ui.Constants.HELP_DESCRIPTION_9;
import static ui.Constants.LINE;
import static ui.Constants.LOGO;
import static ui.Constants.MAX_LINE_LENGTH;
import static ui.Constants.SPACE;
import static ui.Constants.START_MESSAGE;
import static ui.Constants.SUCCESSFUL_CLOSING_OF_UI_LOGGER;
import static ui.Constants.TAB;
import static ui.Constants.UI_END_LOGGER;
import static ui.Constants.UI_START_LOGGER;

//@@author NizarMohd
/**
 * Contains functions used to interact with the user.
 *
 */
public class Ui {
    private Scanner in;
    private final PrintStream out;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * This is the constructor used to create the Ui class in Duke.run().
     */

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * This constructor assigns IO stream to the attributes of Ui.
     *
     * @param in This is the Input Stream for Ui.
     * @param out This is the Output Stream for Ui.
     */

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }


    /**
     * This method allows for other class to get User input.
     *
     * @return String input from User.
     */
    public String getUserIn() {
        boolean isExceptionEncountered;
        String input = "";
        do {
            isExceptionEncountered = false;
            try {
                input = getNextLine();
            } catch (Exception e) {
                isExceptionEncountered = true;
                printLine();
                printMessage(e.getMessage());
                printLine();
            }
        } while (isExceptionEncountered);
        return input;
    }

    /**
     * Returns the line typed in by the user as input and throws an exceptions when user enters an illegal character.
     *
     * @return String representing the line entered by the user.
     * @throws Exception If the usr enters an illegal character.
     */
    private String getNextLine() throws Exception {
        if (!in.hasNext()) {
            in = new Scanner(System.in);
            throw new IllegalCharactersEnteredException();
        }
        return in.nextLine();
    }

    /**
     * This method closes the Input Stream after usage is completed.
     */
    public void close() {
        this.in.close();
        LOGGER.log(Level.INFO, SUCCESSFUL_CLOSING_OF_UI_LOGGER);
    }

    /**
     * Prints a line made up of '_'.
     */
    public void printLine() {
        this.out.println(LINE);
    }


    /**
     * This method ensures that the message printed is within the standard length.
     *
     * @param message is the String that we intend to format to a standard length per line.
     */
    public void printMessage(String message) {
        if (message.equals(GOODBYE_MESSAGE + DAB)) {
            this.out.println(TAB + GOODBYE_MESSAGE);
            this.out.println(DAB);
        } else {
            this.out.println(formatMessage(message, MAX_LINE_LENGTH));
        }
    }

    /**
     * This method allows for StudyAreaCommand to print StudyArea based on its actual format,
     * so that formatMessage method does not interfere with the initial format.
     *
     * @param studyArea This is the StudyArea to be printed.
     */

    public void printStudyArea(StudyArea studyArea) {
        this.out.println(studyArea.toString());
    }

    //@@author hongquan448
    /**
     * Display welcome message.
     */
    public void printWelcomeMessage() {
        this.out.println("Hello from" + System.lineSeparator() + LOGO);
        printLine();
        this.out.println(START_MESSAGE);
        printHelp(false);
        printLine();
        LOGGER.log(Level.INFO, UI_START_LOGGER);
    }


    //@@author NizarMohd
    /**
     * This method prints the list of supported flags for the User.
     */
    public void printStudyAreaHelp() {
        this.out.println(FLAGS);
    }

    /**
     * Prints an empty line.
     */
    public void printEmptyLine() {
        System.out.println();
    }

    /**
     * Prints the closing message.
     */
    public void printByeMessage() {
        printMessage(GOODBYE_MESSAGE + DAB);
        LOGGER.log(Level.INFO, UI_END_LOGGER);
    }

    //@@author hongquan448
    /**
     * Display the list of supported commands.
     * @param withLine This boolean value states if the help message needs to print with or without lines.
     */
    public void printHelp(boolean withLine) {
        if (withLine) {
            printLine();
        }
        this.out.println(HELP_DESCRIPTION_1);
        this.out.println(HELP_DESCRIPTION_2);
        this.out.println(HELP_DESCRIPTION_3);
        this.out.println(HELP_DESCRIPTION_4);
        this.out.println(HELP_DESCRIPTION_5);
        this.out.println(HELP_DESCRIPTION_7);
        this.out.println(HELP_DESCRIPTION_8);
        this.out.println(HELP_DESCRIPTION_9);
        this.out.println(HELP_DESCRIPTION_10);
        this.out.println(HELP_DESCRIPTION_11);
        this.out.println(HELP_DESCRIPTION_12);
        this.out.println(HELP_DESCRIPTION_13);
        this.out.println(HELP_DESCRIPTION_14);
        this.out.println(HELP_DESCRIPTION_15);
        this.out.println(HELP_DESCRIPTION_16);
        this.out.println(HELP_DESCRIPTION_17);
        this.out.println(HELP_DESCRIPTION_18);
        this.out.println(HELP_DESCRIPTION_19);
        this.out.println(HELP_DESCRIPTION_20);
        this.out.println(HELP_DESCRIPTION_24);
        this.out.println(HELP_DESCRIPTION_21);
        this.out.println(HELP_DESCRIPTION_22);
        this.out.println(HELP_DESCRIPTION_23);
        if (withLine) {
            printLine();
        }
    }
    //@@author


    /**
     * This is a modification of a code from Stack Overflow to format strings into a standard length. Minor edition is
     * made to ensure suitability with the program.
     * This method ensures that the message printed is within the standard<br>
     * length.
     *
     * @param message is the String that we intend to format to a standard length<br>
     *                per line.<br>
     * @param maxLength This is the standard length intended to be formatted.
     * @return String of standard length per line
     */
    //@@author NizarMohd-reused
    //Reused from https://stackoverflow.com/questions/7528045/large-string-split-into-lines-with-maximum-length-in-java
    // with minor modification.
    public static String formatMessage(String message, int maxLength) {
        StringTokenizer token = new StringTokenizer(message, SPACE);
        StringBuilder standardLengthMessage = new StringBuilder(message.length());
        int lineLength = 0;
        while (token.hasMoreTokens()) {
            String word = token.nextToken();
            if (lineLength + word.length() > maxLength) {
                String temp = standardLengthMessage.toString().trim();
                standardLengthMessage = new StringBuilder(temp);
                standardLengthMessage.append(lineSeparator()).append("\t ");
                lineLength = 0;
            }
            standardLengthMessage.append(word).append(SPACE);
            lineLength += word.length() + 1;
        }
        return TAB + standardLengthMessage.toString().stripTrailing();
    }

    /**
     * This allows for other classes to print without formatMessage's format.
     *
     * @param message This is the message to be printed.
     * @param withLine This controls if nextLine have to be printed.
     */
    public void printOut(String message, boolean withLine) {
        if (withLine) {
            this.out.println(message);
        } else {
            this.out.print(message);
        }
    }
}
