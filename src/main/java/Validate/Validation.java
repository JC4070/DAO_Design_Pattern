/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate;

import java.util.Calendar;

/**
 *
 * @author jatin
 */
public class Validation {
       
    private static final int ALGONQUIN_COLLEGE_FOUNDING_YEAR = 1967;

    /**
     * Checks if a student ID is valid.
     *
     * @param studentId The student ID to validate.
     * @return true if the student ID is valid, false otherwise.
     */
    public static boolean isValidStudentId(String studentId) {
        return studentId != null && studentId.matches("\\d{9}");
    }

    /**
     * Checks if a year is valid.
     *
     * @param yearStr The year to validate as a string.
     * @return true if the year is valid, false otherwise.
     */
    public static boolean isValidYear(String yearStr) {
        if (yearStr == null || !yearStr.matches("\\d{4}")) {
            return false;
        }
        int year = Integer.parseInt(yearStr);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= ALGONQUIN_COLLEGE_FOUNDING_YEAR && year <= currentYear;
    }
    
    /**
     * Checks if a course ID is valid.
     *
     * @param courseId The course ID to validate.
     * @return true if the course ID is valid, false otherwise.
     */
    public static boolean isValidCourseId(String courseId) {
        return courseId != null && courseId.matches("[ aA-zZ ]{3}\\d{4}");
    }

    
    /**
     * Checks if a term is valid (WINTER, SUMMER, or FALL).
     *
     * @param term The term to validate.
     * @return true if the term is valid, false otherwise.
     */
    public static boolean isValidTerm(String term) {
        return term != null && (term.equals("WINTER") || term.equals("SUMMER") || term.equals("FALL"));
    }
    
    /**
     * Converts a term string to its numeric representation.
     *
     * @param term The term string ("WINTER", "SUMMER", or "FALL").
     * @return The numeric representation of the term (1 for WINTER, 2 for SUMMER, 3 for FALL).
     * @throws IllegalArgumentException if the provided term is invalid.
     */
    public static int getTermValue(String term) {
        switch (term) {
            case "WINTER":
                return 1;
            case "SUMMER":
                return 2;
            case "FALL":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid term: " + term);
        }
    }
}