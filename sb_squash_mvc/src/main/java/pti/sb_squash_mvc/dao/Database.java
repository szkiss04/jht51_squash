package pti.sb_squash_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pti.sb_squash_mvc.config.HibernateUtility;
import pti.sb_squash_mvc.model.Match;
import pti.sb_squash_mvc.model.Place;
import pti.sb_squash_mvc.model.Player;

@Repository
public class Database {

	private final HibernateUtility hbUtil;
	
	
	@Autowired
	public Database(HibernateUtility hbUtil) {
		this.hbUtil = hbUtil;
	}
	
	
	public Player getUserByEmail(String emailAddress) {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Player player = session.get(Player.class, emailAddress);
		
		tx.commit();
		session.close();
		
		this.loadPlayerAccountRole(player);
				
		return player;
	}
	
	
	private void loadPlayerAccountRole(Player player) {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		NativeQuery<Object> roleIdQuery = session.createNativeQuery("SELECT role_id FROM players WHERE email = ?1", Object.class);
		roleIdQuery.setParameter(1, player.getEmail());
		List<Object> roleIdResult = roleIdQuery.getResultList();
		int role_id = Integer.parseInt(roleIdResult.get(0).toString());
		
		NativeQuery<Object> roleQuery = session.createNativeQuery("SELECT role FROM roles WHERE id = ?1", Object.class);
		roleQuery.setParameter(1, role_id);
		List<Object> roleResult = roleQuery.getResultList();
		player.setRole(roleResult.get(0).toString());
		
		tx.commit();
		session.close();		
	}
	
	
	public List<Player> getAllUsers() {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Query<Player> q = session.createQuery("SELECT p FROM Player p", Player.class);
		List<Player> players = q.getResultList();
		
		tx.commit();
		session.close();
		
//		for(Player player : players) {
//			
//			this.loadPlayerAccountRole(player);
//		}
		
		return players;
	}
	
	
	public void addUser(Player player) {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(player);
		
		tx.commit();
		session.close();
	}
	
	
	public void updateUser(Player player) {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(player);
		
		tx.commit();
		session.close();
	}
	
	
	public List<Place> getAllPlaces() {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Query<Place> q = session.createQuery("SELECT p FROM Place p", Place.class);
		List<Place> places = q.getResultList();
		
		tx.commit();
		session.close();
		
		return places;
	}
	
	
	public void addPlace(Place place) {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(place);
		
		tx.commit();
		session.close();
	}
	
	
	private List<Match> getMatches(String queryFilter, int filterId) {
		
		List<Match> allMatches = new ArrayList<>();
		String SQLString = "SELECT id, player1_email, player2_email, place_id FROM matches";
		
		if(queryFilter != null) {
			SQLString += " WHERE " + queryFilter;
		}
		
		if(filterId != 0) {
			SQLString += " = ?1";
		}
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		NativeQuery<Object[]> foreignKeysQuery = session.createNativeQuery(SQLString, Object[].class);
		
		if(filterId != 0) {	
			foreignKeysQuery.setParameter(1, filterId);
		}
		
		List<Object[]> tableIds = foreignKeysQuery.getResultList();
		
		for(int i = 0; i < tableIds.size(); i++) {
			
			int matchId = Integer.parseInt(tableIds.get(i)[0].toString());
			
			Match match = session.get(Match.class, matchId);
			
			Player player1 = session.get(Player.class, tableIds.get(i)[1].toString());
			match.setPlayer1(player1);
			
			Player player2 = session.get(Player.class, tableIds.get(i)[2].toString());
			match.setPlayer2(player2);
			
			Place place = session.get(Place.class, Integer.parseInt(tableIds.get(i)[3].toString()));
			match.setPlace(place);
			
			allMatches.add(match);
		}
		
		tx.commit();
		session.close();
		
		return allMatches;
	}
	
	
	public List<Match> getAllMatches() {
		
		List<Match> allMatches = this.getMatches(null, 0);
		
		return allMatches;
	}
	
	public List<Match> getAllMatchesByUserEmail(String playerEmail) {
		
		List<Match> allMatches = this.getMatches("player1_email = " + playerEmail + " OR player2_email = " + playerEmail, 0);
		
		return allMatches;
	}
	
	public List<Match> getAllMatchesByPlaceId(int placeId) {
		
		List<Match> allMatches = this.getMatches("place_id", placeId);
		
		return allMatches;
	}
	
	
	public void addMatch(Match match) {
		
		Session session = hbUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(match);
		
		tx.commit();
		session.close();
	}
	
	
}
