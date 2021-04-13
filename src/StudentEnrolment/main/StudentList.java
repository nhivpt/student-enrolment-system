package StudentEnrolment.main;

import java.util.ArrayList;

public class StudentList {

    private ArrayList<Student> studentList;

    public StudentList() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent(Student s) {
        studentList.add(s);
    }

    public Student findByID(String id) {
        for (Student student : studentList) {
            if (student.getStudentID().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public boolean contains(Student s) {
        return studentList.contains(s);
    }

    public void printStudentList() {
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

}
