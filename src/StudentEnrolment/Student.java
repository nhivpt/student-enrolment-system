package StudentEnrolment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String studentID;
    private String studentName;
    private Date birthdate;
    private CourseList courseList;
    private SimpleDateFormat df;

    public Student(String studentID, String studentName, Date birthdate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.birthdate = birthdate;
        this.courseList = new CourseList();
        this.df = new SimpleDateFormat("MM/dd/yyyy");

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
        String bdStr = df.format(birthdate);
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", birthdate=" + bdStr.split(" ")[0] +
                '}';
    }

    public CourseList getCourseList() {
        return courseList;
    }

    public void setCourseList(CourseList courseList) {
        this.courseList = courseList;
    }

    public String toCSV() {
        String bdStr = df.format(birthdate);
        return studentID + ","+
                studentName + ","+
                bdStr.split(" ")[0] +"\n";
    }
}
