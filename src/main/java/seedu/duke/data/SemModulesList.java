package seedu.duke.data;

public class SemModulesList extends ModuleList {
    private String semName;

    public SemModulesList(String semName) {
        this.semName = semName;
    }

    public String getSem() {
        return semName;
    }
}
