package StudentEnrolment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        StudentEnrolmentList sem = new StudentEnrolmentList();

        sem.addCourse(new Course("COSC2020", "Intro to Software Development", 12));
        sem.addCourse(new Course("COSC2769", "Software Architecture", 12));
        sem.addCourse(new Course("COSC2589", "Web Programming", 12));

        sem.addStudent(new Student("s3754467", "Vo Phuong Thao Nhi", df.parse("29/05/2000")));
        sem.addStudent(new Student("s3538695", "John Doe", df.parse("12/08/1999")));
        sem.addStudent(new Student("s3697890", "Jane Doe", df.parse("30/01/2001")));

        Menu menu = new Menu(sem);

        menu.start();

    }
}


