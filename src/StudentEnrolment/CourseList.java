package StudentEnrolment;

import java.util.ArrayList;

public class CourseList {

    private ArrayList<Course> courseList;

    public CourseList() {
        this.courseList = new ArrayList<Course>();
    }

    public void addCourse(Course c) {
        this.courseList.add(c);
    }

    public Course findByID(String id) {
        for (Course course : courseList) {
            if (course.getCourseID().equals(id)) {
                return course;
            }
        }
        return null;
    }

    public int size() {
        return courseList.size();
    }

    public boolean contains(Course c) {
        return courseList.contains(c);
    }

    public void printCourseList() {
        for (Course course : courseList) {
            System.out.println(course.toString());
        }
    }

    public ArrayList<Course> getCourseList() {
        return this.courseList;
    }

}
