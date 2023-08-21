package pti.sb_squash_mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {
	
	// Luckily we have bcrypt embedded into the framework
	// we just need to register a bean for it.
	
	@Bean
	public PasswordEncoder bcryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder(12);
	}

}
