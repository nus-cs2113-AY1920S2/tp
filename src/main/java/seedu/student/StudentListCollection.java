package seedu.student;

import java.util.ArrayList;

public class StudentListCollection extends ArrayList<StudentList> {

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < this.size(); ++i) {
            data.append(this.get(i).toString() + System.lineSeparator());
        }
        return data.toString();
    }

    public void push(StudentList studentList) {
        boolean isDuplicate = false;
        int index = 1;
        while (!isDuplicate) {
            isDuplicate = true;
            for (int i = 0; i < this.size(); ++i) {
                if (this.get(i).getListName().toLowerCase().equals(studentList.listName.toLowerCase())) {
                    isDuplicate = false;
                    String wordIndex = " [" + index + "]";
                    studentList.listName = studentList.listName.concat(wordIndex);
                    index++;
                    break;
                }
            }
        }
        this.add(studentList);
    }
}
