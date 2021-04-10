package StudentEnrolment;

import java.io.FileOutputStream;
import java.util.*;

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
                    break;
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
                    sem.printStudentList();
                    System.out.println("---Student List---");
                    sem.printCourseList();
                    enrol();
                    break;
                case 4:

                case 5:
                    System.out.println("---Enrolment List---");
                    sem.getAll();
                    System.out.print("Please enter the ID of the enrolment you want to delete: ");
                    int id = Integer.parseInt(sc.next());
                    if (sem.delete(id)) {
                        System.out.println("Enrolment deleted.");
                    } else {
                        System.out.println("No enrolment with that ID.");
                    }
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
                    sem.printCourseList();
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
                    viewStudentsOfACourse(courseID, semester);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void viewStudentsOfACourse(String courseID, String semester) {
        boolean found = false;
        StudentList students = new StudentList();
        System.out.println(courseID + "'s " + "Student List for Semester " + semester);
        for (StudentEnrolment enrolment : sem.getEnrolmentList()) {
            if (enrolment.getCourse().getCourseID().equals(courseID) &&
                    enrolment.getSemester().equals(semester)) {
                students.addStudent(enrolment.getStudent());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available record.");
        } else {
            students.printStudentList();
            System.out.println("Would you like to save a CSV file of this? (Y/n)");
            String option = sc.next();
            if (option.equals("Y") || option.equals("y")) {
                studentCSV(students, courseID, semester);
                return;
            } else if (option.equals("N") || option.equals("n")) {
                System.out.println("Invalid option.");
                return;
            }
        }
    }

    private void viewCoursesOfSemester(String semester) {
        CourseList courses = new CourseList();
        for (StudentEnrolment enrolment : sem.getEnrolmentList()) {
            if (enrolment.getSemester().equals(semester)) {
                if (courses.findByID(enrolment.getCourse().getCourseID()) == null) {
                    courses.addCourse(enrolment.getCourse());
                }
            }
        }
        if (courses.size() == 0) {
            System.out.println("No course available in semester " + semester);
        } else {
            System.out.println("Courses offered in semester " + semester);
            courses.printCourseList();
            System.out.println("Would you like to save a CSV file of this? (Y/n)");
            String option = sc.next();
            if (option.equals("Y") || option.equals("y")) {
                courseCSV(courses, semester);
                return;
            } else if (option.equals("N") || option.equals("n")) {
                return;
            } else {
                System.out.println("Invalid option.");
                return;
            }
        }
    }

    private void courseCSV(CourseList courses, String semester) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Course c : courses.getCourseList()) {
            stringBuilder.append(c.toCSV());
        }
        String string = stringBuilder.toString();
        writeToFile(string, semester + "_Courses" + ".csv");
        System.out.println("File saved successfully.");
        System.out.println("The file will be available after the program exits.");
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
                    sem.printStudentList();
                    break;
                case 2:
                    System.out.print("Student ID: ");
                    String studentID = sc.next();
                    System.out.print("Semester: ");
                    String semester = sc.next();
                    System.out.println(studentID + "'s Course List for Semester " + semester);
                    for (StudentEnrolment e : sem.getEnrolmentList()) {
                        if (e.getStudent().getStudentID().equals(studentID) && e.getSemester().equals(semester)) {
                            System.out.println(e.getCourse().toString());
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void studentCSV(StudentList students, String courseID, String semester) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Student s : students.getStudentList()) {
            stringBuilder.append(s.toCSV());
        }
        String string = stringBuilder.toString();
        writeToFile(string, semester + "_" + courseID + "_Students" + ".csv");
        System.out.println("File saved successfully.");
        System.out.println("The file will be available after the program exits.");
    }

    private void writeToFile(String s, String filename) {
        FileOutputStream write_file;
        try {
            write_file = new FileOutputStream(filename);
            write_file.write(s.getBytes());
            write_file.close();
        } catch (Exception e) {
            e.printStackTrace();
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
