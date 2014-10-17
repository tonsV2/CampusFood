package dk.fitfit.campusfood;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.fitfit.campusfood.config.PersistenceConfig;
import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.model.Course;
import dk.fitfit.campusfood.repository.CanteenRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class CanteenHasCoursesTest {

	@Autowired
	private CanteenRepository canteenRepository;
	private String canteenName;
	private String courseName;
	private Canteen canteen;
	private Course course0;
	private Course course1;

	@Before
	public void setUp() {
		// Generate unique random name
		canteenName = UUID.randomUUID().toString();
		// Generate unique random name
		courseName = UUID.randomUUID().toString();

		// Create new canteen
		canteen = new Canteen();
		canteen.setName(canteenName);

		Date dateOfServing = new Date();

		// Create new course
		course0 = new Course();
		course0.setName(courseName);
		course0.setDateOfServing(dateOfServing);

		// Create another new course
		course1 = new Course();
		course1.setName(courseName);
		course1.setDateOfServing(dateOfServing);
	}

	@Test
	public void addCourseToCanteenTest() {
		// Add course
		canteen.addCourse(course0);

		// Save canteen
		canteenRepository.save(canteen);

		// Find it again
		Canteen c = canteenRepository.findOne(canteen.getId());

		// Confirm attributes are as expected
		assertNotNull(c);

		Set<Course> courses = c.getCourses();
		Iterator<Course> it = courses.iterator();
		assertTrue(it.hasNext());

		Course course = it.next();
		assertEquals(courseName, course.getName());
	}

	// TODO: expect some data violation exception
//	@Test(expected = IndexOutOfBoundsException.class)
	@Test
	public void addDuplicatedCourseToCanteenTest() {
		// Add course
		boolean add0 = canteen.addCourse(course0);
//		System.out.println("add: " + add0);
//		System.out.println("canteen.getCourses().size: " + canteen.getCourses().size());
		// Add it again
		boolean add1 = canteen.addCourse(course1);
//		System.out.println("add: " + add1);
//		System.out.println("canteen.getCourses().size: " + canteen.getCourses().size());
		// TODO: this is basically just a test confirming that java.util.set works... :(
		assertFalse(add1);
		// Save canteen
//		canteenRepository.save(canteen);
	}

	@After
	public void tearDown() {
		canteenRepository = null;
		canteenName = null;
		courseName = null;
		canteen = null;
		course0 = null;
		course1 = null;
	}

}
