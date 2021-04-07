package StudentEnrolment;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private StudentEnrolmentList sem;

    public Menu(StudentEnrolmentList sem) {
        this.sc = new Scanner(System.in);
        this.sem = sem;
    }

    public int printMenu() {
        int option;
        System.out.println("STUDENT ENROLMENT SYSTEM");
        System.out.println("------------------------");
        System.out.println("1. Enrolment management");
        System.out.println("2. Course management");
        System.out.println("3. Student management");
        System.out.println("0. Exit the program");
        option = readInt();
        return option;
    }

    public void start() {
        while (true) {
            int option = printMenu();
            switch (option) {
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                case 1:
                    enrolmentMenu();
                    break;
                case 2:
                    courseMenu();
                    break;
                case 3:
                    studentMenu();
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private int readInt() {
        int option;

        System.out.print("Enter an option: ");
        option = Integer.parseInt(sc.next());

        return option;
    }

    public void enrolmentMenu() {
        int option;
        while (true) {
            System.out.println("ENROLMENT MANAGEMENT");
            System.out.println("------------------");
            System.out.println("1. View all enrolments");
            System.out.println("2. View one enrolment");
            System.out.println("3. Enrol a student");
            System.out.println("4. Update an enrolment");
            System.out.println("5. Delete an enrolment");
            System.out.println("0. Back to main menu");
            option = readInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    sem.getAll();
                    break;
                case 2:
                    System.out.print("Student ID: ");
                    String studentID = sc.next();
                    System.out.print("Course ID: ");
                    String courseID = sc.next();
                    StudentEnrolment enrolment = sem.getOne(studentID, courseID);
                    if (enrolment == null) {
                        System.out.println("This student is not enrolled in the course " + courseID + "\n");
                        break;
                    }
                    System.out.println(enrolment.toString() + "\n");
                    break;
                case 3:
                    System.out.println("---Course List---");
                    sem.getStudentList();
                    System.out.println("---Student List---");
                    sem.getCourseList();
                    enrol();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public void courseMenu() {
        int option;
        String courseID, semester;
        while (true) {
            System.out.println("COURSE MANAGEMENT");
            System.out.println("------------------");
            System.out.println("1. View all courses");
            System.out.println("2. View all courses in a semester");
            System.out.println("3. View students of a course");
            System.out.println("0. Back to main menu");
            option = readInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    sem.getCourseList();
                    break;
                case 2:
                    System.out.print("Enter semester: ");
                    semester = sc.next();
                    viewCoursesOfSemester(semester);
                    break;
                case 3:
                    System.out.print("Enter course ID: ");
                    courseID = sc.next();
                    System.out.print("Enter semester: ");
                    semester = sc.next();
                    boolean found = false;
                    viewStudentsOfACourse(courseID, semester, found);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void viewStudentsOfACourse(String courseID, String semester, boolean found) {
        System.out.println(courseID + "'s " + "Student List for Semester " + semester);
        for (StudentEnrolment enrolment : sem.getEnrolmentList()) {
            if (enrolment.getCourse().getCourseID().equals(courseID) &&
                enrolment.getSemester().equals(semester)) {
                System.out.println(enrolment.getStudent().toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available record.");
        }
        return;
    }

    private void viewCoursesOfSemester(String semester) {
        ArrayList<Course> courses = new ArrayList<>();
        for (StudentEnrolment enrolment : sem.getEnrolmentList()) {
            if (enrolment.getSemester().equals(semester)) {
                if (!courses.contains(enrolment.getCourse())) {
                    courses.add(enrolment.getCourse());
                }
            }
        }
        if (courses.size() == 0) {
            System.out.println("No course available in semester " + semester);
        } else {
            System.out.println("Courses offered in semester " + semester);
            for (Course course : courses) {
                System.out.println(course.toString());
            }
        }
    }

    private void studentMenu() {
        int option;
        while (true) {
            System.out.println("STUDENT MANAGEMENT");
            System.out.println("------------------");
            System.out.println("1. View all students");
            System.out.println("2. View one student's courses");
            System.out.println("0. Back to main menu");
            option = readInt();
            switch (option) {
                case 0:
                    return;
                case 1:

            }
        }
    }

    public void enrol() {
        String studentID, courseID, semester;
        System.out.println("Please enter student's ID, course's ID and semester to enrol.");
        System.out.print("Student ID: ");
        studentID = sc.next();
        Student student = sem.findStudentByID(studentID);
        if (student == null) {
            System.out.println("There is no student with the ID " + studentID + "\n");
            return;
        }
        System.out.print("Course ID: ");
        courseID = sc.next();
        Course course = sem.findCourseByID(courseID);
        if (course == null) {
            System.out.println("There is no course with the ID " + courseID + "\n");
            return;
        }
        if (sem.isDuplicate(studentID, courseID)) {
            System.out.println("This student has already enrolled in the course.\n");
            return;
        }
        System.out.print("Semester: ");
        semester = sc.next();
        sem.add(student, course, semester);

        System.out.println("Enrolled successfully.");

        course.getStudentList().addStudent(student);
        student.getCourseList().addCourse(course);
    }
}
