package dk.fitfit.campusfood;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

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
	
	@Test
	public void insertTest() {
		// Generate unique name
		final String name = UUID.randomUUID().toString();

		// Create new canteen
		Course course = new Course();
		course.setName(name);

		// Save canteen
		courseRepository.save(course);

		// Find it again
		Course c = courseRepository.findOne(course.getId());

		// Confirm attributes are as expected
		assertNotNull(c);
		assertEquals(name, c.getName());
	}
}
