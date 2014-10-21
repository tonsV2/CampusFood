package dk.fitfit.campusfood.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.fitfit.campusfood.controller.CanteenController;
import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.MealRepository;
import dk.fitfit.campusfood.utils.DateUtil;


@Service
public class MealService {
	private static final Logger logger = LoggerFactory.getLogger(MealService.class);

	@Autowired
	private MealRepository mealRepository;

	public Meal create(Meal meal) {
		Date date = meal.getDateOfServing();
		date = DateUtil.removeTime(date);
		meal.setDateOfServing(date);
		return mealRepository.save(meal);
	}

	public Meal findOne(long id) {
		return mealRepository.findOne(id);
	}

	public Iterable<Meal> findAll() {
		return mealRepository.findAll();
	}

	public List<Meal> findByCanteenId(long id) {
		return mealRepository.findByCanteenId(id);
	}

//	public Meal update(Meal meal) {
//		Strip time from dateOfServing
//		return mealRepository.save(meal);
//	}
//
//	public void delete(long id) {
//		mealRepository.delete(id);
//	}
//
//	public void delete(Meal meal) {
//		mealRepository.delete(meal.getId());
//	}

	public List<Meal> findByDateOfServing(Date date)
	{
		date = DateUtil.removeTime(date);
		return mealRepository.findByDateOfServing(date);
	}

	public List<Meal> findMealsToday()
	{
		Date date = DateUtil.removeTime(new Date());
		return mealRepository.findByDateOfServing(date);
	}

	public List<Meal> findMealsTomorrow()
	{
		Date date = DateUtil.removeTime(new DateTime().plusDays(1).toDate());
		return mealRepository.findByDateOfServing(date);
	}

	public List<Meal> findMealsByWeek(int week)
	{
		LocalDate date = new LocalDate().withWeekOfWeekyear(week);
		LocalDate weekStart = date.dayOfWeek().withMinimumValue();
		LocalDate weekEnd = date.dayOfWeek().withMaximumValue();
		return mealRepository.findByDateOfServingBetween(weekStart.toDate(), weekEnd.toDate());
	}

	public List<Meal> findMealsThisWeek()
	{
		int week = new LocalDate().getWeekOfWeekyear();
		return findMealsByWeek(week);
	}
}
