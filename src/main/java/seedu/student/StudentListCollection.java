package seedu.student;

import java.util.ArrayList;

public class StudentListCollection extends ArrayList<StudentList> {

    public boolean isExistedListName(String name) {
        for (int i = 0; i < this.size(); ++i) {
            if (this.get(i).getListName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public StudentList getList(int index) {
        return this.get(index);
    }

    public String getListCollection() {
        String data = "";
        for (int i = 0; i < this.size(); i++) {
            data = data.concat(System.lineSeparator() + getList(i).listName + "|");
            for (String studentName: this.getList(i).getStudentList()) {
                data = data.concat(studentName + "|");
            }
        }
        return data;
    }

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
