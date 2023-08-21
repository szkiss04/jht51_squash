package pti.sb_squash_mvc.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		for(GrantedAuthority auth : authorities) {
			
			if(auth.getAuthority().equals("ADMIN")) {
				
				response.sendRedirect("/admin");
			} else {
				
				response.sendRedirect("/");
			}
		}
		
	}

}
