package seedu.duke;

import java.util.Scanner;

public class Duke {

    /**
     * Begins the application and creates the relevant objects required for the application to function.
     */

    public static void main(String[] args) {

        FoodNutritionInfo foodNutritionInfo = new FoodNutritionInfo();
        Profile profile = new Profile();
        UI ui = new UI();

        Scanner sc = new Scanner(System.in);

        String logo = "  _____   _        _     __  __                                         \n"
                + " |  __ \\ (_)      | |   |  \\/  |                                        \n"
                + " | |  | | _   ___ | |_  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \n"
                + " | |  | || | / _ \\| __| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\n"
                + " | |__| || ||  __/| |_  | |  | || (_| || | | || (_| || (_| ||  __/| |   \n"
                + " |_____/ |_| \\___| \\__| |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \n"
                + "                                                       __/ |            \n"
                + "                                                      |___/             ";

        System.out.println(logo);
        System.out.println("Welcome to Diet Manager! How may I assist you today?");

        while (!ui.isExitStatus()) {
            String[] inputArray = ui.readInput().trim().split(" ", 2);
            switch (inputArray[0]) {
            case "profile":
                if (profile.isProfileExist()) {
                    System.out.println(String.format("Hello %s, here is your profile information:", profile.getName()));
                    System.out.println(String.format("Age:          %d years old", profile.getAge()));
                    System.out.println(String.format("Gender:       %s", profile.getGender()));
                    System.out.println(String.format("Height:       %f metres", profile.getHeight()));
                    System.out.println(String.format("Weight        %f kilograms", profile.getWeight()));
                    System.out.println(String.format("Weight Goal:  %f kilograms", profile.getWeightGoal()));
                } else {
                    System.out.println("No existing profile found. To create a new profile, enter:\n"
                            + "setProfile {name} {age} {gender} {height} {weight} {weight goal}");
                }
                break;
            case "setProfile":
                String[] profileArray = inputArray[1].split(" ", 6);
                profile.setProfile(profileArray[0], Integer.parseInt(profileArray[1]),
                        profileArray[2], Double.parseDouble(profileArray[3]),
                        Double.parseDouble(profileArray[4]), Double.parseDouble(profileArray[5]));
                break;
            case"setName":
                profile.setName(inputArray[1]);
                System.out.println(String.format("Your username has been changed to %s", profile.getName()));
                break;
            case"setAge":
                profile.setAge(Integer.parseInt(inputArray[1]));
                System.out.println(String.format("Your username has been changed to %s", profile.getName()));
                break;
            case"setGender":
                profile.setGender(inputArray[1]);
                System.out.println(String.format("Your username has been changed to %s", profile.getName()));
                break;
            case"setHeight":
                profile.setHeight(Double.parseDouble(inputArray[1]));
                System.out.println(String.format("Your username has been changed to %s", profile.getName()));
                break;
            case"setWeight":
                profile.setWeight(Double.parseDouble(inputArray[1]));
                System.out.println(String.format("Your username has been changed to %s", profile.getName()));
                break;
            case"setWeightGoal":
                profile.setWeightGoal(Double.parseDouble(inputArray[1]));
                System.out.println(String.format("Your username has been changed to %s", profile.getName()));
                break;
            case "exit":
                System.out.println("Thank you and see you again soon!");
                ui.setExitStatus(false);
                break;
            default:
                System.out.println("Sorry, I don't understand that command :(");
                break;
            }
        }
    }
}
