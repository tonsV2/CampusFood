package dk.fitfit.campusfood.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import dk.fitfit.campusfood.config.PersistenceConfig;
import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.model.Course;
import dk.fitfit.campusfood.repository.CanteenRepository;

@Import(PersistenceConfig.class)	// Without the import CanteenRepository isn't found
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

		Course course = new Course();
		course.setName("Pasta");
		course.setDateOfServing(new Date());
		canteen.addCourse(course);

		Course course1 = new Course();
		course1.setName("Pasta something");
		course1.setDateOfServing(new Date());
		canteen.addCourse(course1);

		Canteen c = canteenRepository.save(canteen);
	}
}
