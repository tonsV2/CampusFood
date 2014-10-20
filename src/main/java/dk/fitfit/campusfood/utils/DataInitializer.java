package dk.fitfit.campusfood.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.CanteenRepository;

@Component
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

		name = "Kantinen i August Krogh Bygningen";
		address = "Somewhere 4";
		openingHours = "Mandag til torsdag 8:00 - 15:00. Fredag 8:00 - 14:30\nMorgenmad 8:00 - 9:30\nFrokost 11:00 - 14:00";
		contact = "Kontakt... someone.";
		Canteen canteen1 = new Canteen(name, address, openingHours, contact);

		Meal meal = new Meal();
		meal.setName("Braiseret skinke culotte");
		meal.setDateOfServing(new Date());
		canteen.addMeal(meal);

		Meal meal1 = new Meal();
		meal1.setName("Kylling i sur sød med bambus");
		meal1.setDateOfServing(new Date());
		canteen.addMeal(meal1);
		
		Meal meal2 = new Meal();
		meal2.setName("Fiske deller med spinat");
		meal2.setDateOfServing(new Date());
		canteen.addMeal(meal2);

		Meal meal3 = new Meal();
		meal3.setName("Chili con carne");
		meal3.setDateOfServing(new Date());
		canteen1.addMeal(meal3);

		Meal meal4 = new Meal();
		meal4.setName("Kalkun stegt med citron");
		meal4.setDateOfServing(new Date());
		canteen1.addMeal(meal4);

		Canteen c = canteenRepository.save(canteen);
		Canteen c1 = canteenRepository.save(canteen1);
	}
}
