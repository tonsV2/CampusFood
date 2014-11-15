package dk.fitfit.campusfood.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dk.fitfit.campusfood.model.Canteen;
import dk.fitfit.campusfood.repository.CanteenRepository;
import dk.fitfit.campusfood.utils.DataInitializer;


@Controller
public class CanteenController {
	private static final Logger logger = LoggerFactory.getLogger(CanteenController.class);

	@Autowired
	private CanteenRepository canteenRepository;

	@Autowired
	private DataInitializer dataInitializer;


	@RequestMapping(value = "/admin/canteen", method = RequestMethod.GET)
	public ModelAndView canteenAdd() {
		return new ModelAndView("canteenAdd", "canteen", new Canteen());
	}

	@RequestMapping(value = {"/admin/canteen"}, method = RequestMethod.POST)
	public String submit(@ModelAttribute Canteen canteen, BindingResult result) {
		if (result.hasErrors()) {
			return "error";
		}

		canteenRepository.save(canteen);
		return "redirect:/canteen/" + canteen.getId();
	}

	@RequestMapping(value = "/canteen/{id}", method = RequestMethod.GET)
	public String canteenDetails(Model model, @PathVariable long id) {
		model.addAttribute("canteen", canteenRepository.findOne(id));
		return "canteenDetails";
	}

	@RequestMapping(value = "/canteens", method = RequestMethod.GET)
	public ModelAndView canteenList(Locale locale, Model model) {
		return new ModelAndView("canteenList", "canteens", canteenRepository.findAll());
	}

	@RequestMapping(value = "/initialize")
	public String initialize() {
		dataInitializer.initialize();
		return "redirect:/canteens";
	}

}
