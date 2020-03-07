package seedu.duke;

public class Student {

    protected static String StudentName;

    public Student(String StudentName){
        this.StudentName = StudentName;
    }

    public static String getStudentName(){
        return StudentName;
    }
}
