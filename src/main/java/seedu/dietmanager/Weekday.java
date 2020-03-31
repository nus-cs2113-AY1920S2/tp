package seedu.dietmanager;

public enum Weekday {
    MONDAY(1,"MONDAY"), TUESDAY(2,"TUESDAY"), WEDNESDAY(3,"WEDNESDAY"),
    THURSDAY(4,"THURSDAY"), FRIDAY(5,"FRIDAY"), SATURDAY(6,"SATURDAY"),
    SUNDAY(7,"SUNDAY");

    private int index;
    private String name;

    Weekday(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }
}
