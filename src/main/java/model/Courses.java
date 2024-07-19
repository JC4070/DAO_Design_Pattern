package model;

/**
 * The Courses class represents a course with an ID and a name.
 * It provides getters and setters for the course ID and name.
 * 
 * @autor Jatin
 */
public class Courses {
    private String courseId;
    private String courseName;

    /**
     * Constructs a new Courses object with the specified course ID and name.
     * 
     * @param courseId the ID of the course
     * @param courseName the name of the course
     */
    public Courses(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    /**
     * Returns the course name.
     * 
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name.
     * 
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    
}
