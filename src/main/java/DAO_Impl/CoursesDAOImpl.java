package DAO_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.CoursesDAO;
import model.Courses;

/**
 * This class provides an implementation of the CoursesDAO interface.
 * It interacts with a database to perform operations related to courses.
 * The database connection is provided during instantiation.
 * 
 * @author Jatin
 */
public class CoursesDAOImpl implements CoursesDAO {
    private final Connection connection;

    /**
     * Constructs a CoursesDAOImpl with the specified database connection.
     * 
     * @param connection the database connection to be used for course operations
     */
    public CoursesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a course from the database using the specified course ID.
     * 
     * @param courseId the ID of the course to retrieve
     * @return the course object associated with the specified ID, or null if no such course exists
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Courses getCourseById(String courseId) throws SQLException {
        String query = "SELECT * FROM Course WHERE courseId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, courseId);
            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    return new Courses(rset.getString("courseId"),
                    		rset.getString("courseName"));
                }
            }
        }
        return null;
    }

    /**
     * Inserts a new course into the database.
     * 
     * @param course the course object to insert
     * @return true if the course was successfully inserted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean inCourse(Courses course) throws SQLException {
        String query = "INSERT IGNORE INTO Course (courseId, courseName) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}