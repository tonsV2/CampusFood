package dk.fitfit.campusfood.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dk.fitfit.campusfood.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	List<Course> findByCanteenId(long id);
	List<Course> findAllByDateOfServing(Date date);
}
