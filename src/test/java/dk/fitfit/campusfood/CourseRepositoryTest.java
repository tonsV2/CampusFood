package dk.fitfit.campusfood;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.fitfit.campusfood.config.PersistenceConfig;
import dk.fitfit.campusfood.model.Course;
import dk.fitfit.campusfood.repository.CourseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;
	private String courseName;
	private Course course;

	@Before
	public void setUp() {
		// Generate unique random name
		courseName = UUID.randomUUID().toString();

		// Create new canteen
		course = new Course();
		course.setName(courseName);
	}

	@Test
	public void insertTest() {
		// Save canteen
		courseRepository.save(course);

		// Find it again
		Course c = courseRepository.findOne(course.getId());

		// Confirm attributes are as expected
		assertNotNull(c);
		assertEquals(courseName, c.getName());
	}
	
	@After
	public void tearDown() {
		courseRepository = null;
		course = null;
		courseName = null;
	}

}
