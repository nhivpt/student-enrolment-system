package StudentEnrolment;

import java.util.Date;

public class Student {
    private String studentID;
    private String studentName;
    private Date birthdate;
    private CourseList courseList;

    public Student(String studentID, String studentName, Date birthdate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.birthdate = birthdate;
        this.courseList = new CourseList();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    public CourseList getCourseList() {
        return courseList;
    }

    public void setCourseList(CourseList courseList) {
        this.courseList = courseList;
    }
}
