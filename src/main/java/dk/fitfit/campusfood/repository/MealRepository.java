package dk.fitfit.campusfood.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dk.fitfit.campusfood.model.Meal;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long>{
	List<Meal> findByCanteenId(long id);
	List<Meal> findByDateOfServing(Date date);
}
