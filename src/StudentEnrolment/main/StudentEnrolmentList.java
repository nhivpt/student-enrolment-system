package StudentEnrolment.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    static private int enrolmentIDCounter = 0;
    private ArrayList<StudentEnrolment> enrolmentList;
    private StudentList studentList;
    private CourseList courseList;

    public StudentEnrolmentList(String filename, StudentList studentList, CourseList courseList) throws IOException, ParseException {
        this.studentList = studentList;
        this.courseList = courseList;
        this.enrolmentList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");
            Student s = new Student(split[0], split[1], df.parse(split[2]));
            Course c = new Course(split[3], split[4], Integer.parseInt(split[5]));
            if (this.studentList.findByID(split[0]) == null) {
                this.studentList.addStudent(s);
            }
            if (this.courseList.findByID(split[3]) == null) {
                this.courseList.addCourse(c);
            }
            enrolmentIDCounter++;
            this.enrolmentList.add(new StudentEnrolment(enrolmentIDCounter, s, c, split[6]));
        }
    }

    public StudentEnrolmentList(StudentList studentList, CourseList courseList) {
        this.studentList = studentList;
        this.courseList = courseList;
        this.enrolmentList = new ArrayList<>();
    }

    public static int getEnrolmentIDCounter() {
        return enrolmentIDCounter;
    }

    public static void setEnrolmentIDCounter(int enrolmentIDCounter) {
        StudentEnrolmentList.enrolmentIDCounter = enrolmentIDCounter;
    }

    public void addStudent(Student s) {
        this.studentList.addStudent(s);
    }

    public void addCourse(Course c) {
        this.courseList.addCourse(c);
    }

    public Student findStudentByID(String id) {
        return this.studentList.findByID(id);
    }

    public Course findCourseByID(String id) {
        return this.courseList.findByID(id);
    }

    public boolean isDuplicate(String studentID, String courseID) {
        studentID = studentID.toUpperCase();
        courseID = courseID.toUpperCase();
        for (StudentEnrolment enrolment : enrolmentList) {
            if (enrolment.getStudent().getStudentID().equals(studentID)
                    && enrolment.getCourse().getCourseID().equals(courseID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Student student, Course course, String semester) {
        enrolmentIDCounter++;
        StudentEnrolment enrolment = new StudentEnrolment(enrolmentIDCounter, student, course, semester);
        if (isDuplicate(student.getStudentID(), course.getCourseID())) {
            enrolmentIDCounter--;
        } else {
            enrolmentList.add(enrolment);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(StudentEnrolment enrolment, Student student, Course course, String semester) {
        semester = semester.toUpperCase();

        if (isDuplicate(student.getStudentID(), course.getCourseID())) {
            return false;
        }

        enrolment.setStudent(student);
        enrolment.setCourse(course);
        enrolment.setSemester(semester);
        return true;
    }

    @Override
    public boolean delete(int id) {
        for (StudentEnrolment enrolment : enrolmentList) {
            if (enrolment.getId() == id) {
                enrolmentList.remove(enrolment);
                return true;
            }
        }
        return false;

    }

    @Override
    public StudentEnrolment getOne(int id) {
        for (StudentEnrolment enrolment : enrolmentList) {
            if (enrolment.getId() == id) {
                return enrolment;
            }
        }
        return null;
    }

    @Override
    public StudentEnrolment getOne(String studentID, String courseID) {
        studentID = studentID.toUpperCase();
        courseID = courseID.toUpperCase();
        for (StudentEnrolment enrolment : enrolmentList) {
            if (enrolment.getStudent().getStudentID().equals(studentID)
                && enrolment.getCourse().getCourseID().equals(courseID)) {
                return enrolment;
            }
        }
        return null;
    }

    @Override
    public void getAll() {
        System.out.println("---Enrolment List---");
        for (StudentEnrolment enrolment : enrolmentList) {
            System.out.println(enrolment.toString());
        }
    }

    public ArrayList<StudentEnrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public void setEnrolmentList(ArrayList<StudentEnrolment> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public CourseList getCourseList() {
        return courseList;
    }

    public void printStudentList() {
        this.studentList.printStudentList();
    }

    public void printCourseList() {
        this.courseList.printCourseList();
    }
}
