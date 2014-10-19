package dk.fitfit.campusfood.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.MealRepository;
import dk.fitfit.campusfood.utils.DateUtil;


@RestController
@RequestMapping("/api")
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private MealRepository mealRepository;

	@RequestMapping(value = "/meals/today")
	public List<Meal> mealsToday() {
		Date date = new Date();
		date = DateUtil.removeTime(date);
		// TODO: MealService!!!
		return mealRepository.findByDateOfServing(date);
//		return mealRepository.findByDateOfServing(new Date());
	}
}
