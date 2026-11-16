package com.assignment;

import com.assignment.collections.CollectionsDemo;
import com.assignment.db.StudentDAO;
import com.assignment.model.Student;

import java.util.*;

public class Main {

    private static final StudentDAO dao = new StudentDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== STUDENT MANAGEMENT (Collections + JDBC) =====");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Update marks");
            System.out.println("4. Delete student");
            System.out.println("5. Reports using Collections (sort, group, topper)");
            System.out.println("6. Collections Framework demo");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> printAll(dao.getAllStudents());
                case 3 -> updateMarks();
                case 4 -> deleteStudent();
                case 5 -> reports();
                case 6 -> CollectionsDemo.run();
                case 0 -> System.out.println("Bye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addStudent() {
        System.out.print("Name: ");   String name = sc.nextLine();
        System.out.print("Course: "); String course = sc.nextLine();
        System.out.print("Marks: ");  double marks = readDouble();
        System.out.print("Email: ");  String email = sc.nextLine();
        System.out.println(dao.addStudent(new Student(name, course, marks, email))
                ? "Student added." : "Could not add student.");
    }

    private static void updateMarks() {
        System.out.print("Student id: "); int id = readInt();
        System.out.print("New marks: ");  double marks = readDouble();
        System.out.println(dao.updateMarks(id, marks) ? "Updated." : "Student not found.");
    }

    private static void deleteStudent() {
        System.out.print("Student id: "); int id = readInt();
        System.out.println(dao.deleteStudent(id) ? "Deleted." : "Student not found.");
    }

    /** Loads rows from the database (JDBC) and processes them with collections. */
    private static void reports() {
        List<Student> students = dao.getAllStudents();   // ArrayList
        if (students.isEmpty()) { System.out.println("No data."); return; }

        // 1. Sort by marks (descending) using Comparator
        List<Student> byMarks = new ArrayList<>(students);
        byMarks.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println("\n--- Ranking by marks ---");
        printAll(byMarks);

        // 2. Group by course using HashMap<String, List<Student>>
        Map<String, List<Student>> byCourse = new TreeMap<>();
        for (Student s : students) {
            byCourse.computeIfAbsent(s.getCourse(), k -> new ArrayList<>()).add(s);
        }
        System.out.println("\n--- Students per course ---");
        byCourse.forEach((course, list) -> {
            double avg = list.stream().mapToDouble(Student::getMarks).average().orElse(0);
            System.out.printf("%-8s count=%d average=%.2f%n", course, list.size(), avg);
        });

        // 3. Unique courses using TreeSet
        Set<String> courses = new TreeSet<>();
        for (Student s : students) courses.add(s.getCourse());
        System.out.println("\nUnique courses (TreeSet): " + courses);

        // 4. Top 3 using PriorityQueue (max-heap)
        PriorityQueue<Student> pq = new PriorityQueue<>(
                Comparator.comparingDouble(Student::getMarks).reversed());
        pq.addAll(students);
        System.out.println("\n--- Top 3 students (PriorityQueue) ---");
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            System.out.println((i + 1) + ". " + pq.poll());
        }
    }

    private static void printAll(List<Student> list) {
        System.out.println(String.format("%-4s %-15s %-8s %6s  %s", "ID", "NAME", "COURSE", "MARKS", "EMAIL"));
        list.forEach(System.out::println);
    }

    private static int readInt() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid number: "); }
        }
    }

    private static double readDouble() {
        while (true) {
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid number: "); }
        }
    }
}
