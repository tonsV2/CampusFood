package dk.fitfit.campusfood.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.model.Course;
import dk.fitfit.campusfood.repository.CanteenRepository;
import dk.fitfit.campusfood.repository.CourseRepository;
import dk.fitfit.campusfood.utils.DateUtil;


@RestController
@RequestMapping("/api")
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private CanteenRepository canteenRepository;

	@Autowired
	private CourseRepository courseRepository;

	@RequestMapping(value = "/course/today")
	public List<Course> sayHello() {
//		initialize();
		Date date = new Date();
		date = DateUtil.removeTime(date);
		// TODO: courseService!!!
		return courseRepository.findAllByDateOfServing(date);
//		return courseRepository.findAllByDateOfServing(new Date());
	}
	
	public void initialize()
	{
		String name = "Kantinen i something";
		String address = "Somewhere 4";
		String openingHours = "Mandag til torsdag 8:00 - 15:00. Fredag 8:00 - 14:30\nMorgenmad 8:00 - 9:30\nFrokost 11:00 - 14:00";
		String contact = "Kontakt... someone.";
		Canteen canteen = new Canteen(name, address, openingHours, contact);

		Course course = new Course();
		course.setName("Pasta");
		course.setDateOfServing(new Date());
		System.out.println("course: " + course.getDateOfServing());
		canteen.addCourse(course);

		Course course1 = new Course();
		course1.setName("Pasta something");
		course1.setDateOfServing(new Date());
		System.out.println("course1: " + course1.getDateOfServing());
		canteen.addCourse(course1);

		Canteen c = canteenRepository.save(canteen);
	}

}
