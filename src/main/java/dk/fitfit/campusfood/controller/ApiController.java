package dk.fitfit.campusfood.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.CanteenRepository;
import dk.fitfit.campusfood.repository.MealRepository;
import dk.fitfit.campusfood.utils.DateUtil;


@RestController
@RequestMapping("/api")
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private CanteenRepository canteenRepository;

	@Autowired
	private MealRepository mealRepository;

	@RequestMapping(value = "/meals/today")
	public List<Meal> sayHello() {
//		initialize();
		Date date = new Date();
		date = DateUtil.removeTime(date);
		// TODO: MealService!!!
		return mealRepository.findAllByDateOfServing(date);
//		return mealRepository.findAllByDateOfServing(new Date());
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
		System.out.println("meal: " + meal.getDateOfServing());
		canteen.addMeal(meal);

		Meal meal1 = new Meal();
		meal1.setName("Pasta something");
		meal1.setDateOfServing(new Date());
		System.out.println("meal1: " + meal1.getDateOfServing());
		canteen.addMeal(meal1);

		Canteen c = canteenRepository.save(canteen);
	}

}
