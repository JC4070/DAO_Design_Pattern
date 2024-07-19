package org.cst8288Lab2;
import DAO.CoursesDAO;
import DAO.StudentCoursesDAO;
import DAO.StudentDAO;
import DAO_Impl.CoursesDAOImpl;
import DAO_Impl.StudentCoursesDAOImpl;
import DAO_Impl.StudentDAOImpl;
import Validate.Validation;
import model.Courses;
import model.StudentCourses;
import model.Students;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * The Lab2Starter class initializes the database connection and processes a CSV file to import data.
 * It reads database connection properties, establishes a connection, and then processes the CSV file
 * to insert students, courses, and student-course associations into the database.
 *
 * @autor Jatin
 */
public class Lab2Starter {
	
    /**
     * The main method is the entry point of the program. It loads database properties,
     * establishes a database connection, and initiates the CSV file processing.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Load database connection properties and initialize connection
        String propertiesFilePath = "data/database.properties";
        if (!DatabaseConnectionManager.initializeConnection(propertiesFilePath)) {
            System.out.println("Failed to initialize database connection. Exiting.");
            return;
        }

        // Get the database connection from connection manager
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            StudentDAO studentDAO = new StudentDAOImpl(connection);
            CoursesDAO courseDAO = new CoursesDAOImpl(connection);
            StudentCoursesDAO studentCourseDAO = new StudentCoursesDAOImpl(connection);
            processCsvFile("data/bulk-import.csv", studentDAO, courseDAO, studentCourseDAO);
        } catch (SQLException ex) {
            System.out.println("Database connection error.");
            ex.printStackTrace();
        } finally {
            // Close database connection
            DatabaseConnectionManager.closeConnection();
        }
    }

    /**
     * Processes the CSV file to read and validate data, then inserts the valid data into the database.
     * It generates an import report and an error report.
     *
     * @param csvFilePath     the path to the CSV file
     * @param studentDAO      the StudentDAO implementation
     * @param courseDAO       the CoursesDAO implementation
     * @param studentCourseDAO the StudentCoursesDAO implementation
     */
    private static void processCsvFile(String csvFilePath, StudentDAO studentDAO, CoursesDAO courseDAO, StudentCoursesDAO studentCourseDAO) {
        List<String> importReport = new ArrayList<>();
        List<String> errorReport = new ArrayList<>();
        Map<Integer, Students> studentsMap = new HashMap<>();
        Map<String, Courses> coursesMap = new HashMap<>();
        
        int successfulImports = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    importReport.add("Processing File: " + line);
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != 7) {
                    errorReport.add("wrong number of columns: " + line);
                    continue;
                }
                String studentIdStr = values[0].trim();
                String firstName = values[1].trim();
                String lastName = values[2].trim();
                String courseId = values[3].trim();
                String courseName = values[4].trim();
                String term = values[5].trim().toUpperCase();
                String yearStr = values[6].trim();

                if (!Validation.isValidStudentId(studentIdStr)) {
                    errorReport.add("Wrong studentId: " + studentIdStr);
                    continue;
                }
                int studentId = Integer.parseInt(studentIdStr);
                if (!Validation.isValidCourseId(courseId)) {
                    errorReport.add("wrong courseId: " + courseId);
                    continue;
                }
                if (!Validation.isValidTerm(term)) {
                    errorReport.add("Wrong term: " + term);
                    continue;
                }
                if (!Validation.isValidYear(yearStr)) {
                    errorReport.add("Wrong year: " + yearStr);
                    continue;
                }
                int year = Integer.parseInt(yearStr);
                int termValue = Validation.getTermValue(term);

                Students student = new Students(studentId, firstName, lastName);
                Courses course = new Courses(courseId, courseName);
                StudentCourses studentCourse = new StudentCourses(studentId, courseId, termValue, year);

                try {
                    if (!studentsMap.containsKey(studentId)) {
                        studentsMap.put(studentId, student);
                    }
                    if (!coursesMap.containsKey(courseId)) {
                        coursesMap.put(courseId, course);
                    }
                    if (studentDAO.getStudentById(studentId) == null) {
                        studentDAO.inStudent(student);
                        successfulImports++;
                    }
                    if (courseDAO.getCourseById(courseId) == null) {
                        courseDAO.inCourse(course);
                        successfulImports++;
                    }
                    if (studentCourseDAO.inStudentCourse(studentCourse)) {
                        importReport.add("Uploaded: " + line);
                        successfulImports++;
                    } else {
                        errorReport.add("Failed inserting: " + line);
                    }
                } catch (SQLException e) {
                    errorReport.add("Database error for line: " + line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int totalStudents = studentsMap.size();
        int totalCourses = coursesMap.size();

        importReport.add("Total Students Imported: " + totalStudents);
        importReport.add("Total Courses Imported: " + totalCourses);
        
        
        writeReport("data/import-report.md", importReport);
        writeReport("data/error-report.md", errorReport);
    }

    /**
     * Writes the report to a file with current date and time.
     *
     * @param filePath the path to the report file
     * @param report   the list of report lines to write
     */
    private static void writeReport(String filePath, List<String> report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("DATE: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            writer.newLine();
            writer.write("TIME: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            writer.newLine();

            for (String line : report) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
