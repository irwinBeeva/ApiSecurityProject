package com.beeva.main.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	
	private final static long EXPIRATION_TIME  = 86400000;
	private final static String SECRET = "ThisIsTheSecret";
	private final static String TOKEN_PREFIX = "Bearer";
	private final static String HEADER_IDENTIFIER = "Authorization";
	
	public static void addAuthentication(HttpServletResponse response,
			String userName) {
		String JWT  = Jwts.builder()
						.setSubject( userName )
						.setExpiration( new Date(System.currentTimeMillis() + EXPIRATION_TIME ))
						.signWith( SignatureAlgorithm.HS512, SECRET )
						.compact();
		
		response.addHeader( HEADER_IDENTIFIER, TOKEN_PREFIX + " " + JWT );
	}
	
	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request
						.getHeader(HEADER_IDENTIFIER);
		if( token != null ) {
			token = token.replace(TOKEN_PREFIX, "");
			String user = Jwts.parser()
							.setSigningKey(SECRET)
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
			if(user != null) 
				return	new UsernamePasswordAuthenticationToken(user, null, Collections.<GrantedAuthority>emptyList());
		}
		throw new UsernameNotFoundException( "Bad Authentication" );
	}

}
