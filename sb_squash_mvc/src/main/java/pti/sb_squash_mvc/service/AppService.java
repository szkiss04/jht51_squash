package pti.sb_squash_mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.model.Match;
import pti.sb_squash_mvc.model.Place;
import pti.sb_squash_mvc.model.Player;

@Service
public class AppService {
	
	// These methods are up for debate, they are nowhere near finalized!
	
	public List<Player> getAllPlayers() {
		
		/*
		 * List<Player> playersList = database.getAllPlayers();
		 * return playersList;
		 * 
		 */
		
		return null;
	}

	public List<Place> getAllPlaces() {
		
		/*
		 * List<Place> placesList = database.getAllPlaces();
		 * return placesList;
		 * 
		 */
		
		return null;
	}

	public List<Match> getAllMatchesByPlayerEmailDateDescending() {
		
		/*
		 * List<Match> filteredMatches = database.getAllMatchesFilteredByUserEmailDateDesc();
		 * return filteredMatches;
		 * 
		 */
		
		return null;
	}

	public List<Match> getAllMatchesDateDescending() {
		
		/*
		 * List<Match> allMatches = database.getAllMatchesDateDesc();
		 * return allMatches;
		 * 
		 */
		
		return null;
	}

}
