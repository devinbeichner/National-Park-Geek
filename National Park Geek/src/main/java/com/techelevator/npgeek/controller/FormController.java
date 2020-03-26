package com.techelevator.npgeek.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.JDBCDAO.ParkDAO;
import com.techelevator.npgeek.model.JDBCDAO.SurveyDAO;


@Controller
public class FormController {
	
	@Autowired
	ParkDAO parkDao;
	
	@Autowired
	SurveyDAO surveyDao;
	
	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String getFormPage(Model modelHolder) {
		if(!modelHolder.containsAttribute("survey")) {
			modelHolder.addAttribute("survey", new Survey());
		}
		return "survey";
	}
	
	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String saveSurveyPage(@Valid @ModelAttribute("survey") Survey newSurvey, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("survey", newSurvey);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
		}
		
		try {
			surveyDao.saveSurvey(newSurvey);
		} catch (Exception e) {
			flash.addAttribute("message", "Sorry, you can only submit one survey per day.");
			return "redirect:/favorites";
		}
		
		return "redirect: favorites";
	}
	
	@RequestMapping("/favorites")
	public String getFavoritePage(ModelMap map) {
		
		List<Park> favoriteParks = parkDao.getFavoriteParks();
		
		map.addAttribute("favoriteParks", favoriteParks);
		
		return "favorites";
	}

}


//@RequestParam String parkCode, @RequestParam String emailAddress, @RequestParam String state, @RequestParam String activityLevel 