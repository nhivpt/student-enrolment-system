package StudentEnrolment;

import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    static private int enrolmentIDCounter = 0;
    private ArrayList<StudentEnrolment> enrolmentList;
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;

    public StudentEnrolmentList() {
        this.enrolmentList = new ArrayList<StudentEnrolment>();
        this.courseList = new ArrayList<Course>();
        this.studentList = new ArrayList<Student>();
    }

    public static int getEnrolmentIDCounter() {
        return enrolmentIDCounter;
    }

    public static void setEnrolmentIDCounter(int enrolmentIDCounter) {
        StudentEnrolmentList.enrolmentIDCounter = enrolmentIDCounter;
    }

    public void addStudent(Student s) {
        studentList.add(s);
    }

    public void addCourse(Course c) {
        courseList.add(c);
    }

    protected Student findStudentByID(String id) {
        for (Student student : studentList) {
            if (student.getStudentID().equals(id)) {
                return student;
            }
        }
        return null;
    }

    protected Course findCourseByID(String id) {
        for (Course course : courseList) {
            if (course.getCourseID().equals(id)) {
                return course;
            }
        }
        return null;
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
            System.out.println(enrolment.toString() + "\n");
        }
    }

    public void getEnrolmentList() {
        for (StudentEnrolment enrolment : enrolmentList) {
            System.out.println(enrolment.toString());
        }
    }

    public void setEnrolmentList(ArrayList<StudentEnrolment> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }

    public void getStudentList() {
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public void getCourseList() {
        for (Course course : courseList) {
            System.out.println(course.toString());
        }
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }
}
