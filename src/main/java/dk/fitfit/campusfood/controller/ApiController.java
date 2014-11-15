package dk.fitfit.campusfood.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.service.MealService;


@RestController
@RequestMapping("/api")
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private MealService mealService;

	@RequestMapping(value = "/meals/today")
	public List<Meal> mealsToday() {
		return mealService.findByDateOfServing(new Date());
	}
	
	@RequestMapping(value = "/meals/tomorrow")
	public List<Meal> mealsTomorrow() {
		return mealService.findMealsTomorrow();
	}

	@RequestMapping(value = "/meals/thisweek")
	public List<Meal> mealsThisWeek() {
		return mealService.findMealsThisWeek();
	}

	@RequestMapping(value = "/meals/week/{week}")
	public List<Meal> mealsByWeek(@PathVariable int week) {
		return mealService.findMealsByWeek(week);
	}

	@RequestMapping(value = "/meal/{id}")
	public Meal mealById(@PathVariable long id) {
		return mealService.findOne(id);
	}

}
