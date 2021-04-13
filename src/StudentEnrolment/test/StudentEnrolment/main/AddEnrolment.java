package StudentEnrolment.main;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class AddEnrolment {

    StudentList studentList = new StudentList();
    CourseList courseList = new CourseList();
    StudentEnrolmentList sem = new StudentEnrolmentList(studentList, courseList);
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    Student s1 = new Student("S3754467", "Vo Phuong Thao Nhi",
            df.parse("05/29/2000"));
    Course c1 = new Course("COSC2101", "Introduction to IT", 12);

    public AddEnrolment() throws ParseException {
    }

    @Before
    public void setUp() {
        sem.addCourse(c1);
        sem.addStudent(s1);
        sem.add(s1, c1, "2020C");
    }

    @Test
    public void addDuplicate() {
        assertFalse(sem.add(s1, c1, "2021A"));
    }

}