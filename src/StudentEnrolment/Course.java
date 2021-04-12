package StudentEnrolment;

public class Course {
    private String courseID;
    private String courseName;
    private int credits;
    private StudentList studentList;

    public Course(String courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
        this.studentList = new StudentList();
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                "'}";
    }

    public String toCSV() {
        return  courseID + ","+
                courseName + ","+
                credits +"\n";
    }

    public StudentList getStudentList() {
        return this.studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }

}
