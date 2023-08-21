package pti.sb_squash_mvc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pti.sb_squash_mvc.dto.MatchDto;
import pti.sb_squash_mvc.model.Player;
import pti.sb_squash_mvc.service.AppService;

@ControllerAdvice
public class AppControllerAdvice {
	
	public final AppService service;
	
	public AppControllerAdvice(AppService service) {
		
		this.service = service; 
	}
	
	@ModelAttribute
	public void populateGlobalModel(
				Model model,
				@AuthenticationPrincipal Player player
			) {
		
		model.addAttribute("playersList", service.getAllPlayers());
		model.addAttribute("placesList", service.getAllPlaces());
		model.addAttribute("loggedInUser", player);
		model.addAttribute("matchdto", new MatchDto());
	}
}
