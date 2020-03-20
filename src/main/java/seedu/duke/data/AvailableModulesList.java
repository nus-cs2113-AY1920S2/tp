package seedu.duke.data;

import seedu.duke.module.Module;
import seedu.duke.module.NewModule;

public class AvailableModulesList extends ModuleList {
    public static ModuleList availableModulesList;

    public AvailableModulesList() {
        super();
        availableModulesList = new ModuleList();
        availableModulesList.add(new NewModule("CS1010", "Programming Methodology"));
        availableModulesList.add(new NewModule("CS2030", "Programming Methodology II", "CS1010"));
        availableModulesList.add(new NewModule("CS2040", "Data Structures and Algorithms", "CS1010"));
        availableModulesList.add(new NewModule("CS2100", "Computer Organisation", "CS1010"));
        availableModulesList.add(new NewModule("CS2106", "Introduction to Operating Systems", "CS2100"));
        availableModulesList.add(new NewModule("CS2113", "Software Engineering & Object-Oriented Programming",
                "CS2040"));
        availableModulesList.add(new NewModule("CS2105", "Introduction to Computer Networks", "CS2040"));
        //availableModulesList.add(new NewModule("ES1000", "Foundation Academic English"));
        //availableModulesList.add(new NewModule("ES1103", "English for Academic Purposes", "ES1000"));
        availableModulesList.add(new NewModule("CS2101", "Effective Communication for Computing Professionals"));
        availableModulesList.add(new NewModule("CS2102", "Database Systems", "CS2030", "CS1231"));
        availableModulesList.add(new NewModule("GEH1001", "Globalisation and New Media"));
        availableModulesList.add(new NewModule("GEH1002", "Economic Issues in Dev World"));
        availableModulesList.add(new NewModule("GEH1004", "Chinese Heritage: Hist & Lit"));
        availableModulesList.add(new NewModule("GEQ1000", "Asking Questions"));
        availableModulesList.add(new NewModule("GER1000", "Quantitative Reasoning"));
        availableModulesList.add(new NewModule("GET1001", "Seeing the World Through Maps"));
        availableModulesList.add(new NewModule("GET1002", "Bridging East and West: Exploring Chinese Communication"));
        availableModulesList.add(new NewModule("GET1003", "Home"));
        availableModulesList.add(new NewModule("GES1000", "Singapore Employment Law"));
        availableModulesList.add(new NewModule("GES1002", "Global EC Dimensions of S'pore"));
        availableModulesList.add((new NewModule("GES1003", "Changing Landscapes of Singapore")));



        for (Module module: availableModulesList) {
            super.add(module);
        }
    }

    @Override
    public boolean add(Module module) {
        availableModulesList.add(module);
        return super.add(module);
    }
}
