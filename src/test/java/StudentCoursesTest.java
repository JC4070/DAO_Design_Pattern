import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.StudentCourses;

public class StudentCoursesTest {
	@Test
    public void testStudentId() {
        StudentCourses studentCourse = new StudentCourses(0, null, 0, 0);
        studentCourse.setStudentId(845612359);
        assertEquals(845612359, studentCourse.getStudentId());
    }

    @Test
    public void testCourseId() {
        StudentCourses studentCourse = new StudentCourses(0, null, 0, 0);
        studentCourse.setCourseId("com8456");
        assertEquals("com8456", studentCourse.getCourseId());
    }

    }
