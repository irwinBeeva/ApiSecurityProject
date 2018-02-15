package com.beeva.main.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.beeva.main.data.dao.DataAccessObject;
import com.beeva.main.model.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private DataAccessObject dataAccessObject;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String userName = authentication.getName(),
				password = authentication.getCredentials().toString();
		Optional<User> user = dataAccessObject.buscarPorNombre(userName);
		
		if(user.isPresent()	) {
			UserDetails userDetail = user.get();
			if(password.equals(userDetail.getPassword()))
				return new UsernamePasswordAuthenticationToken(userDetail.getUsername(),
						userDetail.getPassword(), userDetail.getAuthorities());
		}
		
		throw new UsernameNotFoundException( "Bad Authentication" );
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
