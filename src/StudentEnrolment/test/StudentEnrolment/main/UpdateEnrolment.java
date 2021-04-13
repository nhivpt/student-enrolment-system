package StudentEnrolment.main;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class UpdateEnrolment {

    StudentList studentList = new StudentList();
    CourseList courseList = new CourseList();
    StudentEnrolmentList sem = new StudentEnrolmentList(studentList, courseList);
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    Student s1 = new Student("S3754467", "Vo Phuong Thao Nhi",
            df.parse("05/29/2000"));
    Student s2 = new Student("S3697890", "Jane Doe", df.parse("28/6/1998"));
    Student s3 = new Student("S3759777", "Anh Nguyen", df.parse("12/21/2000"));
    Course c1 = new Course("COSC2101", "Introduction to IT", 12);


    public UpdateEnrolment() throws ParseException {
    }

    @Before
    public void setUp() {
        sem.addCourse(c1);
        sem.addStudent(s1);
        sem.add(s1, c1, "2020C");
        sem.add(s2, c1, "2020C");
    }

    @Test
    public void update() {
        StudentEnrolment s = sem.getOne(3);
        assertTrue(sem.update(s, s3, c1, "2021B"));
    }

    @Test
    public void updateNonExistingID() {
        StudentEnrolment s = sem.getOne(10);
        assertFalse(sem.update(s, s1, c1, "2021B"));
    }

    @Test
    public void updateDuplicate() {
        StudentEnrolment s = sem.getOne(2);
        assertFalse(sem.update(s, s1, c1, "2021B"));
    }
}