package model;

/**
 * The StudentCourses class represents the association between a student and a course.
 * It includes information about the student ID, course ID, term, and year.
 * It provides getters and setters for each of these fields.
 * 
 * @autor Jatin 
 */
public class StudentCourses {
    private String courseId;
    private int studentId;
    private int year;
    private int intake;

    /**
     * Constructs a new StudentCourses object with the specified student ID, course ID, term, and year.
     * 
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     * @param term the term in which the course is taken
     * @param year the year in which the course is taken
     */
    public StudentCourses(int studentId, String courseId, int intake, int year) {
    	this.courseId = courseId;
        this.studentId = studentId;
        this.year = year;
        this.intake = intake;
    }

    /**
     * Returns the course ID.
     * 
     * @return the course ID
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Sets the course ID.
     * 
     * @param courseId the course ID to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    /**
     * Returns the student ID.
     * 
     * @return the student ID
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID.
     * 
     * @param studentId the student ID to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    /**
     * Returns the year.
     * 
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year.
     * 
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     * Returns the term.
     * 
     * @return the term
     */
    public int getIntake() {
        return intake;
    }

    /**
     * Sets the term.
     * 
     * @param term the term to set
     */
    public void setIntake(int Intake) {
        this.intake = intake;
    }

    
}
