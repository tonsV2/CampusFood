package dk.fitfit.campusfood.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import dk.fitfit.campusfood.config.PersistenceConfig;
import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.CanteenRepository;

@Import(PersistenceConfig.class)	// Without the import CanteenRepository isn't found
public class DataInitializer {

	@Autowired
	private CanteenRepository canteenRepository;

	public DataInitializer() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>DataInitializer!!!");
	}

	public void initialize()
	{
		String name = "Kantinen i something";
		String address = "Somewhere 4";
		String openingHours = "Mandag til torsdag 8:00 - 15:00. Fredag 8:00 - 14:30\nMorgenmad 8:00 - 9:30\nFrokost 11:00 - 14:00";
		String contact = "Kontakt... someone.";
		Canteen canteen = new Canteen(name, address, openingHours, contact);

		Meal meal = new Meal();
		meal.setName("Pasta");
		meal.setDateOfServing(new Date());
		canteen.addMeal(meal);

		Meal meal1 = new Meal();
		meal1.setName("Pasta something");
		meal1.setDateOfServing(new Date());
		canteen.addMeal(meal1);

		Canteen c = canteenRepository.save(canteen);
	}
}
