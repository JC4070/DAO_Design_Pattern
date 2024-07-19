package DAO;

import java.sql.SQLException;
import model.Courses;

/**
 * This interface defines the operations for accessing and manipulating course data.
 * It provides methods to get a course by its ID and to insert a new course.
 * Implementations of this interface should handle database interactions and
 * throw appropriate SQL exceptions when database access errors occur.
 * 
 * @author Jatin
 */
public interface CoursesDAO {

    /**
     * Retrieves a course from the database using the specified course ID.
     * 
     * @param courseId the ID of the course to retrieve
     * @return the course object associated with the specified ID, or null if no such course exists
     * @throws SQLException if a database access error occurs
     */
    Courses getCourseById(String courseId) throws SQLException;

    /**
     * Inserts a new course into the database.
     * 
     * @param course the course object to insert
     * @return true if the course was successfully inserted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean inCourse(Courses course) throws SQLException;
}