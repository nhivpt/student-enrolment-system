package StudentEnrolment;

import java.util.ArrayList;
import java.util.Date;

public class Student {
    private String studentID;
    private String studentName;
    private Date birthdate;

    public Student(String studentID, String studentName, Date birthdate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.birthdate = birthdate;
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
}
