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
import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.repository.CanteenRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class CanteenRepositoryTest {

	@Autowired
	CanteenRepository canteenRepository;
	
	@Test
	public void insertTest() {
		// Generate unique name
		final String name = UUID.randomUUID().toString();

		// Create new canteen
		Canteen canteen = new Canteen();
		canteen.setName(name);

		// Save canteen
		canteenRepository.save(canteen);

		// Find it again
		Canteen c = canteenRepository.findOne(canteen.getId());

		// Confirm attributes are as expected
		assertNotNull(c);
		assertEquals(name, c.getName());
	}
}
