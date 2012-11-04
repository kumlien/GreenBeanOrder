package se.ithuset.greenbean.order;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	private static final Map<String, String> PRODUCT_NAME_ID_MAP = new  LinkedHashMap<String, String>();
	
	private static final AtomicInteger ORDER_ID = new AtomicInteger(0);
	
	@Autowired Marshaller marshaller;
	
	
	@PostConstruct public void init() {
		PRODUCT_NAME_ID_MAP.put("Coffee: Robusta 1kg", "1");
		PRODUCT_NAME_ID_MAP.put("Coffee: Arabica Brazil 1kg", "2");
		PRODUCT_NAME_ID_MAP.put("Software: Roast automation system", "3");
		PRODUCT_NAME_ID_MAP.put("Roaster: Hot air roasting machine", "4");
		PRODUCT_NAME_ID_MAP.put("Roaster: Drum roasting machine", "5");
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
		model.addAttribute("products", fetchProducts());
		model.addAttribute("command", new Order());
		
		return "home";
	}
	
	private Object fetchProducts() {
		return PRODUCT_NAME_ID_MAP;
	}

	@RequestMapping(value="/placeOrder", method = RequestMethod.POST)
	public String placeOrder(@ModelAttribute Order order, Model model) {
		logger.info("Order placed: " + order);
		try {
			order.setId(ORDER_ID.addAndGet(1)+"");
			saveOrder(order);
			model.addAttribute("success", "true");
		} catch (IOException e) {
			logger.error("Unable to save the file",e);
			model.addAttribute("success", "false");			
		}
		model.addAttribute("products", fetchProducts());
		model.addAttribute("command", new Order());
		return "home";
	}
	
	/**
	 * Marshal the specified order as an xml file in the home directory of the current user
	 * @param order
	 * @throws IOException
	 */
	private void saveOrder(Order order) throws IOException {
		FileWriter fw = null;
		String dir = System.getProperty("user.home");
		StringBuilder fileName = new StringBuilder(dir).append("/GreenBeanOrder_");
		fileName.append(order.getId()).append(".xml");
		try {
			fw = new FileWriter(fileName.toString());
			StreamResult sr = new StreamResult(fw);
			marshaller.marshal(order, sr);
			logger.info("File created: " + fileName.toString());
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}
	
}
