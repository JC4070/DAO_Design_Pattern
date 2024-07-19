package DAO;

import java.sql.SQLException;
import model.StudentCourses;

/**
 * This interface defines the operations for accessing and manipulating student course data.
 * It provides a method to insert a new student course record.
 * Implementations of this interface should handle database interactions and
 * throw appropriate SQL exceptions when database access errors occur.
 * 
 * @author Jatin
 */
public interface StudentCoursesDAO {

    /**
     * Inserts a new student course record into the database.
     * 
     * @param studentCourse the student course object to insert
     * @return true if the student course was successfully inserted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean inStudentCourse(StudentCourses studentCourse) throws SQLException;
}
