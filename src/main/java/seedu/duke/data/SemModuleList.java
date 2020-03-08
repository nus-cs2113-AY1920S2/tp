package seedu.duke.data;

public class SemModuleList extends ModuleList {
    private String semName;

    public SemModuleList(String semName){
        this.semName = semName;
    }

    public String getSem(){
        return semName;
    }
}
