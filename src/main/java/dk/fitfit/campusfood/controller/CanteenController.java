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

import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.repository.CanteenRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CanteenController {
	private static final Logger logger = LoggerFactory.getLogger(CanteenController.class);

	@Autowired
	private CanteenRepository canteenRepository;

	@RequestMapping(value = "/canteen", method = RequestMethod.GET)
	public ModelAndView addCanteen() {
		return new ModelAndView("addCanteen", "canteen", new Canteen());
	}

	@RequestMapping(value = {"/canteen"}, method = RequestMethod.POST)
	public String submit(@ModelAttribute Canteen canteen, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}

		Canteen c = canteenRepository.save(canteen);
		model.addAttribute("canteen", c);
		return "redirect:/canteen/" + c.getId();
	}

	@RequestMapping(value = "/canteen/{id}", method = RequestMethod.GET)
	public String showRestaurant(Model model, @PathVariable long id) {
		model.addAttribute("canteen", canteenRepository.findOne(id));
		return "canteenDetails";
	}

	@RequestMapping(value = "/canteens", method = RequestMethod.GET)
	public ModelAndView canteen(Locale locale, Model model) {
		return new ModelAndView("canteen", "canteens", canteenRepository.findAll());
	}

}