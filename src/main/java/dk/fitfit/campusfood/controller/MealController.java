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

import dk.fitfit.campusfood.model.Meal;
import dk.fitfit.campusfood.repository.CanteenRepository;
import dk.fitfit.campusfood.service.MealService;


@Controller
public class MealController {
	private static final Logger logger = LoggerFactory.getLogger(CanteenController.class);

	@Autowired
	private MealService mealService;

	@Autowired
	private CanteenRepository canteenRepository;

	@RequestMapping(value = "/meal", method = RequestMethod.GET)
//	public ModelAndView mealAdd() {
//		return new ModelAndView("mealAdd", "meal", new Meal());
//	}
	public String mealAdd(Model model) {
		model.addAttribute("meal", new Meal());
		model.addAttribute("canteens", canteenRepository.findAll());
		return "mealAdd";
	}

	@RequestMapping(value = {"/meal"}, method = RequestMethod.POST)
	public String submit(@ModelAttribute Meal meal, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}

		Meal c = mealService.create(meal);
		model.addAttribute("meal", c);
		return "redirect:/meal/" + c.getId();
	}

	@RequestMapping(value = "/meal/{id}", method = RequestMethod.GET)
	public String mealDetails(Model model, @PathVariable long id) {
		model.addAttribute("meal", mealService.findOne(id));
		return "mealDetails";
	}

	@RequestMapping(value = "/meals", method = RequestMethod.GET)
	public ModelAndView mealList(Locale locale, Model model) {
		return new ModelAndView("mealList", "meals", mealService.findAll());
	}

	// TODO: no output... unless meal.setCanteen(this); is used in Canteen.addMeal()
	@RequestMapping(value = "/canteen/{id}/meals", method = RequestMethod.GET)
	public String mealsByCanteen(Model model, @PathVariable long id) {
		model.addAttribute("meals", mealService.findByCanteenId(id));
		return "mealList";
	}

	@RequestMapping(value = "/meals/today", method = RequestMethod.GET)
	public String mealsToday(Model model) {
		model.addAttribute("meals", mealService.findByDateOfServing(new Date()));
		return "mealList";
	}

}
