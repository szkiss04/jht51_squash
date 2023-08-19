package pti.sb_squash_mvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pti.sb_squash_mvc.config.HibernateUtility;
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
	
	
	
	
}
