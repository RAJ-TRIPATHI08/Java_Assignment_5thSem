package com.assignment.db;

import com.assignment.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Data Access Object: all JDBC (CRUD) operations for the students table. */
public class StudentDAO {

    // CREATE
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (name, course, marks, email) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getCourse());
            ps.setDouble(3, s.getMarks());
            ps.setString(4, s.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert failed: " + e.getMessage());
            return false;
        }
    }

    // READ (all)
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Fetch failed: " + e.getMessage());
        }
        return list;
    }

    // READ (one)
    public Student getById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Fetch failed: " + e.getMessage());
        }
        return null;
    }

    // UPDATE
    public boolean updateMarks(int id, double marks) {
        String sql = "UPDATE students SET marks = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, marks);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete failed: " + e.getMessage());
            return false;
        }
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("course"),
                rs.getDouble("marks"),
                rs.getString("email"));
    }
}
