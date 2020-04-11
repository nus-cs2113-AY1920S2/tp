package report;

import exceptions.ReservationException;
import reservation.Reservation;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Deals with loading reservations from the file. */
public class LoadReservation {
    private String filePath;
    private List<Reservation> fileReservations;
    
    // apply singleton
    private static LoadReservation loadReservation;
    
    private LoadReservation(String filePath) {
        this.filePath = filePath;
        this.fileReservations = new ArrayList<>();
    }

    /**
     * Defines the method to access this object.
     * Instantiates a single copy of the singleton class when it is executed for the first time.
     * @param filePath Path to the "report.txt" file.
     * @return This object
     */
    public static LoadReservation getInstance(String filePath) {
        if (loadReservation == null) {
            loadReservation = new LoadReservation(filePath);
        }
        return loadReservation;
    }

    /**
     * Loads the reservation content of the txt files into the reservation list.
     * Creates the file if the file does not exist.
     * 
     * @return ArrayList of reservations read from the txt file.
     * @throws IOException If error occurs during creating and loading the txt file.
     * @throws ReservationException If the txt file is empty at the first place.
     */
    public List<Reservation> loadFileReservations() throws IOException, ReservationException {
        File f = new File(filePath); // create a File for the given file path
        if (!f.exists()) {
            f.createNewFile();
        }

        Scanner scanner = new Scanner(f); // create a Scanner using the File as the source

        if (!scanner.hasNext()) { // empty list
            throw new ReservationException();
        }

        boolean isReservation = false;
        List<String> reservationLines = new ArrayList<>();
        
        while (scanner.hasNext()) {
            String reservationLine = scanner.nextLine();

            // scan the file until reach the work "Reservations"
            if (reservationLine.equals("Reservations")) {
                isReservation = true;
                continue;
            }
            
            if (!isReservation) {
                continue;
            }
            
            // meet blank line: restart the reservationLines
            if (reservationLine.equals("")) {
                parseReservation(reservationLines);
                reservationLines.clear();
                continue;
            }
            
            reservationLines.add(reservationLine);
        }
        
        if (reservationLines.size() != 0) {
            parseReservation(reservationLines);
        }

        return fileReservations;
    }

    /**
     * Converts the reservation string from the txt file to Reservation object.
     * Adds the converted reservations into the ArrayList of Reservation.
     * 
     * @param fileLines Reservation string read from the txt file.
     */
    private void parseReservation(List<String> fileLines) {
        if (fileLines == null || fileLines.size() == 0 || fileLines.get(0).equals("")) {
            return;
        }
        
        int reservationNumber = 0;
        String status = null;
        String name = null;
        LocalDateTime date = null;
        int numberOfGuests = 0;
        String contact = null;
        String comments = null;
        
        for (String fileLine: fileLines) {
            String[] split = fileLine.split(":", 2);
            String startingWords = split[0].trim();
            
            switch (startingWords) {
            case "Status":
                status = split[1].trim();
                break;
            case "Contact person":
                name = split[1].trim();
                break;
            case "Date":
                date = LocalDateTime.parse(split[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                break;
            case "Number of guests":
                numberOfGuests = Integer.parseInt(split[1].trim());
                break;
            case "Contact details":
                contact = split[1].trim();
                break;
            case "Comments":
                comments = split[1].trim();
                break;
            default:
                if (startingWords.startsWith("Reservation")) {
                    reservationNumber = Integer.parseInt(startingWords.substring("Reservation".length() + 2, 
                            "Reservation".length() + 3).trim());
                }
                break;
            }
        }
        
        Reservation reservation = new Reservation(reservationNumber, name, date, numberOfGuests, contact);
        reservation.setStatus(status);
        reservation.setComments(comments);
        
        fileReservations.add(reservation);
    }
}
