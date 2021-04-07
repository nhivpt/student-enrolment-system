package StudentEnrolment;

import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    static private int enrolmentIDCounter = 0;
    private ArrayList<StudentEnrolment> enrolmentList;
    private StudentList studentList;
    private CourseList courseList;

    public StudentEnrolmentList(CourseList courseList, StudentList studentList) {
        this.enrolmentList = new ArrayList<>();
        this.courseList = courseList;
        this.studentList = studentList;
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
        enrolmentList.add(enrolment);
        return true;
    }

    @Override
    public boolean update(String studentID, String courseID, String semester) {
        StudentEnrolment enrolment = getOne(studentID, courseID);
        if (isDuplicate(studentID, courseID)) {
            return false;
        }
        enrolment.setStudent(findStudentByID(studentID));
        enrolment.setCourse(findCourseByID(courseID));
        enrolment.setSemester(semester);
        return true;
    }

    @Override
    public void delete(int id) {
        for (StudentEnrolment enrolment : enrolmentList) {
            if (enrolment.getId() == id) {
                enrolmentList.remove(id);
            }
        }
    }

    @Override
    public StudentEnrolment getOne(String studentID, String courseID) {
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

    public void getStudentList() {
        this.studentList.getStudentList();
    }

    public void getCourseList() {
        this.courseList.getCourseList();
    }
}
