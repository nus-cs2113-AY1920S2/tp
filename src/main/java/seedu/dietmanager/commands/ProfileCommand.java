package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.ui.UI;

public class ProfileCommand extends Command {


    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public ProfileCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (profile.isProfileExist()) {
            System.out.println(String.format("Hello %s, here is your profile information:", profile.getName()));
            System.out.println(String.format("Age:          %d years old", profile.getAge()));
            System.out.println(String.format("Gender:       %s", profile.getGender()));
            System.out.println(String.format("Height:       %.2f centimetres", profile.getHeight()));
            System.out.println(String.format("Weight        %.2f kilograms", profile.getWeight()));
            System.out.println(String.format("Weight Goal:  %.2f kilograms", profile.getWeightGoal()));
        } else {
            System.out.println("No existing profile found. To create a new profile, enter:\n"
                    + "set-profile {name} {age} {gender} {height} {weight} {weight goal}");
        }
    }
}
