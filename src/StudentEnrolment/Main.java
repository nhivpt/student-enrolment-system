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

//        sem.addCourse(new Course("COSC2020", "Introduction to Software Development", 12));
//        sem.addCourse(new Course("COSC2589", "Web Programming", 12));
//
//        sem.addStudent(new Student("s3754467", "Vo Phuong Thao Nhi", df.parse("29/05/2000")));
//        sem.addStudent(new Student("s3697890", "Jane Doe", df.parse("30/01/2001")));
//
//        Student s1 = new Student("s3759777", "Anh Nguyen", df.parse("21/12/2000"));
//        Student s2 = new Student("s3759056", "Alex T.", df.parse("28/6/1998"));
//
//        sem.addStudent(s1);
//        sem.addStudent(s2);
//
//        Course c1 = new Course("COSC2440", "Software Architecture Design", 12);
//        Course c2 = new Course("ISYS2101", "Software Engineering Management Project", 12);
//
//        sem.addCourse(c1);
//        sem.addCourse(c2);
//
//        sem.add(s1, c1, "2021A");
//        sem.add(s1, c2, "2021A");
//        sem.add(s2, c1, "2021A");
//        sem.add(s2, c2, "2021B");

        Menu menu = new Menu(sem);

        menu.start();

    }
}


