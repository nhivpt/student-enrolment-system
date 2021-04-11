package StudentEnrolment;

import java.io.FileOutputStream;
import java.util.*;

public class Menu {

    private Scanner sc;
    private StudentEnrolmentList sem;

    public Menu(StudentEnrolmentList sem) {
        this.sem = sem;
        this.sc = new Scanner(System.in);
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
            try {
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
            } catch (NumberFormatException e) {
                System.out.println("You must enter a number.");
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
            try {
                option = readInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        sem.getAll();
                        break;
                    case 2:
                        String studentID;
                        do {
                            System.out.print("Student ID: ");
                            studentID = sc.next();
                        } while (checkStudentID(studentID));
                        String courseID;
                        do {
                            System.out.print("Course ID: ");
                            courseID = sc.next();
                        } while (checkCourseID(courseID));
                        StudentEnrolment enrolment = sem.getOne(studentID, courseID);
                        if (enrolment == null) {
                            System.out.println("This student is not enrolled in the course " + courseID + "\n");
                            break;
                        }
                        System.out.println(enrolment + "\n");
                        break;
                    case 3:
                        System.out.println("---Course List---");
                        sem.printStudentList();
                        System.out.println("---Student List---");
                        sem.printCourseList();
                        enrol();
                        break;
                    case 4:
                        sem.getAll();
                        System.out.println("Please enter the ID of the enrolment you want to update: ");
                        int eID = Integer.parseInt(sc.next());
                        Student student;
                        do {

                            System.out.print("New Student's ID: ");
                            String sID = sc.next();
                            sID = sID.toUpperCase();
                            student = sem.findStudentByID(sID);
                            if (student == null) {
                                System.out.println("No student with the ID " + sID);
                            }
                        } while (student == null);
                        Course course;
                        do {

                            System.out.print("New Course's ID: ");
                            String cID = sc.next();
                            cID = cID.toUpperCase();

                            course = sem.findCourseByID(cID);
                            if (course == null) {
                                System.out.println("No course with the ID " + cID);
                            }
                        } while (course == null);
                        String semester;
                        do {
                            System.out.print("Semester: ");
                            semester = sc.next();
                            semester = semester.toUpperCase();
                        } while (checkSemester(semester));
                        if (sem.update(eID, student, course, semester)) {
                            System.out.println("Update successfully.");
                        } else {
                            System.out.println("Update failed. Please try again.");
                        }
                        break;
                    case 5:
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
            } catch (NumberFormatException e) {
                System.out.println("You must enter a number.");
            }
        }
    }

    public void courseMenu() {
        int option;
        while (true) {
            System.out.println("COURSE MANAGEMENT");
            System.out.println("------------------");
            System.out.println("1. View all courses");
            System.out.println("2. View all courses in a semester");
            System.out.println("3. View students of a course");
            System.out.println("0. Back to main menu");
            try {
                option = readInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        sem.printCourseList();
                        break;
                    case 2:
                        String semester;
                        do {
                            System.out.print("Semester: ");
                            semester = sc.next();
                        } while (checkSemester(semester));
                        viewCoursesOfSemester(semester);
                        break;
                    case 3:
                        String courseID1;
                        do {
                            System.out.print("Course ID: ");
                            courseID1 = sc.next();
                        } while (checkCourseID(courseID1));
                        String semester1;
                        do {
                            System.out.print("Semester: ");
                            semester1 = sc.next();
                        } while (checkSemester(semester1));
                        viewStudentsOfACourse(courseID1, semester1);
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("You must enter a number.");
            }
        }
    }

    private void viewStudentsOfACourse(String courseID, String semester) {
        boolean found = false;
        courseID = courseID.toUpperCase();
        semester = semester.toUpperCase();

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
            boolean invalid;
            do {
                System.out.println("Would you like to save a CSV file of this? (Y/n)");
                String option = sc.next();
                if (option.equals("Y") || option.equals("y")) {
                    studentCSV(students, courseID, semester);
                    invalid = false;
                } else if (option.equals("N") || option.equals("n")) {
                    invalid = false;
                } else {
                    System.out.println("Invalid option. Please try again.");
                    invalid = true;
                }
            } while (invalid);
        }
    }

    private void viewCoursesOfSemester(String semester) {
        semester = semester.toUpperCase();
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
            boolean invalid;
            do {
                System.out.println("Would you like to save a CSV file of this? (Y/n)");
                String option = sc.next();
                if (option.equals("Y") || option.equals("y")) {
                    courseCSV("", courses, semester);
                    invalid = false;
                } else if (option.equals("N") || option.equals("n")) {
                    invalid = false;
                } else {
                    System.out.println("Invalid option. Please try again.");
                    invalid = true;
                }
            } while (invalid);
        }
    }

    private void studentMenu() {
        int option;
        while (true) {
            try {
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
                        String studentID;
                        do {
                            System.out.print("Student ID: ");
                            studentID = sc.next();
                        } while (checkStudentID(studentID));
                        String semester;
                        do {
                            System.out.print("Semester: ");
                            semester = sc.next();
                        } while (checkSemester(semester));
                        viewCoursesOfAStudent(studentID, semester);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("You must enter a number.");
            }
        }
    }

    private void viewCoursesOfAStudent(String studentID, String semester) {
        studentID = studentID.toUpperCase();
        semester = semester.toUpperCase();

        CourseList courses = new CourseList();
        for (StudentEnrolment e : sem.getEnrolmentList()) {
            if (e.getStudent().getStudentID().equals(studentID) && e.getSemester().equals(semester)) {
                courses.addCourse(e.getCourse());
            }
        }

        if (courses.size() == 0) {
            System.out.println("No course enrolled by this student for semester " + semester);
        } else {
            System.out.println("Courses enrolled by this student in semester " + semester);
            courses.printCourseList();
            boolean invalid;
            do {
                System.out.println("Would you like to save a CSV file of this? (Y/n)");
                String option = sc.next();
                if (option.equals("Y") || option.equals("y")) {
                    courseCSV(studentID, courses, semester);
                    invalid = false;
                } else if (option.equals("N") || option.equals("n")) {
                    invalid = false;
                } else {
                    System.out.println("Invalid option. Please try again.");
                    invalid = true;
                }
            } while (invalid);
        }
    }

    private void courseCSV(String studentID, CourseList courses, String semester) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Course c : courses.getCourseList()) {
            stringBuilder.append(c.toCSV());
        }
        String string = stringBuilder.toString();
        if (studentID.equals("")) {
            writeToFile(string, semester + "_Courses" + ".csv");
        } else {
            writeToFile(string, studentID + "_Courses_" + semester + ".csv");
        }
        System.out.println("File saved successfully.");
        System.out.println("The file will be available after the program exits.");
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
        do {
            System.out.print("Student ID: ");
            studentID = sc.next();
        } while (checkStudentID(studentID));

        studentID = studentID.toUpperCase();
        Student student = sem.findStudentByID(studentID);
        if (student == null) {
            System.out.println("There is no student with the ID " + studentID + "\n");
            return;
        }
        do {
            System.out.print("Course ID: ");
            courseID = sc.next();
        } while (checkCourseID(courseID));

        courseID = courseID.toUpperCase();
        Course course = sem.findCourseByID(courseID);
        if (course == null) {
            System.out.println("There is no course with the ID " + courseID + "\n");
            return;
        }
        if (sem.isDuplicate(studentID, courseID)) {
            System.out.println("This student has already enrolled in the course.\n");
            return;
        }
        do {
            System.out.print("Semester: ");
            semester = sc.next();
        } while (checkSemester(semester));

        semester = semester.toUpperCase();
        sem.add(student, course, semester);

        System.out.println("Enrolled successfully.");

        course.getStudentList().addStudent(student);
        student.getCourseList().addCourse(course);
    }

    public boolean checkSemester(String semester) {
        String pattern = "[0-9]{4}[A-z]";
        if (!semester.matches(pattern)) {
            System.out.println("Invalid entry. Please try again.");
            return true;
        }
        return false;
    }

    public boolean checkStudentID(String studentID) {
        String pattern = "[A-z][0-9]{6,7}";
        if (!studentID.matches(pattern)) {
            System.out.println("Invalid entry. Please try again.");
            return true;
        }
        return false;
    }

    public boolean checkCourseID(String courseID) {
        String pattern = "[A-z]{3,4}[0-9]{3,4}";
        if (!courseID.matches(pattern)) {
            System.out.println("Invalid entry. Please try again.");
            return true;
        }
        return false;
    }
}

