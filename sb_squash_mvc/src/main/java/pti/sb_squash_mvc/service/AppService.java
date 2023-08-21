package pti.sb_squash_mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.dao.Database;
import pti.sb_squash_mvc.model.Match;
import pti.sb_squash_mvc.model.Place;
import pti.sb_squash_mvc.model.Player;

@Service
public class AppService {
	
	private final Database db;
	
	@Autowired
	public AppService(Database db) {
		
		this.db = db;
	}
	
	// These methods are up for debate, they are nowhere near finalized!
	
	public List<Player> getAllPlayers() {
		
		List<Player> playersList = db.getAllUsers();
		
		return playersList;
	}

	public List<Place> getAllPlaces() {
		
		List<Place> placesList = db.getAllPlaces();
		
		return placesList;
	}

	public List<Match> getAllMatchesByPlayerEmail(String email) {
		
		List<Match> filteredMatches = db.getAllMatchesByUserEmail(email);
		
		return filteredMatches;
	}

	public List<Match> getAllMatches() {
		
		List<Match> allMatches = db.getAllMatches();
		
		return allMatches;
	}
	
	public List<Match> getAllMatchesFilteredByPlaceId(int placeId) {
		
		List<Match> matches = db.getAllMatchesByPlaceId(placeId);
		
		return matches;
	}

}
