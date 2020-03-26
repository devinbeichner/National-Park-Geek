package com.techelevator.npgeek.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.JDBCDAO.ParkDAO;
import com.techelevator.npgeek.model.JDBCDAO.WeatherDAO;

@Controller
public class HomeController {
	
	@Autowired
	ParkDAO parkDao;
	
	@Autowired
	WeatherDAO weatherDAO;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getHomePage(ModelMap map, HttpSession session) {
		
		List<Park> allParks = parkDao.getAllParks();
		
		map.addAttribute("parks", allParks);
		
		return "home";
	}
	
	@RequestMapping(path = "/detail", method = RequestMethod.GET)
	public String getDetailPage(@RequestParam String parkCode, ModelMap map, HttpSession session) {
		
		Park newPark = parkDao.getParkByCode(parkCode);
		
		map.addAttribute("park", newPark);
		
		List<Weather> fiveDayForecast = weatherDAO.getWeatherByParkCode(parkCode);
		
		if(session.getAttribute("indicator") == null) {
			session.setAttribute("indicator", "°F");
		}
		
		if(session.getAttribute("fOrC") != null) {
			if(session.getAttribute("fOrC").equals("c")) {
				for (Weather temp : fiveDayForecast) {
					temp.setHigh(temp.convertFToC((temp.getHigh())));
					temp.setLow(temp.convertFToC(temp.getLow()));
				}
			}			
		}
		
		
		map.addAttribute("weather", fiveDayForecast);
			
		return "detail";
	}
	
	@RequestMapping(path="/detail", method = RequestMethod.POST)
	public String postTempPrefrences(HttpSession session, @RequestParam String fOrC, @RequestParam String parkCode) {
		
		if (fOrC.equals("c")) {
			session.setAttribute("fOrC", "c");
			session.setAttribute("indicator", "°C");
		}
		
		if (fOrC.equals("f")) {
			session.setAttribute("fOrC", "f");
			session.setAttribute("indicator", "°F");
		}
		
		return "redirect:/detail?parkCode=" + parkCode;
	}

}
