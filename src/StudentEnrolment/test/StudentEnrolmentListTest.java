import StudentEnrolment.main.*;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class StudentEnrolmentListTest {


    StudentList studentList = new StudentList();
    CourseList courseList = new CourseList();
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");



    @org.junit.Test
    public void add() throws ParseException, IOException {

        StudentEnrolmentList sem = new StudentEnrolmentList("src/StudentEnrolment/main/default.csv",
                studentList, courseList);

        Student s = new Student("S3759777", "John Doe",
                df.parse("02/21/2000"));
        Course c = new Course("COSC2440", "Software Architecture", 12);

        assertTrue(sem.add(s, c, "2021A"));
    }

    @org.junit.Test
    public void update() throws IOException, ParseException {
        StudentEnrolmentList sem = new StudentEnrolmentList("src/StudentEnrolment/main/default.csv",
                studentList, courseList);

        Student s = new Student("S3759777", "John Doe",
                df.parse("02/21/2000"));
        Course c = new Course("COSC2440", "Software Architecture", 12);

        assertTrue(sem.update(sem.getOne(1), s, c, "2021A"));
    }

    @org.junit.Test
    public void delete() {
    }

    @org.junit.Test
    public void getOne() {
    }

    @org.junit.Test
    public void testGetOne() {
    }

    @org.junit.Test
    public void getAll() {
    }
}