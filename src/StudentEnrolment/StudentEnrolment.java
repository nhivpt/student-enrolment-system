package StudentEnrolment;

public class StudentEnrolment {
    private int id;
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Integer id, Student student, Course course, String semester) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "StudentEnrolment {" +
                "ID: " + id +
                ", Student ID: " + student.getStudentID() +
                ", Course: " + course +
                ", Semester: " + semester + '\'' +
                '}';
    }
}
