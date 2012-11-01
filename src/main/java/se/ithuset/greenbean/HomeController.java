package se.ithuset.greenbean;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static final Map<String, String> PRODUCT_NAME_ID_MAP = new  LinkedHashMap<String, String>();
	
	
	@PostConstruct public void init() {
		PRODUCT_NAME_ID_MAP.put("Coffee: Robusta 1kg", "1");
		PRODUCT_NAME_ID_MAP.put("Coffee: Arabica Brazil 1kg", "2");
		PRODUCT_NAME_ID_MAP.put("Roaster: Hot air roasting machine", "3");
		PRODUCT_NAME_ID_MAP.put("Roaster: Drum roasting machine", "4");
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("products", PRODUCT_NAME_ID_MAP);
		model.addAttribute("command", new Order());
		
		return "home";
	}
	
	@RequestMapping(value="/placeOrder", method = RequestMethod.POST)
	public String placeOrder(@ModelAttribute Order order) {
		logger.info("Order placed: " + order);
		
		return "redirect:";
	}
	
}
