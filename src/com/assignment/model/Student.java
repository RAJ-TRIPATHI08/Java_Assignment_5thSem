package com.assignment.model;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private int id;
    private String name;
    private String course;
    private double marks;
    private String email;

    public Student(int id, String name, String course, double marks, String email) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
        this.email = email;
    }

    public Student(String name, String course, double marks, String email) {
        this(0, name, course, marks, email);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }
    public String getEmail() { return email; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }
    public void setMarks(double marks) { this.marks = marks; }
    public void setEmail(String email) { this.email = email; }

    // Natural ordering: by id
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        return id == ((Student) o).id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return String.format("%-4d %-15s %-8s %6.2f  %s", id, name, course, marks, email);
    }
}
