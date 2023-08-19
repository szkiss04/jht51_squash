package pti.sb_squash_mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.dao.Database;
import pti.sb_squash_mvc.model.Player;

@Service
public class UserAuthObjProvider implements UserDetailsService {

	
	private final Database db;
	
	@Autowired
	public UserAuthObjProvider(Database db) {
		this.db = db;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Player player = db.getUserByEmail(username);
		
		return player;
	}

}
