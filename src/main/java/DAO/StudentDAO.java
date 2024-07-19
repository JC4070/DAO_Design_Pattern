package DAO;

import java.sql.SQLException;
import model.Students;

/**
 * This interface defines the operations for accessing and manipulating student data.
 * It provides methods to get a student by their ID and to insert a new student.
 * Implementations of this interface should handle database interactions and
 * throw appropriate SQL exceptions when database access errors occur.
 * 
 * @author Jatin
 */
public interface StudentDAO {

    /**
     * Retrieves a student from the database using the specified student ID.
     * 
     * @param studentId the ID of the student to retrieve
     * @return the student object associated with the specified ID, or null if no such student exists
     * @throws SQLException if a database access error occurs
     */
    Students getStudentById(int studentId) throws SQLException;

    /**
     * Inserts a new student into the database.
     * 
     * @param student the student object to insert
     * @return true if the student was successfully inserted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean inStudent(Students student) throws SQLException;
}
