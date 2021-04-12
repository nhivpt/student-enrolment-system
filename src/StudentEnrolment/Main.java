/*

COSC2440 - Assignment 1
STUDENT ENROLMENT SYSTEM
Student ID: s3754467
Student Name: Vo Phuong Thao Nhi

*/
package StudentEnrolment;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        StudentList studentList = new StudentList();
        CourseList courseList = new CourseList();

        StudentEnrolmentList sem = new StudentEnrolmentList("src/StudentEnrolment/default.csv", studentList, courseList);

        Student s1 = new Student("S3754467", "Vo Phuong Thao Nhi", df.parse("29/05/2000"));
        Student s2 = new Student("S3697890", "Jane Doe", df.parse("28/6/1998"));

        sem.addStudent(s1);
        sem.addStudent(s2);

        Course c1 = new Course("COSC2440", "Software Architecture", 12);
        Course c2 = new Course("ISYS2101", "Software Engineering Management Project", 12);

        sem.addCourse(c1);
        sem.addCourse(c2);

        sem.add(s1, c1, "2021A");
        sem.add(s2, c1, "2021A");
        sem.add(s2, c2, "2021B");

        Menu menu = new Menu(sem);

        menu.start();

    }
}


