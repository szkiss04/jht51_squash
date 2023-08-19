package pti.sb_squash_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/filter/player")
	public String showFilteredResultsByPlayer(
				@RequestParam(name = "playerEmail") String playerEmail,
				Authentication userLoggedIn,
				Model model
			) {
		
		List<Player> playersList = service.getAllPlayers();
		List<Place> placesList = service.getAllPlaces();
		List<Match> matchesList = service.getAllMatchesByPlayerEmailDateDescending();
		
		model.addAttribute(userLoggedIn);
		model.addAttribute(playersList);
		model.addAttribute(placesList);
		model.addAttribute(matchesList);
		
		return "index";
	}
	
}
