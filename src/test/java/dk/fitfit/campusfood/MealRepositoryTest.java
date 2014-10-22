package dk.fitfit.campusfood;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.fitfit.campusfood.config.PersistenceConfig;
import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.MealRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class MealRepositoryTest {

	@Autowired
	MealRepository mealRepository;
	private String mealName;
	private Meal meal;

	@Before
	public void setUp() {
		// Generate unique random name
		mealName = UUID.randomUUID().toString();

		// Create new canteen
		meal = new Meal();
		meal.setName(mealName);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void insertWithoutCanteenTest() {
		// Save canteen
		mealRepository.save(meal);

//		// Find it again
//		Meal c = mealRepository.findOne(meal.getId());
//
//		// Confirm attributes are as expected
//		assertNotNull(c);
//		assertEquals(mealName, c.getName());
	}
	
	@After
	public void tearDown() {
		mealRepository = null;
		meal = null;
		mealName = null;
	}

}
