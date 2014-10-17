package dk.fitfit.campusfood.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dk.fitfit.campusfood.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
}
