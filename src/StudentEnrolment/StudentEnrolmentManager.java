package StudentEnrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {

    public boolean add(Student student, Course course, String semester);
    public boolean update (StudentEnrolment enrolment, Student student, Course course, String semester);
    public boolean delete (int id);
    public StudentEnrolment getOne (int id);
    public StudentEnrolment getOne (String studentID, String courseID);
    public void getAll();

}
