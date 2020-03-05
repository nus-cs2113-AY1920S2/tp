package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ListCommand extends Command {

    private final String listParam;
    private final String LIST_TODAY_COMMAND = "today";

    public ListCommand(String listParam) {
        this.listParam = listParam;
    }


    @Override
    public void execute() {
        if (listParam.equals(LIST_TODAY_COMMAND)) {

        }


    }


    public static void main(String[] args) {
        LocalDate myObj = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDate = myObj.format(myFormatObj);
        System.out.println(formatDate);
    }
}
