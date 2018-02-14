package org.beeva.apisecurity.apisecurity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AuthenticationFilter  implements Filter{

	private static final String TOKEN_PARAMETER_KEY = "token";
	private static final String TOKEN_CONFIG_KEY = "expectedToken";
	
	private String expectedToken;
	
	public void destroy() {}

	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final String tokenFromRequest = request.getParameter(TOKEN_PARAMETER_KEY);
		if (expectedToken.equalsIgnoreCase(tokenFromRequest))
			chain.doFilter(request, response);
		else {
			response.reset();
			response.getWriter().print("Authentication Error: Token Unavailable");
			response.getWriter().flush();
			return;
		}
	}

	public void init(FilterConfig config) throws ServletException {
		expectedToken = config.getInitParameter(TOKEN_CONFIG_KEY);
	}

}
