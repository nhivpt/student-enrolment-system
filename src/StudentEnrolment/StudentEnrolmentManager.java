package StudentEnrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {

    public void add (StudentEnrolment enrolment);
    public void update (StudentEnrolment enrolment, Course course, String semester);
    public void delete (String studentID, String courseID);
    public StudentEnrolment getOne (String studentID, String courseID);
    public ArrayList<StudentEnrolment> getAll();

}
