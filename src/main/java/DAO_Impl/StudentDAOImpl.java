/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.StudentDAO;
import model.Students;

/**
 * This class provides an implementation of the StudentDAO interface.
 * It interacts with a database to perform operations related to students.
 * The database connection is provided during instantiation.
 * 
 * @autor Jatin
 */
public class StudentDAOImpl implements StudentDAO {
    private final Connection connection;

    /**
     * Constructs a StudentDAOImpl with the specified database connection.
     * 
     * @param connection the database connection to be used for student operations
     */
    public StudentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a student from the database using the specified student ID.
     * 
     * @param studentId the ID of the student to retrieve
     * @return the student object associated with the specified ID, or null if no such student exists
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Students getStudentById(int studentId) throws SQLException {
        String query = "SELECT * FROM Student WHERE studentId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Students(rs.getInt("studentId"), rs.getString("firstName"), rs.getString("lastName"));
                }
            }
        }
        return null;
    }

    /**
     * Inserts a new student into the database.
     * 
     * @param student the student object to insert
     * @return true if the student was successfully inserted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean inStudent(Students student) throws SQLException {
        String query = "INSERT INTO Student (studentId, firstName, lastName) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
