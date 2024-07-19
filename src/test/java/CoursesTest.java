import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Courses;

public class CoursesTest {
	 @Test
	    public void testCourseId() {
	        Courses course = new Courses(null, null);
	        course.setCourseId("cst8228");
	        assertEquals("cst8228", course.getCourseId());
	    }

	    @Test
	    public void testCourseName() {
	        Courses course = new Courses(null, null);
	        course.setCourseName("Communicatios");
	        assertEquals("Communicatios", course.getCourseName());
	    }
}
