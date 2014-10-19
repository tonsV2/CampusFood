package dk.fitfit.campusfood.controller;

import java.util.Date;
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
import dk.fitfit.campusfood.repository.CanteenRepository;
import dk.fitfit.campusfood.repository.CourseRepository;
import dk.fitfit.campusfood.utils.DateUtil;


@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(CanteenController.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CanteenRepository canteenRepository;

	@RequestMapping(value = "/course", method = RequestMethod.GET)
//	public ModelAndView courseAdd() {
//		return new ModelAndView("courseAdd", "course", new Course());
//	}
	public String courseAdd(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("canteens", canteenRepository.findAll());
		return "courseAdd";
	}

	@RequestMapping(value = {"/course"}, method = RequestMethod.POST)
	public String submit(@ModelAttribute Course course, BindingResult result, ModelMap model) {
//		if (result.hasErrors()) {
//			return "error";
//		}

		Course c = courseRepository.save(course);
		model.addAttribute("course", c);
		return "redirect:/course/" + c.getId();
	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public String courseDetails(Model model, @PathVariable long id) {
		model.addAttribute("course", courseRepository.findOne(id));
		return "courseDetails";
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public ModelAndView courseList(Locale locale, Model model) {
		return new ModelAndView("courseList", "courses", courseRepository.findAll());
	}

	// TODO: no output... unless course.setCanteen(this); is used in Canteen.addCourse()
	@RequestMapping(value = "/canteen/{id}/course", method = RequestMethod.GET)
	public String coursesByCanteen(Model model, @PathVariable long id) {
		model.addAttribute("courses", courseRepository.findByCanteenId(id));
		return "courseList";
	}

	@RequestMapping(value = "/course/today", method = RequestMethod.GET)
	public String coursesToday(Model model) {
		Date date = new Date();
		date = DateUtil.removeTime(date);
		model.addAttribute("courses", courseRepository.findAllByDateOfServing(date));
//		model.addAttribute("courses", courseRepository.findAllByDateOfServing(new Date()));
		return "courseList";
	}

}
