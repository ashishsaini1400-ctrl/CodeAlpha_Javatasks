import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90)
            return "A+";
        else if (marks >= 80)
            return "A";
        else if (marks >= 70)
            return "B";
        else if (marks >= 60)
            return "C";
        else if (marks >= 50)
            return "D";
        else
            return "F";
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for(int i = 0; i < n; i++) {

            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            students.add(new Student(name, marks));
        }


        int total = 0;
        int highest = students.get(0).marks;
        int lowest = students.get(0).marks;


        System.out.println("\nStudent Report");

        for(Student s : students) {

            System.out.println(
                s.name + " : " + s.marks + " Grade: " + s.getGrade()
            );

            total += s.marks;

            if(s.marks > highest)
                highest = s.marks;

            if(s.marks < lowest)
                lowest = s.marks;
        }


        System.out.println("\nAverage : " + (double)total / students.size());
        System.out.println("Highest : " + highest);
        System.out.println("Lowest : " + lowest);

        sc.close();
    }
}