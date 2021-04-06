package StudentEnrolment;

import java.util.ArrayList;

public class StudentList {

    private ArrayList<Student> studentList;

    public StudentList() {
        this.studentList = new ArrayList<Student>();
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

    public void getStudentList() {
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

}