package dk.fitfit.campusfood.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dk.fitfit.campusfood.model.Course;
import dk.fitfit.campusfood.repository.CourseRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(CanteenController.class);

	@Autowired
	private CourseRepository courseRepository;

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView addCourse() {
		return new ModelAndView("addCourse", "course", new Course());
	}

	@RequestMapping(value = {"/course"}, method = RequestMethod.POST)
	public String submit(@ModelAttribute Course course, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}

		Course c = courseRepository.save(course);
		model.addAttribute("course", c);
		return "redirect:/course/" + c.getId();
	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public String showCourse(Model model, @PathVariable long id) {
		model.addAttribute("course", courseRepository.findOne(id));
		return "courseDetails";
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public ModelAndView listCourses(Locale locale, Model model) {
		return new ModelAndView("course", "courses", courseRepository.findAll());
	}

}
