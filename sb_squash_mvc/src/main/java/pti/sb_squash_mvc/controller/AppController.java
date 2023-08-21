package pti.sb_squash_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.model.Match;
import pti.sb_squash_mvc.model.Place;
import pti.sb_squash_mvc.model.Player;
import pti.sb_squash_mvc.service.AppService;

@Controller
public class AppController {
	
	private final AppService service;
	
	@Autowired
	public AppController(AppService service) {
		
		this.service = service;
	}
	
	@GetMapping("/")
	public String showAllMatches(
				Model model
			) {
		
		List<Player> playersList = service.getAllPlayers();
		List<Place> placesList = service.getAllPlaces();
		List<Match> matchesList = service.getAllMatches();
		
		model.addAttribute("playersList", playersList);
		model.addAttribute("placesList", placesList);
		model.addAttribute("matchesList", matchesList);
		
		return "index";
	}
	
	@GetMapping("/filter/player")
	public String showFilteredMatchesByPlayer(
				@RequestParam(name = "playerEmail") String playerEmail,
				Model model
			) {
		
		List<Player> playersList = service.getAllPlayers();
		List<Place> placesList = service.getAllPlaces();
		List<Match> matchesList = service.getAllMatchesByPlayerEmail(playerEmail);
		
		model.addAttribute("playersList", playersList);
		model.addAttribute("placesList", placesList);
		model.addAttribute("matchesList", matchesList);
		
		model.addAttribute("filter_playerEmail", playerEmail);
		
		return "index";
	}
	
	@GetMapping("/filter/place")
	public String showFilteredMatchesByPlace(
			Model model,
			@RequestParam(name="placeId") int placeId) {
		
		
		model.addAttribute("playersList", service.getAllPlayers());
		model.addAttribute("placesList", service.getAllPlaces());
		model.addAttribute("matchesList", service.getAllMatchesFilteredByPlaceId(placeId));
		
		model.addAttribute("filter_placeId", placeId);
		
		return "index.html";
	}
	
	
	@GetMapping("/admin")
	public String showAdminPage(Model model) {
		
		model.addAttribute("playersList", service.getAllPlayers());
		model.addAttribute("placesList", service.getAllPlaces());
		
		return "admin.html";
	}
	
	
	@PostMapping("/admin/reg/player")
	public String registerNewPlayer(
			Model model,
			@RequestParam(name="newemail") String newAccEmail,
			@RequestParam(name="newname") String newAccUsername,
			@RequestParam(name="newrole") int newAccRoleId) {
		
		
		String dbUpdateResult = service.addNewPlayer(newAccEmail, newAccUsername, newAccRoleId);
		
		model.addAttribute("message", dbUpdateResult);
		
		model.addAttribute("playersList", service.getAllPlayers());
		model.addAttribute("placesList", service.getAllPlaces());
		
		return "admin.html";
	}
	
	
	@PostMapping("/admin/reg/place")
	public String registerNewPlace(
			Model model,
			@RequestParam(name="newpname") String newPlaceName,
			@RequestParam(name="newpaddress") String newPlaceAddress,
			@RequestParam(name="newpfee") int newPlaceRentFee) {
		
		
		String dbUpdateResult = service.addNewPlace(newPlaceName, newPlaceAddress, newPlaceRentFee);
		
		model.addAttribute("message", dbUpdateResult);
		
		model.addAttribute("playersList", service.getAllPlayers());
		model.addAttribute("placesList", service.getAllPlaces());
		
		return "admin.html";
	}
	
	
	
	
	
}
