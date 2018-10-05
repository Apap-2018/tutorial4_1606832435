package com.apap.tutorial4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot (@RequestParam(value="licenseNumber", required=true) String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> pilotFlights = pilot.getPilotFlight();
		
		model.addAttribute("pilot", pilot);
		model.addAttribute("pilotFlights", pilotFlights);
		
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method=RequestMethod.GET)
	private String deletePilot(@PathVariable(value="licenseNumber", required=true) String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilotService.deletePilot(pilot);
		return "delete";
	}
	
	@RequestMapping(value="/pilot/update/{licenseNumber}", method=RequestMethod.GET)
	private String updatePilot(@PathVariable(value="licenseNumber", required=true) String licenseNumber, Model model) {
		PilotModel currPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		PilotModel updatedPilot = new PilotModel();
		
		model.addAttribute("currPilot", currPilot);
		model.addAttribute("updatePilot", updatedPilot);
		
		return "update-pilot";
	}
	
	@RequestMapping(value="/pilot/update/submit/{licenseNumber}", method=RequestMethod.POST)
	private String updatePilotSubmit(@PathVariable(value="licenseNumber", required=true) String licenseNumber, 
									@ModelAttribute PilotModel pilot, Model model) {
		PilotModel current = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> pilotFlights = current.getPilotFlight();
		
		current.setName(pilot.getName());
		current.setFlyHour(pilot.getFlyHour());
		
		pilotService.addPilot(current);
		model.addAttribute("pilot", current);
		model.addAttribute("pilotFlights", pilotFlights);
		
		return "view-pilot";
}
	
	

}
