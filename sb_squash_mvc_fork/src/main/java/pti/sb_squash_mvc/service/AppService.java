package pti.sb_squash_mvc.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.dao.Database;
import pti.sb_squash_mvc.dto.MatchDto;
import pti.sb_squash_mvc.model.Match;
import pti.sb_squash_mvc.model.Place;
import pti.sb_squash_mvc.model.Player;
import pti.sb_squash_mvc.util.PasswordGenerator;

@Service
public class AppService {
	
	private final Database db;
	private final PasswordEncoder pwEncoder;
	
	@Autowired
	public AppService(Database db, PasswordEncoder pwEncoder) {
		
		this.db = db;
		this.pwEncoder = pwEncoder;
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
	
	
	public String addNewPlayer(String email, String username, int roleId) {
		
		String updateResult = null;
		
		Player playerToRegister = new Player();
		playerToRegister.setEmail(email);
		playerToRegister.setPlayerName(username);
		playerToRegister.setActivated(false);
		
		String randomPassword = PasswordGenerator.generate();
		System.out.println(">>> New password generated for " + email + " account: " + randomPassword + " <<<");
		// Email sending service should be implemented and called here.
		
		playerToRegister.setPassword( pwEncoder.encode(randomPassword) );
		
		
		try {
			
			db.addUser(playerToRegister, roleId);
			updateResult = "Account '" + playerToRegister.getEmail() + "' registered successfully.";
			
			
		}
		catch(ConstraintViolationException e) {
			
			updateResult = "Account registration failed: " + e.getErrorMessage();
		}

		
		return updateResult;
	}

	
	public String addNewPlace(String newPlaceName, String newPlaceAddress, int newPlaceRentFee) {

		String updateResult = null;
		
		Place placeToRegister = new Place();
		placeToRegister.setId(0);
		placeToRegister.setPlaceName(newPlaceName);
		placeToRegister.setAddress(newPlaceAddress);
		placeToRegister.setRentFeePerHourInHuf(newPlaceRentFee);
		
		
		try {
			
			db.addPlace(placeToRegister);
			updateResult = "Place '" + placeToRegister.getPlaceName() + "' registered successfully.";
		}
		catch(ConstraintViolationException e) {
			
			updateResult = "Place registration failed: " + e.getErrorMessage();
		}
		
		
		return updateResult;
	}

	public void registerMatch(MatchDto matchDto) {
		
		Match newMatch = new Match();
		newMatch.setPlayer1Score(matchDto.getPlayer1Score());
		newMatch.setPlayer2Score(matchDto.getPlayer2Score());
		newMatch.setDate(matchDto.getDate());
		
		db.addMatch(newMatch, matchDto.getPlaceId(), matchDto.getPlayer1Email(), matchDto.getPlayer2Email());
	}

}
