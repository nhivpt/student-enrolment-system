package StudentEnrolment;

import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    private ArrayList<StudentEnrolment> enrolmentsList;
    private ArrayList<Student> studentsList = new ArrayList<Student>();
    private ArrayList<Course> coursesList = new ArrayList<Course>();
    static private int enrolmentIDCounter = 0;

    public StudentEnrolmentList() {
        this.enrolmentsList = new ArrayList<StudentEnrolment>();
    }

    public void addStudent(Student s) {
        studentsList.add(s);
    }

    public void addCourse(Course c) {
        coursesList.add(c);
    }

    public Student findStudentByID(String id) {
        for (Student student : studentsList) {
            if (student.getStudentID().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseByID(String id) {
        for (Course course : coursesList) {
            if (course.getCourseID().equals(id)) {
                return course;
            }
        }
        return null;
    }

    public boolean isDuplicate(String studentID, String courseID) {
        for (StudentEnrolment enrolment : enrolmentsList) {
            if (enrolment.getStudent().getStudentID().equals(studentID)
                && enrolment.getCourse().getCourseID().equals(courseID)) {
                System.out.println("This student has already enrolled in this course.");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(String studentID, String courseID, String semester) {
        enrolmentIDCounter++;
        if (isDuplicate(studentID, courseID)) {
            System.out.println("This student has already enrolled in the course.");
            return false;
        }
        Student student = findStudentByID(studentID);
        if (student == null) {
            System.out.println("There is no student with that ID. Please try again.");
            return false;
        }
        Course course = findCourseByID(courseID);
        if (course == null) {
            System.out.println("There is no course with that ID. Please try again.");
            return false;
        }
        StudentEnrolment enrolment = new StudentEnrolment(enrolmentIDCounter, student, course, semester);
        enrolmentsList.add(enrolment);
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
        for (StudentEnrolment enrolment : enrolmentsList) {
            if (enrolment.getId() == id) {
                enrolmentsList.remove(id);
            }
        }
    }

    @Override
    public StudentEnrolment getOne(String studentID, String courseID) {
        for (StudentEnrolment enrolment : enrolmentsList) {
            if (enrolment.getStudent().getStudentID().equals(studentID)
            && enrolment.getCourse().getCourseID().equals(courseID)) {
                return enrolment;
            }
        }
        return null;
    }

    @Override
    public void getAll() {
        for (StudentEnrolment enrolment : enrolmentsList) {
            System.out.println(enrolment.toString() + "\n");
        }
    }

}
