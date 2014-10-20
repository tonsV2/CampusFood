package dk.fitfit.campusfood.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.MealRepository;
import dk.fitfit.campusfood.utils.DateUtil;


@Service
public class MealService {
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

}
