package pti.sb_squash_mvc.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtility {
	
	private final SessionFactory sessionFactory;
	
	
	public HibernateUtility() {
		
		Configuration cfg =  new Configuration();
		cfg.configure();
		
		sessionFactory = cfg.buildSessionFactory();
	}
	
	
	public Session getSession() {
		return sessionFactory.openSession();
	}

}
