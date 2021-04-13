package StudentEnrolment.main;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class GetAll {

    StudentList studentList = new StudentList();
    CourseList courseList = new CourseList();
    StudentEnrolmentList sem = new StudentEnrolmentList(studentList, courseList);
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    Student s1 = new Student("S3754467", "Vo Phuong Thao Nhi",
            df.parse("05/29/2000"));
    Student s2 = new Student("S3697890", "Jane Doe", df.parse("28/6/1998"));
    Student s3 = new Student("S3759777", "Anh Nguyen", df.parse("12/21/2000"));
    Course c1 = new Course("COSC2101", "Introduction to IT", 12);
    Course c2 = new Course("COSC2102", "Introduction to Programming", 12);

    public GetAll() throws ParseException {
    }

    @Test
    public void emptyOnCreation() {
        assertEquals(0, sem.getEnrolmentList().size());
    }

    @Test
    public void getAll() {
        sem.add(s1, c1, "2021A");
        sem.add(s2, c1, "2020C");
        sem.add(s3, c2, "2020B");
        assertNotNull(sem.getEnrolmentList());
    }

    @Test
    public void getSize() {
        sem.add(s1, c1, "2021A");
        sem.add(s2, c1, "2020C");
        sem.add(s3, c2, "2020B");
        assertEquals(3, sem.getEnrolmentList().size());
    }

}