package StudentEnrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {

    public boolean add(String studentID, String courseID, String semester);
    public boolean update (String studentID, String courseID, String semester);
    public void delete (int id);
    public StudentEnrolment getOne (String studentID, String courseID);
    public void getAll();

}
