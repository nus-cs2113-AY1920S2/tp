package exceptions;

public class CommandFormatException {

    public void getMessage() {
        System.out.println("Command is of incorrect format");
        System.out.println("Try the 'help' command for the list of commands");
    }
}
