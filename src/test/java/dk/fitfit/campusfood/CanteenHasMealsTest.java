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
import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.CanteenRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class CanteenHasMealsTest {

	@Autowired
	private CanteenRepository canteenRepository;
	private String canteenName;
	private String mealName;
	private Canteen canteen;
	private Meal meal0;
	private Meal meal1;

	@Before
	public void setUp() {
		// Generate unique random name
		canteenName = UUID.randomUUID().toString();
		// Generate unique random name
		mealName = UUID.randomUUID().toString();

		// Create new canteen
		canteen = new Canteen();
		canteen.setName(canteenName);

		Date dateOfServing = new Date();

		// Create new meal
		meal0 = new Meal();
		meal0.setName(mealName);
		meal0.setDateOfServing(dateOfServing);

		// Create another new meal
		meal1 = new Meal();
		meal1.setName(mealName);
		meal1.setDateOfServing(dateOfServing);
	}

	@Test
	public void addMealToCanteenTest() {
		// Add meal
		canteen.addMeal(meal0);

		// Save canteen
		canteenRepository.save(canteen);

		// Find it again
		Canteen c = canteenRepository.findOne(canteen.getId());

		// Confirm attributes are as expected
		assertNotNull(c);

		Set<Meal> meals = c.getMeals();
		Iterator<Meal> it = meals.iterator();
		assertTrue(it.hasNext());

		Meal meal = it.next();
		assertEquals(mealName, meal.getName());
	}

	// TODO: expect some data violation exception
//	@Test(expected = IndexOutOfBoundsException.class)
	@Test
	public void addDuplicatedMealToCanteenTest() {
		// Add meal
		boolean add0 = canteen.addMeal(meal0);
//		System.out.println("add: " + add0);
//		System.out.println("canteen.getMeals().size: " + canteen.getMeals().size());
		// Add it again
		boolean add1 = canteen.addMeal(meal1);
//		System.out.println("add: " + add1);
//		System.out.println("canteen.getMeals().size: " + canteen.getMeals().size());
		// TODO: this is basically just a test confirming that java.util.set works... :(
		assertFalse(add1);
		// Save canteen
//		canteenRepository.save(canteen);
	}

	@After
	public void tearDown() {
		canteenRepository = null;
		canteenName = null;
		mealName = null;
		canteen = null;
		meal0 = null;
		meal1 = null;
	}

}
