package DAO_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.StudentCoursesDAO;
import model.StudentCourses;

/**
 * This class provides an implementation of the StudentCoursesDAO interface.
 * It interacts with a database to perform operations related to student courses.
 * The database connection is provided during instantiation.
 * 
 * @autor Jatin
 */
public class StudentCoursesDAOImpl implements StudentCoursesDAO {
    private final Connection connection;

    /**
     * Constructs a StudentCoursesDAOImpl with the specified database connection.
     * 
     * @param connection the database connection to be used for student course operations
     */
    public StudentCoursesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new student course record into the database.
     * 
     * @param studentCourse the student course object to insert
     * @return true if the student course was successfully inserted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean inStudentCourse(StudentCourses studentCourse) throws SQLException {
        String query = "INSERT INTO StudentCourse (studentId, courseId, term, year) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentCourse.getStudentId());
            stmt.setString(2, studentCourse.getCourseId());
            stmt.setInt(3, studentCourse.getIntake());
            stmt.setInt(4, studentCourse.getYear());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
