package seedu.duke.data;

import seedu.duke.module.NewModule;

public class AvailableModulesList extends ModuleList {
    public static ModuleList availableModulesList = new ModuleList();

    public AvailableModulesList() {
        super();
        availableModulesList = new ModuleList();
        availableModulesList.add(new NewModule("CS1010", "Programming Methodology"));
        availableModulesList.add(new NewModule("CS1231", "Discrete Structures"));
        availableModulesList.add(new NewModule("CS2030", "Programming Methodology II", "CS1010"));
        availableModulesList.add(new NewModule("CS2040", "Data Structures and Algorithms", "CS1010"));
        availableModulesList.add(new NewModule("CS2100", "Computer Organisation", "CS1010"));
        availableModulesList.add(new NewModule("CS2106", "Introduction to Operating Systems", "CS2100"));
        availableModulesList.add(new NewModule("CS2113", "Software Engineering & Object-Oriented Programming", "CS2040"));
        availableModulesList.add(new NewModule("CS2105", "Introduction to Computer Networks", "CS2040"));
        availableModulesList.add(new NewModule("ES1000", "Foundation Academic English"));
        availableModulesList.add(new NewModule("ES1103", "English for Academic Purposes", "ES1000"));
        availableModulesList.add(new NewModule("CS2101", "Effective Communication for Computing Professionals"));
        availableModulesList.add(new NewModule("CS2102", "Database Systems", "CS2030", "CS1231"));
        /*availableModulesList.add(new NewModule());
        availableModulesList.add(new NewModule());
        availableModulesList.add(new NewModule());
        availableModulesList.add(new NewModule());
        availableModulesList.add(new NewModule());
         */
        System.out.println(availableModulesList);
    }


}
