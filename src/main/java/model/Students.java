package model;

/**
 * The Students class represents a student with an ID, first name, and last name.
 * It provides getters and setters for these fields.
 * 
 * @autor Jatin
 */
public class Students {
    private int studentId;
    private String firstName;
    private String lastName;

    /**
     * Constructs a new Students object with the specified student ID, first name, and last name.
     * 
     * @param studentId the ID of the student
     * @param firstName the first name of the student
     * @param lastName the last name of the student
     */
    public Students(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /**
     * Returns the first name.
     * 
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name.
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * 
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}

