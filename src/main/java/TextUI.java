import java.util.Scanner;

public class TextUI {
    public static void introMsg() {
        String logo = "  ___        _____                 __  .__                 ________                            .__                       ___     \n" +
                " / _ \\_/\\   /     \\   ____   _____/  |_|__| ____    ____   \\_____  \\_______  _________    ____ |__|_______ ___________  / _ \\_/\\ \n" +
                " \\/ \\___/  /  \\ /  \\_/ __ \\_/ __ \\   __\\  |/    \\  / ___\\   /   |   \\_  __ \\/ ___\\__  \\  /    \\|  \\___   // __ \\_  __ \\ \\/ \\___/ \n" +
                "          /    Y    \\  ___/\\  ___/|  | |  |   |  \\/ /_/  > /    |    \\  | \\/ /_/  > __ \\|   |  \\  |/    /\\  ___/|  | \\/          \n" +
                "          \\____|__  /\\___  >\\___  >__| |__|___|  /\\___  /  \\_______  /__|  \\___  (____  /___|  /__/_____ \\\\___  >__|             \n" +
                "                  \\/     \\/     \\/             \\//_____/           \\/     /_____/     \\/     \\/         \\/    \\/                 ";
        System.out.println("Welcome to....\n" + logo);
        System.out.println("1) Schedule a new meeting.");
        System.out.println("2) Delete a scheduled meeting.");
        System.out.println("3) Edit a scheduled meeting.");
        System.out.println("4) List all scheduled meetings.");
        System.out.println("5) Exit application.");
        System.out.println("________________________________________________________________________________________________________________________________");
    }
    public static void exitMsg() {
        System.out.println("Thank you for using MeetingOrganizer, goodbye!");
    }
    public static void errorMsg(MoException e) {
        System.out.println("OOPS!! " + e);
    }
}
